package com.example.androidstudiotankgame.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.androidstudiotankgame.graphics.Sprite;
import com.example.androidstudiotankgame.graphics.SpriteSheet;

public class GrassTile extends Tile {
    private final Sprite sprite;

    public GrassTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getGrassSprite();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
