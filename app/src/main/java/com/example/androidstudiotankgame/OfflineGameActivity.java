package com.example.androidstudiotankgame;



import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class OfflineGameActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("OfflineGameActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);


        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setting content view to game so that objects in the Game class can be rendered to the screen
        game = new Game(this);
        setContentView(game);
    }

    @Override
    protected void onStart() {
        Log.d("OfflineGameActivity.java", "onCreate()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("OfflineGameActivity.java", "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("OfflineGameActivity.java", "onPause()");
        game.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("OfflineGameActivity.java", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("OfflineGameActivity.java", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.d("OfflineGameActivity.java", "onBackPressed()");
        //super.onBackPressed();
    }
}