package com.kocapplication.pixeleye.kockocapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.androidquery.AQuery;

import java.io.File;

/**
 * Created by Administrator on 2015-07-13.
 */
public class CameraCapture extends Activity {

    private ImageView iv;
    private Uri mImageCaptureUri;
    private String uriLargeImagePath, uriThumbnailImagePath;
    private Button btn_camera_next;
    private Cursor mCursor;

    public String getUriLargeImage() {return this.uriLargeImagePath;}
    public String getUriThumbnailImagePath() {return this.uriThumbnailImagePath;}
    public String get_mImageCaptureUriPath() {return this.mImageCaptureUri.getPath();}

    private AQuery aq;

    public AQuery getAq() {return this.aq;}
    public ImageView getIv() {
        return this.iv;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_camera);

        this.aq = new AQuery(this);

        this.iv = (ImageView)findViewById(R.id.iv_camera);
        this.btn_camera_next = (Button)findViewById(R.id.btn_camera_next);
        btn_camera_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("path", get_mImageCaptureUriPath());
                Log.d("Debug","mImageCapturePath : "+getUriThumbnailImagePath());
                setResult(1, intent1);
                finish();
            }
        });

        Intent camera = new Intent("android.media.action.IMAGE_CAPTURE");

        startActivityForResult(camera, 1);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 || requestCode == 1) {
            Log.d("Debug", "onActivityResult start");
            if (resultCode != RESULT_OK)
                return;

            switch (requestCode) {
                case 1:
                    Log.i("TAG", "Inside PICK_FROM_CAMERA");

                    // Describe the columns you'd like to have returned. Selecting from
                    // the Thumbnails location gives you both the Thumbnail Image ID, as
                    // well as the original image ID

                    try {
                        // Create new Cursor to obtain the file Path for the large image

                        String[] largeFileProjection = {
                                MediaStore.Images.ImageColumns._ID,
                                MediaStore.Images.ImageColumns.DATA};

                        String largeFileSort = MediaStore.Images.ImageColumns._ID
                                + " DESC";
//                        Cursor myCursor = this.managedQuery(
//                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                largeFileProjection, null, null, largeFileSort);

                        mCursor = this.managedQuery(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                largeFileProjection, null, null, largeFileSort);

                        String largeImagePath = "";

                        try {
                            mCursor.moveToFirst();

                            // This will actually give yo uthe file path location of the
                            // image.
                            largeImagePath = mCursor
                                    .getString(mCursor
                                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                            mImageCaptureUri = Uri.fromFile(new File(
                                    largeImagePath));

                        } finally {
                            // myCursor.close();
                        }

                        // 찍은 이미지 화면에 띄우기
                        Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(),this.mImageCaptureUri);


                        int id = mCursor.getInt(mCursor.getColumnIndex(MediaStore.MediaColumns._ID));

                        // Thumbnail 만들어 버림
                        MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(),id,MediaStore.Images.Thumbnails.MINI_KIND,null );


//                        Log.d("Debug","uriLargeImage : "+uriLargeImage.toString());
//                        Log.d("Debug","uriLargeImage : "+uriThumbnailImage.toString());
//                        Log.d("Debug","uriLargeImagePath : "+getUriLargeImage());
//                        Log.d("Debug","uriLargeImagePath : "+getUriThumbnailImagePath());
                        Log.d("Debug","mImageCaptureUri : "+this.mImageCaptureUri.toString());
                        Log.d("Debug","mImageCaptureUri : "+this.mImageCaptureUri.getPath());

                        this.iv.setImageBitmap(bm);

                        // I've left out the remaining code, as all I do is assign the
                        // URI's
                        // to my own objects anyways...
                    } catch (Exception e) {

                        Log.i("TAG",
                                "inside catch Samsung Phones exception " + e.toString());

                    }


                    try {

                        Log.i("TAG", "URI Normal:" + mImageCaptureUri.getPath());
                    } catch (Exception e) {
                        Log.i("TAG", "Excfeption inside Normal URI :" + e.toString());
                    }

                    break;
                default:
                    break;
            }
        }
    }


                    //doCrop();


       //     aq.id(this.iv).image(this.mImageCaptureUri.getPath());

      //      Bitmap b = new Bitmap();
         //   Log.d("Debug", "Image Capture Path : " + this.mImageCaptureUri.getPath());

       //     this.mImageCaptureUri.getPath();


    @Override
    protected void onDestroy()
    {
        Log.d("Debug","onDestroy start");
        super.onDestroy();
    }
}
