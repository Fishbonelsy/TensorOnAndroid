package com.live.longsiyang.tensorflowonandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class ImageDataUtils{

    private static final int IMAGE_SIZE_DATA = 784;

    public static byte[] getImageData(String path){

        Bitmap originBitmap = getBitmapByPath(path);
        byte[] result = getImageData(originBitmap);
        return result;
    }


    public static byte[] getImageData(Bitmap bitmap){
        byte[] result;
        Bitmap resultBitmap = processBitmap(bitmap);
        result = transBitmap2Bytes(resultBitmap);
        return result;
    }


    private static Bitmap getBitmapByPath(String path){

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return bitmap;
    }

    private static Bitmap processBitmap(Bitmap originBitmap){
        Bitmap resultBitmap;

        // TODO scale Bitmap 压缩至 28 x 28
        resultBitmap = Bitmap.createBitmap(originBitmap);
        // TODO 彩色变黑白

        return resultBitmap;

    }

    private static byte[] transBitmap2Bytes(Bitmap bitmap){
        byte[] result = new byte[IMAGE_SIZE_DATA];
        // TODO 图片转数组
        return result;
    }
}