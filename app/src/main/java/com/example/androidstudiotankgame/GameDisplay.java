package com.example.androidstudiotankgame;

import static com.example.androidstudiotankgame.Game.map;

import android.graphics.Rect;

import com.example.androidstudiotankgame.gameobject.GameObject;

public class GameDisplay {
    public final Rect DISPLAY_RECT;
    private final int widthPixels;
    private final int heightPixels;
    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private double displayCenterX;
    private double gameCenterX;
    private double displayCenterY;
    private double gameCenterY;
    private GameObject centerObject;


    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject){
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        DISPLAY_RECT = new Rect(0, 0, widthPixels, heightPixels);

        this.centerObject = centerObject;

        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }

    public void update(){
//        if(centerObject.getPositionX()-displayCenterX<0 ){
//            gameCenterX = displayCenterX;
//        }else{
//            gameCenterX = centerObject.getPositionX();
//        }
//        if(centerObject.getPositionY()-displayCenterY<0){
//            gameCenterY = displayCenterY;
//        }else{
//            gameCenterY = centerObject.getPositionY();
//        }

//        gameCenterX = centerObject.getPositionX();
//        gameCenterY = centerObject.getPositionY();

        gameToDisplayCoordinateOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinateOffsetY = displayCenterY - gameCenterY;
    }

    public double gameToDisplayCoordinatesX(double x) {
        return x + gameToDisplayCoordinateOffsetX;
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinateOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) gameCenterX - widthPixels/2,
                (int) gameCenterY - heightPixels/2,
                (int) gameCenterX + widthPixels/2,
                (int) gameCenterY + heightPixels/2

        );
    }
}
