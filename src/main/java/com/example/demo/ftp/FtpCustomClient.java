package com.example.demo.ftp;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Utility class to establish the FTP connection to a remote FTP host.
 * @author Nirmith_106015
 */
@Service
public class FtpCustomClient {
    private FTPSClient ftpClient;

    public FtpCustomClient() {
        ftpClient = new FTPSClient();
    }

    /**
     * Get a instance of FTPClient Initiated with credentials.
     */
    public synchronized FTPSClient getFtpClient() {
        try {
            ftpClient.connect("192.168.40.191", 21);
            ftpClient.login("tester", "password");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient;
        } catch (Exception e) {
            // LOGGER
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Download the file when given the remote path to the file.
     * @param path
     * @return
     */
    public byte[] downloadFile(String path) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            getFtpClient().retrieveFile(path, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e){
            // LOGGER
            e.printStackTrace();
        }
        return null;
    }
}
