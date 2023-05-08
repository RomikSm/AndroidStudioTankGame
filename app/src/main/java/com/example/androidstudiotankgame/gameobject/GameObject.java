package com.example.androidstudiotankgame.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidstudiotankgame.GameDisplay;

public abstract class GameObject {
    protected double positionX;
    protected double positionY;
    protected double velocityX;
    protected double velocityY;
    protected double directionX;
    protected double directionY;

    public GameObject(Context context, double positionX, double positionY, double y, double radius){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    protected static double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
        return Math.sqrt(
            Math.pow(obj2.getPositionX() - obj1.getPositionX(), 2) +
            Math.pow(obj2.getPositionY() - obj1.getPositionY(), 2)
        );
    }

    public abstract void draw(@NonNull Canvas canvas, GameDisplay gameDisplay);

    public abstract void update();

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    protected double getDirectionX() {
        return directionX;
    }

    protected double getDirectionY() {
        return directionY;
    }
}
