package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {

    int deleteByPrimaryKey(Long id);

    Client selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Client record);

    /**
     * 查询带page
     */
    PageInfo<Client> findByAllwithPage(int page, int pageSize, Client client);

    int updateByPrimaryKeySelective(Client record);

    int insertSelective(Client record);

    List<Client> findAll();

    Client findById(Long id);

    List<Client> findByProjectId(Long projectId);

    List<Client> findByName(String name);

    int insert(Client record);

    Map<String, Client> asynOnlineStatus();

    void registCliet(Client record);

    List<Client> findByRemarkAndIdNot(String remark, Long notId);

}


















