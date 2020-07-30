package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rabbit.model.TFlag;
import com.rabbit.dao.TFlagMapper;
import com.rabbit.service.TFlagService;

import java.util.List;

@Service
public class TFlagServiceImpl implements TFlagService {

    @Resource
    private TFlagMapper tFlagMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tFlagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TFlag record) {
        return tFlagMapper.insert(record);
    }

    @Override
    public int insertSelective(TFlag record) {
        return tFlagMapper.insertSelective(record);
    }

    @Override
    public TFlag selectByPrimaryKey(Long id) {
        return tFlagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TFlag record) {
        return tFlagMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TFlag record) {
        return tFlagMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TFlag> findByAllwithPage(int page, int pageSize, TFlag tFlag) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tFlagMapper.findByAll(tFlag));
    }

    @Override
    public List<TFlag> findByProjectId(Long projectId) {
        return tFlagMapper.findByProjectId(projectId);
    }

    @Override
    public List<TFlag> findByProjectIdAndType(Long projectId, Integer type) {
        return tFlagMapper.findByProjectIdAndType(projectId, type);
    }
    @Override
    public List<TFlag> findByNameAndTypeAndProjectId(String name, Integer type, Long projectId) {
        return tFlagMapper.findByNameAndTypeAndProjectId(name, type, projectId);
    }
    @Override
    public List<TFlag> findByNameAndTypeAndProjectIdAndIdNot(String name, Integer type, Long projectId, Long notId) {
        return tFlagMapper.findByNameAndTypeAndProjectIdAndIdNot(name, type, projectId, notId);
    }
}



