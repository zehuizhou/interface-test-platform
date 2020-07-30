package com.rabbit.dao;
import java.util.Date;

import com.rabbit.model.SysDictValue;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface SysDictValueMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysDictValue record);

    SysDictValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDictValue record);

    int updateByPrimaryKey(SysDictValue record);

    List<SysDictValue> findByAll(SysDictValue sysDictValue);

    List<SysDictValue> findByDictId(@Param("dictId")Long dictId);

    List<SysDictValue> findByDictIdAndKey(@Param("dictId")Long dictId,@Param("key")String key);

    List<SysDictValue> findByDictIdAndKeyAndIdNot(@Param("dictId")Long dictId,@Param("key")String key,@Param("notId")Long notId);

}