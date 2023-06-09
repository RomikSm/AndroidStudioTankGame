package com.example.androidstudiotankgame;

import static com.example.androidstudiotankgame.MainActivity.database;
import static com.example.androidstudiotankgame.MainActivity.dbReference;
import static com.example.androidstudiotankgame.MainActivity.gameCode;
import static com.example.androidstudiotankgame.PlayerName.group_uuid;
import static com.example.androidstudiotankgame.PlayerName.second_player_uuid;
import static com.example.androidstudiotankgame.PlayerName.user_uuid;
import static com.example.androidstudiotankgame.PlayerName.username;
import static com.example.androidstudiotankgame.TankChoice.tank_type;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreateGroup extends AppCompatActivity {

    TextView codeTV;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        codeTV = findViewById(R.id.codeTV);

        gameCode = generateRandomCode();
        group_uuid = user_uuid;

        codeTV.setText(gameCode);

        pushDataToDatabase();

        dbReference.child(gameCode).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getChildrenCount() == 2){
                    openTankChoiceActivity();
                    HashMap<String, HashMap> valueMap = (HashMap) snapshot.getValue();
                    List<String> list = new ArrayList<>(valueMap.keySet());
                    list.remove(user_uuid);
                    second_player_uuid = list.get(0);
                    dbReference.child(gameCode).removeEventListener(this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });

    }


    private void pushDataToDatabase(){
        Map<String, String> map = new HashMap<>();
        map.put("name", username);
        dbReference.child(gameCode).child(user_uuid).setValue(map);
    }

    public static String generateRandomCode() {
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString().replace("-", "");
        return randomString.substring(0, 6).toUpperCase();
    }

    public void openTankChoiceActivity(){
        Intent intent = new Intent(this, TankChoice.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}