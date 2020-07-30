package com.rabbit.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

public interface FileUploadService {
     void upload(String filename, InputStream data);

     String uploadFile(MultipartFile file, HttpServletRequest request,String pathName);
}
