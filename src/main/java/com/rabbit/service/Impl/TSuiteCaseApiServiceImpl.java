package com.rabbit.service.Impl;

import com.rabbit.model.TSuiteCaseUi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TSuiteCaseApiMapper;
import com.rabbit.model.TSuiteCaseApi;
import com.rabbit.service.TSuiteCaseApiService;

import java.util.List;

@Service
public class TSuiteCaseApiServiceImpl implements TSuiteCaseApiService {

    @Resource
    private TSuiteCaseApiMapper tSuiteCaseApiMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tSuiteCaseApiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TSuiteCaseApi record) {
        return tSuiteCaseApiMapper.insert(record);
    }

    @Override
    public int insertSelective(TSuiteCaseApi record) {
        return tSuiteCaseApiMapper.insertSelective(record);
    }

    @Override
    public TSuiteCaseApi selectByPrimaryKey(Long id) {
        return tSuiteCaseApiMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TSuiteCaseApi record) {
        return tSuiteCaseApiMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TSuiteCaseApi record) {
        return tSuiteCaseApiMapper.updateByPrimaryKey(record);
    }

    @Override
    public void updateCaseSort(List<TSuiteCaseApi> tSuiteCaseApis) {
        int sort = 0;
        for (TSuiteCaseApi tSuiteCaseApi : tSuiteCaseApis) {
            tSuiteCaseApi.setSort(sort);
            updateByPrimaryKeySelective(tSuiteCaseApi);
            sort++;
        }
    }

    @Override
    public void addCaseToSuite(List<TSuiteCaseApi> tSuiteCaseApis) {
        Integer maxSortBySuiteId = tSuiteCaseApiMapper.findMaxSortBySuiteId(tSuiteCaseApis.get(0).getSuiteId());
        int sort = 0;
        if (maxSortBySuiteId != null) {
            sort = maxSortBySuiteId + 1;
        }
        for (TSuiteCaseApi tSuiteCaseUi : tSuiteCaseApis) {
            tSuiteCaseUi.setSort(sort);
            tSuiteCaseApiMapper.insertSelective(tSuiteCaseUi);
            sort++;
        }
    }
}
