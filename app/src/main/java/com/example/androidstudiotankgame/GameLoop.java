package com.example.androidstudiotankgame;

import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.util.Observer;

public class GameLoop extends Thread{
    private static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private double averageUPS;
    private double averageFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        //declare time and cycle count variables
        int updateСount = 0;
        int frameСount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        //game loop
        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while (isRunning){

            //try to update and render game
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    game.update();
                    updateСount++;
                    game.draw(canvas);
                }
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameСount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //pause the game Loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateСount*UPS_PERIOD - elapsedTime);
            if(sleepTime > 0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //skip frames to keep up with UPS
            while(sleepTime < 0 && updateСount < MAX_UPS-1){
                game.update();
                updateСount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateСount*UPS_PERIOD - elapsedTime);
            }

            //calculate avg UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000){
                averageUPS = updateСount / (1E-3 * elapsedTime);
                averageFPS = frameСount / (1E-3 * elapsedTime);
                updateСount = 0;
                frameСount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
}
