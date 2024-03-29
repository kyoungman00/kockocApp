package com.kocapplication.pixeleye.kockocapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-06-21.
 */
public class mPagerAdapter extends PagerAdapter {

    private LayoutInflater mInflater;
    private LinearLayout ll_left;
    private LinearLayout ll_right;
    private Display mDisplay;

    public mPagerAdapter(Context context, Display display){
        super();
        this.mInflater = LayoutInflater.from(context);
        this.mDisplay = display;
    }

    public mPagerAdapter(Context context){
        super();
        this.mInflater = LayoutInflater.from(context);
    }

    public Object instantiateItem(View pager, int position) {
        View v = null;

        switch (position) {
            case 0 :
                Log.v("Tag", "Log : ViewPager - story");
                v = mInflater.inflate(R.layout.layout_stroy,null);
                dynamicAdd(v,0);
                v.setTag(0);
                break;
            case 1 :
                Log.v("Tag", "Log : ViewPager start - interest");
                v = mInflater.inflate(R.layout.layout_interest,null);
                dynamicAdd(v,1);
                v.setTag(1);
                Log.v("Tag", "Log : ViewPager end - interest");

                break;
            case 2 :
                Log.v("Tag", "Log : ViewPager - recommendation");
                v = mInflater.inflate(R.layout.layout_recommendation,null);
                dynamicAdd(v,2);
                v.setTag(2);
                break;
            case 3 :
                Log.v("Tag", "Log : ViewPager - Trevalpath");
                v = mInflater.inflate(R.layout.layout_trevalpath,null);
                dynamicAdd(v, 3);
                v.setTag(3);
                break;
            case 4 :
                Log.v("Tag", "Log : ViewPager - magazine");
                v = mInflater.inflate(R.layout.layout_magazine,null);
                dynamicAdd(v,4);
                v.setTag(4);


                break;
            case 5 :
                Log.v("Tag", "Log : ViewPager - my_treval");
                v = mInflater.inflate(R.layout.layout_my_treval,null);
                dynamicAdd(v,5);
                v.setTag(5);
                break;
        }
        ((ViewPager)pager).addView(v,null);
        return v;
    }
    // ViewPager에서 관리하는 페이지의 수
    public int getCount(){
        return 6;
    }

    // View를 삭제합니다.
    public void destroyItem(View pager, int position, Object view) {
        ((ViewPager)pager).removeView((View) view);
    }

    // instantiateItem에서 생성한 객체를 이용할 것인지 여부 반환
    public boolean isViewFromObject(View v, Object obj) {
        return v == obj;
    }


    public void dynamicAdd(final View v, final int position) {
        switch (position) {
            case 0:
                ll_left = (LinearLayout) v.findViewById(R.id.ll_stroy_left);
                ll_right = (LinearLayout) v.findViewById(R.id.ll_stroy_right);
                break;
            case 1:
                ll_left = (LinearLayout) v.findViewById(R.id.ll_interest_left);
                ll_right = (LinearLayout) v.findViewById(R.id.ll_interest_right);
                break;
            case 2:
                ll_left = (LinearLayout) v.findViewById(R.id.ll_recommendation_left);
                ll_right = (LinearLayout) v.findViewById(R.id.ll_recommendation_right);
                break;
            case 3:
                ll_left = (LinearLayout) v.findViewById(R.id.ll_trevalpath_left);
                ll_right = (LinearLayout) v.findViewById(R.id.ll_trevalpath_right);
                break;
            case 4:
                ll_left = (LinearLayout) v.findViewById(R.id.ll_magazine_left);
                ll_right = (LinearLayout) v.findViewById(R.id.ll_magazine_right);
                break;
            case 5:
                ll_left = (LinearLayout) v.findViewById(R.id.ll_my_treval_left);
                ll_right = (LinearLayout) v.findViewById(R.id.ll_my_treval_right);
                break;
        }
        Log.d("Debug", "dynamicAdd Called ");


//        for (int i = 0; i < 20; i++) {
//            // Board_List bl= new Board_List(v.getContext(),mDisplay);
//            Board_List bl = new Board_List(v.getContext());
//            if (i % 2 == 0) {
//                bl.setLayout("http://192.168.0.18:8080/board_image/so5.jpg", "예제Text" + i, "예제Tag" + i, true, "2015-06-26", "3", "4", "5", i);
//                LinearLayout ll = bl.getLinerLayout();
//                ll_left.addView(ll);
//            } else {
//                bl.setLayout("http://192.168.0.18:8080/board_image/thumbnail1.jpg", "예제Text" + i, "예제Tag" + i + "hbaoianoiabioaboibaibooiabnoiadnoiearm;dfhpiasnio;adoipadgsnoi", true, "2015-06-26", "3", "4", "5", i);
//                LinearLayout ll = bl.getLinerLayout();
//                ll_right.addView(ll);
//            }
//        }

        News news = new News();
        news.loadBoard();
        ArrayList<Board> arrBoard = news.getBoards();
        if(arrBoard.size() > 0) {
            arrBoard = news.getBoards();

            for(int i=0; i<arrBoard.size(); i++) {
                Board_List bl = new Board_List(v.getContext());
                Board tmp = arrBoard.get(i);
                boolean b = false;
                if(tmp.getImagePathArr().size()>0) {
                    b = true;
                }
                bl.setLayout("http://192.168.0.18:8080/board_image/"+tmp.getMainImage(),tmp.gettext(),tmp.getHashStr(),b,tmp.getDate(),"3","4","5",i);
                Log.d("Debug","LoadImage from Server , imagePath : "+tmp.getMainImage());
                LinearLayout ll = bl.getLinerLayout();
                if(i%2 == 0) {
                    ll_left.addView(ll);
                }
                else {
                    ll_right.addView(ll);

                }
            }
        }
        else {
            Log.d("Debug","news.loadBoard is null");
        }

    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setAdapter_inListView(View v, int position) {

        Log.v("Tag", "Log : setAdapter_inListView");
        switch (position){
            case 0 :
                //   listView = (ListView)v.findViewById(R.id.listView_story_left);
                break;
            case 1 :
                //    listView = (ListView)v.findViewById(R.id.listView_interest);
                break;
            case 2 :
                //   listView = (ListView)v.findViewById(R.id.listView_recommendation);
                break;
            case 3 :
                //    listView = (ListView)v.findViewById(R.id.listView_trevalpath);
                break;
            case 4 :
                //    listView = (ListView)v.findViewById(R.id.listView_magazine);
                break;
            case 5 :
                //    listView = (ListView)v.findViewById(R.id.listView_my_treval);
                break;
        }
        //myAdapter = new MyAdapter(v.getContext(), arry);
        //listView.setAdapter(myAdapter);
    }
}
