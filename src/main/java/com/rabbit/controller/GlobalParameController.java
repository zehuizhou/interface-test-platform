package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.GlobalParam;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.po.GlobalVar;
import com.rabbit.service.GlobalParamService;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公共参数相关接口
 */
@Slf4j
@RestController
@RequestMapping("/globalParams")
@Api(tags = "公共参数相关接口")
public class GlobalParameController {

    @Autowired
    private GlobalParamService globalParamService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        GlobalParam globalParam = JSONObject.toJavaObject(jsonObject, GlobalParam.class);
        PageInfo pageInfo = globalParamService.findByAllwithPage(pageNum, pageSize, globalParam);
        return new ResponseInfo(true, pageInfo);
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaGlobalParam(@RequestBody GlobalParam globalParam) {
        List<GlobalVar> envVars = globalParam.getEnvVars();
        for (GlobalVar globalVar : envVars) {
            if (globalVar.getEnvId() == null) {
                return new ResponseInfo(false, new ErrorInfo(500, "参数环境不能为空"));
            }
        }
        List<GlobalParam> globalParams = globalParamService.findByParamNameAndProjectIdAndType(globalParam.getParamName(), globalParam.getProjectId(), globalParam.getType());
        if (globalParams.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "公共参数【" + globalParam.getParamName() + "】已存在"));
        }
        globalParam.setUpdateBy(UserUtil.getLoginUser().getUsername());
        globalParam.setCreateBy(UserUtil.getLoginUser().getUsername());
        globalParamService.insertSelective(globalParam);
        return new ResponseInfo(true, "保存公共参数成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editGlobalParam(@RequestBody GlobalParam globalParam) {
        List<GlobalVar> envVars = globalParam.getEnvVars();
        for (GlobalVar globalVar : envVars) {
            if (globalVar.getEnvId() == null) {
                return new ResponseInfo(false, new ErrorInfo(500, "参数环境不能为空"));
            }
        }
        List<GlobalParam> globalParams = globalParamService.findByParamNameAndProjectIdAndTypeAndIdNot(globalParam.getParamName(), globalParam.getProjectId(), globalParam.getType(), globalParam.getId());
        if (globalParams.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "公共参数【" + globalParam.getParamName() + "】已存在"));
        }
        globalParam.setUpdateBy(UserUtil.getLoginUser().getUsername());
        globalParamService.updateByPrimaryKey(globalParam);
        return new ResponseInfo(true, "修改公共参数成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delGlobalParam(@RequestBody GlobalParam globalParam) {
        globalParamService.deleteByPrimaryKey(globalParam.getId());
        return new ResponseInfo(true, "删除公共参数成功");
    }
}
