package com.example.androidstudiotankgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OfflineGame extends AppCompatActivity {
    public int tank_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tank_type = getIntent().getIntExtra("tank_type", 0);
        setContentView(new Game(this, tank_type));

    }
}