package com.example.androidstudiotankgame;




import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Player {

    private double positionX;
    private double positionY;
    private final Paint paint;
    Bitmap tankBitMap;
    private double velocityX;
    private double velocityY;
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private int playerCenterX = 300/2;
    private int playerCenterY = 210/2;



    public Player(Context context, double positionX, double positionY, Bitmap tankBitMap){
        this.positionX = positionX;
        this.positionY = positionY;
        this.tankBitMap = tankBitMap;

        paint = new Paint();

    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(tankBitMap, null, new Rect((int) (positionX-playerCenterX), (int) (positionY-playerCenterY), (int) (positionX+playerCenterX), (int) (positionY+playerCenterY)), paint);

    }

    public void update(Joystick joystick) {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
