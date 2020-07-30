package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.common.constant.FileType;
import com.rabbit.config.RabbitConfig;
import com.rabbit.model.UploadFile;
import com.rabbit.utils.HttpServletUtil;
import com.rabbit.utils.UUIDUtil;
import com.rabbit.utils.file.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.model.Device;
import com.rabbit.dao.DeviceMapper;
import com.rabbit.service.DeviceService;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return deviceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Device record) {
        return deviceMapper.insert(record);
    }

    @Override
    public int insertSelective(Device record) {
        return deviceMapper.insertSelective(record);
    }

    @Override
    public Device selectByPrimaryKey(String id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Device record) {
        return deviceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Device record) {
        return deviceMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Device> findByAllwithPage(int page, int pageSize, Device device) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(deviceMapper.findByAll(device));
    }

    @Override
    public List<Device> getOnlineDevices(Integer platform) {
        return deviceMapper.findByPlatformAndStatusNot(platform, Device.OFFLINE_STATUS);
    }

    @Override
    public List<Device> findByAll(Device device) {
        return deviceMapper.findByAll(device);
    }

    @Override
    public UploadFile upload(String originalFilename, Integer fileType, InputStream data) {
        UploadFile uploadFile = new UploadFile();
        String uploadFilePath;
        switch (fileType) {
            case FileType.IMG:
                uploadFilePath = UploadFile.IMG_PATH;
                break;
            case FileType.VIDEO:
                uploadFilePath = UploadFile.VIDEO_PATH;
                break;
            case FileType.APP:
                uploadFilePath = UploadFile.APP_PATH;
                break;
            case FileType.DRIVER:
                uploadFilePath = UploadFile.DRIVER_PATH;
                break;
            default:
                uploadFilePath = UploadFile.OTHER_FILE_PATH;
        }
        String destFileName = UUIDUtil.getUUID();
        if (originalFilename.contains(".")) {
            destFileName = destFileName + "." + StringUtils.unqualify(originalFilename);
        }
        String destFilePath = uploadFilePath + "/" + destFileName;
        String fullPath = RabbitConfig.profile + destFilePath;
        FileUtils.writeInputStream(fullPath, data);
        uploadFile.setFilePath(destFilePath);
        uploadFile.setDownloadUrl(HttpServletUtil.getStaticResourceUrl(destFilePath));
        return uploadFile;
    }
}




