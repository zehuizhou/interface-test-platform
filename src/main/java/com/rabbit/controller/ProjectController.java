package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.Project;
import com.rabbit.model.ResponseInfo;
import com.rabbit.service.ProjectService;
import com.rabbit.utils.FastJSONHelper;
import com.rabbit.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目相关接口
 */
@Slf4j
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    //	查询所有项目
    @GetMapping("/all")
//	@PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public ResponseInfo getAll() {
        return new ResponseInfo(true, projectService.findAll());
    }

    @GetMapping("/listPage")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        Project project = FastJSONHelper.deserialize(serchData,Project.class);
        PageInfo pageInfo = projectService.findByAllwithPage(pageNum, pageSize, project);
        return new ResponseInfo(true, pageInfo);
    }

    @PostMapping("/add")
    public ResponseInfo savaProject(@RequestBody Project project) {

        List<Project> projects = projectService.findByProjectName(project.getProjectName());
        if (projects.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, project.getProjectName() + "已存在"));
        }
        project.setCreateBy(UserUtil.getLoginUser().getUsername());
        project.setUpdateBy(UserUtil.getLoginUser().getUsername());
        projectService.insertSelective(project);
        return new ResponseInfo(true, "保存项目成功");
    }

    @PutMapping("/edit")
    public ResponseInfo editProject(@RequestBody Project project) {
        List<Project> projects = projectService.findByProjectNameAndIdNot(project.getProjectName(), project.getId());
        if (projects.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, project.getProjectName() + "已存在"));
        }
        project.setUpdateBy(UserUtil.getLoginUser().getUsername());
        projectService.updateByPrimaryKey(project);
        return new ResponseInfo(true, "修改项目成功");
    }

    @PostMapping("/remove")
    public ResponseInfo delProject(@RequestBody Project project) {
        projectService.deleteByPrimaryKey(project.getId());
        return new ResponseInfo(true, "删除项目成功");
    }

}
