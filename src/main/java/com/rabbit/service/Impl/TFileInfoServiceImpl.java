package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.config.RabbitConfig;
import com.rabbit.utils.UUIDUtil;
import com.rabbit.utils.UserUtil;
import com.rabbit.utils.file.FileUploadUtils;
import com.rabbit.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TFileInfoMapper;
import com.rabbit.model.TFileInfo;
import com.rabbit.service.TFileInfoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TFileInfoServiceImpl implements TFileInfoService {

    @Resource
    private TFileInfoMapper tFileInfoMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        TFileInfo tFileInfo = selectByPrimaryKey(id);
        if (tFileInfo != null && StringUtils.isNotEmpty(tFileInfo.getPath())) {
            String fullPath = RabbitConfig.systemfile + tFileInfo.getPath();
            FileUtils.deleteFile(fullPath);
        }
        return tFileInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int deleteBySourceTypeAndSourceId(Integer sourceType, Long sourceId) {
        FileUtils.delFile(new File(RabbitConfig.systemfile + sourceType + "/" + sourceId));
        return tFileInfoMapper.deleteBySourceTypeAndSourceId(sourceType, sourceId);
    }

    @Override
    public TFileInfo selectByPrimaryKey(Long id) {
        return tFileInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TFileInfo record) {
        return tFileInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TFileInfo record) {
        return tFileInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public TFileInfo add(MultipartFile file, TFileInfo fileInfo) throws IOException {
        String fileOrigName = file.getOriginalFilename();
        if (!fileOrigName.contains(".")) {
            throw new IllegalArgumentException("缺少后缀名");
        }
        String md5 = FileUtils.fileMd5(file.getInputStream());
        fileOrigName = fileInfo.getName() + fileOrigName.substring(fileOrigName.lastIndexOf("."));
//        String pathname = FileUtils.getPath() + fileOrigName;
        String pathname = fileInfo.getSourceType() + "/" + fileInfo.getSourceId() + "/" + fileOrigName;
        String fullPath = RabbitConfig.systemfile + pathname;
        FileUploadUtils.upload(fullPath, file);
        long size = file.getSize();
        String contentType = file.getContentType();
        fileInfo.setMd5(md5);
        fileInfo.setContentType(contentType);
        fileInfo.setSize(size);
        fileInfo.setPath(pathname);
        fileInfo.setType(contentType.startsWith("image/") ? 1 : 0);
        fileInfo.setUpdateBy(UserUtil.getLoginUser().getUsername());
        fileInfo.setCreateBy(UserUtil.getLoginUser().getUsername());
        tFileInfoMapper.insert(fileInfo);
        return fileInfo;
    }

    @Override
    public int insert(TFileInfo record) {
        return tFileInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(TFileInfo record) {
        return tFileInfoMapper.insertSelective(record);
    }

    @Override
    public PageInfo<TFileInfo> findByAllwithPage(int page, int pageSize, TFileInfo tFileInfo) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tFileInfoMapper.findByAll(tFileInfo));
    }

    @Override
    public List<TFileInfo> findByAll(TFileInfo tFileInfo) {
        return tFileInfoMapper.findByAll(tFileInfo);
    }

    @Override
    public byte[] getFileByte(int sourceType, Long sourceId, String fileName) {
        TFileInfo tFileInfo = new TFileInfo();
        tFileInfo.setSourceType(sourceType);
        tFileInfo.setSourceId(sourceId);
        tFileInfo.setName(fileName);
        List<TFileInfo> byAll = findByAll(tFileInfo);
        if (byAll.size() > 0) {
            TFileInfo fileInfo = byAll.get(0);
            String fullPath = RabbitConfig.systemfile + fileInfo.getPath();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(fullPath);
                return IOUtils.toByteArray(fileInputStream);
            } catch (Exception e) {
                return new byte[0];
            }
        } else {
            return new byte[0];
        }
    }

    @Override
    public String getAbsolutePath(int sourceType, Long sourceId, String fileName) {
        TFileInfo tFileInfo = new TFileInfo();
        tFileInfo.setSourceType(sourceType);
        tFileInfo.setSourceId(sourceId);
        tFileInfo.setName(fileName);
        List<TFileInfo> byAll = findByAll(tFileInfo);
        if (byAll.size() > 0) {
            TFileInfo fileInfo = byAll.get(0);
            return RabbitConfig.systemfile + fileInfo.getPath();
        } else {
            return null;
        }
    }


}





