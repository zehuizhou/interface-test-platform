package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.rabbit.dao.TTestInterfaceMapper;
import com.rabbit.model.TTestInterface;
import com.rabbit.service.TTestInterfaceService;

import java.util.List;

@Service
public class TTestInterfaceServiceImpl implements TTestInterfaceService {

    @Resource
    private TTestInterfaceMapper tTestInterfaceMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tTestInterfaceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TTestInterface record) {
        return tTestInterfaceMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestInterface record) {
        return tTestInterfaceMapper.insertSelective(record);
    }

    @Override
    public TTestInterface selectByPrimaryKey(Long id) {
        return tTestInterfaceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TTestInterface record) {
        return tTestInterfaceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestInterface record) {
        return tTestInterfaceMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TTestInterface> findByAllwithPage(int page, int pageSize, TTestInterface tTestInterface) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tTestInterfaceMapper.findByAll(tTestInterface));
    }

    @Override
    public List<TTestInterface> findByProjectId(Long projectId) {
        return tTestInterfaceMapper.findByProjectId(projectId);
    }

    @Override
    public List<TTestInterface> findByNameAndProjectId(String name, Long projectId) {
        return tTestInterfaceMapper.findByNameAndProjectId(name, projectId);
    }

    @Override
    public List<TTestInterface> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId) {
        return tTestInterfaceMapper.findByNameAndProjectIdAndIdNot(name, projectId, notId);
    }
}

