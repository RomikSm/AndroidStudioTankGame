package com.example.androidstudiotankgame;


import static com.example.androidstudiotankgame.MainActivity.dbReference;
import static com.example.androidstudiotankgame.MainActivity.gameCode;
import static com.example.androidstudiotankgame.PlayerName.user_uuid;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class OnlineGame extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new Game(this));



        //getting values from Firebase and setting second player's tank
//        dbReference.child(gameCode).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                HashMap<String, HashMap> valueMap = (HashMap) snapshot.getValue();
//                String retrievedPosX = String.valueOf(valueMap.get(user_uuid).get("posX"));
//                player1.setPositionX(Double.parseDouble(retrievedPosX));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                System.out.println("The read failed: " + error.getCode());
//            }
//        });
    }


}
