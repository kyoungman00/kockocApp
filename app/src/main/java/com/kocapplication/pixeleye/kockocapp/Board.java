package com.kocapplication.pixeleye.kockocapp;



import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by pixeleye02 on 2015-07-03.
 */

import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;

public class Board{
    private int boardNo=0;
    private int userNo=-1;
    private ArrayList<String> HashArr;
    private ArrayList<String> ImagePathArr;
    private ArrayList<String> ImageNameArr;
    private ArrayList<String> CourseArr;
    private String text = null;
    private int HashNum = 0;
    private int CourseNum = 0;
    private int ImageNum = 0;

    private String HashStr="";
    public boolean CourseBool = false;
    private  String mainImg=null;
    private int courseNo=0;
    private int imageNo=0;
    private String date;

    Board(){
        //this.userNo = userNo;
        HashArr = new ArrayList<String>();
        ImagePathArr = new ArrayList<String>();
        ImageNameArr = new ArrayList<String>();
        CourseArr = new ArrayList<String>();
    }
    public boolean HashAdd(String HashTag){
        if(Check(this.HashArr,HashTag)) {
            if (HashNum < 15) {//해시태그는 15개까지 입력가능
                HashArr.add(HashTag);
                HashNum++;
                return true;
            } else {
                Log.e("HashTag", " The HashNum overflow");
                return false;
            }
        }else{
            Log.e("HashTag","Existed HashTag");
            return false;
        }
    }
    public boolean Check(ArrayList<String> arr,String str){
        boolean result=true;
        for(String temp:arr){
            if(temp==str)result=false;
        }
        return result;
    }
    public boolean CheckImage(ArrayList<String> ImagePathArr,ArrayList<String> ImageNameArr,String path,String name){
        boolean result=true;
        for(int i=0; i<ImageNum; i++){
            if((ImagePathArr.get(i)+ImageNameArr.get(i)).equals(path+name)){
                result = false;
            }
        }
        return result;
    }
    public boolean CourseAdd(String Course){
        if(Check(this.CourseArr, Course)) {
            if (CourseNum < 15) {//코스는 15개까지 입력가능
                CourseArr.add(Course);
                CourseNum++;
                return true;
            } else {
                Log.e("Course", "The CourseNum overflow");
                return false;
            }
        }else{
            Log.e("Course","Existed Course");
            return false;
        }
    }
    public boolean ImageAdd(String ImagePath,String ImageName){
        if(CheckImage(this.ImageNameArr, this.ImagePathArr, ImagePath, ImageName)) {
            Log.d("insert Image","enter record");
            if (ImageNum < 10) {
                Log.d("insert",ImagePath +"  "+ImageName);
                ImageNameArr.add(ImageName);
                ImagePathArr.add(ImagePath);
                ImageNum++;
                return true;
            } else {
                Log.e("Image", "The ImageNum overflow");
                return false;
            }
        }else{
            Log.e("Image","Existed Image");
            return false;
        }
    }
    public void setuserNo(int userNo){
        this.userNo = userNo;
    }
    public void setboardNo(int boardNo){
        this.boardNo = boardNo;
    }
    public int getuserNo(){
        return userNo;
    }
    public int getboarNo(){
        return boardNo;
    }
    public void settext(String text){
        this.text = text;
    }
    public void setCourseNo(int No){
        courseNo = No;
    }
    public int getCourseNo(){
        return courseNo;
    }
    public void setImageNo(int No){
        imageNo = No;
    }
    public int getImageNo(){
        return imageNo;
    }
    public void setMainImage(String name){
        mainImg = name;
    }
    public String getMainImage(){
        return mainImg;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }

    public void setHashStr(String str){
        HashStr = str;
    }
    public String getHashStr(){
        return HashStr;
    }
    public String gettext(){
        return text;
    }
    public ArrayList getHashArr(){
        return HashArr;
    }
    public ArrayList getImagePathArr(){
        return ImagePathArr;
    }
    public ArrayList getImageNameArr(){
        return ImageNameArr;
    }
    public ArrayList getCourseArr(){
        return CourseArr;
    }
    public boolean write_Borad(){//현재 인스턴스의 정보를 서버로 전달하여 기록한다.
        if(userNo<0)//사용자번호가 들어오지 않는경우 error
        {
            Log.e("userNo", "NOT NULL");
            return false;
        }else if(HashNum==0){//해시태그가 없으면(태그는 적어도 하나 존재)
            Log.e("Hash","The hashTag must have one at least!!");
            return false;
        }else{
            if(text==null&&ImageNum==0){//
                Log.e("write Error","Text and images which one, Must be present!");
                return false;
            }else {
                upload_Image(ImagePathArr,ImageNameArr);
                JspConn.write(this);
                return true;
            }
        }

    }
    public void upload_Image(ArrayList<String> ImagePathArr,ArrayList<String> ImageNameArr){//썸네일 후 전송!
        for(int i=0; i<ImageNum; i++){
            Log.d("Board","Path:"+ImagePathArr.get(i)+" Name:"+ImageNameArr.get(i));
            String temp =Thumbnail.createThumbnail(ImagePathArr.get(i), ImageNameArr.get(i));
            this.ImageNameArr.set(i,temp);
            Log.d("Board","temp:"+temp);
            if(temp!="") {
                mk_Thread(temp);
            }
        }//make Thumbnail
    }
    private void mk_Thread(String ImageName){
        Thread FT = new Thread(new FileSend(this.userNo,String.valueOf(Environment.getExternalStorageDirectory())+"/KocKoc",ImageName));
        FT.start();
    }
}
