package com.rabbit.service;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.SysDictValue;

import java.util.List;

public interface SysDictValueService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysDictValue record);

    SysDictValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDictValue record);

    int updateByPrimaryKey(SysDictValue record);

    List<SysDictValue> findByDictId(Long dictId);

    List<SysDictValue> findByDictIdAndKey(Long dictId, String key);

    List<SysDictValue> findByDictIdAndKeyAndIdNot(Long dictId, String key, Long notId);

    PageInfo<SysDictValue> findByAllwithPage(int page, int pageSize, SysDictValue sysDictValue);
}


