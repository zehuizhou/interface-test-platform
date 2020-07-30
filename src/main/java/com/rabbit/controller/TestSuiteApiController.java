package com.rabbit.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.*;
import com.rabbit.service.TSuiteCaseApiService;
import com.rabbit.service.TTestcaseApiService;
import com.rabbit.service.TTestsuiteApiService;
import com.rabbit.utils.FastJSONHelper;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/testsuiteApi")
@Api(tags = "API用例集相关接口")
public class TestSuiteApiController {

    @Resource
    private TTestcaseApiService testcaseApiService;

    @Resource
    private TSuiteCaseApiService suiteCaseApiService;

    @Resource
    private TTestsuiteApiService testsuiteApiService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        TTestsuiteApi testsuiteApi = FastJSONHelper.deserialize(serchData, TTestsuiteApi.class);
        PageInfo pageInfo = testsuiteApiService.findByAllwithPage(pageNum, pageSize, testsuiteApi);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true, testsuiteApiService.findByProjectId(id));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaTTestsuiteUi(@RequestBody TTestsuiteApi testsuiteApi) {
        List<TTestsuiteApi> testsuiteUis = testsuiteApiService.selectByNameAndProjectId(testsuiteApi.getName(), testsuiteApi.getProjectId());
        if (testsuiteUis.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "接口用例集【" + testsuiteApi.getName() + "】已存在"));
        }
        testsuiteApi.setUpdateBy(UserUtil.getLoginUser().getUsername());
        testsuiteApi.setCreateBy(UserUtil.getLoginUser().getUsername());
        testsuiteApiService.insertSelective(testsuiteApi);
        return new ResponseInfo(true, "保存接口用例集成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editTTestsuiteUi(@RequestBody TTestsuiteApi testsuiteUi) {
        List<TTestsuiteApi> testsuiteApis = testsuiteApiService.selectByNameAndProjectIdAndIdNot(testsuiteUi.getName(), testsuiteUi.getProjectId(), testsuiteUi.getId());
        if (testsuiteApis.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "接口用例集【" + testsuiteUi.getName() + "】已存在"));
        }
        testsuiteUi.setUpdateBy(UserUtil.getLoginUser().getUsername());
        testsuiteApiService.updateByPrimaryKey(testsuiteUi);
        return new ResponseInfo(true, "修改接口用例集成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delTTestsuiteUi(@RequestBody TTestsuiteApi testsuiteApi) {
        testsuiteApiService.deleteByPrimaryKey(testsuiteApi.getId());
        return new ResponseInfo(true, "删除接口用例集成功");
    }


    @GetMapping("/listCaseById/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listCaseById(@PathVariable long id) {
        return new ResponseInfo(true, testcaseApiService.selectDtoBySuiteId(id));
    }

    @PostMapping("/updateCaseSort")
    @ApiOperation(value = "更新用例排序")
    public ResponseInfo updateCaseSort(@RequestBody List<TSuiteCaseApi> tSuiteCaseApis) {
        suiteCaseApiService.updateCaseSort(tSuiteCaseApis);
        return new ResponseInfo(true, "更新用例排序成功");
    }

    @PostMapping("/addCaseToSuite")
    @ApiOperation(value = "把用例加入用例集")
    public ResponseInfo addCaseToBusiness(@RequestBody List<TSuiteCaseApi> tSuiteCaseApis) {
        suiteCaseApiService.addCaseToSuite(tSuiteCaseApis);
        return new ResponseInfo(true, "新增用例成功");
    }

    @PostMapping("/delSuiteCaseById/{id}")
    public ResponseInfo deleteBusinessCaseByCaseId(@PathVariable Long id) {
        suiteCaseApiService.deleteByPrimaryKey(id);
        return new ResponseInfo(true, "删除用例集和用例关系成功");
    }
}
