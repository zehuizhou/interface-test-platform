package com.rabbit.service.Impl;

import com.rabbit.dao.TStepApiDtoMapper;
import com.rabbit.dto.StepApiDto;
import com.rabbit.model.TTestcaseApi;
import com.rabbit.service.TFileInfoService;
import com.rabbit.service.TTestcaseApiService;
import com.rabbit.utils.BeanUtils;
import com.rabbit.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.model.TStepApi;
import com.rabbit.dao.TStepApiMapper;
import com.rabbit.service.TStepApiService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TStepApiServiceImpl implements TStepApiService {

    @Resource
    private TStepApiMapper tStepApiMapper;
    @Resource
    private TTestcaseApiService testcaseApiService;
    @Autowired
    private TStepApiDtoMapper tStepApiDtoMapper;
    @Autowired
    private TFileInfoService fileInfoService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        fileInfoService.deleteBySourceTypeAndSourceId(4, id);
        return tStepApiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TStepApi record) {
        return tStepApiMapper.insert(record);
    }

    @Override
    public int insertSelective(TStepApi record) {
        return tStepApiMapper.insertSelective(record);
    }

    @Override
    public TStepApi selectByPrimaryKey(Long id) {
        return tStepApiMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TStepApi record) {
        return tStepApiMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TStepApi record) {
        return tStepApiMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public void insertApis(List<TStepApi> stepApiList) {
        if (stepApiList.size() > 0) {
            TStepApi stepApi1 = stepApiList.get(0);
            TTestcaseApi testcaseApi = testcaseApiService.selectByPrimaryKey(stepApi1.getTestcaseId());
            if (testcaseApi == null) {
                throw new IllegalArgumentException("该用例已被删除");
            }
            Integer maxSortByTestcaseId = tStepApiMapper.findMaxSortByTestcaseId(stepApi1.getTestcaseId());
            int maxSort = 0;
            if (maxSortByTestcaseId != null) {
                maxSort = maxSortByTestcaseId;
            }
            for (TStepApi stepApi : stepApiList) {
                maxSort = maxSort + 1;
                stepApi.setType(1);
                stepApi.setSort(maxSort);
                insertSelective(stepApi);
            }
        }
    }

    @Override
    public List<StepApiDto> findDtoByTestcaseId(Long testcaseId) {
        return tStepApiDtoMapper.findDtoByTestcaseId(testcaseId);
    }

    @Override
    public void saveSteps(List<StepApiDto> testSteps) {
        if (testSteps.size() > 0) {
            int sort = 1;
            for (StepApiDto stepApiDto : testSteps) {
                stepApiDto.setSort(sort);
                sort = sort + 1;
                if (stepApiDto.getId() == null) {
                    stepApiDto.setUpdateBy(UserUtil.getLoginUser().getUsername());
                    stepApiDto.setCreateBy(UserUtil.getLoginUser().getUsername());
                    tStepApiMapper.insertSelective(stepApiDto);
                } else {
                    stepApiDto.setUpdateBy(UserUtil.getLoginUser().getUsername());
                    tStepApiMapper.updateByPrimaryKey(stepApiDto);
                }
            }
        }
    }

    @Override
    public List<TStepApi> findByTestcaseId(Long testcaseId) {
        return tStepApiMapper.findByTestcaseId(testcaseId);
    }

    @Override
    @Transactional
    public void copyStep(TStepApi testStep) {
        List<TStepApi> byTestcaseId = tStepApiMapper.findByTestcaseId(testStep.getTestcaseId());
        List<StepApiDto> newSteps = new ArrayList<>();
        if (byTestcaseId != null) {
            for (TStepApi stepApi : byTestcaseId) {
                StepApiDto stepApiDto = new StepApiDto();
                BeanUtils.copyBeanProp(stepApiDto, stepApi);
                newSteps.add(stepApiDto);
                if (testStep.getId().equals(stepApi.getId())) {
                    StepApiDto stepApiDto1 = new StepApiDto();
                    BeanUtils.copyBeanProp(stepApiDto1, stepApi);
                    stepApiDto1.setId(null);
                    newSteps.add(stepApiDto1);
                }
            }
        }
        saveSteps(newSteps);
    }
}












