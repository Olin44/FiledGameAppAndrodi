package com.example.filedgameapptest;

public enum ObjectType {
    STONE("stone"),
    HOUSE("house");

    public final String type;

    ObjectType(String type) {
        this.type = type;
    }
}