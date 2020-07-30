package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rabbit.model.TApiEnv;
import com.rabbit.dao.TApiEnvMapper;
import com.rabbit.service.TApiEnvService;

import java.util.List;

@Service
public class TApiEnvServiceImpl implements TApiEnvService {

    @Resource
    private TApiEnvMapper tApiEnvMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tApiEnvMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TApiEnv record) {
        return tApiEnvMapper.insert(record);
    }

    @Override
    public int insertSelective(TApiEnv record) {
        return tApiEnvMapper.insertSelective(record);
    }

    @Override
    public TApiEnv selectByPrimaryKey(Long id) {
        return tApiEnvMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TApiEnv record) {
        return tApiEnvMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TApiEnv record) {
        return tApiEnvMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TApiEnv> findByAllwithPage(int page, int pageSize, TApiEnv tApiEnv) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tApiEnvMapper.findByAll(tApiEnv));
    }

    @Override
    public List<TApiEnv> findByNameAndProjectId(String name, Long projectId) {
        return tApiEnvMapper.findByNameAndProjectId(name, projectId);
    }

    @Override
    public List<TApiEnv> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId) {
        return tApiEnvMapper.findByNameAndProjectIdAndIdNot(name, projectId, notId);
    }

    @Override
    public List<TApiEnv> findByProjectId(Long projectId) {
        return tApiEnvMapper.findByProjectId(projectId);
    }
}








