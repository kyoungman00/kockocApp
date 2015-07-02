package com.kocapplication.pixeleye.kockocapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    private ViewPager pager;
    private Button btn_story, btn_interest, btn_recommendation, btn_trevalpath, btn_magazine, btn_my_treval, btn_pathWrite, btn_basicWrite;
    private ImageButton btn_setting;
    private boolean Menu_Check[] =  new boolean[5];
    private Context context;
    private Display mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getApplicationContext();
        super.onCreate(savedInstanceState);

        this.context = this;
        this.mDisplay = this.getWindowManager().getDefaultDisplay();

        setContentView(R.layout.activity_main);

        Arrays.fill(Menu_Check,false);

        Log.v("태그", "Log Example");

        // Top Menu Button
        btn_story = (Button)findViewById(R.id.btn_stroy);
        btn_story.setTag(0);

        btn_interest = (Button)findViewById(R.id.btn_interest);
        btn_interest.setTag(1);

        btn_recommendation = (Button)findViewById(R.id.btn_recommendation);
        btn_recommendation.setTag(2);

        btn_trevalpath = (Button)findViewById(R.id.btn_trevalpath);
        btn_trevalpath.setTag(3);

        btn_magazine = (Button)findViewById(R.id.btn_magazine);
        btn_magazine.setTag(4);

        btn_my_treval = (Button)findViewById(R.id.btn_my_treval);
        btn_my_treval.setTag(5);

        btnTextColorChange(0);



        View.OnClickListener bottom_btn_click_listener = new View.OnClickListener(){

            // Popup Menu Click Listener
            PopupMenu.OnMenuItemClickListener write_menu_click_listener = new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.append_write :


                            break;
                        case R.id.new_wirte :
                            Intent intent = new Intent(getApplicationContext(),NewWrite.class);

                            startActivity(intent);
                            break;
                    }

                    return false;
                }
            };


            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.btn_setting :
                        Toast.makeText(getApplicationContext(),"btn_setting",Toast.LENGTH_LONG).show();
                        PopupMenu popup_setting = new PopupMenu(context,v);
                        getMenuInflater().inflate(R.menu.menu_popup_setting,popup_setting.getMenu());
                        popup_setting.show();

                        break;
                    case R.id.btn_pathWrite :
                        Toast.makeText(getApplicationContext(),"btn_pathWrite",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.btn_basicWrite :
                        Toast.makeText(getApplicationContext(),"btn_basicWrite",Toast.LENGTH_LONG).show();
                        PopupMenu popup_write = new PopupMenu(context,v);
                        getMenuInflater().inflate(R.menu.menu_popup_write,popup_write.getMenu());
                        popup_write.setOnMenuItemClickListener( write_menu_click_listener);
                        popup_write.show();
                        break;
                }
            }
        };

        // Bottom Menu Button
        btn_setting = (ImageButton)findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(bottom_btn_click_listener);
        btn_pathWrite = (Button)findViewById(R.id.btn_pathWrite);
        btn_pathWrite.setOnClickListener(bottom_btn_click_listener);
        btn_basicWrite = (Button)findViewById(R.id.btn_basicWrite);
        btn_basicWrite.setOnClickListener(bottom_btn_click_listener);



        // ViewPager pagerAdapter connect
        pager = (ViewPager)findViewById(R.id.viewPager);
        pager.setAdapter(new mPagerAdapter(this,mDisplay));
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                // i값을 매개변수로 바로 주면 Error
                btnTextColorChange(Integer.valueOf( String.valueOf(i)));

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // Top Menu Button Event Create
        btn_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem((Integer)v.getTag());
                btnTextColorChange((Integer)v.getTag());
            }
        });
        btn_interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem((Integer)v.getTag());
                btnTextColorChange((Integer)v.getTag());
            }
        });
        btn_recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem((Integer)v.getTag());
                btnTextColorChange((Integer)v.getTag());
            }
        });
        btn_trevalpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem((Integer)v.getTag());
                btnTextColorChange((Integer)v.getTag());
            }
        });
        btn_magazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem((Integer)v.getTag());
                btnTextColorChange((Integer)v.getTag());
            }
        });
        btn_my_treval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem((Integer)v.getTag());
                btnTextColorChange((Integer)v.getTag());
            }
        });
    }




    // Top Menu Button Text Color Change Function
    public void btnTextColorChange(int position){

        // all Button Color reset
        this.getResources().getColor(R.color.subTextColor);
        btn_story.setTextColor(this.getResources().getColor(R.color.subTextColor));
        btn_interest.setTextColor(this.getResources().getColor(R.color.subTextColor));
        btn_recommendation.setTextColor(this.getResources().getColor(R.color.subTextColor));
        btn_trevalpath.setTextColor(this.getResources().getColor(R.color.subTextColor));
        btn_magazine.setTextColor(this.getResources().getColor(R.color.subTextColor));
        btn_my_treval.setTextColor(this.getResources().getColor(R.color.subTextColor));

        // selected Button Color Change
        switch (position){
            case 0:
                btn_story.setTextColor(this.getResources().getColor(R.color.white));
                break;
            case 1:
                btn_interest.setTextColor(this.getResources().getColor(R.color.white));
                break;
            case 2:
                btn_recommendation.setTextColor(this.getResources().getColor(R.color.white));
                break;
            case 3:
                btn_trevalpath.setTextColor(this.getResources().getColor(R.color.white));
                break;
            case 4:
                btn_magazine.setTextColor(this.getResources().getColor(R.color.white));
                break;
            case 5:
                btn_my_treval.setTextColor(this.getResources().getColor(R.color.white));
                break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // ActionBar Button Selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ic_actionBar_search) {
            LinearLayout ll = (LinearLayout) findViewById(R.id.layout_search_list);
            if(!Menu_Check[0]) {
                //Toast.makeText(getApplicationContext(), "Clicked ActionBar Search Button", Toast.LENGTH_LONG).show();
                Search_LIst sl = new Search_LIst(this);
                ll.addView(sl.getLinearLayout());
                Menu_Check[0] = true;
            }
            else {
                ll.removeAllViews();
                Menu_Check[0] = false;
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
