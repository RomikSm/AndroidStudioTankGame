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
    private SpriteSheet spriteSheet;
    private Rect rect;

    public Sprite(Context context, int drawable){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), drawable, bitmapOptions);
    }

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
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

    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                new Rect(x, y, x+getWidth(), y+getHeight()),
                null
        );
    }

    public int getWidth() {
        return rect.width();
    }

    public int getHeight() {
        return rect.height();
    }
}
