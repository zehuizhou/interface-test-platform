package com.rabbit.dao;

import com.rabbit.dto.StepUiNewDto;
import com.rabbit.model.TStepUiNew;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TStepUiNewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TStepUiNew record);

    int insertSelective(TStepUiNew record);

    TStepUiNew selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TStepUiNew record);

    int updateByPrimaryKey(TStepUiNew record);

    List<TStepUiNew> findByTestcaseId(@Param("testcaseId") Long testcaseId);

    int deleteByTestcaseId(@Param("testcaseId") Long testcaseId);

    @Select("SELECT t1.by_type,t1.by_value,t.* FROM t_step_ui_new t LEFT JOIN t_page_element t1 ON t.element_id = t1.id WHERE  t.testcase_id = #{testcaseId} order by t.sort")
    List<StepUiNewDto> findDtoByTestcaseId(@Param("testcaseId") Long testcaseId);

    int insertList(@Param("list") List<TStepUiNew> list);

    List<TStepUiNew> findByActionTypeAndElementId(@Param("actionType")String actionType,@Param("elementId")Long elementId);




}