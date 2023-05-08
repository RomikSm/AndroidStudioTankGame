package com.example.androidstudiotankgame.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.androidstudiotankgame.graphics.Sprite;
import com.example.androidstudiotankgame.graphics.SpriteSheet;

public class TreeTile extends Tile {
    private final Sprite sprite;

    public TreeTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getTreeSprite();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
