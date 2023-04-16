package com.example.androidstudiotankgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set window to fullscreen
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        //set content view to game, so that objects in the Game class can be rendered to the screen
        setContentView(new Game(this));
    }
}