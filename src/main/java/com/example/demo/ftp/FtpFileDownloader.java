package com.example.demo.ftp;

import java.util.Arrays;

public class FtpFileDownloader {
    public static void main(String[] args) {
        FtpCustomClient ftpCustomClient = new FtpCustomClient();
        byte[] file  = ftpCustomClient.downloadFile("testfile.txt");
        System.out.println(Arrays.toString(file));
    }
}
