package com.kocapplication.pixeleye.kockocapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-06-24.
 */

/*
public class MyAdapter extends BaseAdapter {
    private Context contxt;
    private ArrayList<Custom_list> items;
    private LayoutInflater inflater;


    public MyAdapter(Context c, ArrayList<Custom_list> items){

        Log.v("tag","Log : MyAdapter 생성자가 실행되었습니다.");
        this.contxt = c;
        this.inflater = LayoutInflater.from(c);
        this.items = items;

        //inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder = null;
        Custom_list pos = items.get(position);
        if (v == null) {
            holder = new ViewHolder();
            v = inflater.inflate(R.layout.layout_inner_layout, null);
            holder.txt_innerText = (TextView)v.findViewById(R.id.tv_innerText);
            holder.txt_innerTag = (TextView)v.findViewById(R.id.tv_innerTag);
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }
        holder.txt_innerText.setText(pos.getInnerText());
        holder.txt_innerTag.setText(pos.getInnerTag());
        return v;
    }
    class ViewHolder{
        ImageView img_board;
        TextView txt_innerText;
        TextView txt_innerTag;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_inner_layout,parent,false);
        RelativeLayout layout = (RelativeLayout)view;

        TextView tv_Text = (TextView)layout.findViewById(R.id.txt_title);
        TextView tv_Tag = (TextView)layout.findViewById(R.id.txt_content);
        TextView tv_t = (TextView)layout.findViewById(R.id.tv_text);

        tv_Text.setText((String)lst.get(position));
        tv_Tag.setText((String)lst.get(position)+"입니다.");
        tv_t.setText("asdfasdf");

        return layout;
    }


}
class Custom_list {
    private String txt_innerText;
    private String txt_innerTag;
    public Custom_list(String innerText, String innerTag){
        this.txt_innerText = innerText;
        this.txt_innerTag = innerTag;
    }
    public String getInnerText(){
        return this.txt_innerText;
    }
    public String getInnerTag() {
        return this.txt_innerTag;
    }
}
*/

