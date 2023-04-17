package com.example.androidstudiotankgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private int playerWeight = 240;
    private int playerHeight = 160;
    private int playerCenterX = playerWeight/2;
    private int playerCenterY = playerHeight/2;
    private float previousRotationAngle = 0;


    public Player(Context context, double positionX, double positionY, int drawable){
        this.positionX = positionX;
        this.positionY = positionY;
        this.tankBitMap = BitmapFactory.decodeResource(context.getResources(), drawable);

        paint = new Paint();

    }


    public void draw(Canvas canvas, float rotationAngle) {
        canvas.save();
        if(rotationAngle!=0){
            previousRotationAngle = rotationAngle;
        }
        canvas.rotate(previousRotationAngle, (float) (positionX), (float) (positionY));
        canvas.drawBitmap(tankBitMap, null, new Rect((int) (positionX - playerCenterX), (int) (positionY - playerCenterY), (int) (positionX + playerCenterX), (int) (positionY + playerCenterY)), paint);
        canvas.restore();
    }

    public void update(Joystick joystick) {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;
    }

//    public void setPosition(double positionX, double positionY) {
//        this.positionX = positionX;
//        this.positionY = positionY;
//    }
}
