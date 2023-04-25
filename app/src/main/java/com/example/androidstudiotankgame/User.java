package com.example.androidstudiotankgame;

public class User {

    private String name;
    private String posX;
    private String posY;



    private int tankType;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public User(String name, double posX, double posY, int tankType) {
        this.name = name;
        this.posX = String.valueOf(posX);
        this.posY = String.valueOf(posY);
        this.tankType = tankType;
    }

    public int getTankType() {
        return tankType;
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
