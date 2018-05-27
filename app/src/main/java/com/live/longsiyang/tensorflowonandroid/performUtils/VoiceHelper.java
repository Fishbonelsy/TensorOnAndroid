package com.live.longsiyang.tensorflowonandroid.performUtils;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;

public class VoiceHelper{

    private static TextToSpeech textToSpeech;
    private static  TextToSpeech.OnInitListener listener;
    private VoiceHelper(){

    }

    private volatile static VoiceHelper instance;

    public static VoiceHelper getInstance() {
        if (instance == null){
            synchronized (VoiceHelper.class){
                instance = new VoiceHelper();



            }
        }
        return instance;
    }

    public void init(Context context){
        listener = new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        };
        textToSpeech = new TextToSpeech(context, listener); // 参数Context,TextToSpeech.OnInitListener
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void speakStr(String str){
        textToSpeech.setPitch(1.0f);
        textToSpeech.speak(str, TextToSpeech.QUEUE_FLUSH , null , null);
    }

}
