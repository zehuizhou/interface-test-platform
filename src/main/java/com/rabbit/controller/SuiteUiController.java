package com.rabbit.controller;

import com.rabbit.model.ResponseInfo;
import com.rabbit.model.TSuiteUi;
import com.rabbit.service.TSuiteUiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/suiteUi")
@Api(tags = "UI新版用例集相关接口")
public class SuiteUiController {

    @Resource
    private TSuiteUiService suiteUiService;


    @GetMapping("/listTreeByProjectId")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo listTreeByProjectId(@RequestParam(value = "projectId") Long projectId) {
        List<TSuiteUi> suiteList = suiteUiService.listTreeByProjectId(projectId);
        return new ResponseInfo(true, suiteList);
    }


}
