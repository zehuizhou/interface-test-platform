package com.rabbit.service.Impl;

import com.rabbit.dao.TApiMapper;
import com.rabbit.dto.TApiSuiteDto;
import com.rabbit.model.*;
import com.rabbit.service.RequestExecutorServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TApiSuiteMapper;
import com.rabbit.service.TApiSuiteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TApiSuiteServiceImpl implements TApiSuiteService {

    @Resource
    private TApiSuiteMapper tApiSuiteMapper;

    @Autowired
    private TApiMapper tApiMapper;




    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        tApiMapper.deleteByApiSuiteId(id);
        return tApiSuiteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TApiSuite record) {
        return tApiSuiteMapper.insert(record);
    }

    @Override
    public int insertSelective(TApiSuite record) {
        return tApiSuiteMapper.insertSelective(record);
    }

    @Override
    public TApiSuite selectByPrimaryKey(Long id) {
        return tApiSuiteMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TApiSuite record) {
        return tApiSuiteMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TApiSuite record) {
        return tApiSuiteMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TApiSuiteDto> findDtoByProjectId(Long projectId) {
        List<TApiSuiteDto> byProjectId = tApiSuiteMapper.findByProjectId(projectId);
        for (TApiSuiteDto tApiSuiteDto : byProjectId) {
            tApiSuiteDto.setApiList(tApiMapper.findByApiSuiteId(tApiSuiteDto.getId()));
        }
        return byProjectId;
    }

    @Override
    public List<TApiSuite> findByNameAndProjectId(String name, Long projectId) {
        return tApiSuiteMapper.findByNameAndProjectId(name, projectId);
    }

    @Override
    public List<TApiSuite> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId) {
        return tApiSuiteMapper.findByNameAndProjectIdAndIdNot(name, projectId, notId);
    }

    @Override
    public List<TApiSuiteDto> findByProjectId(Long projectId) {
        return tApiSuiteMapper.findByProjectId(projectId);
    }

}
