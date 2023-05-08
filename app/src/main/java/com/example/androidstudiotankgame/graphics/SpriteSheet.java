package com.example.androidstudiotankgame.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.androidstudiotankgame.R;


public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 128;
    private static final int SPRITE_HEIGHT_PIXELS = 128;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getWaterSprite() {
        return getSpriteByIndex(0, 0);
    }

    public Sprite getLavaSprite() {
        return getSpriteByIndex(0, 1);
    }

    public Sprite getGroundSprite() {
        return getSpriteByIndex(0, 2);
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(0, 3);
    }

    public Sprite getTreeSprite() {
        return getSpriteByIndex(0, 4);
    }


    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                (idxCol + 1)*SPRITE_WIDTH_PIXELS,
                (idxRow + 1)*SPRITE_HEIGHT_PIXELS
        ));
    }
}