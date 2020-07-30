package com.rabbit.dao;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.rabbit.model.TFileInfo;

public interface TFileInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TFileInfo record);

    int insertSelective(TFileInfo record);

    TFileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TFileInfo record);

    int updateByPrimaryKey(TFileInfo record);

    List<TFileInfo> findBySourceTypeAndSourceIdAndName(@Param("sourceType") Integer sourceType, @Param("sourceId") Long sourceId, @Param("name") String name);

    List<TFileInfo> findBySourceTypeAndSourceIdAndNameAndIdNot(@Param("sourceType")Integer sourceType,@Param("sourceId")Long sourceId,@Param("name")String name,@Param("notId")Long notId);

    List<TFileInfo> findByAll(TFileInfo tFileInfo);

    int deleteBySourceTypeAndSourceId(@Param("sourceType")Integer sourceType,@Param("sourceId")Long sourceId);

    List<TFileInfo> findBySourceTypeAndSourceId(@Param("sourceType")Integer sourceType,@Param("sourceId")Long sourceId);

}