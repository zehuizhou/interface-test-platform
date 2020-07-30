package com.rabbit.service.Impl;

import com.rabbit.dto.JobDto;
import com.rabbit.dto.TPlanSuiteApiDto;
import com.rabbit.dto.TestsuiteUiDto;
import com.rabbit.model.TPlanSuiteUi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.model.TPlanSuiteApi;
import com.rabbit.dao.TPlanSuiteApiMapper;
import com.rabbit.service.TPlanSuiteApiService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TPlanSuiteApiServiceImpl implements TPlanSuiteApiService {

    @Resource
    private TPlanSuiteApiMapper tPlanSuiteApiMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tPlanSuiteApiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TPlanSuiteApi record) {
        return tPlanSuiteApiMapper.insert(record);
    }

    @Override
    public int insertSelective(TPlanSuiteApi record) {
        return tPlanSuiteApiMapper.insertSelective(record);
    }

    @Override
    public TPlanSuiteApi selectByPrimaryKey(Long id) {
        return tPlanSuiteApiMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TPlanSuiteApi record) {
        return tPlanSuiteApiMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TPlanSuiteApi record) {
        return tPlanSuiteApiMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByJobId(Long jobId) {
        return tPlanSuiteApiMapper.deleteByJobId(jobId);
    }

    @Override
    @Transactional
    public void addApiSuiteToPlan(JobDto jobDto) {
        List<TPlanSuiteApiDto> suiteList = jobDto.getApiSuiteList();
        log.info("获取的测试集suiteList：{}", suiteList);
        if (suiteList != null && suiteList.size() > 0) {
            List<TPlanSuiteApi> dataSuiteList = tPlanSuiteApiMapper.findByJobId(jobDto.getJobId());
            for (TPlanSuiteApi planSuiteApi : dataSuiteList) {
                boolean flag = false;
                for (TPlanSuiteApiDto testsuiteUiDto : suiteList) {
                    if (testsuiteUiDto.getId() != null && planSuiteApi.getId().equals(testsuiteUiDto.getId())) {
                        //如果存在步骤id不为空且在数据库存在，就不需要删除
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
//                    删除
                    tPlanSuiteApiMapper.deleteByPrimaryKey(planSuiteApi.getId());
                }
            }
            int sort = 0;
            for (TPlanSuiteApiDto testsuite : suiteList) {
                testsuite.setSort(sort);
                testsuite.setJobId(jobDto.getJobId());
                sort = sort + 1;
                if (testsuite.getId() == null) {
                    tPlanSuiteApiMapper.insertSelective(testsuite);
                } else {
                    tPlanSuiteApiMapper.updateByPrimaryKey(testsuite);
                }
            }
        }
    }
}

