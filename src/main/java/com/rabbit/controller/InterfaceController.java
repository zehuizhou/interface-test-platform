package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.TTestInterface;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.TTestInterface;
import com.rabbit.service.TTestInterfaceService;
import com.rabbit.service.TTestInterfaceService;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接口相关接口
 */
@Slf4j
@RestController
@RequestMapping("/interface")
@Api(tags = "接口相关接口")
public class InterfaceController {

    @Autowired
    private TTestInterfaceService testInterfaceService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        TTestInterface testInterface = JSONObject.toJavaObject(jsonObject, TTestInterface.class);
        PageInfo pageInfo = testInterfaceService.findByAllwithPage(pageNum, pageSize, testInterface);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true,  testInterfaceService.findByProjectId(id));
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaTTestInterface(@RequestBody TTestInterface testInterface) {
        List<TTestInterface> testInterfaces = testInterfaceService.findByNameAndProjectId(testInterface.getName(),testInterface.getProjectId());
        if (testInterfaces.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "接口" + testInterface.getName() + "已存在"));
        }
        testInterface.setUpdateBy(UserUtil.getLoginUser().getUsername());
        testInterface.setCreateBy(UserUtil.getLoginUser().getUsername());
        testInterfaceService.insertSelective(testInterface);
        return new ResponseInfo(true, "保存接口成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editTTestInterface(@RequestBody TTestInterface testInterface) {
        List<TTestInterface> testInterfaces = testInterfaceService.findByNameAndProjectIdAndIdNot(testInterface.getName(),testInterface.getProjectId(),testInterface.getId());
        if (testInterfaces.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "接口" + testInterface.getName() + "已存在"));
        }
        testInterface.setUpdateBy(UserUtil.getLoginUser().getUsername());
        testInterfaceService.updateByPrimaryKey(testInterface);
        return new ResponseInfo(true, "修改接口成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delTTestInterface(@RequestBody TTestInterface testInterface) {
        testInterfaceService.deleteByPrimaryKey(testInterface.getId());
        return new ResponseInfo(true, "删除接口成功");
    }
}
