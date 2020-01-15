package com.gmall.util;

import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * fastFDS上传工具类
 * */
public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile) {
        String file = PmsUploadUtil.class.getResource("/tracker.conf").getFile();
        String s = "";
        try {
            ClientGlobal.init(file);
            TrackerClient trackerClient = new TrackerClient();
            StorageClient storageClient = new StorageClient(null, null);
            byte[] bytes = multipartFile.getBytes();
            String filename = multipartFile.getOriginalFilename();
            String substringAfterLast = StringUtils.substringAfterLast(filename, ".");
            String[] upload_file = storageClient.upload_file(bytes, substringAfterLast, null);
            for (int i = 0; i < upload_file.length; i++) {
                if (upload_file.length==i+1){
                    s += upload_file[i];
                }else {
                    s += upload_file[i]+"/";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("http://47.110.239.179/" + s);
        return "http://47.110.239.179/" + s;
    }
}
