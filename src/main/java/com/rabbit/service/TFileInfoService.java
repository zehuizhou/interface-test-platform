package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TFileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TFileInfoService {


    int deleteByPrimaryKey(Long id);

    int deleteBySourceTypeAndSourceId(Integer sourceType, Long sourceId);

    TFileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TFileInfo record);

    int updateByPrimaryKey(TFileInfo record);

    int insert(TFileInfo record);

    TFileInfo add(MultipartFile file, TFileInfo tFileInfo) throws IOException;

    int insertSelective(TFileInfo record);

    PageInfo<TFileInfo> findByAllwithPage(int page, int pageSize, TFileInfo tFileInfo);

    List<TFileInfo> findByAll(TFileInfo tFileInfo);

    byte[] getFileByte(int sourceType, Long sourceId, String fileName);

    String getAbsolutePath(int sourceType, Long sourceId, String fileName);
}





