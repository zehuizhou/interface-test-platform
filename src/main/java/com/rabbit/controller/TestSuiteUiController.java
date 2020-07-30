package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.*;
import com.rabbit.model.TTestsuiteUi;
import com.rabbit.service.TTestsuiteUiService;
import com.rabbit.service.TTestcaseUiNewService;
import com.rabbit.utils.FastJSONHelper;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/testsuiteUi")
@Api(tags = "UI用例集相关接口")
public class TestSuiteUiController {

    @Autowired
    private TTestsuiteUiService tTestsuiteUiService;

    @Autowired
    private TTestcaseUiNewService tTestcaseUiNewService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        TTestsuiteUi testsuiteUi = FastJSONHelper.deserialize(serchData, TTestsuiteUi.class);
        PageInfo pageInfo = tTestsuiteUiService.findByAllwithPage(pageNum, pageSize, testsuiteUi);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true, tTestsuiteUiService.findByProjectId(id));
    }

    @GetMapping("/listCaseById/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listCaseById(@PathVariable long id) {
        return new ResponseInfo(true, tTestcaseUiNewService.selectDtoBySuiteId(id));
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaTTestsuiteUi(@RequestBody TTestsuiteUi testsuiteUi) {
        List<TTestsuiteUi> testsuiteUis = tTestsuiteUiService.selectByNameAndProjectId(testsuiteUi.getName(), testsuiteUi.getProjectId());
        if (testsuiteUis.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "UI用例集【" + testsuiteUi.getName() + "】已存在"));
        }
        testsuiteUi.setUpdateBy(UserUtil.getLoginUser().getUsername());
        testsuiteUi.setCreateBy(UserUtil.getLoginUser().getUsername());
        tTestsuiteUiService.insertSelective(testsuiteUi);
        return new ResponseInfo(true, "保存UI用例集成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editTTestsuiteUi(@RequestBody TTestsuiteUi testsuiteUi) {
        List<TTestsuiteUi> testsuiteUis = tTestsuiteUiService.selectByNameAndProjectIdAndIdNot(testsuiteUi.getName(), testsuiteUi.getProjectId(), testsuiteUi.getId());
        if (testsuiteUis.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "UI用例集【" + testsuiteUi.getName() + "】已存在"));
        }
        testsuiteUi.setUpdateBy(UserUtil.getLoginUser().getUsername());
        tTestsuiteUiService.updateByPrimaryKey(testsuiteUi);
        return new ResponseInfo(true, "修改UI用例集成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delTTestsuiteUi(@RequestBody TTestsuiteUi testsuiteUi) {
        tTestsuiteUiService.deleteByPrimaryKey(testsuiteUi.getId());
        return new ResponseInfo(true, "删除UI用例集成功");
    }

    @PostMapping("/updateCaseSort")
    @ApiOperation(value = "更新用例排序")
    public ResponseInfo updateCaseSort(@RequestBody List<TSuiteCaseUi> suiteCaseUis) {
        tTestsuiteUiService.updateCaseSort(suiteCaseUis);
        return new ResponseInfo(true, "更新用例排序成功");
    }

    @PostMapping("/addCaseToSuite")
    @ApiOperation(value = "把用例加入用例集")
    public ResponseInfo addCaseToBusiness(@RequestBody List<TSuiteCaseUi> suiteCaseUis) {
        tTestsuiteUiService.addCaseToSuite(suiteCaseUis);
        return new ResponseInfo(true, "新增用例成功");
    }

    @PostMapping("/delSuiteCaseById/{id}")
    public ResponseInfo deleteBusinessCaseByCaseId(@PathVariable Long id) {
        tTestsuiteUiService.deleteSuiteCaseById(id);
        return new ResponseInfo(true, "删除用例集和用例关系成功");
    }
}
