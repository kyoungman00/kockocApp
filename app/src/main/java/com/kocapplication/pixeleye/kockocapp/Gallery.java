package com.kocapplication.pixeleye.kockocapp;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015-07-02.
 */
public class Gallery extends Activity{
    private int count;
    private Bitmap[] bmGalleryItem;
    private String[] arrPath;
    private ImageAdapter imageAdapter;
    private Cursor imageCursor;
    private String[] columns;
    private String orderBy;
    private Display mDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallery);

        columns = new String[]{MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        orderBy = MediaStore.Images.Media._ID;

        imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,columns,null,null,orderBy);

        int image_columns_index = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
        this.count = imageCursor.getCount();
        this.bmGalleryItem = new Bitmap[this.count];
        this.arrPath = new String[this.count];

        for(int i=0; i<this.count; i++) {
            imageCursor.moveToPosition(i);
            int id=imageCursor.getInt(image_columns_index);
            int dataColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);

            bmGalleryItem[i] = MediaStore.Images.Thumbnails.getThumbnail(
                    getApplication().getContentResolver(),id,MediaStore.Images.Thumbnails.MICRO_KIND,null);
            arrPath[i] = imageCursor.getString(dataColumnIndex);
        }

        GridView gv = (GridView)findViewById(R.id.gv_gallery);
        imageAdapter = new ImageAdapter();
        gv.setAdapter(imageAdapter);

    }

    private class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ImageAdapter() {
            this.mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return count;
        }
        public Object getItem(int position){ return position; }
        public long getItemId(int position) { return position; }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.layout_gallery_item,null);
                holder.setImgView((ImageView)convertView.findViewById(R.id.iv_item));

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.getImgView().setId(position);
            bmGalleryItem[position] = Bitmap.createScaledBitmap(bmGalleryItem[position],320,240,true);

            holder.getImgView().setImageBitmap(bmGalleryItem[position]);
            holder.setId(position);

            return convertView;
        }
    }
    private class ViewHolder {
        private ImageView imgView;
        private int id;

        public ImageView getImgView(){
            return this.imgView;
        }
        public int getId(){
            return this.id;
        }
        public void setImgView(ImageView v){
            this.imgView = v;
        }
        public void setId(int id) {
            this.id = id;
        }
    }
}

