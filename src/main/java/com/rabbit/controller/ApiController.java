package com.rabbit.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.*;
import com.rabbit.model.po.ApiParam;
import com.rabbit.service.GlobalParamService;
import com.rabbit.service.TApiService;
import com.rabbit.service.TApiSuiteService;
import com.rabbit.service.TStepApiService;
import com.rabbit.utils.FastJSONHelper;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "api相关接口")
public class ApiController {

    @Resource
    private TApiService tApiService;

    @Resource
    private TApiSuiteService tApiSuiteService;

    @Resource
    private GlobalParamService globalParamService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        TApi tApi = FastJSONHelper.deserialize(serchData, TApi.class);
        PageInfo pageInfo = tApiService.findByAllwithPage(pageNum, pageSize, tApi);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listTreeByProjectId/{id}")
    @ApiOperation(value = "获取树形列表")
    public ResponseInfo getPageList(@PathVariable long id) {
        return new ResponseInfo(true, tApiSuiteService.findDtoByProjectId(id));
    }

    @GetMapping("/listSuiteByProjectId/{id}")
    @ApiOperation(value = "获取树形列表")
    public ResponseInfo getSuiteList(@PathVariable long id) {
        return new ResponseInfo(true, tApiSuiteService.findByProjectId(id));
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true, tApiService.findByProjectId(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo getByid(@PathVariable long id) {
        return new ResponseInfo(true, tApiService.selectByPrimaryKey(id));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaTApi(@RequestBody TApi tApi) {
        List<TApi> tApis = tApiService.findByNameAndProjectId(tApi.getName(), tApi.getProjectId());
        if (tApis.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "接口" + tApi.getName() + "已存在"));
        }
        tApi.setUpdateBy(UserUtil.getLoginUser().getUsername());
        tApi.setCreateBy(UserUtil.getLoginUser().getUsername());
        tApiService.insertSelective(tApi);
        return new ResponseInfo(true, tApi);
    }

    @PostMapping("/addSuite")
    @ApiOperation(value = "新增api分类")
    public ResponseInfo savaTApiSuite(@RequestBody TApiSuite tApiSuite) {
        List<TApiSuite> tApis = tApiSuiteService.findByNameAndProjectId(tApiSuite.getName(), tApiSuite.getProjectId());
        if (tApis.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "api分类" + tApiSuite.getName() + "已存在"));
        }
        tApiSuite.setUpdateBy(UserUtil.getLoginUser().getUsername());
        tApiSuite.setCreateBy(UserUtil.getLoginUser().getUsername());
        tApiSuiteService.insertSelective(tApiSuite);
        return new ResponseInfo(true, "保存api分类成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editTApi(@RequestBody TApi tApi) {
        List<TApi> tApis = tApiService.findByNameAndProjectIdAndIdNot(tApi.getName(), tApi.getProjectId(), tApi.getId());
        if (tApis.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "接口" + tApi.getName() + "已存在"));
        }
        tApi.setUpdateBy(UserUtil.getLoginUser().getUsername());
        tApiService.updateByPrimaryKey(tApi);
        return new ResponseInfo(true, "修改接口成功");
    }

    @PutMapping("/editSuite")
    @ApiOperation(value = "编辑api分类")
    public ResponseInfo editTApiSuite(@RequestBody TApiSuite tApiSuite) {
        List<TApiSuite> tApis = tApiSuiteService.findByNameAndProjectIdAndIdNot(tApiSuite.getName(), tApiSuite.getProjectId(), tApiSuite.getId());
        if (tApis.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "api分类" + tApiSuite.getName() + "已存在"));
        }
        tApiSuite.setUpdateBy(UserUtil.getLoginUser().getUsername());
        tApiSuiteService.updateByPrimaryKey(tApiSuite);
        return new ResponseInfo(true, "修改api分类成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delTApi(@RequestBody TApi tApi) {
        tApiService.deleteByPrimaryKey(tApi.getId());
        return new ResponseInfo(true, "删除接口成功");
    }

    @PostMapping("/removeSuite")
    @ApiOperation(value = "删除api分类")
    public ResponseInfo delTApi(@RequestBody TApiSuite apiSuite) {
        tApiSuiteService.deleteByPrimaryKey(apiSuite.getId());
        return new ResponseInfo(true, "删除api分类成功");
    }

    @PostMapping("/debug")
    @ApiOperation(value = "调试api")
    public ResponseInfo debugTApi(@RequestBody TApi api) {
        Map<String, Object> gVars = globalParamService.findByProjectIdAndTypeAndEnvId(api.getProjectId(), 2, api.getEnvId());
        Map<String, Object> caseVars = new ConcurrentHashMap<>();
        List<ApiParam> params = new ArrayList<>();
        try {
            TApiResult TApiResult = tApiService.excApi(api, gVars, caseVars, params);
            return new ResponseInfo(true, TApiResult);
        } catch (Exception e) {
            log.error("debug出错：", e);
            return new ResponseInfo(false, new ErrorInfo(12, e.getMessage()));
        }
    }
}
