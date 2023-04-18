package com.example.androidstudiotankgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PlayerName extends AppCompatActivity {
    ImageButton continueBtn;
    EditText usernameEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        //making fullscreen with no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        continueBtn = findViewById(R.id.continueBtn);
        usernameEditText = findViewById(R.id.usernameEditText);


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(usernameEditText)){
                    Toast.makeText(PlayerName.this, "Enter your name!", Toast.LENGTH_SHORT).show();
                }else openGameModeChoiceActivity();;

            }
        });

    }

    public void openGameModeChoiceActivity(){
        Intent intent = new Intent(this, GameModeChoice.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}