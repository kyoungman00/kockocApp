package com.kocapplication.pixeleye.kockocapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-06-30.
 */
public class NewWrite extends Activity {

    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_write);

        View.OnClickListener btn_Click_Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_back :
                        Toast.makeText(getApplicationContext(),"Back Button is Clicked",Toast.LENGTH_LONG).show();
                        finish();
                }
            }
        };

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(btn_Click_Listener);


    }
}
