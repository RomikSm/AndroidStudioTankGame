package com.example.androidstudiotankgame;

public class User {

    private String name;
    private String posX;
    private String posY;

    public User() {}

    public User(String name, double posX, double posY) {
        this.name = name;
        this.posX = String.valueOf(posX);
        this.posY = String.valueOf(posY);

    }

    public String getName() {
        return name;
    }

    public String getPosX() {
        return posX;
    }

    public String getPosY() {
        return posY;
    }
}
