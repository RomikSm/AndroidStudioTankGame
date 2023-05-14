package com.example.androidstudiotankgame.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.androidstudiotankgame.GameDisplay;
import com.example.androidstudiotankgame.R;

public class Map {
    private Bitmap bitmap;

    public Map(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tilemap, bitmapOptions);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(
                bitmap,
                null,
                new Rect(
                        0,
                        0,
                        1920,
                        1152

                ),
                null
        );

    }
}
