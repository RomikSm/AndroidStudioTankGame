package com.example.androidstudiotankgame;

public class User {

    private String name;
    private String positionX;
    private String positionY;


    public User() {}

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User(String name, String positionX, String positionY) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
