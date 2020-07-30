package com.rabbit.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rabbit.dao.TJmeterExcDetailMapper;
import com.rabbit.model.TJmeterExcDetail;
import com.rabbit.service.TJmeterExcDetailService;

@Service
public class TJmeterExcDetailServiceImpl implements TJmeterExcDetailService {

    @Resource
    private TJmeterExcDetailMapper tJmeterExcDetailMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tJmeterExcDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TJmeterExcDetail record) {
        return tJmeterExcDetailMapper.insert(record);
    }

    @Override
    public int insertSelective(TJmeterExcDetail record) {
        return tJmeterExcDetailMapper.insertSelective(record);
    }

    @Override
    public TJmeterExcDetail selectByPrimaryKey(Long id) {
        return tJmeterExcDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TJmeterExcDetail record) {
        return tJmeterExcDetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TJmeterExcDetail record) {
        return tJmeterExcDetailMapper.updateByPrimaryKey(record);
    }

}


