package com.rabbit.service.Impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TSuiteUiMapper;
import com.rabbit.model.TSuiteUi;
import com.rabbit.service.TSuiteUiService;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TSuiteUiServiceImpl implements TSuiteUiService {

    @Resource
    private TSuiteUiMapper tSuiteUiMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tSuiteUiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TSuiteUi record) {
        return tSuiteUiMapper.insert(record);
    }

    @Override
    public TSuiteUi selectByPrimaryKey(Long id) {
        return tSuiteUiMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(TSuiteUi record) {
        return tSuiteUiMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TSuiteUi> listTreeByProjectId(Long projectId) {
        final List<TSuiteUi> suiteUis = tSuiteUiMapper.findByProjectId(projectId);
        List<TSuiteUi> firstLevel = suiteUis.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(p -> {
            setChild(p, suiteUis);
        });
        return firstLevel;
    }

    private void setChild(TSuiteUi p, List<TSuiteUi> permissions) {
        List<TSuiteUi> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
        p.setChildren(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级目录
                setChild(c, permissions);
            });
        }
    }
}



