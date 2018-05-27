package com.live.longsiyang.tensorflowonandroid;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.live.longsiyang.tensorflowonandroid.bitmapUtils.BitmapUtils;
import com.live.longsiyang.tensorflowonandroid.performUtils.VoiceHelper;
import com.live.longsiyang.tensorflowonandroid.tensorlib.MnistUtils;

public class MainActivity extends AppCompatActivity {

    PainterView mPainterView;
    Button mBtnReset , mBtnConfirm;
    ImageView mTestImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MnistUtils.init(this);
        VoiceHelper.getInstance().init(this);
        initView();
        initTest();
    }

    private void initView(){
        mPainterView = findViewById(R.id.painter_view);
        mBtnReset = findViewById(R.id.btn_reset);
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPainterView.clearPath();
            }
        });
        mBtnConfirm = findViewById(R.id.btn_confirm);
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                Bitmap bitmap = mPainterView.createBitmap();
                if (bitmap != null && !bitmap.isRecycled()){
                    Bitmap resizeBitmap = BitmapUtils.resizeBitmap(bitmap , 28,28);

                    int[] greyBuffer = BitmapUtils.getGreyBuffer(resizeBitmap);
                    Bitmap resultBitmap = BitmapUtils.creatBimtapByGreyBuffer(greyBuffer , 28,28);
                    mTestImg.setImageBitmap(resultBitmap);
                    int answer = MnistUtils.recgnizeNum(greyBuffer);
                    String str = " " + answer;
                    Toast.makeText(MainActivity.this , str, Toast.LENGTH_SHORT).show();
                    VoiceHelper.getInstance().speakStr(str);
                    mPainterView.clearPath();
                }

            }
        });
    }

    private void initTest(){
        mTestImg = findViewById(R.id.test_img);
    }
}
