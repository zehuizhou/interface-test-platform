package com.rabbit.dao;

import com.rabbit.model.Client;import org.apache.ibatis.annotations.Param;import java.util.Collection;import java.util.List;

public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);

    List<Client> findAll();

    List<Client> findByProjectId(@Param("projectId") Long projectId);

    List<Client> findByAll(Client client);

    Client findById(@Param("id") Long id);

    List<Client> findByName(@Param("name")String name);

    List<Client> findByRemarkAndIdNot(@Param("remark")String remark,@Param("notId")Long notId);

    List<Client> findByIdIn(@Param("idCollection") Collection<Integer> idCollection);

    List<Client> findByStatus(@Param("status") Integer status);

    List<Client> selectByClientIpAndClientPort(@Param("clientIp") String clientIp, @Param("clientPort") Integer clientPort);

    int updateByClientIpAndClientPort(@Param("updated") Client updated, @Param("clientIp") String clientIp, @Param("clientPort") Integer clientPort);

}