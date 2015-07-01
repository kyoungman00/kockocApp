package com.kocapplication.pixeleye.kockocapp;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015-06-26.
 */
public class Search_LIst {
    private LinearLayout ll;
    private ImageButton imgBtn_searchEnter;
    private HorizontalScrollView horizon_Scroll;
    private TextView tv_seartchTag;


    public Search_LIst(Context context){
        ll = init_ll(context);
        imgBtn_searchEnter = init_imgBtn(context);
        horizon_Scroll = init_horizonScroll(context);
    }
    public LinearLayout getLinearLayout(){
        ll.addView(imgBtn_searchEnter);
        ll.addView(horizon_Scroll);

        return ll;
    }

    public LinearLayout init_ll(Context context) {
        LinearLayout ll_temp = new LinearLayout(context);
        ll_temp.setOrientation(LinearLayout.HORIZONTAL);
        ll_temp.setBackgroundColor(context.getResources().getColor(R.color.mainBgColor));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll_temp.setLayoutParams(layoutParams);

        return ll_temp;
    }

    public ImageButton init_imgBtn(Context context) {
        ImageButton imgBtn = new ImageButton(context);
        imgBtn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imgBtn.setImageResource(R.drawable.s_01);
        imgBtn.setBackgroundColor(context.getResources().getColor(R.color.subBgColor2));

        return imgBtn;
    }
    public HorizontalScrollView init_horizonScroll(Context context){
        HorizontalScrollView horizonScroll = new HorizontalScrollView(context);
        horizonScroll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        TextView tv_temp = new TextView(context);
        tv_temp.setId(R.id.tv_tag_id);
        tv_temp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv_temp.setTextColor(context.getResources().getColor(R.color.white));
        tv_temp.setGravity(Gravity.CENTER);

        horizonScroll.addView(tv_temp);
        return horizonScroll;
    }

}
