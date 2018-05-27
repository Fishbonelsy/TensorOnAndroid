package com.live.longsiyang.tensorflowonandroid.tensorlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.util.Arrays;

public class MnistUtils {

    private static TensorFlowInferenceInterface inferenceInterface;

    private static final String MODEL_FILENAME = "file:///android_asset/frozen_model.pb";


    private static final String INPUT_DATA_NAME = "input:0";
    private static final String OUTPUT_SCORES_NAME = "output:0";

    public static void init(Context context){
        inferenceInterface = new TensorFlowInferenceInterface(context.getAssets() , MODEL_FILENAME);
    }

    public static int recgnizeNum(int[] greyBuffer){

        float[] floatInputBuffer = new float[greyBuffer.length];
        float[] outputScores = new float[10];
        String[] outputScoresNames = new String[] {OUTPUT_SCORES_NAME};

        for (int i = 0 ; i < greyBuffer.length ; i++){
            floatInputBuffer[i] = 255 - greyBuffer[i];
        }

        Log.d("tag", "tensorflowonandroid input array : " + Arrays.toString(floatInputBuffer));
        // Run the model.
        inferenceInterface.feed(INPUT_DATA_NAME, floatInputBuffer,1,784);
        inferenceInterface.run(outputScoresNames);
        inferenceInterface.fetch(OUTPUT_SCORES_NAME, outputScores);
        Log.d("tag", "tensorflowonandroid output array : " + Arrays.toString(outputScores));
        // get max
        int result = 0;
        for (int i = 0 ; i < outputScores.length ; i++){
            if (outputScores[i] > outputScores[result]){
                result = i;
            }
        }
        return result;
    }
}
