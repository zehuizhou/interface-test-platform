package com.rabbit.dao;

import com.rabbit.model.TFlag;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface TFlagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TFlag record);

    int insertSelective(TFlag record);

    TFlag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TFlag record);

    int updateByPrimaryKey(TFlag record);

    List<TFlag> findByAll(TFlag tFlag);

    List<TFlag> findByProjectIdAndType(@Param("projectId") Long projectId, @Param("type") Integer type);

    List<TFlag> findByProjectId(@Param("projectId") Long projectId);


    List<TFlag> findByNameAndTypeAndProjectId(@Param("name")String name,@Param("type")Integer type,@Param("projectId")Long projectId);


    List<TFlag> findByNameAndTypeAndProjectIdAndIdNot(@Param("name")String name,@Param("type")Integer type,@Param("projectId")Long projectId,@Param("notId")Long notId);


}