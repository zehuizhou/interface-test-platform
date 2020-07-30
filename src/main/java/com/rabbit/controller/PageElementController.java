package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.PageElement;
import com.rabbit.model.ResponseInfo;
import com.rabbit.service.PageElementService;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 页面元素相关接口
 */
@Slf4j
@RestController
@RequestMapping("/pageElement")
@Api(tags = "页面元素相关接口")
public class PageElementController {

    @Autowired
    private PageElementService pageElementService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        PageElement pageElement = JSONObject.toJavaObject(jsonObject, PageElement.class);
        PageInfo pageInfo = pageElementService.findByAllwithPage(pageNum, pageSize, pageElement);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "通过项目id取页面")
    public ResponseInfo getlistProjectId(@PathVariable Long id) {
        List<PageElement> projectPages = pageElementService.findByProjectId(id);
        return new ResponseInfo(true, projectPages);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaPageElement(@RequestBody PageElement pageElement) {
        if (StringUtils.isEmpty(pageElement.getElementName()) || StringUtils.isEmpty(pageElement.getByType()) || StringUtils.isEmpty(pageElement.getByValue())) {
            return new ResponseInfo(false, new ErrorInfo(520, "有必填项为空"));
        }
        List<PageElement> pageElements = pageElementService.findByPageIdAndElementName(pageElement.getPageId(), pageElement.getElementName());
        if (pageElements.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "页面元素" + pageElement.getElementName() + "已存在"));
        }
        pageElementService.insertSelective(pageElement);
        return new ResponseInfo(true, "保存页面元素成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editPageElement(@RequestBody PageElement pageElement) {
        if (StringUtils.isEmpty(pageElement.getElementName()) || StringUtils.isEmpty(pageElement.getByType()) || StringUtils.isEmpty(pageElement.getByValue())) {
            return new ResponseInfo(false, new ErrorInfo(520, "有必填项为空 "));
        }
        List<PageElement> pageElements = pageElementService.findByPageIdAndElementNameAndIdNot(pageElement.getPageId(), pageElement.getElementName(), pageElement.getId());
        if (pageElements.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "页面元素" + pageElement.getElementName() + "已存在"));
        }
        pageElementService.updateByPrimaryKey(pageElement);
        return new ResponseInfo(true, "修改页面元素成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delPageElement(@RequestBody PageElement pageElement) {
        pageElementService.deleteByPrimaryKey(pageElement.getId());
        return new ResponseInfo(true, "删除页面元素成功");
    }

    @GetMapping("/copyElemenById/{id}")
    @ApiOperation(value = "通过id复制元素")
    public ResponseInfo copyElemenById(@PathVariable Long id) {
        pageElementService.copyElemenById(id);
        return new ResponseInfo(true, "复制成功");
    }

    @PostMapping("/batchSaveElements")
    @ApiOperation(value = "批量保存")
    public ResponseInfo batchSaveElements(@RequestBody List<PageElement> pageElements) {
        pageElementService.batchSaveElements(pageElements);
        return new ResponseInfo(true, "批量保存成功");
    }
}
