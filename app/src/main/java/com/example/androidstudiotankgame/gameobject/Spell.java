package com.example.androidstudiotankgame.gameobject;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.androidstudiotankgame.GameLoop;
import com.example.androidstudiotankgame.R;

public class Spell extends Circle{

    public static final double SPEED_PIXELS_PER_SECOND = 600.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    public Spell(Context context, Player spellcaster) {
        super(
                context,
                ContextCompat.getColor(context, R.color.purple_200),
                spellcaster.getPositionX(),
                spellcaster.getPositionY(),
                10
        );
        velocityX = spellcaster.getDirectionX()*MAX_SPEED;
        velocityY = spellcaster.getDirectionY()*MAX_SPEED;
    }

    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;
    }
}
