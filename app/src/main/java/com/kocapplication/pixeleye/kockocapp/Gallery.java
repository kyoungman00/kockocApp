package com.kocapplication.pixeleye.kockocapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Created by Administrator on 2015-07-02.
 */
public class Gallery extends Activity{

    private int count;
//    private Bitmap[] bmGalleryItem;
  //  public String[] arrThumbnailsPath;

  //  public String[] arrMediaPath;

    private boolean[] arrSelectedItem;
    private int[] arrSelectedItemNumber;
    private ImageAdapter imageAdapter;
  //  private Cursor imageCursor;
  //  private Cursor imageCursorMedia;
  //  private String[] columns;
  //  private String orderBy;
    private int displayWidth, displayHeight;
    private AQuery aq;
    private DisplayInfo displayInfo;
    private Button btn_galleryNext, btn_back;
    private Bitmap[] selectedImage;
    private ArrayList< String> selected_image_path;

    private ArrayList<PhotoData> photoArrayList;
    private ArrayList<Integer> ImageOrder;


    public int image_columns_index;

    private int count_CheckedItem =0;

    public int getCountCheckedItem(){
        return this.count_CheckedItem;
    }

    public int getCount(){
        return this.count;
    }


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
        this.selected_image_path = intent.getStringArrayListExtra("path");
        if(this.selected_image_path == null){
            this.selected_image_path = new ArrayList<String>();
        }

        for(int i =0; i<this.selected_image_path.size(); i++) {
            Log.d("Debug","check! "+this.selected_image_path.get(i));
        }

        this.aq = new AQuery(this);
        this.ImageOrder = new ArrayList<Integer>();

        displayInfo = (DisplayInfo)getApplicationContext();


        String[] proj = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME
        };
        int[] idx = new int[proj.length];

        //columns = new String[]{MediaStore.Images.Media.DATA,MediaStore.Images.Media._ID};
        //orderBy = MediaStore.Images.Media._ID;
        Cursor cursor = MediaStore.Images.Media.query(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI,proj);

        this.photoArrayList = new ArrayList<PhotoData>();

        if(cursor != null && cursor.moveToLast()){
            idx[0] = cursor.getColumnIndex(proj[0]);
            idx[1] = cursor.getColumnIndex(proj[1]);
            idx[2] = cursor.getColumnIndex(proj[2]);
            int index =0;
            do {

                int photoID = cursor.getInt(idx[0]);
                String photoPath = cursor.getString(idx[1]);
                String displayName = cursor.getString(idx[2]);
                if( displayName != null ) {


                    PhotoData photo = new PhotoData();
                    photo.photoID = photoID;
                    photo.photoPath = photoPath;
                    photoArrayList.add(photo);
                }
            }
            while( cursor.moveToPrevious() );

        }

        this.count = photoArrayList.size();
        this.arrSelectedItem = new boolean[this.count];
        Arrays.fill(this.arrSelectedItem, false);
        this.arrSelectedItemNumber = new int[this.count];
        Arrays.fill(arrSelectedItemNumber,0);

        for(int i=0; i<this.count; i++) {

            if(selected_image_path.contains(photoArrayList.get(i).photoPath)){
                arrSelectedItem[i] = true;
            //    setArrSelectedItemNumber(i);
            }
        }





   //     imageCursorMedia = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null);

       // imageCursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,columns,null,null,orderBy);


     //   image_columns_index = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
    //    this.count = imageCursor.getCount();
  //      this.count = imageCursorMedia.getCount();
   //     int mainImageCount = imageCursorMedia.getCount();

    //    this.bmGalleryItem = new Bitmap[this.count];
        //this.arrThumbnailsPath = new String[this.count];
     //   this.arrMediaPath = new String[this.count];



//        for(int i=0; i<photoArrayList.size(); i++) {
//
//        }
//
//        Log.v("Tag","Gallery : 갤러리에서 경로 불러오기 시작");
//
//        for(int i=0; i<this.count; i++) {
//   //         imageCursor.moveToPosition(this.count-i-1);
//            imageCursorMedia.moveToPosition(this.count-i-1);
//
//    //        int dataColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
//            int dataColumnIndexMedia = imageCursorMedia.getColumnIndex(MediaStore.Images.Media.DATA);
//
//      //      arrThumbnailsPath[i] = imageCursor.getString(dataColumnIndex);
//            //arrMediaPath[i] = String.valueOf(i);
//            //arrMediaPath[i] = imageCursorMedia.getString(dataColumnIndex);
//
//
//            Log.d("Debug","arrThumbnailsPath["+i+"] : "+arrThumbnailsPath[i]);
//            //Log.d("Debug","arrMediaPath["+i+"] : "+arrMediaPath[i]);
//
//            if(this.selected_image_path.contains(arrMediaPath[i])){
//                Log.d("Debug","check! selected_image_path : "+arrMediaPath[i]);
//                arrSelectedItem[i] = true;
//                Log.d("Debug","check! SelectedItem : "+String.valueOf(arrSelectedItem[i]));
//                setArrSelectedItemNumber(i);
//            }
//        }
//        Log.v("Tag", "Gallery : 갤러리에서 불러오기 종료");

        GridView gv = (GridView)findViewById(R.id.gv_gallery);
        imageAdapter = new ImageAdapter();
        gv.setAdapter(imageAdapter);

        this.btn_galleryNext = (Button)findViewById(R.id.btn_galleryNext);

        this.btn_galleryNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCountCheckedItem() != 0) {
                    selected_image_path.clear();
                    for(int i=0; i<ImageOrder.size(); i++) {
                        selected_image_path.add(photoArrayList.get(ImageOrder.get(i)).photoPath);
                    }
                   // for (int i = 0; i < getCount(); i++) {
                      //  if (arrSelectedItem[i]) {
                     //       selected_image_path.add(photoArrayList.get(arrSelectedItemNumber[i]-1).photoPath);
                    //    }
                    //}
                }
                Intent intent_galleryNext = new Intent();
                intent_galleryNext.putExtra("path", selected_image_path);
                setResult(0, intent_galleryNext);

                finish();
            }
        });
        if(ImageOrder.size()>0){
            this.btn_galleryNext.setVisibility(View.VISIBLE);
        }

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (arrSelectedItem[position]) { //선택되어있을 때 선택 해제
                 //   minusArrSelectedItemNumber(position);
                    ImageOrder.remove((Object)position);
                    arrSelectedItem[position] = false;
                } else { //선택 안되어있을 때 선택하기
                 //   setArrSelectedItemNumber(position);
                    ImageOrder.add(position);
                    arrSelectedItem[position] = true;
                }
                if(getCountCheckedItem() > 0){
                    btn_galleryNext.setVisibility(View.VISIBLE);
                }
                else {
                    btn_galleryNext.setVisibility(View.INVISIBLE);
                }
                imageAdapter.notifyDataSetChanged();
            }
        });
    }

//    @Override
//    public boolean onKeyDown(int KeyCode, KeyEvent event){
//        if(event.getAction() == KeyEvent.ACTION_DOWN) {
//            if(KeyCode == KeyEvent.KEYCODE_BACK){
//                if(getCount() != 0) {
//                    setResult(0,null);
//                    //moveTaskToBack(true);
//                   finish();
//                }
//            }
//        }
//        return super.onKeyDown(KeyCode,event);
//    }

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

         //   int rePosition = getCount() - position - 1;

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
               // holder.getTextView().setText(String.valueOf(getArrSelectedItemNumber(position)));
                holder.getTextView().setText(String.valueOf(ImageOrder.indexOf(position)+1));
                holder.getImgView().setImageResource(R.drawable.bg_rectangle_shape_click);
            }

            holder.getImgView().setId(position);


            Thread img = new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            int uid = photoArrayList.get(position).photoID;
                            String path = photoArrayList.get(position).photoPath;
                            String[] proj = {MediaStore.Images.Thumbnails.DATA};

                            Bitmap resultImage;
                            Log.d("Test", "micro image path is : " + path);
                            // }else

                            Cursor mini = MediaStore.Images.Thumbnails.queryMiniThumbnail(getContentResolver(), uid, MediaStore.Images.Thumbnails.MINI_KIND, proj);
                            if (mini != null && mini.moveToFirst()) {
                                path = mini.getString(mini.getColumnIndex(proj[0]));

                                Log.d("Test", "mini image path is : " + path);
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = true;
                                BitmapFactory.decodeFile(path, options);
                                options.inJustDecodeBounds = false;
                                options.inSampleSize = 1;
                                if (options.outWidth > 96) {

                                    int ws = options.outWidth / 96 + 1;
                                    if (ws > options.inSampleSize)
                                        options.inSampleSize = ws;
                                }
                                if (options.outHeight > 96) {

                                    int hs = options.outHeight / 96 + 1;
                                    if (hs > options.inSampleSize)
                                        options.inSampleSize = hs;
                                }
                                resultImage = BitmapFactory.decodeFile(path, options);
                                BitmapDrawable ob = new BitmapDrawable(getResources(), resultImage);
                                holder.getImgView().setBackgroundDrawable(ob);
                                holder.setId(position);
                            }




//                            Log.d("Debug","Image["+position+"] Load Thread start");
//                            Bitmap bm = aq.getCachedImage(arrThumbnailsPath[position]);
//
//                            Log.d("Debug", "Convert Position is : " + position);
//
//                            BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
//                            holder.getImgView().setBackgroundDrawable(ob);
//                            holder.setId(position);
//                            Log.d("Debug","Image["+position+"] Load Thread end");
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

    private class PhotoData {
        int photoID;
        String photoPath;
    }
}

