package com.example.filedgameapptest.apiconnections.models;

public class GameUserDTO {
    String id;
    String mapId;
    String userId;
    Long points;
    boolean active;

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
        active = active;
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
        return active;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GameUserDTO{" +
                "id='" + id + '\'' +
                ", mapId='" + mapId + '\'' +
                ", userId='" + userId + '\'' +
                ", points=" + points +
                ", isActive=" + active +
                '}';
    }
}
