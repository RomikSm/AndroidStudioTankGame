package com.example.androidstudiotankgame;


import static com.example.androidstudiotankgame.Game.player1;
import static com.example.androidstudiotankgame.MainActivity.dbReference;
import static com.example.androidstudiotankgame.PlayerName.group_uuid;
import static com.example.androidstudiotankgame.PlayerName.second_player_uuid;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Objects;

public class OnlineGame extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new Game(this));


        DatabaseReference groupReference = dbReference.child(group_uuid);
        //getting values from Firebase and setting second player's tank
        groupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, HashMap> valueMap = (HashMap) snapshot.getValue();
                assert valueMap != null;
                String retrievedPosX = String.valueOf(Objects.requireNonNull(valueMap.get(second_player_uuid)).get("posX"));
                String retrievedPosY = String.valueOf(Objects.requireNonNull(valueMap.get(second_player_uuid)).get("posY"));
                float retrievedRotationAngle = Float.parseFloat(String.valueOf(Objects.requireNonNull(valueMap.get(second_player_uuid)).get("rotation_angle")));
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