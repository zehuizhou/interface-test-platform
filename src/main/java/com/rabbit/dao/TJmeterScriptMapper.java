package com.rabbit.dao;

import com.rabbit.model.TJmeterScript;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface TJmeterScriptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TJmeterScript record);

    int insertSelective(TJmeterScript record);

    TJmeterScript selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TJmeterScript record);

    int updateByPrimaryKeySelectiveCustomer(TJmeterScript record);

    int updateByPrimaryKey(TJmeterScript record);

    List<TJmeterScript> findByAll(TJmeterScript tJmeterScript);

    List<TJmeterScript> findByProjectId(@Param("projectId") Long projectId);

    List<TJmeterScript> findByNameAndProjectId(@Param("name") String name, @Param("projectId") Long projectId);

    List<TJmeterScript> findByNameAndProjectIdAndIdNot(@Param("name") String name, @Param("projectId") Long projectId, @Param("notId") Long notId);

    List<TJmeterScript> findByName(@Param("name")String name);

    TJmeterScript findByIdAndProjectId(@Param("id")Long id,@Param("projectId")Long projectId);

    int updateDataPathById(@Param("updatedDataPath")String updatedDataPath,@Param("id")Long id);

}