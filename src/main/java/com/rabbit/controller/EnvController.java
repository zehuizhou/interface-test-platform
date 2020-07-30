package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.TEnv;
import com.rabbit.service.TEnvService;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/env")
@Api(tags = "环境相关接口")
public class EnvController {

    @Autowired
    private TEnvService envService;


    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        TEnv tEnv = JSONObject.toJavaObject(jsonObject, TEnv.class);
        PageInfo pageInfo = envService.findByAllwithPage(pageNum, pageSize, tEnv);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true, envService.findByProjectId(id));
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaTEnv(@RequestBody TEnv tEnv) {
        List<TEnv> tEnvs = envService.findByNameAndProjectId(tEnv.getName(), tEnv.getProjectId());
        if (tEnvs.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "环境【" + tEnv.getName() + "】已存在"));
        }
        tEnv.setCreateBy(UserUtil.getLoginUser().getUsername());
        envService.insertSelective(tEnv);
        return new ResponseInfo(true, "保存环境成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editTEnv(@RequestBody TEnv tEnv) {
        List<TEnv> tEnvs = envService.findByNameAndProjectIdAndIdNot(tEnv.getName(), tEnv.getProjectId(), tEnv.getId());
        if (tEnvs.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "环境【" + tEnv.getName() + "】已存在"));
        }
        envService.updateByPrimaryKey(tEnv);
        return new ResponseInfo(true, "修改环境成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delTEnv(@RequestBody TEnv tEnv) {
        envService.deleteByPrimaryKey(tEnv.getId());
        return new ResponseInfo(true, "删除环境成功");
    }
}
