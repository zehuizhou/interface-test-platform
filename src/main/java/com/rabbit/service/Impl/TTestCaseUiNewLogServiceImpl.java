package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dao.TTestcaseUiNewDtoMapper;
import com.rabbit.dto.TestcaseUiNewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TTestCaseUiNewLogMapper;
import com.rabbit.model.TTestCaseUiNewLog;
import com.rabbit.service.TTestCaseUiNewLogService;

@Service
public class TTestCaseUiNewLogServiceImpl implements TTestCaseUiNewLogService {

    @Resource
    private TTestCaseUiNewLogMapper tTestCaseUiNewLogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tTestCaseUiNewLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TTestCaseUiNewLog record) {
        return tTestCaseUiNewLogMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestCaseUiNewLog record) {
        return tTestCaseUiNewLogMapper.insertSelective(record);
    }

    @Override
    public Long insertSelectiveReturnKey(TTestCaseUiNewLog record) {
        tTestCaseUiNewLogMapper.insertSelective(record);
        return record.getId();
    }

    @Override
    public TTestCaseUiNewLog insertSelectiveReturnObj(TTestCaseUiNewLog record) {
        tTestCaseUiNewLogMapper.insertSelective(record);
        return record;
    }

    @Override
    public TTestCaseUiNewLog selectByPrimaryKey(Long id) {
        return tTestCaseUiNewLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TTestCaseUiNewLog record) {
        return tTestCaseUiNewLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestCaseUiNewLog record) {
        return tTestCaseUiNewLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TTestCaseUiNewLog> findByAllwithPage(int page, int pageSize, TTestCaseUiNewLog tTestCaseUiNewLog) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tTestCaseUiNewLogMapper.findByAll(tTestCaseUiNewLog));
    }

    @Override
    public Long countBySuiteLogIdAndStatus(Long suiteLogId, Integer status) {
        return tTestCaseUiNewLogMapper.countBySuiteLogIdAndStatus(suiteLogId, status);
    }

}

