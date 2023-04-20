package com.example.androidstudiotankgame;

import static com.example.androidstudiotankgame.TankChoice.tank_type;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * Game manages all objects int the game and is responsible for updating all states and render all
 * objects to the screen
 */

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private Player player;
    private final Joystick joystick;
    private GameLoop gameLoop;


    public Game(Context context) {
        super(context);

        //get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        // get the display height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        //initialize game objects
        joystick = new Joystick(275, screenHeight-400, 150, 75);
        player = new Player(getContext(), 500, 500, tank_type);

        setFocusable(true);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //handle touch event actions
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed((double) event.getX(),(double) event.getY())){
                    joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double) event.getX(),(double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;
        }

        return super.onTouchEvent(event);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
        joystick.draw(canvas);
        player.draw(canvas, joystick.getRotationAngle());

    }


    public void drawUPS(Canvas canvas){        
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(100);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(100);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update() {

        //update game state
        joystick.update();
        player.update(joystick);
    }
}
