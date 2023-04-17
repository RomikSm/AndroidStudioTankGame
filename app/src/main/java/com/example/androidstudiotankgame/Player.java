package com.example.androidstudiotankgame;




import android.content.Context;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

public class Player {

    private double positionX;
    private double positionY;
    private Paint paint;
    Bitmap tankBitMap;


    public Player(Context context, double positionX, double positionY, Bitmap tankBitMap){
        this.positionX = positionX;
        this.positionY = positionY;
        this.tankBitMap = tankBitMap;

        paint = new Paint();

    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(tankBitMap, null, new Rect((int) (positionX-150), (int) (positionY-105), (int) (positionX+150), (int) (positionY+105)), paint);

    }

    public void update() {
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
