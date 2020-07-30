package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rabbit.model.TEnv;
import com.rabbit.dao.TEnvMapper;
import com.rabbit.service.TEnvService;

import java.util.List;

@Service
public class TEnvServiceImpl implements TEnvService{

    @Resource
    private TEnvMapper tEnvMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tEnvMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TEnv record) {
        return tEnvMapper.insert(record);
    }

    @Override
    public int insertSelective(TEnv record) {
        return tEnvMapper.insertSelective(record);
    }

    @Override
    public TEnv selectByPrimaryKey(Long id) {
        return tEnvMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TEnv record) {
        return tEnvMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TEnv record) {
        return tEnvMapper.updateByPrimaryKey(record);
    }
    @Override
    public PageInfo<TEnv> findByAllwithPage(int page, int pageSize, TEnv tEnv) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tEnvMapper.findByAll(tEnv));
    }
    @Override
    public List<TEnv> findByProjectId(Long projectId) {
        return tEnvMapper.findByProjectId(projectId);
    }
    @Override
    public List<TEnv> findByNameAndProjectId(String name, Long projectId) {
        return tEnvMapper.findByNameAndProjectId(name, projectId);
    }
    @Override
    public List<TEnv> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId) {
        return tEnvMapper.findByNameAndProjectIdAndIdNot(name, projectId, notId);
    }
}
