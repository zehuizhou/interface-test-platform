package com.rabbit.service.Impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dao.SysDictValueMapper;
import com.rabbit.model.SysDictValue;
import com.rabbit.service.SysDictValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysDictValueServiceImpl implements SysDictValueService {
    @Autowired
    private SysDictValueMapper sysDictValueMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysDictValueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SysDictValue record) {
        return sysDictValueMapper.insertSelective(record);
    }

    @Override
    public SysDictValue selectByPrimaryKey(Long id) {
        return sysDictValueMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysDictValue record) {
        return sysDictValueMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysDictValue record) {
        return sysDictValueMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysDictValue> findByDictId(Long dictId) {
        return sysDictValueMapper.findByDictId(dictId);
    }

    @Override
    public List<SysDictValue> findByDictIdAndKey(Long dictId, String key) {
        return sysDictValueMapper.findByDictIdAndKey(dictId, key);
    }

    @Override
    public List<SysDictValue> findByDictIdAndKeyAndIdNot(Long dictId, String key, Long notId) {
        return sysDictValueMapper.findByDictIdAndKeyAndIdNot(dictId, key, notId);
    }
    @Override
    public PageInfo<SysDictValue> findByAllwithPage(int page, int pageSize, SysDictValue sysDictValue) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(sysDictValueMapper.findByAll(sysDictValue));
    }

}


