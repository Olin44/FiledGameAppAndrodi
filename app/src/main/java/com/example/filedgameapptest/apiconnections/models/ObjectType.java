package com.example.filedgameapptest.apiconnections.models;

/**
 * Enum przechowujący informacje o tym jakie obiekty należy znaleźć na mapie.
 */
public enum ObjectType {
    STONE("stone"),
    HOUSE("house");
    /**
     * Pole String reprezentujące typ obiektu.
     */
    public final String type;
    /**
     * Konstruktor obiektu.
     */
    ObjectType(String type) {
        this.type = type;
    }
}