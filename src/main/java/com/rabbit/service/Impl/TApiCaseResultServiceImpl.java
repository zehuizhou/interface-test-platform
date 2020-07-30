package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.rabbit.dao.TApiCaseResultMapper;
import com.rabbit.model.TApiCaseResult;
import com.rabbit.service.TApiCaseResultService;

@Service
public class TApiCaseResultServiceImpl implements TApiCaseResultService {

    @Resource
    private TApiCaseResultMapper tApiCaseResultMapper;

    @Override
    public int insert(TApiCaseResult record) {
        return tApiCaseResultMapper.insert(record);
    }

    @Override
    public int insertSelective(TApiCaseResult record) {
        return tApiCaseResultMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(TApiCaseResult record) {
        return tApiCaseResultMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TApiCaseResult record) {
        return tApiCaseResultMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tApiCaseResultMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TApiCaseResult selectByPrimaryKey(Long id) {
        return tApiCaseResultMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<TApiCaseResult> findByAll(TApiCaseResult tApiCaseResult) {
        return tApiCaseResultMapper.findByAll(tApiCaseResult);
    }
}





