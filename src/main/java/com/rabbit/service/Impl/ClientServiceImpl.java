package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.hessian.factory.config.ClientFactory;
import com.rabbit.model.Job;
import com.rabbit.utils.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.rabbit.model.Client;
import com.rabbit.dao.ClientMapper;
import com.rabbit.service.ClientService;

import java.beans.Transient;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private static Map<String, Client> onlineClient = new ConcurrentHashMap<>();

    @Resource
    private ClientMapper clientMapper;

    @Autowired
    private ClientFactory clientFactory;

    /**
     * 项目启动时，初始化在线客户端
     */
    @PostConstruct
    public void init() {
        List<Client> byStatus = clientMapper.findByStatus(1);
        if (byStatus.size() > 0) {
            for (Client client : byStatus) {
                onlineClient.put(client.getName(), client);
            }
        }
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return clientMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Client selectByPrimaryKey(Long id) {
        return clientMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Client record) {
        return clientMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Client record) {
        return clientMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Client> findByAllwithPage(int page, int pageSize, Client client) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(clientMapper.findByAll(client));
    }

    @Override
    public int insertSelective(Client record) {
        return clientMapper.insertSelective(record);
    }

    @Override
    public List<Client> findAll() {
        return clientMapper.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientMapper.findById(id);
    }

    @Override
    public List<Client> findByProjectId(Long projectId) {
        return clientMapper.findByProjectId(projectId);
    }

    @Override
    public int insert(Client record) {
        return clientMapper.insert(record);
    }

    @Transient
    @Override
    public void registCliet(Client record) {
        List<Client> clients = clientMapper.selectByClientIpAndClientPort(record.getClientIp(), record.getClientPort());
        record.setStatus(1);
        if (clients.size() == 0) {
            clientMapper.insertSelective(record);
            onlineClient.put(record.getName(), record);
        } else {
            Client client = onlineClient.get(record.getName());
            clientMapper.updateByClientIpAndClientPort(record, record.getClientIp(), record.getClientPort());
            if (client == null) {
                Client client1 = clientMapper.selectByClientIpAndClientPort(record.getClientIp(), record.getClientPort()).get(0);
                onlineClient.put(record.getName(), client1);
            }
        }
    }

    @Override
    public Map<String, Client> asynOnlineStatus() {
        log.info("同步在线客户端，还有{}个在线客户端", onlineClient.size());
        Map<String, Client> offLineClient = new HashMap();
        for (Map.Entry<String, Client> entry : onlineClient.entrySet()) {
            Client value = entry.getValue();
            try {
                clientFactory.clientTestService(value.getClientIp(), value.getClientPort()).getClientInfo();
            } catch (Exception e) {
                value.setStatus(2);
                clientMapper.updateByPrimaryKeySelective(value);
                onlineClient.remove(entry.getKey());
                offLineClient.put(entry.getKey(), entry.getValue());
                log.info("客户端离线，更改状态");
            }
        }
        return offLineClient;
    }

    @Override
    public List<Client> findByName(String name) {
        return clientMapper.findByName(name);
    }

    @Override
    public List<Client> findByRemarkAndIdNot(String remark, Long notId) {
        return clientMapper.findByRemarkAndIdNot(remark, notId);
    }
}


















