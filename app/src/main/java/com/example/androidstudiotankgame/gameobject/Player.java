package com.example.androidstudiotankgame.gameobject;

import static com.example.androidstudiotankgame.MainActivity.dbReference;

import static com.example.androidstudiotankgame.PlayerName.username;
import static com.example.androidstudiotankgame.TankChoice.tank_type;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.example.androidstudiotankgame.GameDisplay;
import com.example.androidstudiotankgame.GameLoop;
import com.example.androidstudiotankgame.gamepanel.HealthBar;
import com.example.androidstudiotankgame.gamepanel.Joystick;
import com.example.androidstudiotankgame.R;
import com.example.androidstudiotankgame.utils.Utils;
import com.example.androidstudiotankgame.graphics.Sprite;
import java.util.HashMap;
import java.util.Map;

public class Player extends Rectangle {

    private final Joystick joystick;
    public static final int MAX_HEALTH_POINTS = 5;
    private Bitmap tankBitMap;
    public static final double SPEED_PIXELS_PER_SECOND = 600.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final int PLAYER_HEIGHT = 80;
    private final int PLAYER_WIDTH = PLAYER_HEIGHT*3/2;

    private final int PLAYER_HALF_WIDTH = PLAYER_WIDTH /2;
    private final int PLAYER_HALF_HEIGHT = PLAYER_HEIGHT/2;

    private int tankType;
    private HealthBar healthBar;
    private int healthPoints;
    private Sprite sprite;


    public Player(@NonNull Context context, Joystick joystick, double positionX, double positionY, Sprite sprite){
        super(context, positionX, positionY);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context, this);
        this.healthPoints = MAX_HEALTH_POINTS;
        this.sprite = sprite;
        this.x1 = positionX - PLAYER_HALF_WIDTH;
        this.y1 = positionY - PLAYER_HALF_HEIGHT;
        this.x2 = positionX + PLAYER_HALF_WIDTH;
        this.y2 = positionY + PLAYER_HALF_HEIGHT;
        this.previousRotationAngle = 0;
    }


    public void update() {
        //updating velocity based on actuator
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;

        //updating position
        positionX += velocityX;
        positionY += velocityY;
        x1 = positionX - PLAYER_HALF_WIDTH;
        y1 = positionY - PLAYER_HALF_HEIGHT;
        x2 = positionX + PLAYER_HALF_WIDTH;
        y2 = positionY + PLAYER_HALF_HEIGHT;

        //updating direction
        if(velocityX !=0 || velocityY !=0){
            //Normalize velocity to get direction (unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }

//        if(game_mode.equals("online")) {
//            pushPositionToDatabase();
//        }
    }

    //method that pushes updated data to realtime database
    private void pushPositionToDatabase(){
        Map<String, String> map = new HashMap<>();
        map.put("name", username);
        map.put("posX", String.valueOf(positionX));
        map.put("posY", String.valueOf(positionY));
        map.put("tank_type", String.valueOf(tank_type));
        map.put("rotation_angle", String.valueOf(previousRotationAngle));
        //dbReference.child(group_uuid).child(user_uuid).setValue(map);
        dbReference.child("test").child("test").setValue(map);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, int rotationAngle) {
        if(rotationAngle!=0) previousRotationAngle = rotationAngle;
        sprite.draw(
                canvas,
                (int) gameDisplay.gameToDisplayCoordinatesX(x1),
                (int) gameDisplay.gameToDisplayCoordinatesY(y1),
                (int) gameDisplay.gameToDisplayCoordinatesX(x2),
                (int) gameDisplay.gameToDisplayCoordinatesY(y2),
                previousRotationAngle
        );
        healthBar.draw(canvas, gameDisplay);
    }

    public void setPreviousRotationAngle(float previousRotationAngle) {
        this.previousRotationAngle = previousRotationAngle;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if(healthPoints >= 0) {
            this.healthPoints = healthPoints;
        }
    }
}
