package com.rabbit.service.Impl;

import com.rabbit.dto.StepUiNewDto;
import com.rabbit.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TStepUiNewMapper;
import com.rabbit.model.TStepUiNew;
import com.rabbit.service.TStepUiNewService;

import java.util.List;

@Slf4j
@Service
public class TStepUiNewServiceImpl implements TStepUiNewService {

    @Resource
    private TStepUiNewMapper tStepUiNewMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tStepUiNewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TStepUiNew record) {
        return tStepUiNewMapper.insert(record);
    }

    @Override
    public int insertSelective(TStepUiNew record) {
        return tStepUiNewMapper.insertSelective(record);
    }

    @Override
    public TStepUiNew selectByPrimaryKey(Long id) {
        return tStepUiNewMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TStepUiNew record) {
        return tStepUiNewMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TStepUiNew record) {
        return tStepUiNewMapper.updateByPrimaryKey(record);
    }

    @Override
    public String savaStep(List<TStepUiNew> uiTestStepList) {
        if (uiTestStepList.size() > 0) {
            List<TStepUiNew> byTestcaseId = tStepUiNewMapper.findByTestcaseId(uiTestStepList.get(0).getTestcaseId());
            for (TStepUiNew stepUi : byTestcaseId) {
                boolean flag = false;
                for (TStepUiNew uiTestStep : uiTestStepList) {
                    if (uiTestStep.getId() != null && stepUi.getId().equals(uiTestStep.getId())) {
                        //如果存在步骤id不为空且在数据库存在，就不需要删除
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    tStepUiNewMapper.deleteByPrimaryKey(stepUi.getId());
                }
            }
            Long sort = 0L;
            for (TStepUiNew uiTestStep : uiTestStepList) {
                uiTestStep.setSort(sort);
                sort = sort + 1;
                if (uiTestStep.getId() == null) {
                    uiTestStep.setUpdateBy(UserUtil.getLoginUser().getUsername());
                    uiTestStep.setCreateBy(UserUtil.getLoginUser().getUsername());
                    tStepUiNewMapper.insertSelective(uiTestStep);
                } else {
                    uiTestStep.setUpdateBy(UserUtil.getLoginUser().getUsername());
                    tStepUiNewMapper.updateByPrimaryKey(uiTestStep);
                }
            }
        }
        return "保存用例步骤成功";
    }

    @Override
    public List<StepUiNewDto> findDtoByTestcaseId(Long testcaseId) {
        return tStepUiNewMapper.findDtoByTestcaseId(testcaseId);
    }
}

