package com.rabbit.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.dao.TFileInfoMapper;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.TFileInfo;
import com.rabbit.service.TFileInfoService;
import com.rabbit.utils.FastJSONHelper;
import com.rabbit.utils.file.FileUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * 上传相关接口
 */
@Slf4j
@RestController
    @RequestMapping("/file")
@Api(tags = "文件管理相关接口")
public class FileControl {

    @Autowired
    private TFileInfoService fileInfoService;
    @Autowired
    private TFileInfoMapper fileInfoMapper;

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "上传文件")
    public ResponseInfo uploadQusImages(@RequestParam("files") MultipartFile[] files, @RequestParam("fileInfo") String fileInfo) {
        if (files != null && files.length > 0) {
            TFileInfo deserialize = FastJSONHelper.deserialize(fileInfo, TFileInfo.class);
            List<TFileInfo> bySourceTypeAndSourceIdAndName = fileInfoMapper.findBySourceTypeAndSourceIdAndName(deserialize.getSourceType(), deserialize.getSourceId(), deserialize.getName());
            if (bySourceTypeAndSourceIdAndName.size() > 0) {
                return new ResponseInfo(false,  new ErrorInfo(650, "上传的文件名已存在"));
            }
            try {
                MultipartFile multipartFile = files[0];
                TFileInfo save = fileInfoService.add(multipartFile, deserialize);
                return new ResponseInfo(true, save);
            } catch (Exception e) {
                log.error("上传失败[{}]", e.getMessage());
                return new ResponseInfo(false, new ErrorInfo(650, e.getMessage()));
            }
        } else {
            return new ResponseInfo(false, new ErrorInfo(650, "上传文件不能为空"));
        }
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        TFileInfo fileInfo = FastJSONHelper.deserialize(serchData, TFileInfo.class);
        PageInfo pageInfo = fileInfoService.findByAllwithPage(pageNum, pageSize, fileInfo);
        return new ResponseInfo(true, pageInfo);
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public ResponseInfo getList(@RequestBody TFileInfo fileInfo) {
        return new ResponseInfo(true, fileInfoService.findByAll(fileInfo));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除文件")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true, fileInfoService.deleteByPrimaryKey(id));
    }

}
