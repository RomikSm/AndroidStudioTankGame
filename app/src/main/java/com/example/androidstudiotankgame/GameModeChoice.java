package com.example.androidstudiotankgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class GameModeChoice extends AppCompatActivity {

    ImageButton onlineBtn;
    ImageButton offlineBtn;
    public static String game_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_choice);

        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        onlineBtn = findViewById(R.id.onlineBtn);
        offlineBtn = findViewById(R.id.offlineBtn);

        offlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game_mode = "offline";
                openTankChoiceActivity();
            }
        });

        onlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game_mode = "online";
                openOnlineMenuActivity();
            }
        });
    }

    public void openTankChoiceActivity(){
        Intent intent = new Intent(this, TankChoice.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openOnlineMenuActivity(){
        Intent intent = new Intent(this, PlayerName.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}