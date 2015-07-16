package com.kocapplication.pixeleye.kockocapp;

/**
 * Created by Administrator on 2015-07-13.
 */



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

;

/**
 * Created by pixeleye02 on 2015-07-02.
 */
public class Thumbnail {
    public static int width=300, height=248;
    static public String createThumbnail(String targetPath,String targetName)
    {
        String result="";
        int degree =0;
        makeKocDir();// \\ /storage/emulated/0/KocKoc 디렉토리 생성
        Bitmap bitmap = BitmapFactory.decodeFile(targetPath + targetName);
        File f = new File(targetPath,targetName);
        if(f.exists()) {
            degree = getPhotoOrientationDegree(targetPath + targetName);//사진의 가로,세로 여부 얻어오기
            FileOutputStream out = null;//파일을 쓰기위한 FileOutputStream 생성
            try {
                // Log.d("targetPath", makePath + makeName);
                String makePath = String.valueOf(Environment.getExternalStorageDirectory()) + "/KocKoc/";
                StringTokenizer stk = new StringTokenizer(targetName, ".");
                result =stk.nextToken()+"_Main.jpg";
                out = new FileOutputStream(makePath + result);//thumbnail 파일이 생길 위치를 지정하며 메모리 할당
                if (degree == 0) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);//비트맵이미지 썸네일
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);//파일 쓰기
                    //deleteFile(strFilePath,filename);
                } else {
                    //bitmap = getRotatedBitmap(bitmap,90);
                    bitmap = Bitmap.createScaledBitmap(bitmap, height, width, true);//비트맵이미지 썸네일
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);//파일 쓰기
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            Log.e("Image","No Exist Image Name & Path");
        }
        return result;
    }

    static public void makeKocDir(){
        //storage/emulated/0/
        File tempDir = new File(String.valueOf(Environment.getExternalStorageDirectory())+"/KocKoc");
        if(!tempDir.exists()){
            tempDir.mkdir();
        }
    }
    public static boolean deleteFile(String DirPath, String FileName){
        File delFile = new File(DirPath,FileName);
        if(delFile.exists()) {
            if (delFile.delete()) return true;
            else
                return false;
        }else return false;
    }
    public synchronized static int getPhotoOrientationDegree(String filepath)
    {
        int degree = 0;
        ExifInterface exif = null;
        try
        {
            exif = new ExifInterface(filepath);
        }
        catch (IOException e)
        {
            // Log.e(TAG, "cannot read exif");
            e.printStackTrace();
        }
        if (exif != null)
        {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1)
            {
// We only recognize a subset of orientation tag values.
                switch (orientation)
                {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }
    public synchronized static Bitmap getRotatedBitmap(Bitmap bitmap, int degrees)
    {

        if ( degrees != 0 && bitmap != null )
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2 );
            try
            {
                Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                if (bitmap != b2)
                {
                    bitmap.recycle();
                    bitmap = b2;
                }
            }
            catch (OutOfMemoryError ex)
            {
// We have no memory to rotate. Return the original bitmap.
            }
        }
        return bitmap;
    }
}
