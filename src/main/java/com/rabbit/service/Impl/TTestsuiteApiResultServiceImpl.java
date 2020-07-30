package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dto.TTestsuiteApiResultDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TTestsuiteApiResultMapper;
import com.rabbit.model.TTestsuiteApiResult;
import com.rabbit.service.TTestsuiteApiResultService;

import java.util.List;

@Service
public class TTestsuiteApiResultServiceImpl implements TTestsuiteApiResultService {

    @Resource
    private TTestsuiteApiResultMapper tTestsuiteApiResultMapper;

    @Override
    public int insert(TTestsuiteApiResult record) {
        return tTestsuiteApiResultMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestsuiteApiResult record) {
        return tTestsuiteApiResultMapper.insertSelective(record);
    }


    @Override
    public int updateByPrimaryKeySelective(TTestsuiteApiResult record) {
        return tTestsuiteApiResultMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestsuiteApiResult record) {
        return tTestsuiteApiResultMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tTestsuiteApiResultMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TTestsuiteApiResult selectByPrimaryKey(Long id) {
        return tTestsuiteApiResultMapper.selectByPrimaryKey(id);
    }

    @Override
    public TTestsuiteApiResult findByPlanIdCount(Long planLogId) {
        return tTestsuiteApiResultMapper.findByPlanIdCount(planLogId);
    }
    @Override
    public List<TTestsuiteApiResultDto> findDtoByAll(TTestsuiteApiResult tTestsuiteApiResult) {
        return tTestsuiteApiResultMapper.findDtoByAll(tTestsuiteApiResult);
    }
}


