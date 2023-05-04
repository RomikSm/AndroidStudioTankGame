package com.example.androidstudiotankgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private ImageButton startBtn;

    public static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static final DatabaseReference dbReference = database.getReference("Groups");

    public static String gameCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameModeChoiceActivity();
            }
        });
    }


    public void openGameModeChoiceActivity(){
        Intent intent = new Intent(this, GameModeChoice.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}