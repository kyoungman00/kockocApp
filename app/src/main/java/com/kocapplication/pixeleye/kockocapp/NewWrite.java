package com.kocapplication.pixeleye.kockocapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-06-30.
 */
public class NewWrite extends Activity {

    private Button btn_back, btn_gallery, btn_tagPlus, btn_upLoad, btn_camera;
    private EditText edit_tag, edit_text;
    private TextView tv_tag;
    private LinearLayout ll_imageAppend, ll_bottom, ll_tagAppend;

    private int displayWidth,displayHeight;
    private Bitmap[] resultBitmap;
    private ArrayList<String> resultBitmapPath;
    private AQuery aq;



    private DisplayInfo displayInfo;
    private Board board;


    public AQuery getAq(){
        return  this.aq;
    }

    public ArrayList<String> getResultBitmapPath(){return this.resultBitmapPath;}

    public TextView getTv_tag(){
        return this.tv_tag;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_write);
        Intent intent = getIntent();
        this.aq = new AQuery(this);
        this.ll_imageAppend = (LinearLayout)findViewById(R.id.layout_append_image);
        this.ll_tagAppend = (LinearLayout)findViewById(R.id.layout_append_tag);
        this.displayInfo = (DisplayInfo)getApplicationContext();

        this.board = new Board();
        //this.displayWidth = intent.getIntExtra("displayWidth",0);
        //this.displayHeight = intent.getIntExtra("displayHeight",0);


        View.OnClickListener btn_Click_Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_back :
                        Toast.makeText(getApplicationContext(),"Back Button is Clicked",Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    case R.id.btn_gallery :
                        Intent intent = new Intent(getApplicationContext(),Gallery.class);
                        if(resultBitmapPath != null) {
                            intent.putStringArrayListExtra("path",resultBitmapPath);
                        }
                        //startActivity(intent);
                       // ll_imageAppend.removeAllViews();
                        startActivityForResult(intent,1000);
                        break;
                    case R.id.btn_tagPlus :
                        if( ! (edit_tag.getText().length() == 0) ) {
                            //Toast.makeText(getApplicationContext(), String.valueOf(edit_tag.getText().length()) ,Toast.LENGTH_LONG).show();

                            TextView t = addTextView(edit_tag.getText().toString());
                            t.setTag("#"+edit_tag.getText().toString());
                            ll_tagAppend.removeView(tv_tag);
                            ll_tagAppend.addView(t);

                            edit_tag.setText("");
                        }
                        else {
                            //태그 입력이 없을경우
                        }

                        break;
                    case R.id.btn_upLoad :

                        if(edit_text.getText().length() == 0 || ll_tagAppend.getChildAt(0).getTag().toString().equals("HELP_TAG_KM_")) {
                            return;
                        }
                        Board board1 = new Board();
                        board1.setuserNo(1);

                        // 텍스트 삽입
                        board1.settext(edit_text.getText().toString());

                        // 테그 삽입
                        for(int i=0; i<ll_tagAppend.getChildCount(); i++) {
                            Log.e("error","write : "+ll_tagAppend.getChildAt(i).getTag().toString());
                            board1.HashAdd(ll_tagAppend.getChildAt(i).getTag().toString());
                        }

                        // 이미지 삽입
                        if(getResultBitmapPath() != null) {
                            for (int i = 0; i < getResultBitmapPath().size(); i++) {
                                Log.e("error","write : "+getResultBitmapPath().get(i));

                                String path = getResultBitmapPath().get(i).toString();
                                int split = getResultBitmapPath().get(i).toString().lastIndexOf("/");

                                String name = path.substring(split+1);
                                path = path.substring(0,split+1);

                                Log.e("error","path : "+path+" name : "+name);
                                board1.ImageAdd(path,name);
                            }
                        }

                        board1.write_Borad();
                        finish();

                        break;

                    case R.id.btn_camera :
                        Intent intent2 = new Intent(getApplicationContext(),CameraCapture.class);
                        startActivityForResult(intent2,1);
                        break;
                }
            }
        };

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(btn_Click_Listener);

        btn_gallery = (Button)findViewById(R.id.btn_gallery);
        btn_gallery.setOnClickListener(btn_Click_Listener);

        btn_camera = (Button)findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(btn_Click_Listener);

        this.btn_tagPlus = (Button)findViewById(R.id.btn_tagPlus);
        this.btn_tagPlus.setOnClickListener(btn_Click_Listener);

        this.edit_tag = (EditText)findViewById(R.id.edit_tag);

        this.edit_text = (EditText)findViewById(R.id.edit_text);
        this.edit_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //ll_bottom.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        this.ll_bottom = (LinearLayout)findViewById(R.id.ll_bottomMenu);

        this.btn_upLoad = (Button)findViewById(R.id.btn_upLoad);
        this.btn_upLoad.setOnClickListener(btn_Click_Listener);

        this.tv_tag = (TextView)findViewById(R.id.tv_tag);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(resultCode,resultCode,data);
        switch (resultCode){
            case 0 :
                if(data != null) {
                    ll_imageAppend.removeAllViews();
                    this.resultBitmapPath = data.getStringArrayListExtra("path");
                  //this.resultBitmap = new Bitmap[resultBitmapPath.length];
                      for (int i = 0; i < resultBitmapPath.size(); i++) {
                         //resultBitmap[i] = getAq().getCachedImage(resultBitmapPath[i]);
                            ImageView iv = addImageView(resultBitmapPath.get(i));
                          this.ll_imageAppend.addView((iv));
                      }
                }

                break;
            case 1 :
                if(data != null) {
                    ll_imageAppend.removeAllViews();
                    if(this.resultBitmapPath == null) {
                        this.resultBitmapPath = new ArrayList<String>();
                    }
                    this.resultBitmapPath.add(data.getStringExtra("path"));
                    Log.d("Debug","check! data.getStringExtra('path') : "+data.getStringExtra("path"));
                    for (int i = 0; i < resultBitmapPath.size(); i++) {
                        ImageView iv = addImageView(resultBitmapPath.get(i));
                        this.ll_imageAppend.addView((iv));
                    }
                }
            default:
                break;
        }
    }
    public ImageView addImageView(String imgPath){
        ImageView img = new ImageView(this);

        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(new LinearLayout.LayoutParams(displayInfo.getDisplayWidth() / 4, displayInfo.getDisplayWidth() / 4));
        margin.setMargins(10, 5, 10, 5);
        img.setLayoutParams(new LinearLayout.LayoutParams(margin));

        BitmapDrawable ob = new BitmapDrawable(getResources(), getAq().getCachedImage(imgPath));
        img.setTag(imgPath);
        img.setBackgroundDrawable(ob);

        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ll_imageAppend.removeView(v);
                resultBitmapPath.remove(v.getTag().toString());
                return true;
            }
        });

        return img;
    }

    public TextView addTextView(String text) {
        TextView t = new TextView(this);

        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        margin.setMargins(10, 0, 10, 0);
        t.setLayoutParams(new LinearLayout.LayoutParams(margin));

        t.setText("#"+text);
        t.setTextColor(getResources().getColor(R.color.innerTagColor));

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_tagAppend.removeView(v);
                if(ll_tagAppend.getChildCount() == 0) {
                    TextView t = addTextView("'태그'를 입력해주세요.");
                    t.setTag("HELP_TAG_KM_");
                    ll_tagAppend.addView(t);

                }
            }
        });

        return t;
    }
}
