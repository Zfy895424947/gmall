package com.gmall.manager;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GmallManagerWebApplicationTests {

    @Test
    void contextLoads() throws IOException, MyException {
        String file = this.getClass().getResource("/tracker.conf").getFile();
        ClientGlobal.init(file);
        TrackerClient trackerClient=new TrackerClient();
        StorageClient storageClient=new StorageClient(null,null);
        String orginalFilename="C:\\Users\\root\\Pictures\\Saved Pictures\\O1CN01eGMjAL22C3u6ejvrT_!!0-item_pic.jpg_430x430q90.png";
        String[] upload_file = storageClient.upload_file(orginalFilename, "jpg", null);
        String s = "";
        for (int i = 0; i < upload_file.length; i++) {
                s += upload_file[i];

        }
        System.out.println("http://47.110.239.179/" + s);


    }

}
