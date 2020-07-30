package com.rabbit.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dao.SysLogsDao;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.SysLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class SysLogsController {

    @Autowired
    private SysLogsDao sysLogsDao;

    @GetMapping
//	@PreAuthorize("hasAuthority('sys:log:query')")
    public ResponseInfo listPage(
            @RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        Map maps= JSON.parseObject(serchData);
        PageHelper.startPage(pageNum, pageSize);
        List<SysLogs> list = sysLogsDao.listByPage(maps);
        PageInfo page = new PageInfo(list);
        return new ResponseInfo(true, page);
    }

    @PostMapping
//	@PreAuthorize("hasAuthority('sys:log:query')")
    public ResponseInfo delLog(
            @RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize) {
        //删除log
        return new ResponseInfo(true, " ");
    }

}
