package com.rabbit.service.Impl;

import com.rabbit.config.RabbitConfig;
import com.rabbit.service.FileUploadService;
import com.rabbit.utils.UUIDUtil;
import com.rabbit.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Async
    @Override
    public void upload(String filename, InputStream data) {
        String fullPath = RabbitConfig.profile + filename;
        log.info(fullPath);
        FileUtils.writeInputStream(fullPath, data);
        log.info("-----------上传成功-----------");
    }

    @Override
    public String uploadFile(MultipartFile file, HttpServletRequest request,String pathName) {
        if (file == null) {
            throw new IllegalArgumentException("文件不能为空");
        }

        String newFileName = UUIDUtil.getUUID() + "." + StringUtils.unqualify(file.getOriginalFilename());
        try {
//            file.transferTo(new File(new File(uploadImgPath).getAbsolutePath() + File.separator + newFileName));
            file.transferTo(new File(new File(pathName).getAbsolutePath() + File.separator + newFileName));
        } catch (IOException e) {
            log.error("transfer err", e);
            throw new IllegalArgumentException(e.getMessage());
        }

        String downloadURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + newFileName;
        return downloadURL;
    }
}
