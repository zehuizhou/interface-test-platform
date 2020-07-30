package com.rabbit.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.dao.TPlanSuiteUiMapper;
import com.rabbit.dao.TTestSuiteUiLogMapper;
import com.rabbit.dto.UiTemplateParams;
import com.rabbit.hessian.factory.config.ClientFactory;
import com.rabbit.model.*;
import com.rabbit.service.*;
import com.rabbit.utils.DateUtil;
import com.rabbit.utils.NetUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ExcUiServiceImpl implements ExcUiService {

    @Autowired
    private ClientFactory clientFactory;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TTestPlanUiNewLogService testPlanUiNewLogService;

    @Autowired
    private TPlanSuiteUiMapper tPlanSuiteUiMapper;

    @Autowired
    private TTestSuiteUiLogMapper testSuiteUiLogMapper;

    @Autowired
    private TTestSuiteUiLogService testSuiteUiLogService;


    @Autowired
    private SendMailSevice sendMailSevice;

    //    private static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private static String EMAIL_REGEX = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${server.port}")
    private String webPort;

    @Value("${rabbit.reportEmailDoman}")
    private String reportEmailDoman;

    @Override
    public String excJob(Job job) {
        JSONObject jsonObject = JSONObject.parseObject(job.getMethodParams());
        JobParams jobParams = JSONObject.toJavaObject(jsonObject, JobParams.class);
        Client client = clientService.findById(jobParams.getClientId());
        log.info("开始执行ui自动化任务,测试自动化计划日志");
        TTestPlanUiNewLog tTestPlanUiLog = new TTestPlanUiNewLog();
        tTestPlanUiLog.setCreateTime(new Date());
        tTestPlanUiLog.setName(job.getJobGroup());
        tTestPlanUiLog.setJobId(job.getJobId());
        tTestPlanUiLog.setProjectId(job.getProjectId());
        tTestPlanUiLog.setStatus(1);
        testPlanUiNewLogService.insertSelective(tTestPlanUiLog);
        log.info("调用执行机执行用例");
        TTestPlanUiNewLog endPlanLog = new TTestPlanUiNewLog();
        endPlanLog.setId(tTestPlanUiLog.getId());
        try {
            Result result = clientFactory.clientUiTestNewService(client.getClientIp(), client.getClientPort()).runPlan(job, tTestPlanUiLog.getId());
            if (result.getFlag()) {
                endPlanLog.setRemark("执行完毕");
                endPlanLog.setStatus(2);
            } else {
                endPlanLog.setRemark("执行结果失败");
                endPlanLog.setStatus(5);
            }
        } catch (Exception e) {
            endPlanLog.setStatus(4);
            endPlanLog.setRemark("连接客户端【" + client.getClientIp() + ":" + client.getClientPort() + "】失败");
            log.error("连接客户端【" + client.getClientIp() + ":" + client.getClientPort() + "】失败", e);
        }
        int totalCount = tPlanSuiteUiMapper.countByJobId(job.getJobId()).intValue();
        int succCount = testSuiteUiLogMapper.countByPlanLogIdAndStatus(tTestPlanUiLog.getId(), 0).intValue();
        int failCount = testSuiteUiLogMapper.countByPlanLogIdAndStatus(tTestPlanUiLog.getId(), 1).intValue();
        endPlanLog.setSuiteTotalCount(totalCount);
        endPlanLog.setSuiteSuccCount(succCount);
        endPlanLog.setSuiteFailCount(failCount);
        endPlanLog.setEndTime(new Date());
        testPlanUiNewLogService.updateByPrimaryKeySelective(endPlanLog);
        if (jobParams.getIsSendEmail().equals(1) & StringUtils.isNotBlank(jobParams.getReceivers())) {
            try {
                sendUiReportMail(jobParams.getReceivers(), tTestPlanUiLog.getId(), tTestPlanUiLog.getName(), totalCount, tTestPlanUiLog.getCreateTime());
            } catch (Exception e) {
                log.info("邮件发送失败", e);
//                e.printStackTrace();
            }
        }
        return "执行ui自动化任务结束";
    }

    private void sendUiReportMail(String sendTo, Long planLogId, String planLogName, int businesscount, Date createTime) throws Exception {
        String[] split = sendTo.replace("；", ";").split(";");
        List list = new ArrayList();
        for (String to : split) {
            String trim = to.trim();
            if (trim.matches(EMAIL_REGEX)) {
                list.add(trim);
            }
        }
        if (list.size() > 0) {
            UiTemplateParams uiTemplateParams = new UiTemplateParams();
            TTestSuiteUiLog byPlanIdCount = testSuiteUiLogService.findByPlanIdCount(planLogId);
            if (byPlanIdCount != null) {
                Long businesstime = (byPlanIdCount.getEndTime().getTime() - byPlanIdCount.getCreateTime().getTime()) / 1000;
                uiTemplateParams.setBusinesstime(businesstime.intValue());
                uiTemplateParams.setCasecount(byPlanIdCount.getCaseTotalCount());
                uiTemplateParams.setCasesuc(byPlanIdCount.getCaseSuccCount());
                uiTemplateParams.setCasefail(byPlanIdCount.getCaseFailCount());
                uiTemplateParams.setCaseskip(byPlanIdCount.getCaseSkipCount());
            }
            uiTemplateParams.setPlanLogId(planLogId);
            uiTemplateParams.setCreateTime(DateUtil.format(createTime, "yyyy-MM-dd HH:mm:ss"));
            uiTemplateParams.setJobname(planLogName);
//            uiTemplateParams.setWebip(InetAddress.getLocalHost().getHostAddress());
            if (reportEmailDoman == null || reportEmailDoman.trim().equals("")) {
                uiTemplateParams.setWebip(NetUtil.getLocalIpv4Address());
            } else {
                uiTemplateParams.setWebip(reportEmailDoman);
            }
            uiTemplateParams.setWebport(webPort);
            uiTemplateParams.setContextpath(contextPath);
            uiTemplateParams.setBusinesscount(businesscount);

            sendMailSevice.sendMailTemplate(list, "任务：【" + planLogName + "】自动化测试结果邮件通知！", "ui-test-new.ftl", uiTemplateParams);
        }
    }

}


















