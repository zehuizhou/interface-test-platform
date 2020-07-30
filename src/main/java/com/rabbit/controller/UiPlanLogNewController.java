package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.config.RabbitConfig;
import com.rabbit.model.*;
import com.rabbit.service.*;
import com.rabbit.utils.ZipUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * ui自动化日志相关接口
 */
@Slf4j
@RestController
@RequestMapping("/uiLogNew")
@Api(tags = "ui自动化日志相关接口(新)")
public class UiPlanLogNewController {

    @Autowired
    private TTestPlanUiNewLogService testPlanUiLogService;

    @Autowired
    private TTestSuiteUiLogService testSuiteUiLogService;

    @Autowired
    private TTestCaseUiNewLogService testCaseUiNewLogService;

    @Autowired
    private TTestStepUiNewLogService testStepUiNewLogService;

    @GetMapping("/planListPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPlanListPage(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        TTestPlanUiNewLog tTestPlanUiLog = JSONObject.toJavaObject(jsonObject, TTestPlanUiNewLog.class);
        PageInfo pageInfo = testPlanUiLogService.findByAllwithPage(pageNum, pageSize, tTestPlanUiLog);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/planById/{id}")
    @ApiOperation(value = "获取单条ui任务日志")
    public ResponseInfo getPlanById(@PathVariable("id") Long planId) {
        return new ResponseInfo(true, testPlanUiLogService.selectByPrimaryKey(planId));
    }

    @DeleteMapping("/removePlan/{planId}")
    @ApiOperation(value = "删除测试计划日志")
    public ResponseInfo delPlanLog(@PathVariable("planId") Long planId) {
        testPlanUiLogService.deleteByPrimaryKey(planId);
        return new ResponseInfo(true, "删除测试计划日志成功");
    }


    @GetMapping("/suiteInfo/{planId}")
    @ApiOperation(value = "获取测试计划用例汇总信息信息")
    public ResponseInfo getBusinessInfoByPlanId(@PathVariable("planId") Long planId) {
        return new ResponseInfo(true, testSuiteUiLogService.findByPlanIdCount(planId));
    }

    @GetMapping("/caseListPage")
    @ApiOperation(value = "获取分页带参用例日志列表")
    public ResponseInfo getCaseListPageByPlanId(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        TTestCaseUiNewLog testCaseUiLog = JSONObject.toJavaObject(jsonObject, TTestCaseUiNewLog.class);
        PageInfo pageInfo = testCaseUiNewLogService.findByAllwithPage(pageNum, pageSize, testCaseUiLog);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/stepListPage")
    @ApiOperation(value = "获取分页带参步骤日志列表")
    public ResponseInfo getStepListPage(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        TTestStepUiNewLog testStepUiLog = JSONObject.toJavaObject(jsonObject, TTestStepUiNewLog.class);
        PageInfo pageInfo = testStepUiNewLogService.findByAllwithPage(pageNum, pageSize, testStepUiLog);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/downloadReport")
    @ApiOperation(value = "下载离线测试报告")
    public void download(@RequestParam(value = "logId") Long logId, @RequestParam(value = "language") String language,
                         HttpServletRequest request, HttpServletResponse resp) throws IOException {
        try {
            String reportText = testStepUiNewLogService.getReportHtml(logId, language);
            resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-Disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode("Web-test-report.html", "UTF-8"));
            resp.getOutputStream().write(reportText.getBytes());
        } catch (Exception e) {
            log.error("文件[ {} ]下载错误", "测试文件======");
            throw new IllegalArgumentException("下载失败:" + e.getMessage());
        } finally {
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        }
    }

}
