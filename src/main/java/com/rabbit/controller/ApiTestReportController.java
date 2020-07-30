package com.rabbit.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.*;
import com.rabbit.service.*;
import com.rabbit.utils.FastJSONHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/apiReport")
@Api(tags = "接口自动化日志相关接口")
public class ApiTestReportController {

    @Resource
    private TTestPlanResultApiService testPlanResultApiService;

    @Resource
    private TTestsuiteApiResultService testsuiteApiResultService;

    @Resource
    private TApiCaseResultService apiCaseResultService;

    @Resource
    private TApiResultService apiResultService;


    @GetMapping("/planListPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPlanListPage(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        TTestPlanResultApi testPlanResultApi = FastJSONHelper.deserialize(serchData, TTestPlanResultApi.class);
        PageInfo pageInfo = testPlanResultApiService.findByAllwithPage(pageNum, pageSize, testPlanResultApi);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/planById/{id}")
    @ApiOperation(value = "获取单条接口任务日志")
    public ResponseInfo getPlanById(@PathVariable("id") Long planId) {
        return new ResponseInfo(true, testPlanResultApiService.selectByPrimaryKey(planId));
    }

    @GetMapping("/suiteById/{id}")
    @ApiOperation(value = "获取单条用例集日志")
    public ResponseInfo getBusinessById(@PathVariable("id") Long suiteId) {
        return new ResponseInfo(true, testsuiteApiResultService.selectByPrimaryKey(suiteId));
    }

    @GetMapping("/caseById/{id}")
    @ApiOperation(value = "获取单条接口用例日志")
    public ResponseInfo getCaseById(@PathVariable("id") Long suiteId) {
        return new ResponseInfo(true, apiCaseResultService.selectByPrimaryKey(suiteId));
    }

    @DeleteMapping("/removePlan/{planId}")
    @ApiOperation(value = "删除测试计划日志")
    public ResponseInfo delPlanLog(@PathVariable("planId") Long planId) {
        testPlanResultApiService.deleteByPrimaryKey(planId);
        return new ResponseInfo(true, "删除测试计划日志成功");
    }


    @GetMapping("/suiteInfo/{planId}")
    @ApiOperation(value = "获取测试计划用例汇总信息信息")
    public ResponseInfo getBusinessInfoByPlanId(@PathVariable("planId") Long planId) {
        return new ResponseInfo(true, testsuiteApiResultService.findByPlanIdCount(planId));
    }

    @GetMapping("/suiteList")
    @ApiOperation(value = "获取带参业务流日志列表")
    public ResponseInfo getBusinessListPageByPlanId(TTestsuiteApiResult testsuiteApiResult) {
        return new ResponseInfo(true, testsuiteApiResultService.findDtoByAll(testsuiteApiResult));
    }

    @GetMapping("/caseList")
    @ApiOperation(value = "获取用例日志列表")
    public ResponseInfo getCaseListPageByPlanId(TApiCaseResult apiCaseResult) {
        return new ResponseInfo(true, apiCaseResultService.findByAll(apiCaseResult));
    }

    @GetMapping("/stepList")
    @ApiOperation(value = "获取带参步骤日志列表")
    public ResponseInfo getStepListPage(TApiResult apiResult) {
        return new ResponseInfo(true, apiResultService.findByAll(apiResult));
    }

}
