package com.rabbit.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.rabbit.model.TTestInterface;

public interface TTestInterfaceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestInterface record);

    int insertSelective(TTestInterface record);

    TTestInterface selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestInterface record);

    int updateByPrimaryKey(TTestInterface record);

    List<TTestInterface> findByAll(TTestInterface tTestInterface);

    List<TTestInterface> findByProjectId(@Param("projectId")Long projectId);

    List<TTestInterface> findByNameAndProjectId(@Param("name")String name,@Param("projectId")Long projectId);

    List<TTestInterface> findByNameAndProjectIdAndIdNot(@Param("name")String name,@Param("projectId")Long projectId,@Param("notId")Long notId);
}