package com.example.androidstudiotankgame;

import static com.example.androidstudiotankgame.Game.player1;
import static com.example.androidstudiotankgame.MainActivity.dbReference;
import static com.example.androidstudiotankgame.MainActivity.gameCode;
import static com.example.androidstudiotankgame.PlayerName.group_uuid;
import static com.example.androidstudiotankgame.PlayerName.second_player_uuid;
import static com.example.androidstudiotankgame.PlayerName.user_uuid;
import static com.example.androidstudiotankgame.PlayerName.username;
import static com.example.androidstudiotankgame.TankChoice.tank_type;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class WaitingRoom extends AppCompatActivity {

    public static int retrieved_tank_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);

        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pushPositionToDatabase();

        //getting second player's tank type value from Firebase
        dbReference.child(group_uuid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount() == 2) {
                    HashMap<String, HashMap> valueMap = (HashMap) snapshot.getValue();
                    retrieved_tank_type = Integer.parseInt(String.valueOf(valueMap.get(second_player_uuid).get("tank_type")));

                    dbReference.child(gameCode).removeValue();
                    openOnlineGameActivity();
                    dbReference.child(group_uuid).removeEventListener(this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });

    }
    public void openOnlineGameActivity(){
        Intent intent = new Intent(this, OfflineGame.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void pushPositionToDatabase(){
        Map<String, String> map = new HashMap<>();
        map.put("name", username);
        map.put("posX", "500");
        map.put("posY", "500");
        map.put("tank_type", String.valueOf(tank_type));
        map.put("rotation_angle", "0");
        dbReference.child(group_uuid).child(user_uuid).setValue(map);
    }
}