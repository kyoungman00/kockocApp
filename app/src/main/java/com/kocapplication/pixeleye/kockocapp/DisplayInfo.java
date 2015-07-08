package com.kocapplication.pixeleye.kockocapp;

import android.app.Application;
import android.view.Display;

/**
 * Created by Administrator on 2015-07-02.
 */
 public class DisplayInfo extends Application{
    private Display mDisplay;

    public void setDisplay(Display display) {
        this.mDisplay = display;
    }

    public int getDisplayWidth(){
        return this.mDisplay.getWidth();
    }

    public int getDisplayHeight(){
        return this.mDisplay.getHeight();
    }

    public int resize_Height(int width, int height, int resize_width){
        return (this.getDisplayHeight()*resize_width)/getDisplayWidth();
    }

}
