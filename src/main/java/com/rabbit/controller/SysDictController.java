package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.dto.SysDictDto;
import com.rabbit.model.*;
import com.rabbit.service.SysDictService;
import com.rabbit.service.SysDictValueService;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典相关接口
 */
@RestController
@RequestMapping("/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private SysDictValueService sysDictValueService;

    @GetMapping("/listDict")
    public ResponseInfo getPageList() {
        List<SysDict> sysDicts = sysDictService.findAll();
        return new ResponseInfo(true, sysDicts);
    }

    @PostMapping("/addDict")
    public ResponseInfo savaDict(@RequestBody SysDict sysDict) {
        List<SysDict> sysDicts = sysDictService.findByKey(sysDict.getKey());
        if (sysDicts.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "数据字典【"+sysDict.getKey() + "】已存在"));
        }
        sysDict.setUpdateBy(UserUtil.getLoginUser().getUsername());
        sysDict.setCreateBy(UserUtil.getLoginUser().getUsername());
        sysDictService.insertSelective(sysDict);
        return new ResponseInfo(true, "保存数据字典成功");
    }

    @PutMapping("/editDict")
    public ResponseInfo editDict(@RequestBody  SysDict sysDict) {
        sysDict.setUpdateBy(UserUtil.getLoginUser().getUsername());
        sysDictService.updateByPrimaryKey(sysDict);
        return new ResponseInfo(true, "修改数据字典成功");
    }

    @PostMapping("/removeDict/{id}")
    public ResponseInfo delDict(@PathVariable Long id) {
        sysDictService.deleteByPrimaryKey(id);
        return new ResponseInfo(true, "删除数据字典成功");
    }

    @GetMapping("/uiActionList")
    public ResponseInfo getUiActionList() {
        List<SysDictDto> uiActions = sysDictService.findUiActions();
        return new ResponseInfo(true, uiActions);
    }

    @GetMapping("/apiActionList")
    public ResponseInfo getApiActionList() {
        List<SysDictDto> uiActions = sysDictService.findApiActions();
        return new ResponseInfo(true, uiActions);
    }

    @GetMapping("/listDictValue/{id}")
    public ResponseInfo getPageList1(@PathVariable Long id) {
        List<SysDictValue> sysDictValues = sysDictValueService.findByDictId(id);
        return new ResponseInfo(true, sysDictValues);
    }

    @PostMapping("/addDictValue")
    public ResponseInfo savaDict1(@RequestBody SysDictValue sysDict) {
        List<SysDictValue> sysDictValueList = sysDictValueService.findByDictIdAndKey(sysDict.getDictId(), sysDict.getKey());
        if (sysDictValueList.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "数据字典【"+sysDict.getKey() + "】已存在"));
        }
        sysDict.setUpdateBy(UserUtil.getLoginUser().getUsername());
        sysDict.setCreateBy(UserUtil.getLoginUser().getUsername());
        sysDictValueService.insertSelective(sysDict);
        return new ResponseInfo(true, "保存数据字典成功");
    }

    @PutMapping("/editDictValue")
    public ResponseInfo editDict1(@RequestBody  SysDictValue sysDict) {
        List<SysDictValue> sysDictValueList = sysDictValueService.findByDictIdAndKeyAndIdNot(sysDict.getDictId(), sysDict.getKey(),sysDict.getId());
        if (sysDictValueList.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "数据字典【"+sysDict.getKey() + "】已存在"));
        }
        sysDict.setUpdateBy(UserUtil.getLoginUser().getUsername());
        sysDictValueService.updateByPrimaryKey(sysDict);
        return new ResponseInfo(true, "修改数据字典成功");
    }

    @PostMapping("/removeDictValue/{id}")
    public ResponseInfo delDict1(@PathVariable Long id) {
        sysDictValueService.deleteByPrimaryKey(id);
        return new ResponseInfo(true, "删除数据字典成功");
    }

    @PostMapping("/getDictValueById/{id}")
    public ResponseInfo getDict1(@PathVariable Long id) {
        return new ResponseInfo(true,  sysDictValueService.selectByPrimaryKey(id));
    }

    @GetMapping("/dictValue/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        SysDictValue sysDictValue = JSONObject.toJavaObject(jsonObject, SysDictValue.class);
        PageInfo pageInfo = sysDictValueService.findByAllwithPage(pageNum, pageSize, sysDictValue);
        return new ResponseInfo(true, pageInfo);
    }


}
