package com.kocapplication.pixeleye.kockocapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import java.util.Arrays;


/**
 * Created by Administrator on 2015-07-02.
 */
public class Gallery extends Activity{

    private int count;
    private Bitmap[] bmGalleryItem;
    public String[] arrPath;
    private boolean[] arrSelectedItem;
    private int[] arrSelectedItemNumber;
    private ImageAdapter imageAdapter;
    private Cursor imageCursor;
    private String[] columns;
    private String orderBy;
    private int displayWidth, displayHeight;
    private AQuery aq;
    private DisplayInfo displayInfo;
    public int image_columns_index;

    private int count_CheckedItem =0;

    public int getArrSelectedItemNumber(int position){
        return this.arrSelectedItemNumber[position];
    }

    public void setArrSelectedItemNumber(int position) {
        this.arrSelectedItemNumber[position] = ++(this.count_CheckedItem);
    }

    public void minusArrSelectedItemNumber(int position){
        for(int i=0; i<this.count; i++) {
            if(getArrSelectedItemNumber(i) >= getArrSelectedItemNumber(position)){
                this.arrSelectedItemNumber[i]--;
            }
        }
        this.count_CheckedItem--;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallery);
        Intent intent = getIntent();
        this.aq = new AQuery(this);

        displayInfo = (DisplayInfo)getApplicationContext();

        columns = new String[]{MediaStore.Images.Thumbnails.DATA,MediaStore.Images.Thumbnails._ID};
        orderBy = MediaStore.Images.Thumbnails._ID;

        imageCursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,columns,null,null,orderBy);

        image_columns_index = imageCursor.getColumnIndex(MediaStore.Images.Thumbnails._ID);
        this.count = imageCursor.getCount();
        this.bmGalleryItem = new Bitmap[this.count];
        this.arrPath = new String[this.count];
        this.arrSelectedItem = new boolean[this.count];
        Arrays.fill(this.arrSelectedItem, false);
        this.arrSelectedItemNumber = new int[this.count];
        Arrays.fill(arrSelectedItemNumber,0);

        Log.v("Tag","Gallery : 갤러리에서 불러오기 시작");

        for(int i=0; i<this.count; i++) {

            imageCursor.moveToPosition(this.count-i-1);

            int dataColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA);

            arrPath[i] = imageCursor.getString(dataColumnIndex);
        }

        Log.v("Tag", "Gallery : 갤러리에서 불러오기 종료");

        GridView gv = (GridView)findViewById(R.id.gv_gallery);

        imageAdapter = new ImageAdapter();
        gv.setAdapter(imageAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (arrSelectedItem[position]) { //선택되어있을 때 선택 해제
                        minusArrSelectedItemNumber(position);
                        arrSelectedItem[position] = false;
                    } else { //선택 안되어있을 때 선택하기
                        setArrSelectedItemNumber(position);
                        arrSelectedItem[position] = true;
                    }
                imageAdapter.notifyDataSetChanged();
            }
        });
    }

    private class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ImageAdapter() {
            this.mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, final ViewGroup parent) {

            Log.v("Tag","Convert Position is : "+position);


            final ViewHolder holder;
            if (convertView == null) {

                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.layout_gallery_item, null);
                holder.setImgView((ImageView) convertView.findViewById(R.id.iv_item));
                holder.setTextView((TextView) convertView.findViewById(R.id.tv_item));

                holder.getImgView().setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, displayInfo.getDisplayWidth() / 3));

                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.getTextView().setVisibility(View.INVISIBLE);
            holder.getImgView().setImageResource(0);

            if (arrSelectedItem[position]) {
                holder.getTextView().setVisibility(View.VISIBLE);
                holder.getTextView().setText(String.valueOf(getArrSelectedItemNumber(position)));
                holder.getImgView().setImageResource(R.drawable.bg_rectangle_shape_click);
            }

            holder.getImgView().setId(position);


            Thread img = new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Bitmap bm = aq.getCachedImage(arrPath[position]);

                            BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
                            holder.getImgView().setBackgroundDrawable(ob);
                            holder.setId(position);

                        }
                    });
                }
            });
            img.start();

            return convertView;
        }
    }

    private class ViewHolder {
        private ImageView imgView;
        private int id;
        private boolean check = false;
        private TextView textView;

        public TextView getTextView() {
            return this.textView;
        }

        public ImageView getImgView(){
            return this.imgView;
        }
        public int getId(){
            return this.id;
        }
        public boolean getCheck(){
            return this.check;
        }

        public void  setTextView(TextView tv) {
            this.textView = tv;
        }

        public void setImgView(ImageView v){
            this.imgView = v;
        }
        public void setId(int id) {
            this.id = id;
        }
        public void setCheck(boolean bool) {
            this.check = bool;
        }

    }
}

