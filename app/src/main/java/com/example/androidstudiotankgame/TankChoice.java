package com.example.androidstudiotankgame;

import static com.example.androidstudiotankgame.GameModeChoice.game_mode;
import static com.example.androidstudiotankgame.MainActivity.dbReference;
import static com.example.androidstudiotankgame.MainActivity.gameCode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class TankChoice extends AppCompatActivity implements View.OnClickListener{

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
        waterTankBtn.setOnClickListener(this);
        earthTankBtn = findViewById(R.id.earthTankBtn);
        earthTankBtn.setOnClickListener(this);
        fireTankBtn = findViewById(R.id.fireTankBtn);
        fireTankBtn.setOnClickListener(this);
        airTankBtn = findViewById(R.id.airTankBtn);
        airTankBtn.setOnClickListener(this);

//        waterTankBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tank_type = R.drawable.water_tank;
//                if(game_mode.equals("online")){
//                    openWaitingRoomActivity();
//                }
//
//            }
//        });
//
//        earthTankBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tank_type = R.drawable.earth_tank;
//                openWaitingRoomActivity();
//            }
//        });
//
//        fireTankBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tank_type = R.drawable.fire_tank;
//                openWaitingRoomActivity();
//            }
//        });
//
//        airTankBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tank_type = R.drawable.air_tank;
//                openWaitingRoomActivity();
//            }
//        });
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.waterTankBtn:
                tank_type = R.drawable.water_tank;
                break;
            case R.id.earthTankBtn:
                tank_type = R.drawable.earth_tank;
                break;
            case R.id.fireTankBtn:
                tank_type = R.drawable.fire_tank;
                break;
            case R.id.airTankBtn:
                tank_type = R.drawable.air_tank;
                break;
            default:
                break;
        }
        if(game_mode.equals("online")){
            openWaitingRoomActivity();
        }else if(game_mode.equals("offline")){
            openOfflineGameActivity();
        }
    }
}