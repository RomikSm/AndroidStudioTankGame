package com.example.androidstudiotankgame.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.androidstudiotankgame.GameDisplay;
import com.example.androidstudiotankgame.gameobject.GameObject;

public class Sprite {
    private Bitmap bitmap;

    public Sprite(Context context, int drawable){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), drawable, bitmapOptions);
    }

    public void draw(Canvas canvas, int left, int top, int right, int bottom, float rotationAngle, GameDisplay gameDisplay) {
        canvas.save();
        canvas.rotate(rotationAngle,(float) left+(right-left)/2,(float) top+(bottom-top)/2);
        canvas.drawBitmap(
                bitmap,
                null,
                new Rect(
                        left,
                        top,
                        right,
                        bottom
                ),
                null
        );
        canvas.restore();
    }
}
