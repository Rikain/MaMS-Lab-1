package pl.edu.pwr.lab1.i242571;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "The onCreate() event");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "The onStart() event");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "The onResume() event");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "The onPause() event");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "The onStop() event");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "The onDestroy() event");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "The onRestart() event");
    }
}