package com.kocapplication.pixeleye.kockocapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-06-30.
 */
public class NewWrite extends Activity {

    private Button btn_back, btn_gallery;
    private int displayWidth,displayHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_write);
        Intent intent = getIntent();
        this.displayWidth = intent.getIntExtra("displayWidth",0);
        this.displayHeight = intent.getIntExtra("displayHeight",0);

        View.OnClickListener btn_Click_Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_back :
                        Toast.makeText(getApplicationContext(),"Back Button is Clicked",Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    case R.id.btn_gallery :
                        Intent intent = new Intent(getApplicationContext(),Gallery.class);
                        intent.putExtra("displayWidth",displayWidth);
                        intent.putExtra("displayHeight",displayHeight);
                        startActivity(intent);
                }
            }
        };

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(btn_Click_Listener);

        btn_gallery = (Button)findViewById(R.id.btn_gallery);
        btn_gallery.setOnClickListener(btn_Click_Listener);


    }
}
