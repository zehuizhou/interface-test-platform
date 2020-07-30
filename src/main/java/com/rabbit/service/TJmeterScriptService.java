package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.Result;
import com.rabbit.model.TJmeterScript;
import net.lingala.zip4j.core.ZipFile;

import java.io.File;
import java.util.List;

public interface TJmeterScriptService {


    int deleteByPrimaryKey(Long id);

    int insert(TJmeterScript record);

    int insertSelective(TJmeterScript record);

    TJmeterScript selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TJmeterScript record);

    int updateByPrimaryKey(TJmeterScript record);

    PageInfo<TJmeterScript> findByAllwithPage(int page, int pageSize, TJmeterScript tJmeterScript);

    List<TJmeterScript> findByProjectId(Long projectId);

    List<TJmeterScript> findByNameAndProjectId(String name, Long projectId);

    List<TJmeterScript> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId);

    int updateByPrimaryKeySelectiveCustomer(TJmeterScript record);

    String uploadJmeterScript(ZipFile zipFile) throws Exception;

    Result isJmeterZip(ZipFile zipFile);

    int saveCsv(Long id, String csvName, File file);

    int delJmeterCsv(Long id, String csvName);

    int coverCsv(TJmeterScript jmeterScript, String orgCsvName, String newCsvName, File file);
}

