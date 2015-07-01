package com.kocapplication.pixeleye.kockocapp;

import android.app.ActionBar;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;


/**
 * Created by Administrator on 2015-06-26.
 */
public class Board_List{
    private LinearLayout ll_Board_List;
    private ImageView img_Main;
    private TextView tv_InnerText;
    private TextView tv_InnerTag;
    private ImageView img_Path;
    private LinearLayout ll_Bottom_List;
    private TextView tv_Board_Date;
    private LinearLayout ll_GoodCount;
    private TextView tv_Good;
    private TextView tv_Scrap;
    private TextView tv_Comment;
    private Context context;
    private AQuery aq;

    public Board_List(Context context){
        this.context = context;
        this.aq = new AQuery(context);
        this.ll_Board_List = new LinearLayout(context);
        this.img_Main = new ImageView(context);
        this.tv_InnerText = new TextView(context);
        this.tv_InnerTag = new TextView(context);
        this.img_Path = new ImageView(context);



        this.ll_Bottom_List = new LinearLayout(context);
        this.tv_Board_Date = new TextView(context);
        //
        this.ll_GoodCount = new LinearLayout(context);
        this.tv_Good = new TextView(context);
        this.tv_Scrap = new TextView(context);
        this.tv_Comment = new TextView(context);
        //

        //init control
        initMainImg(this.img_Main);
        iniText(this.tv_InnerText);
        iniTag(this.tv_InnerTag);
        initPathImg(this.img_Path);
        initLinearLayout(ll_Board_List);
        initBoardDate(tv_Board_Date);
        init_ll_BottomList(ll_Bottom_List);
    }
    public void initPathImg(ImageView img) {
        img.setImageResource(R.drawable.main_i_01);
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void initMainImg(ImageView img){
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void iniText(TextView tv){
        tv.setTextColor(context.getResources().getColor(R.color.innerTextColor));
        tv.setTextSize(12);
        tv.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT,1f));
    }
    public void iniTag(TextView tv) {
        tv.setTextColor(context.getResources().getColor(R.color.innerTagColor));
        tv.setTextSize(10);
    }
    public void initLinearLayout(LinearLayout ll) {
        LinearLayout.LayoutParams layoutParams =  new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 30;
        ll.setLayoutParams(layoutParams);
        ll.setBackgroundColor(context.getResources().getColor(R.color.white));
        ll.setOrientation(LinearLayout.VERTICAL);
      //  final String board_id = ll.getTag().toString();
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("TAG","Log : borad ID is "+v.getTag().toString());
            }
        });
    }
    public void initBoardDate(TextView tv) {
        tv.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, 1f));
        tv.setTextColor(context.getResources().getColor(R.color.gray));
    }
    public void init_ll_BottomList(LinearLayout ll_Bottom_List) {
        ll_Bottom_List.setOrientation(LinearLayout.HORIZONTAL);
        ll_GoodCount.setOrientation(LinearLayout.HORIZONTAL);

        tv_Board_Date.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, 1f));
        ll_GoodCount.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, 1f));
        ll_Bottom_List.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

        tv_Good.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, 1f));
        tv_Scrap.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, 1f));
        tv_Comment.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, 1f));

        tv_Good.setCompoundDrawablesWithIntrinsicBounds(R.drawable.main_i_02,0,0,0);
        tv_Scrap.setCompoundDrawablesWithIntrinsicBounds(R.drawable.main_i_03,0,0,0);
        tv_Comment.setCompoundDrawablesWithIntrinsicBounds(R.drawable.main_i_04,0,0,0);

        tv_Good.setTextColor(context.getResources().getColor(R.color.gray));
        tv_Scrap.setTextColor(context.getResources().getColor(R.color.gray));
        tv_Comment.setTextColor(context.getResources().getColor(R.color.gray));

        ll_GoodCount.addView(tv_Good);
        ll_GoodCount.addView(tv_Scrap);
        ll_GoodCount.addView(tv_Comment);

        ll_Bottom_List.addView(tv_Board_Date);
        ll_Bottom_List.addView(ll_GoodCount);
    }

    public void setLayout(String img_url, String innerText, String innerTag, boolean path, String date, String goodCount, String scrapCount, String commentCount, int id){

        img_url = "http://192.168.0.18:8080/testimage.jpg";
        aq.id(img_Main).image(img_url,true,true,400,0);

        img_Main.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tv_InnerText.setText(innerText);
        tv_InnerTag.setText(innerTag);
        tv_Board_Date.setText(date);

        tv_Good.setText(goodCount);
        tv_Scrap.setText(scrapCount);
        tv_Comment.setText(commentCount);

        ll_Board_List.addView(img_Main);
        ll_Board_List.addView(tv_InnerText);
        ll_Board_List.addView(tv_InnerTag);
        if(path) {

            ll_Board_List.addView(img_Path);
        }
        ll_Board_List.addView(ll_Bottom_List);
        ll_Board_List.setTag(id);
    }

    public LinearLayout getLinerLayout() {
        return ll_Board_List;
    }
}
