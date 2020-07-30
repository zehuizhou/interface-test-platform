package com.rabbit.utils.file;

import com.rabbit.common.advice.Log;
import com.rabbit.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Date;

/**
 * 文件上传工具类
 */
@Slf4j
public class FileUploadUtils {

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException {
        try {
            return upload(RabbitConfig.profile + file.getOriginalFilename(), file);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }


    /**
     * 根据文件路径上传
     *
     * @param pathName 文件全名
     * @param file     上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String pathName, MultipartFile file)
            throws RuntimeException, IOException {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new RuntimeException("文件长度不能超过" + FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        File desc = getAbsoluteFile(pathName);
        file.transferTo(desc);
        return pathName;
    }


    /**
     * 通过文件名获取文件对象
     *
     * @return
     * @throws IOException
     */
    public static File getAbsoluteFile(String pathname) throws IOException {
        File desc = new File(pathname);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }

    /**
     * @description ：上传文件方式:由Spring转到java
     * @author : luoyunlin
     * @date : 2019/9/22 13:57
     */
    public static File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 用当前时间作为文件名，防止生成的临时文件重复
        try {
            File file = File.createTempFile(System.currentTimeMillis() + "", prefix, new File(RabbitConfig.profile + "temp"));
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getEncodeFileName(HttpServletRequest request, String filename) throws
            UnsupportedEncodingException {
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        if (userAgent.contains("msie") || userAgent.contains("trident/7.0") || userAgent.contains("edge")) {
            return URLEncoder.encode(filename, "UTF-8");
        } else if (userAgent.contains("mozilla") || userAgent.contains("chrome")) {
            return new String(filename.getBytes(), "ISO-8859-1");
        } else {
            return URLEncoder.encode(filename, "UTF-8");
        }
    }

}
