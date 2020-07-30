package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dao.TPlanSuiteApiMapper;
import com.rabbit.dao.TSuiteCaseApiMapper;
import com.rabbit.model.TSuiteCaseApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TTestsuiteApiMapper;
import com.rabbit.model.TTestsuiteApi;
import com.rabbit.service.TTestsuiteApiService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TTestsuiteApiServiceImpl implements TTestsuiteApiService {

    @Resource
    private TTestsuiteApiMapper tTestsuiteApiMapper;
    @Resource
    private TSuiteCaseApiMapper suiteCaseApiMapper;
    @Resource
    private TPlanSuiteApiMapper planSuiteApiMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        suiteCaseApiMapper.deleteBySuiteId(id);
        planSuiteApiMapper.deleteBySuiteId(id);
        return tTestsuiteApiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TTestsuiteApi record) {
        return tTestsuiteApiMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestsuiteApi record) {
        return tTestsuiteApiMapper.insertSelective(record);
    }

    @Override
    public TTestsuiteApi selectByPrimaryKey(Long id) {
        return tTestsuiteApiMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TTestsuiteApi record) {
        return tTestsuiteApiMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestsuiteApi record) {
        return tTestsuiteApiMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TTestsuiteApi> findByAllwithPage(int page, int pageSize, TTestsuiteApi tTestsuiteApi) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tTestsuiteApiMapper.findByAll(tTestsuiteApi));
    }

    @Override
    public List<TTestsuiteApi> findByProjectId(Long projectId) {
        return tTestsuiteApiMapper.findByProjectId(projectId);
    }

    @Override
    public List<TTestsuiteApi> selectByNameAndProjectId(String name, Long projectId) {
        return tTestsuiteApiMapper.selectByNameAndProjectId(name, projectId);
    }

    @Override
    public List<TTestsuiteApi> selectByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId) {
        return tTestsuiteApiMapper.selectByNameAndProjectIdAndIdNot(name, projectId, notId);
    }
}
