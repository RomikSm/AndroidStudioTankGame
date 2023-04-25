package com.example.androidstudiotankgame;

import static com.example.androidstudiotankgame.MainActivity.dbReference;
import static com.example.androidstudiotankgame.MainActivity.gameCode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class TankChoice extends AppCompatActivity {

    ImageButton waterTankBtn;
    ImageButton earthTankBtn;
    ImageButton fireTankBtn;
    ImageButton airTankBtn;
    public static int tank_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_choice);

        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        waterTankBtn = findViewById(R.id.waterTankBtn);
        earthTankBtn = findViewById(R.id.earthTankBtn);
        fireTankBtn = findViewById(R.id.fireTankBtn);
        airTankBtn = findViewById(R.id.airTankBtn);


        waterTankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tank_type = R.drawable.water_tank;
                openWaitingRoomActivity();

            }
        });

        earthTankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tank_type = R.drawable.earth_tank;
                openWaitingRoomActivity();
            }
        });

        fireTankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tank_type = R.drawable.fire_tank;
                openWaitingRoomActivity();
            }
        });

        airTankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tank_type = R.drawable.air_tank;
                openWaitingRoomActivity();
            }
        });
    }

    public void openOfflineGameActivity(){
        Intent intent = new Intent(this, OfflineGame.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openWaitingRoomActivity(){
        Intent intent = new Intent(this, WaitingRoom.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}