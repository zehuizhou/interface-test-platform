package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.TFlag;
import com.rabbit.model.TFlag;
import com.rabbit.service.TFlagService;
import com.rabbit.service.TFlagService;
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
@RequestMapping("/flag")
@Api(tags = "标签相关接口")
public class FlagController {

    @Autowired
    private TFlagService flagService;


    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        TFlag tFlag = FastJSONHelper.deserialize(serchData, TFlag.class);
        PageInfo pageInfo = flagService.findByAllwithPage(pageNum, pageSize, tFlag);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true, flagService.findByProjectId(id));
    }

    @GetMapping("/listByProjectIdAndType/{id}/{type}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectIdAndType(@PathVariable long id, @PathVariable int type) {
        return new ResponseInfo(true, flagService.findByProjectIdAndType(id, type));
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaTFlag(@RequestBody TFlag tFlag) {
        List<TFlag> tFlags = flagService.findByNameAndTypeAndProjectId(tFlag.getName(), tFlag.getType(), tFlag.getProjectId());
        if (tFlags.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "标签【" + tFlag.getName() + "】已存在"));
        }
        tFlag.setCreateBy(UserUtil.getLoginUser().getUsername());
        flagService.insertSelective(tFlag);
        return new ResponseInfo(true, "保存标签成功");
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editTFlag(@RequestBody TFlag tFlag) {
        List<TFlag> tFlags = flagService.findByNameAndTypeAndProjectIdAndIdNot(tFlag.getName(), tFlag.getType(), tFlag.getProjectId(), tFlag.getId());
        if (tFlags.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "标签【" + tFlag.getName() + "】已存在"));
        }
        flagService.updateByPrimaryKey(tFlag);
        return new ResponseInfo(true, "修改标签成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delTFlag(@RequestBody TFlag tFlag) {
        flagService.deleteByPrimaryKey(tFlag.getId());
        return new ResponseInfo(true, "删除标签成功");
    }
}
