package com.example.filedgameapptest.apiconnections.models;

public class GameUserDTO {
    String id;
    String mapId;
    String userId;
    Long points;
    boolean isActive;

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getMapId() {
        return mapId;
    }

    public String getUserId() {
        return userId;
    }

    public Long getPoints() {
        return points;
    }

    public boolean isActive() {
        return isActive;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
