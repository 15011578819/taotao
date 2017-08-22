package com.taotao;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Administrator on 2017-8-21.
 */
public class FTPClientTest {

    public void testFtp() throws Exception {
        //1、连接ftp服务器
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("10.10.3.162", 21);
        //2、登录ftp服务器
        ftpClient.login("ftpuser", "wclzxf123");
        //3、读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("D:\\Linux_service\\e_bw.jpg"));
        //4、上传文件
        //1）指定上传目录
        ftpClient.changeWorkingDirectory("/home/ftpuser");
        //2）指定文件类型
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        //第一个参数：文件在远程服务器的名称
        //第二个参数：文件流
        ftpClient.storeFile("hello.jpg", inputStream);
        //5、退出登录
        ftpClient.logout();
    }
}
