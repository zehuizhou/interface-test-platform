package com.rabbit.controller;

import com.rabbit.model.JobLog;
import com.rabbit.model.ResponseInfo;
import com.rabbit.service.IJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度日志操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class JobLogController {
    @Autowired
    private IJobLogService jobLogService;

    @PostMapping("/list")
    @ResponseBody
    public ResponseInfo list(JobLog jobLog) {
        List<JobLog> list = jobLogService.selectJobLogList(jobLog);
        return new ResponseInfo(true, list);
    }

    @PostMapping("/export")
    @ResponseBody
    public ResponseInfo export(JobLog jobLog) {
        List<JobLog> list = jobLogService.selectJobLogList(jobLog);
//        ExcelUtil<JobLog> util = new ExcelUtil<JobLog>(JobLog.class);
//        return util.exportExcel(list, "调度日志");
        return new ResponseInfo(true, list);

    }

    @PostMapping("/remove")
    @ResponseBody
    public ResponseInfo remove(String ids) {
        jobLogService.deleteJobLogByIds(ids);
        return new ResponseInfo(true, "删除成功");
    }

    @PostMapping("/clean")
    @ResponseBody
    public ResponseInfo clean() {
        jobLogService.cleanJobLog();
        return new ResponseInfo(true, "删除成功");
    }
}
