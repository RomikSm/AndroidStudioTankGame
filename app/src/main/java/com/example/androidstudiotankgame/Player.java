package com.example.androidstudiotankgame;

import static com.example.androidstudiotankgame.PlayerName.user_uuid;
import static com.example.androidstudiotankgame.PlayerName.username;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private double positionX;
    private double positionY;
    private String receivedPositionX;
    private double receivedPositionY;
    private final Paint paint;
    Bitmap tankBitMap;
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final int playerWidth = 240;
    private final int playerHeight = 160;
    private final int playerCenterX = playerWidth /2;
    private final int playerCenterY = playerHeight/2;
    private float previousRotationAngle = 0;


    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference dbReference = database.getReference("Users");
    private DatabaseReference childRef = dbReference.child(user_uuid);





    public Player(Context context, double positionX, double positionY, int drawable){
        this.positionX = positionX;
        this.positionY = positionY;
        this.tankBitMap = BitmapFactory.decodeResource(context.getResources(), drawable);
        paint = new Paint();
    }

    public void draw(Canvas canvas, float rotationAngle) {

        canvas.save();
        if(rotationAngle!=0) previousRotationAngle = rotationAngle;
        canvas.rotate(previousRotationAngle, (float) (positionX), (float) (positionY));
        canvas.drawBitmap(tankBitMap, null, new Rect((int) (positionX - playerCenterX), (int) (positionY - playerCenterY), (int) (positionX + playerCenterX), (int) (positionY + playerCenterY)), paint);
        canvas.restore();
    }

    public void update(Joystick joystick) {
        double velocityX = joystick.getActuatorX() * MAX_SPEED;
        double velocityY = joystick.getActuatorY() * MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;
        pushPositionToDatabase();

    }

    private void pushPositionToDatabase(){
        User user = new User(username, positionX, positionY);
        Map<String, String> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("posX", user.getPosX());
        map.put("posY", user.getPosY());
        dbReference.child(user_uuid).setValue(map);
    }



    public int getPlayerCenterX() {
        return playerCenterX;
    }

    public int getPlayerCenterY() {
        return playerCenterY;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }
//    public void setPosition(double positionX, double positionY) {
//        this.positionX = positionX;
//        this.positionY = positionY;
//    }
}
