package com.rabbit.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.rabbit.model.TTestDatabese;

public interface TTestDatabeseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTestDatabese record);

    int insertSelective(TTestDatabese record);

    TTestDatabese selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTestDatabese record);

    int updateByPrimaryKey(TTestDatabese record);

    List<TTestDatabese> findByAll(TTestDatabese tTestDatabese);

    List<TTestDatabese> findByProjectId(@Param("projectId")Long projectId);

    List<TTestDatabese> findByNameAndProjectId(@Param("name")String name,@Param("projectId")Long projectId);

    List<TTestDatabese> findByNameAndProjectIdAndIdNot(@Param("name")String name,@Param("projectId")Long projectId,@Param("notId")Integer notId);

}