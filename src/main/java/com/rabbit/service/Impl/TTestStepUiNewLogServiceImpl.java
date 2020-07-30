package com.rabbit.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.config.RabbitConfig;
import com.rabbit.dao.TTestCaseUiNewLogMapper;
import com.rabbit.dao.TTestPlanUiNewLogMapper;
import com.rabbit.dao.TTestSuiteUiLogMapper;
import com.rabbit.dto.TestcaseUiNewLogDto;
import com.rabbit.model.TTestPlanUiNewLog;
import com.rabbit.model.TTestSuiteUiLog;
import com.rabbit.service.SendMailSevice;
import com.rabbit.utils.FreemarkerUtil;
import com.rabbit.utils.file.FileUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TTestStepUiNewLogMapper;
import com.rabbit.model.TTestStepUiNewLog;
import com.rabbit.service.TTestStepUiNewLogService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TTestStepUiNewLogServiceImpl implements TTestStepUiNewLogService {

    @Resource
    private TTestStepUiNewLogMapper tTestStepUiNewLogMapper;

    @Resource
    private TTestSuiteUiLogMapper testSuiteUiLogMapper;
    @Resource
    private TTestPlanUiNewLogMapper tTestPlanUiNewLogMapper;

    @Resource
    private TTestCaseUiNewLogMapper testCaseUiNewLogMapper;
    @Resource
    private SendMailSevice sendMailSevice;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tTestStepUiNewLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TTestStepUiNewLog record) {
        return tTestStepUiNewLogMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestStepUiNewLog record) {
        return tTestStepUiNewLogMapper.insertSelective(record);
    }

    @Override
    public TTestStepUiNewLog selectByPrimaryKey(Long id) {
        return tTestStepUiNewLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TTestStepUiNewLog record) {
        return tTestStepUiNewLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestStepUiNewLog record) {
        return tTestStepUiNewLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TTestStepUiNewLog> findByAllwithPage(int page, int pageSize, TTestStepUiNewLog tTestStepUiNewLog) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tTestStepUiNewLogMapper.findByAll(tTestStepUiNewLog));
    }

    @Override
    public String getReportHtml(Long logId, String language) {
        TTestSuiteUiLog planIdCountInfo = testSuiteUiLogMapper.findByPlanIdCount(logId);
        TTestPlanUiNewLog tTestPlanUiNewLog = tTestPlanUiNewLogMapper.selectByPrimaryKey(logId);
        List<TestcaseUiNewLogDto> tTestCaseUiNewLogs = testCaseUiNewLogMapper.findDtoByPlanLogId(logId);
        if (!CollectionUtils.isEmpty(tTestCaseUiNewLogs)) {
            for (TestcaseUiNewLogDto uiNewLogDto : tTestCaseUiNewLogs) {
                List<TTestStepUiNewLog> testStepLogs = uiNewLogDto.getTestStepLogs();
                if (uiNewLogDto.getStatus() != 1) {
                    if (!CollectionUtils.isEmpty(testStepLogs)) {
                        for (TTestStepUiNewLog testStepUiNewLog : testStepLogs) {
                            testStepUiNewLog.setImgname("");
                        }
                    }
                } else {
                    if (!CollectionUtils.isEmpty(testStepLogs)) {
                        for (TTestStepUiNewLog testStepUiNewLog : testStepLogs) {
                            String imgname = testStepUiNewLog.getImgname();
                            //将图片的path转成base64
                            String fullPath = RabbitConfig.profile + imgname;
                            File file = new File(fullPath);
                            if (file.exists()) {
                                String s = StrUtil.removeAllLineBreaks(FileUtils.ImageToBase64(fullPath));
                                testStepUiNewLog.setImgname(s);
                            } else {
                                testStepUiNewLog.setImgname("");
                            }
                        }
                    }
                }
            }
        }

        String process = "";
        Map<String, Object> model = new HashMap<>();
        model.put("suiteLog", planIdCountInfo);
        model.put("planLog", tTestPlanUiNewLog);
        model.put("dataList", tTestCaseUiNewLogs);
        try {
            if (language.equals("en")) {
                process = FreemarkerUtil.process("web-report-en.ftl", model);
            } else {
                process = FreemarkerUtil.process("web-report-zh.ftl", model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            sendMailSevice.sendMail("237371257@qq.com", "测试邮件", "邮件内容", "附件.html", process);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return process;
    }
}
