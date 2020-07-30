package com.rabbit.myTest;

import com.rabbit.utils.ZipUtils;
import com.rabbit.utils.file.FileUtils;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;

import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class ZipTest {

    @Test
    public void file() {
        String s = "result.csv,";
        String[] split = s.split(",");
        System.out.println(split.length);
    }

    @Test
    public void createZip() throws Exception {

        ZipUtils.renameFile("D:\\浏览器下载路径\\jmeterTemplate.zip","jmeterTemplate/data/result1111.csv","jmeterTemplate/data/中国参数.csv");

    }

    @Test
    public void unzip() {
        try {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            ZipFile zipFile = new ZipFile("D:\\浏览器下载路径\\jmeterTemplate.zip");
//            zipFile.extractAll(System.getProperty("user.dir") + "/profile/jmeter/script/" + uuid + "/");
            zipFile.removeFile("jmeterTemplate/data/");
            // 如果解压需要密码
//            if(zipFile.isEncrypted()) {
//                zipFile.setPassword("111");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractFilezip() {
        try {
            ZipFile zipFile = new ZipFile("D:\\浏览器下载路径\\test.zip");
            zipFile.setFileNameCharset("GBK");
            List<FileHeader> fileHeaders = zipFile.getFileHeaders();
            String rootPath = fileHeaders.get(0).getFileName();

            for (int i = 0; i < fileHeaders.size(); i++) {
                FileHeader fileHeader = fileHeaders.get(i);
                String fileName = fileHeader.getFileName();
                boolean isDirectory = fileHeader.isDirectory();
                System.out.println(rootPath + "===" + fileName + "====" + isDirectory);
            }
//            zipFile.extractAll("E:\\java_space\\vue\\rabbit-platform-server-new\\profile\\2019\\09\\19");
            // 如果解压需要密码
//            if(zipFile.isEncrypted()) {
//                zipFile.setPassword("111");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void extractWithZipInputStream(File zipFile) throws IOException {
//        LocalFileHeader localFileHeader;
//        int readLen;
//        byte[] readBuffer = new byte[4096];
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//
//        try (FileInputStream fileInputStream = new FileInputStream(zipFile);
//             ZipInputStream zipInputStream = new ZipInputStream(fileInputStream)) {
//            while ((localFileHeader = zipInputStream.getNextEntry()) != null) {
//                //像执行文件解压
//
//                File extractedFile = new File(System.getProperty("user.dir") + "/profile/jmeter/script/" + uuid + "/" + localFileHeader.getFileName());
//                if (!extractedFile.getParentFile().exists()) {
//                    extractedFile.getParentFile().mkdirs();
//                }
//                if (!localFileHeader.isDirectory()) {
//                    if (!extractedFile.exists()) {
//                        extractedFile.createNewFile();
//                    }
//                    try (OutputStream outputStream = new FileOutputStream(extractedFile)) {
//                        while ((readLen = zipInputStream.read(readBuffer)) != -1) {
//                            outputStream.write(readBuffer, 0, readLen);
//                        }
//                    }
//                } else {
//                    if (!extractedFile.exists()) {
//                        extractedFile.mkdirs();
//                    }
//                }
//            }
//        }
//    }
}
