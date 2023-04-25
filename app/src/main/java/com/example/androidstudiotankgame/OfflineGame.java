package com.example.androidstudiotankgame;


import static com.example.androidstudiotankgame.Game.player1;
import static com.example.androidstudiotankgame.MainActivity.dbReference;
import static com.example.androidstudiotankgame.MainActivity.gameCode;
import static com.example.androidstudiotankgame.PlayerName.group_uuid;
import static com.example.androidstudiotankgame.PlayerName.second_player_uuid;
import static com.example.androidstudiotankgame.PlayerName.user_uuid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class OfflineGame extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new Game(this));



        //getting values from Firebase and setting second player's tank
        dbReference.child(group_uuid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, HashMap> valueMap = (HashMap) snapshot.getValue();
                String retrievedPosX = String.valueOf(valueMap.get(second_player_uuid).get("posX"));
                String retrievedPosY = String.valueOf(valueMap.get(second_player_uuid).get("posY"));
                float retrievedRotationAngle = Float.parseFloat(String.valueOf(valueMap.get(second_player_uuid).get("rotation_angle")));
                player1.setPositionX(Double.parseDouble(retrievedPosX));
                player1.setPositionY(Double.parseDouble(retrievedPosY));
                player1.setPreviousRotationAngle(retrievedRotationAngle);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });

    }
}