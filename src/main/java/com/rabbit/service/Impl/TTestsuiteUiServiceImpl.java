package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dao.TPlanSuiteUiMapper;
import com.rabbit.dao.TSuiteCaseUiMapper;
import com.rabbit.dao.TTestsuiteUiMapper;
import com.rabbit.dto.JobDto;
import com.rabbit.dto.TestsuiteUiDto;
import com.rabbit.model.TPlanSuiteUi;
import com.rabbit.model.TSuiteCaseUi;
import com.rabbit.model.TTestsuiteUi;
import com.rabbit.service.TTestsuiteUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TTestsuiteUiServiceImpl implements TTestsuiteUiService {

    @Resource
    private TTestsuiteUiMapper tTestsuiteUiMapper;

    @Autowired
    private TSuiteCaseUiMapper suiteCaseUiMapper;

    @Autowired
    private TPlanSuiteUiMapper planSuiteUiMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        suiteCaseUiMapper.deleteBySuiteId(id);
        return tTestsuiteUiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TTestsuiteUi record) {
        return tTestsuiteUiMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestsuiteUi record) {
        return tTestsuiteUiMapper.insertSelective(record);
    }

    @Override
    public TTestsuiteUi selectByPrimaryKey(Long id) {
        return tTestsuiteUiMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TTestsuiteUi record) {
        return tTestsuiteUiMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestsuiteUi record) {
        return tTestsuiteUiMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TTestsuiteUi> findByAllwithPage(int page, int pageSize, TTestsuiteUi tTestsuiteUi) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tTestsuiteUiMapper.findByAll(tTestsuiteUi));
    }

    @Override
    public List<TTestsuiteUi> selectByNameAndProjectId(String name, Long projectId) {
        return tTestsuiteUiMapper.selectByNameAndProjectId(name, projectId);
    }

    @Override
    public List<TTestsuiteUi> selectByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId) {
        return tTestsuiteUiMapper.selectByNameAndProjectIdAndIdNot(name, projectId, notId);
    }

    @Override
    public List<TTestsuiteUi> findByProjectId(Long projectId) {
        return tTestsuiteUiMapper.findByProjectId(projectId);
    }

    @Override
    public void updateCaseSort(List<TSuiteCaseUi> suiteCaseUis) {
        int sort = 0;
        for (TSuiteCaseUi tSuiteCaseUi : suiteCaseUis) {
            tSuiteCaseUi.setSort(sort);
            suiteCaseUiMapper.updateByPrimaryKeySelective(tSuiteCaseUi);
            sort++;
        }
    }

    @Override
    public void addCaseToSuite(List<TSuiteCaseUi> suiteCaseUis) {
        Integer maxSortBySuiteId = suiteCaseUiMapper.findMaxSortBySuiteId(suiteCaseUis.get(0).getSuiteId());
        int sort = 0;
        if (maxSortBySuiteId != null) {
            sort = maxSortBySuiteId + 1;
        }
        for (TSuiteCaseUi tSuiteCaseUi : suiteCaseUis) {
            tSuiteCaseUi.setSort(sort);
            suiteCaseUiMapper.insertSelective(tSuiteCaseUi);
            sort++;
        }
    }

    @Override
    public int deleteSuiteCaseById(Long id) {
        return suiteCaseUiMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void addUiSuiteToPlan(JobDto jobDto) {
        List<TestsuiteUiDto> suiteList = jobDto.getSuiteList();
        if (suiteList.size() > 0) {
            List<TPlanSuiteUi> dataSuiteList = planSuiteUiMapper.findByJobId(jobDto.getJobId());
            for (TPlanSuiteUi planSuiteUi : dataSuiteList) {
                boolean flag = false;
                for (TestsuiteUiDto testsuiteUiDto : suiteList) {
                    if (testsuiteUiDto.getSuiteCaseId() != null && planSuiteUi.getId().equals(testsuiteUiDto.getSuiteCaseId())) {
                        //如果存在步骤id不为空且在数据库存在，就不需要删除
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
//                    删除
                    planSuiteUiMapper.deleteByPrimaryKey(planSuiteUi.getId());
                }
            }
            int sort = 0;
            for (TestsuiteUiDto testsuite : suiteList) {
                TPlanSuiteUi planSuiteUi = new TPlanSuiteUi();
                planSuiteUi.setSort(sort);
                planSuiteUi.setJobId(jobDto.getJobId());
                planSuiteUi.setSuiteId(testsuite.getId());
                planSuiteUi.setIsValid(testsuite.getIsValid());
                sort = sort + 1;
                if (testsuite.getSuiteCaseId() == null) {
                    planSuiteUiMapper.insertSelective(planSuiteUi);
                } else {
                    planSuiteUi.setId(testsuite.getSuiteCaseId());
                    planSuiteUiMapper.updateByPrimaryKey(planSuiteUi);
                }
            }
        }
    }

    @Override
    public List<TestsuiteUiDto> findDtoByJobId(Long jobId) {
        return tTestsuiteUiMapper.findDtoByJobId(jobId);
    }
}

