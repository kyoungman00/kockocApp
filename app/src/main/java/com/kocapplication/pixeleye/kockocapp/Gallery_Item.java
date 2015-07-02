package com.kocapplication.pixeleye.kockocapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015-07-02.
 */
public class Gallery_Item extends Activity{
    private String fileName;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
    //    super.onCreate(savedInstanceState);
       // setContentView(R.layout.layout_gallery_item);

      //  imgView = (ImageView)findViewById(R.id.iv_item);

      //  processIntent();

    }

    private void processIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        this.fileName = extras.getString("filename");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        Bitmap bitmap = BitmapFactory.decodeFile(fileName,options);
        imgView.setImageBitmap(bitmap);

    }

}
