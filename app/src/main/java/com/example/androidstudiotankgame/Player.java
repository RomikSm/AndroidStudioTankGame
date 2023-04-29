package com.example.androidstudiotankgame;

import static com.example.androidstudiotankgame.MainActivity.dbReference;
import static com.example.androidstudiotankgame.MainActivity.screenHeight;
import static com.example.androidstudiotankgame.PlayerName.group_uuid;
import static com.example.androidstudiotankgame.PlayerName.user_uuid;
import static com.example.androidstudiotankgame.PlayerName.username;
import static com.example.androidstudiotankgame.TankChoice.tank_type;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.annotation.NonNull;


import java.util.HashMap;
import java.util.Map;

public class Player {

    private double positionX;
    private double positionY;
    private final Paint paint;
    private Bitmap tankBitMap;
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private final double MULTIPLIER = (double)240/1440;
    private final int PLAYER_HEIGHT = (int) (MULTIPLIER*screenHeight);
    private final int PLAYER_WIDTH = PLAYER_HEIGHT*3/2;

    private final int PLAYER_CENTER_X = PLAYER_WIDTH /2;
    private final int PLAYER_CENTER_Y = PLAYER_HEIGHT/2;
    private float previousRotationAngle = 0;
    private int tankType;

    public Player(@NonNull Context context, double positionX, double positionY, int drawable){
        this.positionX = positionX;
        this.positionY = positionY;
        this.tankType = drawable;
        this.tankBitMap = BitmapFactory.decodeResource(context.getResources(), tankType);
        paint = new Paint();
    }

    public void draw(@NonNull Canvas canvas, float rotationAngle) {
        canvas.save();
        if(rotationAngle!=0) previousRotationAngle = rotationAngle;
        canvas.rotate(previousRotationAngle, (float) (positionX), (float) (positionY));
        canvas.drawBitmap(tankBitMap, null, new Rect((int) (positionX - PLAYER_CENTER_X), (int) (positionY - PLAYER_CENTER_Y), (int) (positionX + PLAYER_CENTER_X), (int) (positionY + PLAYER_CENTER_Y)), paint);
        canvas.restore();
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.rotate(previousRotationAngle, (float) (positionX), (float) (positionY));
        canvas.drawBitmap(tankBitMap, null, new Rect((int) (positionX - PLAYER_CENTER_X), (int) (positionY - PLAYER_CENTER_Y), (int) (positionX + PLAYER_CENTER_X), (int) (positionY + PLAYER_CENTER_Y)), paint);
        canvas.restore();
    }

    public void update(@NonNull Joystick joystick) {

        double velocityX = joystick.getActuatorX() * MAX_SPEED;
        double velocityY = joystick.getActuatorY() * MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;
        pushPositionToDatabase();
    }

    private void pushPositionToDatabase(){
        Map<String, String> map = new HashMap<>();
        map.put("name", username);
        map.put("posX", String.valueOf(positionX));
        map.put("posY", String.valueOf(positionY));
        map.put("tank_type", String.valueOf(tank_type));
        map.put("rotation_angle", String.valueOf(previousRotationAngle));
        dbReference.child(group_uuid).child(user_uuid).setValue(map);
    }


    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void setPreviousRotationAngle(float previousRotationAngle) {
        this.previousRotationAngle = previousRotationAngle;
    }
}
