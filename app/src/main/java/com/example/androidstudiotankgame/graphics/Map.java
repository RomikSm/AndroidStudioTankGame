package com.example.androidstudiotankgame.graphics;

import static com.example.androidstudiotankgame.Game.screen_height;
import static com.example.androidstudiotankgame.Game.screen_width;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.example.androidstudiotankgame.GameDisplay;
import com.example.androidstudiotankgame.R;

public class Map {
    private Bitmap bitmap;


    public Map(Context context){
        int right = (int)((double)screen_height/10*21);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tilemap);
        bitmap = Bitmap.createScaledBitmap(bitmap, screen_width, screen_height, false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, new Matrix(), null);
    }
}
