package com.rabbit.service.Impl;


import com.rabbit.dao.SysDictDtoMapper;
import com.rabbit.dao.SysDictMapper;
import com.rabbit.dto.SysDictDto;
import com.rabbit.model.SysDict;
import com.rabbit.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysDictServiceImpl implements SysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;
    @Autowired
    private SysDictDtoMapper sysDictDtoMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysDictMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SysDict record) {
        return sysDictMapper.insertSelective(record);
    }

    @Override
    public SysDict selectByPrimaryKey(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysDict record) {
        return sysDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysDict record) {
        return sysDictMapper.updateByPrimaryKey(record);
    }
    @Override
    public List<SysDict> findAll() {
        return sysDictMapper.findAll();
    }

    @Override
    public List<SysDictDto> findUiActions() {
        return sysDictDtoMapper.findUiActions();
    }
    @Override
    public List<SysDict> findByKey(String key) {
        return sysDictMapper.findByKey(key);
    }
    @Override
    public List<SysDictDto> findApiActions() {
        return sysDictDtoMapper.findApiActions();
    }
}


