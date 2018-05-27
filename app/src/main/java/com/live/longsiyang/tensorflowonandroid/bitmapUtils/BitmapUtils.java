package com.live.longsiyang.tensorflowonandroid.bitmapUtils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

public class BitmapUtils {


    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        int l = v.getLeft();
        int t = v.getTop();
        v.layout(l, t, l+v.getLayoutParams().width, t+v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();


        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;
    }

//    /**
//     * 将彩色图转换为灰度图
//     * @param img 位图
//     * @return  返回转换好的位图
//     */
//    public static Bitmap convertGreyImg(Bitmap img) {
//        int width = img.getWidth();         //获取位图的宽
//        int height = img.getHeight();       //获取位图的高
//
//        int []pixels = new int[width * height]; //通过位图的大小创建像素点数组
//
//        img.getPixels(pixels, 0, width, 0, 0, width, height);
//        int alpha = 0xFF << 24;
//        for(int i = 0; i < width; i++)  {
//            for(int j = 0; j < height; j++) {
//                int grey = pixels[width * i + j];
//
//                int red = ((grey  & 0x00FF0000 ) >> 16);
//                int green = ((grey & 0x0000FF00) >> 8);
//                int blue = (grey & 0x000000FF);
//
//                grey = (int)((float) red * 0.3 + (float)green * 0.59 + (float)blue * 0.11);
//                grey = alpha | (grey << 16) | (grey << 8) | grey;
//                pixels[width * i + j] = grey;
//            }
//        }
//        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//        result.setPixels(pixels, 0, width, 0, 0, width, height);
//        return result;
//    }
//
    public static Bitmap creatBimtapByGreyBuffer(int [] greyBuffer , int width , int height){

        final int alpha = 0xFF << 24;
        int []pixels = new int[width * height]; //通过位图的大小创建像素点数组

        for(int i = 0; i < height; i++)  {
            for(int j = 0; j < width; j++) {
                int grey = greyBuffer[width * i + j];
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        result.setPixels(pixels, 0, width, 0, 0, width, height);
        return result;
    }

    public static int[] getGreyBuffer(Bitmap bitmap){
        int width = bitmap.getWidth();         //获取位图的宽
        int height = bitmap.getHeight();       //获取位图的高
        int length = width*height;
        int[] greyBuffer = new int[length];
        int []pixels = new int[width * height]; //通过位图的大小创建像素点数组

        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for(int i = 0; i < width; i++)  {
            for(int j = 0; j < height; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey  & 0x00FF0000 ) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int)((float) red * 0.3 + (float)green * 0.59 + (float)blue * 0.11);
                greyBuffer[width * i + j] = grey;
            }
        }

        return greyBuffer;
    }
}
