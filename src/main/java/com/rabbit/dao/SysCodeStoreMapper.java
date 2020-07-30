package com.rabbit.dao;

import com.rabbit.model.SysCodeStore;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface SysCodeStoreMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysCodeStore record);

    SysCodeStore selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SysCodeStore record);

    List<SysCodeStore> findByCodeLike(@Param("likeCode")String likeCode);


}