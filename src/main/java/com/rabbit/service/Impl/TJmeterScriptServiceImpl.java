package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.config.RabbitConfig;
import com.rabbit.model.Result;
import com.rabbit.utils.UUIDUtil;
import com.rabbit.utils.UserUtil;
import com.rabbit.utils.ZipUtils;
import com.rabbit.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TJmeterScriptMapper;
import com.rabbit.model.TJmeterScript;
import com.rabbit.service.TJmeterScriptService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TJmeterScriptServiceImpl implements TJmeterScriptService {

    @Resource
    private TJmeterScriptMapper tJmeterScriptMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        TJmeterScript jmeterScript = tJmeterScriptMapper.selectByPrimaryKey(id);
        String pathName = RabbitConfig.jmeterfile + jmeterScript.getProjectId() + "/script/" + jmeterScript.getScriptPath();
        File file = new File(pathName);
        if (file.exists() & file.isFile()) {
            file.delete();
        }
        return tJmeterScriptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TJmeterScript record) {
        return tJmeterScriptMapper.insert(record);
    }

    @Override
    public int insertSelective(TJmeterScript record) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String newScriptPathInZip = uuid + "/script/" + record.getName() + ".jmx";
        String zipPathName = RabbitConfig.jmeterfile + record.getProjectId() + "/script/" + uuid + ".zip";
        try {
            ZipUtils.addStingToZip(zipPathName, "", newScriptPathInZip);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        record.setScriptPath(uuid + ".zip");
        return tJmeterScriptMapper.insertSelective(record);
    }

    @Override
    public TJmeterScript selectByPrimaryKey(Long id) {
        return tJmeterScriptMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TJmeterScript record) {
        return tJmeterScriptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TJmeterScript record) {
        return tJmeterScriptMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TJmeterScript> findByAllwithPage(int page, int pageSize, TJmeterScript tJmeterScript) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tJmeterScriptMapper.findByAll(tJmeterScript));
    }

    @Override
    public List<TJmeterScript> findByProjectId(Long projectId) {
        return tJmeterScriptMapper.findByProjectId(projectId);
    }

    @Override
    public List<TJmeterScript> findByNameAndProjectId(String name, Long projectId) {
        return tJmeterScriptMapper.findByNameAndProjectId(name, projectId);
    }

    @Override
    public List<TJmeterScript> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId) {
        return tJmeterScriptMapper.findByNameAndProjectIdAndIdNot(name, projectId, notId);
    }

    @Override
    public int updateByPrimaryKeySelectiveCustomer(TJmeterScript record) {
        TJmeterScript jmeterScript = tJmeterScriptMapper.selectByPrimaryKey(record.getId());
        String name = jmeterScript.getName();
        String scriptPath = jmeterScript.getScriptPath();
        String newScriptPathInZip = scriptPath.replace(".zip", "") + "/script/" + record.getName() + ".jmx";
        String zipPathName = RabbitConfig.jmeterfile + jmeterScript.getProjectId() + "/script/" + scriptPath;
        String oldScriptPathInZip = scriptPath.replace(".zip", "") + "/script/" + name + ".jmx";
        if (!name.equals(record.getName())) {
            //修改文件的jmx名
            try {
                ZipUtils.renameFile(zipPathName, oldScriptPathInZip, newScriptPathInZip);
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        String scriptContent = record.getScriptContent();
        try {
            ZipUtils.addStingToZip(zipPathName, scriptContent, newScriptPathInZip);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return tJmeterScriptMapper.updateByPrimaryKeySelectiveCustomer(record);
    }

    @Override
    public String uploadJmeterScript(ZipFile zipFile) throws Exception {
        List<FileHeader> fileHeaders;
        Long projectId = UserUtil.getLoginUser().getProjectId();
        List<FileHeader> scripts = new ArrayList();
        List<FileHeader> datas = new ArrayList();

        zipFile.setFileNameCharset("GBK");
        fileHeaders = zipFile.getFileHeaders();

        String oldRootDir = fileHeaders.get(0).getFileName();
        for (int i = 0; i < fileHeaders.size(); i++) {
            FileHeader fileHeader = fileHeaders.get(i);
            String fileName = fileHeader.getFileName();
            if (fileName.startsWith(oldRootDir + "script/") & fileName.toLowerCase().endsWith(".jmx")) {
                scripts.add(fileHeader);
            }
            if (fileName.startsWith(oldRootDir + "data/") & fileName.toLowerCase().endsWith(".csv")) {
                datas.add(fileHeader);
            }
        }

        for (FileHeader scriptsFileHeader : scripts) {
            //遍历文件中的jmx脚本文件
            String jmxName = scriptsFileHeader.getFileName().replace(oldRootDir + "script/", "").replace(".jmx", "");
            List<TJmeterScript> byName = tJmeterScriptMapper.findByName(jmxName);
            if (byName.size() > 0) {
                //修改
                TJmeterScript jmeterScript = byName.get(0);
                String zipPathName = RabbitConfig.jmeterfile + projectId + "/script/" + jmeterScript.getScriptPath();

                File targetFile = new File(zipPathName);
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                ZipFile scripZip = new ZipFile(zipPathName);

                ZipInputStream inputStream = zipFile.getInputStream(scriptsFileHeader);
                String newRootDir = jmeterScript.getScriptPath().replace(".zip", "") + "/";
                String inZipJmxName = newRootDir + "script/" + jmxName + ".jmx";
                ZipUtils.addInputStreamToZip(scripZip, inputStream, inZipJmxName);
                //解压文件
//                scripZip.extractAll(RabbitConfig.jmeterfile + projectId);
                //获取zip文件中的文本
                String scriptText = FileUtils.getText(zipFile.getInputStream(scriptsFileHeader));
                String jmeterDataPath = ZipUtils.getJmeterDataPath(zipFile, scripZip, datas, oldRootDir, newRootDir);
                jmeterScript.setDataPath(jmeterDataPath);
                jmeterScript.setName(jmxName);
                jmeterScript.setScriptContent(scriptText);
                jmeterScript.setUpdateBy(UserUtil.getLoginUser().getUsername());
                tJmeterScriptMapper.updateByPrimaryKeySelective(jmeterScript);
                inputStream.close();
            } else {
                //新增
                String uuid = UUIDUtil.getUUID();
                String jmxPathName = RabbitConfig.jmeterfile + projectId + "/script/" + uuid + ".zip";
                String newRootDir = uuid + "/";
                String inZipJmxName = scriptsFileHeader.getFileName().replace(oldRootDir, newRootDir);

                File targetFile = new File(jmxPathName);
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                ZipFile scripZip = new ZipFile(jmxPathName);
                ZipInputStream inputStream = zipFile.getInputStream(scriptsFileHeader);

                ZipUtils.addInputStreamToZip(scripZip, inputStream, inZipJmxName);
                TJmeterScript jmeterScript = new TJmeterScript();
                String scriptText = FileUtils.getText(zipFile.getInputStream(scriptsFileHeader));
                String jmeterDataPath = ZipUtils.getJmeterDataPath(zipFile, scripZip, datas, oldRootDir, newRootDir);
                jmeterScript.setDataPath(jmeterDataPath);
                jmeterScript.setName(jmxName);
                jmeterScript.setScriptContent(scriptText);
                jmeterScript.setScriptPath(uuid + ".zip");
                jmeterScript.setProjectId(projectId);
                jmeterScript.setUpdateBy(UserUtil.getLoginUser().getUsername());
                tJmeterScriptMapper.insertSelective(jmeterScript);
                inputStream.close();
            }
        }
        return "上传成功";
    }

    @Override
    public Result isJmeterZip(ZipFile zipFile) {
        //校验是否jmeter脚本格式zip
        List<FileHeader> fileHeaders;
        try {
            zipFile.setFileNameCharset("GBK");
            fileHeaders = zipFile.getFileHeaders();
        } catch (ZipException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        String rootPath = fileHeaders.get(0).getFileName();

        boolean scriptFlag = false;
        boolean dataFlag = false;
        int jxmCnt = 0;
        boolean jmxIsExists = false;
        for (int i = 0; i < fileHeaders.size(); i++) {
            FileHeader fileHeader = fileHeaders.get(i);
            String fileName = fileHeader.getFileName();
            if ((rootPath + "script/").equals(fileName)) {
                scriptFlag = true;
            }
            if ((rootPath + "data/").equals(fileName)) {
                dataFlag = true;
            }
            if (fileName.startsWith(rootPath + "script/") & fileName.toLowerCase().endsWith(".jmx")) {
                jxmCnt = jxmCnt + 1;
                if (!jmxIsExists) {
                    String jmxName = fileName.replace(rootPath + "script/", "").replace(".jmx", "");
                    List<TJmeterScript> byName = tJmeterScriptMapper.findByName(jmxName);
                    if (byName.size() > 0) {
                        jmxIsExists = true;
                    }
                }
            }
        }

        if (!scriptFlag) {
            return new Result(false, "导入zip没有script文件夹");
        } else if (!dataFlag) {
            return new Result(false, "导入zip没有data文件夹");
        } else if (jxmCnt == 0) {
            return new Result(false, "导入data文件夹没有jmx文件");
        } else if (jmxIsExists) {
            return new Result(false, "存在同名jmx脚本");
        } else {
            return new Result(true, "");
        }
    }

    @Override
    public int saveCsv(Long id, String csvName, File file) {
        TJmeterScript jmeterScript = tJmeterScriptMapper.selectByPrimaryKey(id);
        String scriptPath = jmeterScript.getScriptPath();
        String dataPath = jmeterScript.getDataPath();
        String zipPathName = RabbitConfig.jmeterfile + jmeterScript.getProjectId() + "/script/" + jmeterScript.getScriptPath();
        String csvPathInZip = scriptPath.replace(".zip", "") + "/data/" + csvName;

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            ZipUtils.addInputStreamToZip(zipPathName, fileInputStream, csvPathInZip);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
        if (!dataPath.contains(csvName + ',')) {
            dataPath = dataPath + csvName + ',';
        }
        return tJmeterScriptMapper.updateDataPathById(dataPath, id);
    }

    @Override
    public int coverCsv(TJmeterScript jmeterScript, String orgCsvName, String newCsvName, File file) {
        String scriptPath = jmeterScript.getScriptPath();
        String dataPath = jmeterScript.getDataPath();
        String newCsvPathInZip = scriptPath.replace(".zip", "") + "/data/" + newCsvName;
        String zipPathName = RabbitConfig.jmeterfile + jmeterScript.getProjectId() + "/script/" + jmeterScript.getScriptPath();
        String oldCsvPathInZip = scriptPath.replace(".zip", "") + "/data/" + orgCsvName;

        FileInputStream fileInputStream = null;
        try {
            ZipUtils.removeFile(zipPathName, oldCsvPathInZip);
            fileInputStream = new FileInputStream(file);
            ZipUtils.addInputStreamToZip(zipPathName, fileInputStream, newCsvPathInZip);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
        dataPath = dataPath.replace(orgCsvName, newCsvName);
        return tJmeterScriptMapper.updateDataPathById(dataPath, jmeterScript.getId());
    }


    @Override
    public int delJmeterCsv(Long id, String csvName) {
        TJmeterScript jmeterScript = tJmeterScriptMapper.selectByPrimaryKey(id);
        String scriptPath = jmeterScript.getScriptPath();
        String zipPathName = RabbitConfig.jmeterfile + jmeterScript.getProjectId() + "/script/" + jmeterScript.getScriptPath();
        String dataPath = jmeterScript.getDataPath();
        String csvPathInZip = scriptPath.replace(".zip", "") + "/data/" + csvName;
        try {
            ZipUtils.removeFile(zipPathName, csvPathInZip);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        if (dataPath.contains(csvName + ',')) {
            dataPath = dataPath.replace(csvName + ',', "");
        }
        return tJmeterScriptMapper.updateDataPathById(dataPath, id);
    }
}

