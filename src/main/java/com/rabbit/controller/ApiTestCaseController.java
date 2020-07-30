package com.rabbit.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.dto.TApiCaseResultDto;
import com.rabbit.dto.TestcaseApiDto;
import com.rabbit.model.*;
import com.rabbit.service.GlobalParamService;
import com.rabbit.service.TStepApiService;
import com.rabbit.service.TTestcaseApiService;
import com.rabbit.utils.FastJSONHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@Api(tags = "api用例相关接口")
@RequestMapping("/apiTestCase")
public class ApiTestCaseController {

    @Autowired
    private TTestcaseApiService testcaseApiService;
    @Resource
    private TStepApiService stepApiService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        TTestcaseApi testcaseApi = FastJSONHelper.deserialize(serchData, TTestcaseApi.class);
        PageInfo pageInfo = testcaseApiService.findByAllwithPage(pageNum, pageSize, testcaseApi);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/allBusiness/{id}")
    @ApiOperation(value = "获取所有业务")
    public ResponseInfo getList(@PathVariable Long id) {
        return new ResponseInfo(true, testcaseApiService.findByCaseTypeAndProjectId(2, id));
    }

    @PostMapping("/addApiToCase")
    @ApiOperation(value = "新增api到用例")
    public ResponseInfo addApiToCase(@RequestBody List<TStepApi> stepApiList) {
        stepApiService.insertApis(stepApiList);
        return new ResponseInfo(true, "新增api到用例成功");
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增用例")
    public ResponseInfo savaProject(@RequestBody TestcaseApiDto testcaseApi) {
        testcaseApiService.add(testcaseApi);
        return new ResponseInfo(true, testcaseApi);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑用例")
    public ResponseInfo editProject(@RequestBody TestcaseApiDto testcaseApi) {
        if (testcaseApi.getTestSteps().size() == 0) {
            return new ResponseInfo(false, new ErrorInfo(20, "用例步骤不能为空"));
        }
        testcaseApiService.edit(testcaseApi);
        return new ResponseInfo(true, "修改用例成功");
    }

    @PutMapping("/editStep")
    @ApiOperation(value = "编辑用例步骤")
    public ResponseInfo editStep(@RequestBody TStepApi stepApiDto) {
        stepApiService.updateByPrimaryKey(stepApiDto);
        return new ResponseInfo(true, "修改用例步骤成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除用例")
    public ResponseInfo delProject(@RequestBody TTestcaseApi testcaseApi) {
        testcaseApiService.deleteByPrimaryKey(testcaseApi.getId());
        return new ResponseInfo(true, "删除用例成功");
    }

    @PostMapping("/removeStep")
    @ApiOperation(value = "删除步骤")
    public ResponseInfo delProject(@RequestBody TStepApi stepApi) {
        stepApiService.deleteByPrimaryKey(stepApi.getId());
        return new ResponseInfo(true, "删除步骤成功");
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取单条用例")
    public ResponseInfo getById(@PathVariable Long id) {
        return new ResponseInfo(true, testcaseApiService.selectDtoByIdAndCaseType(id, 1));
    }

    @GetMapping("/steps/{id}")
    @ApiOperation(value = "获取单条用例步骤")
    public ResponseInfo getByCaseId(@PathVariable Long id) {
        return new ResponseInfo(true, stepApiService.findDtoByTestcaseId(id));
    }

    @GetMapping("/business/{id}")
    @ApiOperation(value = "获取单条业务用例")
    public ResponseInfo getBusinessById(@PathVariable Long id) {
        return new ResponseInfo(true, testcaseApiService.selectDtoByIdAndCaseType(id, 2));
    }

    @PostMapping("/copyStep")
    @ApiOperation(value = "复制单条测试步骤")
    public ResponseInfo copyStep(@RequestBody TStepApi stepApi) {
        stepApiService.copyStep(stepApi);
        return new ResponseInfo(true, "复制步骤成功");
    }


    @PostMapping("/debug")
    @ApiOperation(value = "debug用例")
    public ResponseInfo debug(@RequestBody TestcaseApiDto testcaseApi) {
        try {
            TApiCaseResultDto apiCaseResultDto = testcaseApiService.excCase(testcaseApi);
            return new ResponseInfo(true, apiCaseResultDto);
        } catch (Exception e) {
            log.error("debug出错：", e);
            return new ResponseInfo(false, new ErrorInfo(12, e.getMessage()));
        }
    }
}
