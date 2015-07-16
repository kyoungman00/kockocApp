package com.kocapplication.pixeleye.kockocapp;

/**
 * Created by Administrator on 2015-07-15.
 */
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {
    static public ArrayList<Board> readNews(String MSG){
        ArrayList<Board> result = new ArrayList<>();
        Log.d("resdNo MSG",MSG);
        try{
            JSONObject getObj = new JSONObject(MSG);
            JSONArray Boards;
            JSONArray[] temp = new JSONArray[20];
            Boards = getObj.getJSONArray("Boards");
            for(int i=19; i>=0; i--){
                temp[19-i] = Boards.getJSONArray(i);
            }
            Board bmp;
            JSONObject OBJ;
            for(JSONArray tmp :temp){
                int i=0;
                bmp= new Board();
                OBJ = tmp.getJSONObject(0);
                bmp.settext(OBJ.getString("Text"));
                //OBJ2 = tmp.getJSONObject(i++);
                bmp.setHashStr(OBJ.getString("Hash"));
                // OBJ3 = tmp.getJSONObject(i++);
                bmp.setCourseNo(OBJ.getInt("CourseNo"));
                // OBJ4 = tmp.getJSONObject(i++);
                bmp.setDate(OBJ.getString("Date"));
                // OBJ5 = tmp.getJSONObject(i++);
                bmp.setuserNo(OBJ.getInt("UserNo"));
                // OBJ6 = tmp.getJSONObject(i++);
                bmp.setImageNo(OBJ.getInt("ImageNo"));
                // OBJ7 = tmp.getJSONObject(i++);
                bmp.setboardNo(OBJ.getInt("No"));
                // OBJ8 = tmp.getJSONObject(i++);
                bmp.setMainImage(bmp.getuserNo()+"/"+OBJ.getString("MainImage"));
                result.add(bmp);
            }
            return result;
        }catch (Exception e){
        }
        return result;
    }
}