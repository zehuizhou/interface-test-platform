package com.rabbit.service.Impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.model.TTestSuiteUiLog;
import com.rabbit.dao.TTestSuiteUiLogMapper;
import com.rabbit.service.TTestSuiteUiLogService;

@Service
public class TTestSuiteUiLogServiceImpl implements TTestSuiteUiLogService {

    @Resource
    private TTestSuiteUiLogMapper tTestSuiteUiLogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tTestSuiteUiLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TTestSuiteUiLog record) {
        return tTestSuiteUiLogMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestSuiteUiLog record) {
        return tTestSuiteUiLogMapper.insertSelective(record);
    }

    @Override
    public Long insertSelectiveReturnKey(TTestSuiteUiLog record) {
        tTestSuiteUiLogMapper.insertSelective(record);
        return record.getId();
    }

    @Override
    public TTestSuiteUiLog insertSelectiveReturnObj(TTestSuiteUiLog record) {
        tTestSuiteUiLogMapper.insertSelective(record);
        return record;
    }

    @Override
    public TTestSuiteUiLog selectByPrimaryKey(Long id) {
        return tTestSuiteUiLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TTestSuiteUiLog record) {
        return tTestSuiteUiLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestSuiteUiLog record) {
        return tTestSuiteUiLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public TTestSuiteUiLog findByPlanIdCount(Long planLogId) {
        return tTestSuiteUiLogMapper.findByPlanIdCount(planLogId);
    }
}
