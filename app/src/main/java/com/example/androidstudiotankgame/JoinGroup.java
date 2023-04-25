package com.example.androidstudiotankgame;


import static com.example.androidstudiotankgame.MainActivity.dbReference;
import static com.example.androidstudiotankgame.MainActivity.gameCode;
import static com.example.androidstudiotankgame.PlayerName.group_uuid;
import static com.example.androidstudiotankgame.PlayerName.second_player_uuid;
import static com.example.androidstudiotankgame.PlayerName.user_uuid;
import static com.example.androidstudiotankgame.PlayerName.username;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinGroup extends AppCompatActivity{

    ImageButton joinImageButton;
    EditText enterCodeET;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);

        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        joinImageButton = findViewById(R.id.joinImageButton);
        enterCodeET = findViewById(R.id.enterCodeET);


        joinImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameCode = enterCodeET.getText().toString();

                pushNameToDatabase();


                dbReference.child(gameCode).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        HashMap<String, HashMap> valueMap = (HashMap) snapshot.getValue();
                        List<String> list = new ArrayList<>(valueMap.keySet());
                        list.remove(user_uuid);
                        group_uuid = list.get(0);
                        second_player_uuid = group_uuid;
                        if(snapshot.getChildrenCount() == 2){
                            openTankChoiceActivity();
                            dbReference.child(gameCode).removeEventListener(this);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("The read failed: " + error.getCode());
                    }
                });
            }
        });



    }

    private void pushNameToDatabase(){
        Map<String, String> map = new HashMap<>();
        map.put("name", username);
        dbReference.child(gameCode).child(user_uuid).setValue(map);
    }

    public void openTankChoiceActivity(){
        Intent intent = new Intent(this, TankChoice.class);
        intent.putExtra("game_mode", "online");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

}