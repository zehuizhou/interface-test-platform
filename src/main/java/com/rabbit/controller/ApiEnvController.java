package com.rabbit.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.TApiEnv;
import com.rabbit.model.ResponseInfo;
import com.rabbit.service.TApiEnvService;
import com.rabbit.utils.FastJSONHelper;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/apiEnv")
@Api(tags = "接口环境相关接口")
public class ApiEnvController {

    @Autowired
    private TApiEnvService tApiEnvService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        TApiEnv globalParam = FastJSONHelper.deserialize(serchData, TApiEnv.class);
        PageInfo pageInfo = tApiEnvService.findByAllwithPage(pageNum, pageSize, globalParam);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProject/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo getByProject(@PathVariable(value = "id") Long id) {
        return new ResponseInfo(true, tApiEnvService.findByProjectId(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo getById(@PathVariable(value = "id") Long id) {
        return new ResponseInfo(true, tApiEnvService.selectByPrimaryKey(id));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaTApiEnv(@RequestBody TApiEnv apiEnv) {
        List<TApiEnv> apiEnvList = tApiEnvService.findByNameAndProjectId(apiEnv.getName(), apiEnv.getProjectId());
        if (apiEnvList.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "接口环境【" + apiEnv.getName() + "】已存在"));
        }
        apiEnv.setUpdateBy(UserUtil.getLoginUser().getUsername());
        apiEnv.setCreateBy(UserUtil.getLoginUser().getUsername());
        tApiEnvService.insertSelective(apiEnv);
        return new ResponseInfo(true, apiEnv);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editTApiEnv(@RequestBody TApiEnv apiEnv) {
        List<TApiEnv> apiEnvList = tApiEnvService.findByNameAndProjectIdAndIdNot(apiEnv.getName(), apiEnv.getProjectId(), apiEnv.getId());
        if (apiEnvList.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "接口环境【" + apiEnv.getName() + "】已存在"));
        }
        apiEnv.setUpdateBy(UserUtil.getLoginUser().getUsername());
        tApiEnvService.updateByPrimaryKey(apiEnv);
        return new ResponseInfo(true, apiEnv);
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delTApiEnv(@RequestBody TApiEnv globalParam) {
        tApiEnvService.deleteByPrimaryKey(globalParam.getId());
        return new ResponseInfo(true, "删除接口环境成功");
    }
}
