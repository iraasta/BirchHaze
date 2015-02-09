package gwr.com.birchhaze.STT;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpeechToText {
    //voice recognition and general variables

    //variable for checking Voice Recognition support on user device
    public static final int VR_REQUEST = 999;

    //ListView for displaying suggested words
    private ListView wordList;

    //Log tag for output information
    public static final String LOG_TAG = "SpeechRepeatActivity";//***enter your own tag here***

//TTS variables

    //variable for checking TTS engine data on user device
    private int MY_DATA_CHECK_CODE = 0;

    //Text To Speech instance
    private TextToSpeech repeatTTS;
    private Activity context;

    public SpeechToText(Activity context, Button speechBtn)
    {
        this.context = context;
        PackageManager packManager = context.getPackageManager();
        List<ResolveInfo> intActivities = packManager.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (intActivities.size() != 0) {
            //speech recognition is supported - detect user button clicks
            listenToSpeech();
        }
        else
        {
            //speech recognition not supported, disable button and output message
            speechBtn.setEnabled(false);
            Toast.makeText(context, "Oops - Speech recognition not supported!", Toast.LENGTH_LONG).show();
        }
    }
    private void listenToSpeech() {

        //start the speech recognition intent passing required data
        Intent listenIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //indicate package
        listenIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        //message to display while listening
        listenIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a word!");
        //set speech model
        listenIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //specify number of results to retrieve
        listenIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pl-PL");

        listenIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);

        //start listening
        context.startActivityForResult(listenIntent, VR_REQUEST);
    }
    /**
     * onActivityResults handles:
     *  - retrieving results of speech recognition listening
     *  - retrieving result of TTS data check
     */



}
