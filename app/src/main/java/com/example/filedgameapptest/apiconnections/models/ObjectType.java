package com.example.filedgameapptest.apiconnections.models;

public enum ObjectType {
    STONE("stone"),
    HOUSE("house");

    public final String type;

    ObjectType(String type) {
        this.type = type;
    }
}