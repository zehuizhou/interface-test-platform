package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.dto.ProjectPageDto;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ProjectPage;
import com.rabbit.model.ProjectPage;
import com.rabbit.model.ResponseInfo;
import com.rabbit.service.ProjectPageService;
import com.rabbit.service.ProjectPageService;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目页面相关接口
 */
@Slf4j
@RestController
@RequestMapping("/projectPage")
@Api(tags = "项目页面相关接口")
public class ProjectPageController {

    @Autowired
    private ProjectPageService projectPageService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        ProjectPage projectPage = JSONObject.toJavaObject(jsonObject, ProjectPage.class);
        PageInfo pageInfo = projectPageService.findDtoByAllwithPage(pageNum, pageSize, projectPage);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "通过项目id取页面")
    public ResponseInfo getlistProjectId(@PathVariable Long id) {
        List<ProjectPageDto> projectPages = projectPageService.findDtoByProjectId(id);
        return new ResponseInfo(true, projectPages);
    }

    @GetMapping("/listById/{id}")
    @ApiOperation(value = "通过id取页面")
    public ResponseInfo getlistById(@PathVariable Long id) {
        ProjectPageDto projectPage = projectPageService.selectDtoByPrimaryKey(id);
        return new ResponseInfo(true, projectPage);
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaProjectPage(@RequestBody ProjectPage projectPage) {
        List<ProjectPageDto> projectPages = projectPageService.findDtoByProjectIdAndPageName(projectPage.getProjectId(), projectPage.getPageName());
        if (projectPages.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "项目页面" + projectPage.getPageName() + "已存在"));
        }
        projectPage.setUpdateBy(UserUtil.getLoginUser().getUsername());
        projectPage.setCreateBy(UserUtil.getLoginUser().getUsername());
        projectPageService.insertSelective(projectPage);
        return new ResponseInfo(true, "保存项目页面成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editProjectPage(@RequestBody ProjectPage projectPage) {
        List<ProjectPageDto> projectPages = projectPageService.findDtoByProjectIdAndPageNameAndIdNot(projectPage.getProjectId(), projectPage.getPageName(), projectPage.getId());
        if (projectPages.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "项目页面" + projectPage.getPageName() + "已存在"));
        }
        projectPage.setUpdateBy(UserUtil.getLoginUser().getUsername());
        projectPageService.updateByPrimaryKey(projectPage);
        return new ResponseInfo(true, "修改项目页面成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delProjectPage(@RequestBody ProjectPage projectPage) {
        projectPageService.deleteByPrimaryKey(projectPage.getId());
        return new ResponseInfo(true, "删除项目页面成功");
    }

    @GetMapping("/copyPageById/{id}")
    @ApiOperation(value = "通过id复制页面")
    public ResponseInfo copyPageById(@PathVariable Long id) {
        projectPageService.copyPageById(id);
        return new ResponseInfo(true, "复制页面成功");
    }
}
