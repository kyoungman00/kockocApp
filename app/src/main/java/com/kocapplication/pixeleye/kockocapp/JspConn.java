package com.kocapplication.pixeleye.kockocapp;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015-07-10.
 */
import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JspConn {
    static public String write(Board board)
    {
        String result ="";
        int uNo=board.getuserNo();
        Log.d("uNo:",""+uNo);
        if(uNo>0){
            try {
                passiveMethod();
                HttpClient client = new DefaultHttpClient();
                String postURL = "http://192.168.0.18:8080/News/writeBoard.jsp";
                HttpPost post = new HttpPost(postURL);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userNo", "" + board.getuserNo()));//65000자
                params.add(new BasicNameValuePair("text", "" + board.gettext()));

                //Send Hash Arr
                int i = 0;
                ArrayList<String> HashArr = board.getHashArr();
                for (String temp : HashArr) {
                    params.add(new BasicNameValuePair("Hash" + (i++), temp));
                }
                params.add(new BasicNameValuePair("HashNo", "" + i));

                //send ImageArr
                ArrayList<String> ImageArr = board.getImageNameArr();
                i = 0;
                for (String temp : ImageArr) {
                    //Log.d("entered","Image for"+i);
                    params.add(new BasicNameValuePair("Image" + (i++), temp));
                }
                params.add(new BasicNameValuePair("ImageNo", "" + i));

                //send CourseArr
                i = 0;
                ArrayList<String> CourseArr = board.getCourseArr();
                for (String temp : CourseArr) {
                    params.add(new BasicNameValuePair("Course" + (i++), temp));
                }
                params.add(new BasicNameValuePair("CourseNo", "" + i));

                UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
                post.setEntity(ent);
                //HttpResponse responsePOST = client.execute(post);
                //HttpEntity resEntity = responsePOST.getEntity();

                HttpResponse response = client.execute(post);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), HTTP.UTF_8));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                Log.d("Debug","result!! "+result);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }else{
            Log.e("UserNo error","be existed!");
        }
        return result;
    }
    static public void passiveMethod(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    static public String readNews(){
        String result="";
        try
        {
            passiveMethod();
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://192.168.0.18:8080/News/readNews.jsp";
            HttpPost post = new HttpPost(postURL);
            HttpResponse response = client.execute(post);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), HTTP.UTF_8));
            String line;
            while((line = bufferedReader.readLine())!=null){
                result+=line;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
