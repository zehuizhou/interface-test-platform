package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.utils.AesEncryptUtil;
import com.rabbit.utils.DbUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TTestDatabeseMapper;
import com.rabbit.model.TTestDatabese;
import com.rabbit.service.TTestDatabeseService;

import java.sql.Connection;
import java.util.List;

@Slf4j
@Service
public class TTestDatabeseServiceImpl implements TTestDatabeseService {

    @Resource
    private TTestDatabeseMapper tTestDatabeseMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tTestDatabeseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TTestDatabese record) {
        return tTestDatabeseMapper.insert(record);
    }

    @Override
    public int insertSelective(TTestDatabese record) {
        if (!checkDatabase(record)) {
            throw new IllegalArgumentException("数据库连接失败，请检查数据库信息");
        }
        return tTestDatabeseMapper.insertSelective(record);
    }

    @Override
    public TTestDatabese selectByPrimaryKey(Integer id) {
        return tTestDatabeseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TTestDatabese record) {
        return tTestDatabeseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TTestDatabese record) {
        if (!checkDatabase(record)) {
            throw new IllegalArgumentException("数据库连接失败，请检查数据库信息");
        }
        return tTestDatabeseMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TTestDatabese> findByAllwithPage(int page, int pageSize, TTestDatabese tTestDatabese) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tTestDatabeseMapper.findByAll(tTestDatabese));
    }

    @Override
    public List<TTestDatabese> findByProjectId(Long projectId) {
        return tTestDatabeseMapper.findByProjectId(projectId);
    }

    @Override
    public List<TTestDatabese> findByNameAndProjectId(String name, Long projectId) {
        return tTestDatabeseMapper.findByNameAndProjectId(name, projectId);
    }

    @Override
    public List<TTestDatabese> findByNameAndProjectIdAndIdNot(String name, Long projectId, Integer notId) {
        return tTestDatabeseMapper.findByNameAndProjectIdAndIdNot(name, projectId, notId);
    }

    private boolean checkDatabase(TTestDatabese record) {
        String password = record.getPassword();
        try {
            password = AesEncryptUtil.desEncrypt(password).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection connection = DbUtil.createConnection(record.getConnectUrl(), record.getUsername(), password, 1);
        return DbUtil.testConnection(connection);
    }
}
