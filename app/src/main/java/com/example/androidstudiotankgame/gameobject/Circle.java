package com.example.androidstudiotankgame.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.androidstudiotankgame.GameDisplay;

public abstract class Circle extends GameObject {

    protected Paint paint;
    protected double radius;

    public Circle(Context context, int color, double positionX, double positionY, double radius) {
        super(context, positionX, positionY, positionY, radius);

        this.radius = radius;

        paint = new Paint();
        paint.setColor(color);
    }

    //Checks if two circle objects are colliding, based on their positions and radii
    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distance = getDistanceBetweenObjects(obj1, obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        if(distance < distanceToCollision) return true;
        return false;
    }

    private double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        canvas.drawCircle(
                (float) gameDisplay.gameToDisplayCoordinatesX(positionX),
                (float) gameDisplay.gameToDisplayCoordinatesY(positionY),
                (float) radius,
                paint
        );
    }

}
