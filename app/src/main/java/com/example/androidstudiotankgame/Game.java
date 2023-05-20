package com.example.androidstudiotankgame;

import static com.example.androidstudiotankgame.GameModeChoice.game_mode;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.androidstudiotankgame.gameobject.Circle;
import com.example.androidstudiotankgame.gameobject.Enemy;
import com.example.androidstudiotankgame.gameobject.Player;
import com.example.androidstudiotankgame.gameobject.Spell;
import com.example.androidstudiotankgame.gamepanel.GameOver;
import com.example.androidstudiotankgame.gamepanel.Joystick;
import com.example.androidstudiotankgame.gamepanel.Performance;
import com.example.androidstudiotankgame.graphics.Map;
import com.example.androidstudiotankgame.graphics.Sprite;
import com.example.androidstudiotankgame.utils.CircleRectangleCollisionDetector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Game manages all objects int the game and is responsible for updating all states and render all
 * objects to the screen
 */

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    public static Player player;
    public static Player player1;
    private final Joystick joystick;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Spell> spellList = new ArrayList<>();
    private int joystickPointerId = 0;
    private int numberOfSpellsToCast = 0;
    private GameOver gameOver;
    private Performance performance;
    private GameDisplay gameDisplay;
    public static Map map;
    public static int screen_height;
    public static int screen_width;


    public Game(Context context) {
        super(context);

        //get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screen_height = displayMetrics.heightPixels;
        screen_width = displayMetrics.widthPixels;

        gameLoop = new GameLoop(this, surfaceHolder);

        //initialize game map
        map = new Map(context);


        //initialize game panels
        performance = new Performance(context, gameLoop);
        gameOver = new GameOver(context);
        joystick = new Joystick(275, 700, 150, 75);

        //initialize game objects
        Sprite sprite = new Sprite(context, TankChoice.tank_type);
        player = new Player(getContext(), joystick, 2*500, 500, sprite);

        //initialize game display and center it around the player
        gameDisplay = new GameDisplay(screen_width, screen_height, player);


//        if(game_mode.equals("online")){
//            player1 = new Player(getContext(), joystick, 500, 500, retrieved_tank_type);
//        }
        if(game_mode.equals("offline")){

        }

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //handle touch event actions
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(joystick.getIsPressed()){
                    //Joystick was pressed before this event -> cast spell
                    numberOfSpellsToCast++;
                }else if(joystick.isPressed((double) event.getX(),(double) event.getY())){
                    //Joystick is pressed in this event -> setIsPressed(true) and store ID
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                }else{
                    //Joystick was not pressed not previously nor in this this event -> cast spell
                    numberOfSpellsToCast++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                //Joystick was pressed previously and is now moved
                if(joystick.getIsPressed()){
                    joystick.setActuator((double) event.getX(),(double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerId == event.getPointerId(event.getActionIndex())){
                    //Joystick was let go off -> setIsPressed(false) and resetActuator
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }

                return true;
        }

        return super.onTouchEvent(event);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if(gameLoop.getState().equals(Thread.State.TERMINATED)) {
            gameLoop = new GameLoop(this, holder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Log.d("Game.java", "surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //draw game map
        map.draw(canvas);

        for(Spell spell : spellList){
            spell.draw(canvas, gameDisplay);
        }

        //draw game objects
        player.draw(canvas, gameDisplay, (int) joystick.getRotationAngle());

        for (Enemy enemy : enemyList){
            enemy.draw(canvas, gameDisplay);
        }


        //draw game panels
        joystick.draw(canvas);
        performance.draw(canvas);

        // Draw Game over if the player is dead
        if(player.getHealthPoints() <= 0){
            gameOver.draw(canvas);
        }

//        player.draw(canvas, joystick.getRotationAngle());
//        if(game_mode.equals("online")){
//            player1.draw(canvas);
//        }
    }


    public void update() {
        //stop updating the game if the player is dead
        if(player.getHealthPoints() <= 0){
            return;
        }
        //update game state
        joystick.update();
        player.update();
        if(game_mode.equals("offline")){

            //Add enemy if it is time to spawn new enemies
            if(Enemy.readyToSpawn()){
                enemyList.add(new Enemy(getContext(), player));
            }

            //Update state of each enemy
            while(numberOfSpellsToCast > 0){
                spellList.add(new Spell(getContext(), player));
                numberOfSpellsToCast --;
            }
            for(Enemy enemy : enemyList){
                enemy.update();
            }

            //Update state of each spell
            for(Spell spell : spellList){
                spell.update();
            }

            //Iterate through enemyList and check for collision between each enemy and the player and
            //all spells in spellList
            Iterator<Enemy> iteratorEnemy = enemyList.iterator();
            while(iteratorEnemy.hasNext()){
                Circle enemy = iteratorEnemy.next();
                if(CircleRectangleCollisionDetector.isCircleTouchingRectangle(enemy, player)){
                    //Remove enemy if it collides with the player
                    iteratorEnemy.remove();
                    player.setHealthPoints(player.getHealthPoints() - 1);
                    continue;
                }

                Iterator<Spell> iteratorSpell = spellList.iterator();
                while(iteratorSpell.hasNext()){
                    Circle spell = iteratorSpell.next();
                    //remove spell if it collides with enemy
                    if(Circle.isColliding(spell, enemy)){
                        iteratorSpell.remove();
                        iteratorEnemy.remove();
                        break;
                    }
                }
            }
        }

        gameDisplay.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }

}
