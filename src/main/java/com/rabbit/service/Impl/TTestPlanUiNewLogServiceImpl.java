package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.config.RabbitConfig;
import com.rabbit.dao.TTestCaseUiNewLogMapper;
import com.rabbit.dao.TTestStepUiNewLogMapper;
import com.rabbit.dao.TTestSuiteUiLogMapper;
import com.rabbit.model.TTestStepUiNewLog;
import com.rabbit.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.model.TTestPlanUiNewLog;
import com.rabbit.dao.TTestPlanUiNewLogMapper;
import com.rabbit.service.TTestPlanUiNewLogService;

import java.io.File;
import java.util.List;

@Service
public class TTestPlanUiNewLogServiceImpl implements TTestPlanUiNewLogService {

    @Resource
    private TTestPlanUiNewLogMapper tTestPlanUiNewLogMapper;
    @Autowired
    private TTestSuiteUiLogMapper testSuiteUiLogMapper;
    @Autowired
    private TTestCaseUiNewLogMapper tTestCaseUiNewLogMapper;
    @Autowired
    private TTestStepUiNewLogMapper tTestStepUiNewLogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        String pathname = RabbitConfig.profile + File.separator + "UI" + File.separator + "report" + File.separator + id;
        FileUtils.delFile(new File(pathname));
        tTestStepUiNewLogMapper.deleteByPlanJobId(id);
        tTestCaseUiNewLogMapper.deleteByPlanLogId(id);
        testSuiteUiLogMapper.deleteByPlanLogId(id);
        return tTestPlanUiNewLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TTestPlanUiNewLog record) {
        return tTestPlanUiNewLogMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestPlanUiNewLog record) {
        return tTestPlanUiNewLogMapper.insertSelective(record);
    }

    @Override
    public TTestPlanUiNewLog selectByPrimaryKey(Long id) {
        return tTestPlanUiNewLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TTestPlanUiNewLog record) {
        return tTestPlanUiNewLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestPlanUiNewLog record) {
        return tTestPlanUiNewLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TTestPlanUiNewLog> findByAllwithPage(int page, int pageSize, TTestPlanUiNewLog tTestPlanUiNewLog) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tTestPlanUiNewLogMapper.findByAll(tTestPlanUiNewLog));
    }
}

