package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.TTestDatabese;
import com.rabbit.service.TTestDatabeseService;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试数据库相关接口
 */
@Slf4j
@RestController
@RequestMapping("/testDatabese")
@Api(tags = "测试数据库相关接口")
public class DatabaseController {

    @Autowired
    private TTestDatabeseService testDatabeseService;


    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        TTestDatabese testDatabese = JSONObject.toJavaObject(jsonObject, TTestDatabese.class);
        PageInfo pageInfo = testDatabeseService.findByAllwithPage(pageNum, pageSize, testDatabese);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true,  testDatabeseService.findByProjectId(id));
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaTTestDatabese(@RequestBody TTestDatabese testDatabese) {
        List<TTestDatabese> testDatabeses = testDatabeseService.findByNameAndProjectId(testDatabese.getName(),testDatabese.getProjectId());
        if (testDatabeses.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "测试数据库【" + testDatabese.getName() + "】已存在"));
        }
        testDatabese.setUpdateBy(UserUtil.getLoginUser().getUsername());
        testDatabese.setCreateBy(UserUtil.getLoginUser().getUsername());
        testDatabeseService.insertSelective(testDatabese);
        return new ResponseInfo(true, "保存测试数据库成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editTTestDatabese(@RequestBody TTestDatabese testDatabese) {
        List<TTestDatabese> testDatabeses = testDatabeseService.findByNameAndProjectIdAndIdNot(testDatabese.getName(),testDatabese.getProjectId(),testDatabese.getId());
        if (testDatabeses.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "测试数据库【" + testDatabese.getName() + "】已存在"));
        }
        testDatabese.setUpdateBy(UserUtil.getLoginUser().getUsername());
        testDatabeseService.updateByPrimaryKey(testDatabese);
        return new ResponseInfo(true, "修改测试数据库成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delTTestDatabese(@RequestBody TTestDatabese testDatabese) {
        testDatabeseService.deleteByPrimaryKey(testDatabese.getId());
        return new ResponseInfo(true, "删除测试数据库成功");
    }
}
