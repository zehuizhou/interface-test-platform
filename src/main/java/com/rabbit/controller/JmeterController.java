package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.config.RabbitConfig;
import com.rabbit.dao.TJmeterScriptMapper;
import com.rabbit.model.Result;
import com.rabbit.model.TJmeterScript;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.service.TJmeterScriptService;
import com.rabbit.utils.UserUtil;
import com.rabbit.utils.ZipUtils;
import com.rabbit.utils.file.FileUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * jmeter脚本相关接口
 */
@Slf4j
@RestController
@RequestMapping("/jmeterScript")
@Api(tags = "jmeter脚本相关接口")
public class JmeterController {

    @Autowired
    private TJmeterScriptService jmeterScriptService;

    @Autowired
    private TJmeterScriptMapper jmeterScriptMapper;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        TJmeterScript jmeterScript = JSONObject.toJavaObject(jsonObject, TJmeterScript.class);
        PageInfo pageInfo = jmeterScriptService.findByAllwithPage(pageNum, pageSize, jmeterScript);
        return new ResponseInfo(true, pageInfo);
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true, jmeterScriptService.findByProjectId(id));
    }

    @GetMapping("/listById/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listById(@PathVariable long id) {
        return new ResponseInfo(true, jmeterScriptService.selectByPrimaryKey(id));
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public ResponseInfo savaTJmeterScript(@RequestBody TJmeterScript jmeterScript) {
        List<TJmeterScript> jmeterScripts = jmeterScriptService.findByNameAndProjectId(jmeterScript.getName(), jmeterScript.getProjectId());
        if (jmeterScripts.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "jmeter脚本【" + jmeterScript.getName() + "】已存在"));
        }
        jmeterScript.setUpdateBy(UserUtil.getLoginUser().getUsername());
        jmeterScript.setCreateBy(UserUtil.getLoginUser().getUsername());
        jmeterScriptService.insertSelective(jmeterScript);
        return new ResponseInfo(true, jmeterScript);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editTJmeterScript(@RequestBody TJmeterScript jmeterScript) {
        List<TJmeterScript> jmeterScripts = jmeterScriptService.findByNameAndProjectIdAndIdNot(jmeterScript.getName(), jmeterScript.getProjectId(), jmeterScript.getId());
        if (jmeterScripts.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "jmeter脚本【" + jmeterScript.getName() + "】已存在"));
        }
        jmeterScript.setUpdateBy(UserUtil.getLoginUser().getUsername());
        jmeterScriptService.updateByPrimaryKeySelectiveCustomer(jmeterScript);
        return new ResponseInfo(true, "修改jmeter脚本成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delTJmeterScript(@RequestBody TJmeterScript jmeterScript) {
        jmeterScriptService.deleteByPrimaryKey(jmeterScript.getId());
        return new ResponseInfo(true, "删除jmeter脚本成功");
    }

    @PostMapping(value = "/uploadScripts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "上传jmeter脚本")
    public ResponseInfo uploadQusImages(@RequestParam("files") MultipartFile[] files, @RequestParam("isCover") boolean isCover) {
        if (files != null && files.length > 0) {
            MultipartFile multipartFile = files[0];
            String fileOrigName = multipartFile.getOriginalFilename();
            if (!fileOrigName.toLowerCase().endsWith(".zip")) {
                return new ResponseInfo(false, new ErrorInfo(777, "脚本只支持zip文件导入"));
            }
            File file = FileUploadUtils.multipartFileToFile(multipartFile);
            Result result;
            try {
                //校验上传文件
                ZipFile zipFile = new ZipFile(file);
                result = jmeterScriptService.isJmeterZip(zipFile);
                if (!result.getFlag()) {
                    if (result.getMsg().equals("存在同名jmx脚本") & !isCover) {
                        file.delete();
                        return new ResponseInfo(true, "存在同名jmx脚本");
                    } else if (result.getMsg().equals("存在同名jmx脚本") & isCover) {
                        //有同名的，直接覆盖
                        try {
                            jmeterScriptService.uploadJmeterScript(zipFile);
                        } catch (Exception e) {
                            throw new IllegalArgumentException(e.getMessage());
                        }
                        file.delete();
                        return new ResponseInfo(true, "上传了");
                    } else {
                        file.delete();
                        return new ResponseInfo(false, new ErrorInfo(520, result.getMsg()));
                    }
                } else {
                    //没有同名的，直接新增
                    try {
                        jmeterScriptService.uploadJmeterScript(zipFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        file.delete();
                        throw new IllegalArgumentException(e.getMessage());
                    }
                    file.delete();
                    return new ResponseInfo(true, "上传了");
                }
            } catch (ZipException e) {
                file.delete();
                throw new IllegalArgumentException(e.getMessage());
            }
        } else {
            return new ResponseInfo(true, new ErrorInfo(777, "上传文件不能为空"));
        }
    }

    @GetMapping("/downloadCsv")
    @ApiOperation(value = "获取列表")
    public void download(@RequestParam(value = "scriptName") String scriptName, @RequestParam(value = "projectId") int projectId, @RequestParam(value = "fileName") String fileName,
                         HttpServletRequest request, HttpServletResponse resp) throws IOException {
        InputStream fis = null;
        try {
            String zipPathName = RabbitConfig.jmeterfile + projectId + "/script/" + scriptName;
            String zipName = scriptName.replace(".zip", "") + "/data/" + fileName;
            fis = ZipUtils.getInputStream(zipPathName, zipName);
            resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-Disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            IOUtils.copy(fis, resp.getOutputStream());
        } catch (Exception e) {
            log.error("文件[ {} ]下载错误", "测试文件======");
            throw new IllegalArgumentException("下载失败:" + e.getMessage());
        } finally {
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
            fis.close();
        }
    }

    @PostMapping(value = "/uploadCsv", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "上传csv数据")
    public ResponseInfo uploadCsv(@RequestParam("files") MultipartFile[] files, @RequestParam("scriptId") long scriptId, @RequestParam("isCover") boolean isCover) {
        if (files != null && files.length > 0) {
            MultipartFile multipartFile = files[0];
            String fileOrigName = multipartFile.getOriginalFilename();
            if (!fileOrigName.toLowerCase().endsWith(".csv")) {
                return new ResponseInfo(false, new ErrorInfo(777, "脚本只支持csv文件导入"));
            }
            try {
                //校验上传文件
                TJmeterScript jmeterScript = jmeterScriptMapper.selectByPrimaryKey(scriptId);
                String[] dataList = jmeterScript.getDataPath().split(",");

                boolean isExists = false;
                for (String dataName : dataList) {
                    if (dataName.equals(fileOrigName)) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists & !isCover) {
                    //不覆盖返回
                    return new ResponseInfo(true, "存在同名csv文件");
                } else {
                    File file = FileUploadUtils.multipartFileToFile(multipartFile);
                    jmeterScriptService.saveCsv(scriptId, fileOrigName, file);
                    file.delete();
                    return new ResponseInfo(true, "上传了");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } else {
            return new ResponseInfo(true, new ErrorInfo(777, "上传文件不能为空"));
        }
    }


    @GetMapping("/removeCsv")
    @ApiOperation(value = "删除")
    public ResponseInfo delJmeterCsv(@RequestParam("scriptId") long scriptId, @RequestParam("csvName") String csvName) {
        jmeterScriptService.delJmeterCsv(scriptId, csvName);
        return new ResponseInfo(true, "删除csv数据成功");
    }

    @PostMapping(value = "/coverCsv", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "覆盖csv数据")
    public ResponseInfo uploadCsv(@RequestParam("files") MultipartFile[] files, @RequestParam("scriptId") long scriptId, @RequestParam("orgCsvName") String orgCsvName) {
        if (files != null && files.length > 0) {
            MultipartFile multipartFile = files[0];
            String fileOrigName = multipartFile.getOriginalFilename();
            if (!fileOrigName.toLowerCase().endsWith(".csv")) {
                return new ResponseInfo(false, new ErrorInfo(777, "脚本只支持csv文件导入"));
            }
            try {
                //校验上传文件
                TJmeterScript jmeterScript = jmeterScriptMapper.selectByPrimaryKey(scriptId);
                String[] dataList = jmeterScript.getDataPath().split(",");
                boolean isExists = false;
                for (String dataName : dataList) {
                    if (dataName.equals(fileOrigName) & !fileOrigName.equals(orgCsvName)) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists) {
                    //不覆盖返回
                    return new ResponseInfo(false, new ErrorInfo(777, "该脚本已存在相同文件名的文件"));
                } else {
                    File file = FileUploadUtils.multipartFileToFile(multipartFile);
                    jmeterScriptService.coverCsv(jmeterScript, orgCsvName, fileOrigName, file);
                    file.delete();
                    return new ResponseInfo(true, "上传了");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } else {
            return new ResponseInfo(true, new ErrorInfo(777, "上传文件不能为空"));
        }
    }

    @GetMapping("/debug")
    @ApiOperation(value = "调试jmeter脚本")
    public ResponseInfo debug(@RequestParam("scriptId") long scriptId, @RequestParam("clientId") long clientId) {
        return new ResponseInfo(true, jmeterScriptService.selectByPrimaryKey(scriptId));
    }

}
