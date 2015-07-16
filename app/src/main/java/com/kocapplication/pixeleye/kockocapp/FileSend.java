package com.kocapplication.pixeleye.kockocapp;

/**
 * Created by Administrator on 2015-07-13.
 */


import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by pixeleye02 on 2015-06-29.
 */
public class FileSend implements Runnable {
    FTPClient ftpClient = null;
    String IPAddress = "192.168.0.18";
    String ConnID = "kocserver";
    String PWD = "0505";
    String targetPath = "storage/emulated/0/KocKoc";
    String targetName = "";
    String PathAndName = "";
    int userNo = 0;

    FileSend(int userNo,String targetPath, String targetName){
        ftpClient = new FTPClient();
        this.userNo  = userNo;
        this.targetName = targetName;
        this.targetPath= targetPath;
    }

    @Override
    public void run() {//업로드 실행
        File file;
        // JspConn.passiveMethod();
        try {
            Log.d("FTP","Start!");
            ftpClient.connect(IPAddress, 21);
            ftpClient.login(ConnID, PWD);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(5 * 1024 * 1024);
            ftpClient.enterLocalPassiveMode();
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                Log.e("FTP","refused connection");
            }
            if(PathAndName != ""){
                file = new File(PathAndName);
            }else{
                file = new File(targetPath,targetName);
            }
            //Log.d("try upload",file.getName()+ " : "+file.getPath());
            //Log.d("isfile",""+file.isFile());
            if (file.isFile()) {
                Log.d("FTP",file.getName());
                FileInputStream ifile = new FileInputStream(file);
                ftpClient.makeDirectory("/kocHDD/boardImage/"+userNo);
                ftpClient.cwd("/kocHDD/boardImage/"+userNo);
                ftpClient.rest(file.getName());  // ftp에 해당 파일이있다면 이어쓰기
                ftpClient.appendFile(file.getName(), ifile); // ftp 해당 파일이 없다면 새로쓰기
            }else{
                Log.d("FTP", "fail");
            }
            /*if(path.listFiles().length>0){
                for (File file : path.listFiles()) {
                    if (file.isFile()) {
                        FileInputStream ifile = new FileInputStream(file)

                        ftpClient.rest(file.getName());  // ftp에 해당 파일이있다면 이어쓰기

                        ftpClient.appendFile(file.getName(), ifile); // ftp 해당 파일이 없다면 새로쓰기
                    }
                }
            }*/
            ftpClient.disconnect();
        } catch (IOException e) {
            Log.e("FTP",""+e.getMessage());
        }

    }
}
