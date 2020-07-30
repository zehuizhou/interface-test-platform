package com.rabbit.dao;
import com.rabbit.dto.TestsuiteUiDto;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.rabbit.model.TTestsuiteUi;
import org.apache.ibatis.annotations.Select;

public interface TTestsuiteUiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestsuiteUi record);

    int insertSelective(TTestsuiteUi record);

    TTestsuiteUi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestsuiteUi record);

    int updateByPrimaryKey(TTestsuiteUi record);

    List<TTestsuiteUi> findByAll(TTestsuiteUi tTestsuiteUi);

    List<TTestsuiteUi> selectByNameAndProjectId(@Param("name")String name,@Param("projectId")Long projectId);

    List<TTestsuiteUi> selectByNameAndProjectIdAndIdNot(@Param("name")String name,@Param("projectId")Long projectId,@Param("notId")Long notId);

    List<TTestsuiteUi> findByProjectId(@Param("projectId")Long projectId);

    @Select("SELECT p.job_id,p.is_valid,t.* FROM t_testsuite_ui t ,t_plan_suite_ui p WHERE p.suite_id = t.id AND  p.job_id = #{jobId}")
    List<TestsuiteUiDto> findDtoByJobId(Long jobId);
}