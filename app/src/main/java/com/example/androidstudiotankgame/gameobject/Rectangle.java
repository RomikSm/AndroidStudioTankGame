package com.example.androidstudiotankgame.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.androidstudiotankgame.GameDisplay;

public abstract class Rectangle extends GameObject{
    protected double x1;
    protected double x2;
    protected double y1;
    protected double y2;
    protected float previousRotationAngle;

    public Rectangle(Context context, double positionX, double positionY) {
        super(context, positionX, positionY);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        canvas.drawCircle(
                (float) gameDisplay.gameToDisplayCoordinatesX(positionX),
                (float) gameDisplay.gameToDisplayCoordinatesY(positionY),
                (float) 60,
                new Paint()
        );
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public float getRotationAngle() {
        return previousRotationAngle;
    }
}
