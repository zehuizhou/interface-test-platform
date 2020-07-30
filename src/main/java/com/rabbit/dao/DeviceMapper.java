package com.rabbit.dao;

import com.rabbit.model.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> findByAll(Device device);

    List<Device> findByPlatformAndStatusNot(@Param("platform") Integer platform, @Param("notStatus") Integer notStatus);
}