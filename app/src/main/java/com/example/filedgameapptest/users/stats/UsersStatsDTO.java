package com.example.filedgameapptest.users.stats;

import java.util.Date;

public class UsersStatsDTO {
    String mapName;
    Long points;
    Date endGame;

    public UsersStatsDTO() {
    }

    public UsersStatsDTO(String mapName, Long points, Date endGame) {
        this.mapName = mapName;
        this.points = points;
        this.endGame = endGame;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public void setEndGame(Date endGame) {
        this.endGame = endGame;
    }

    public String getMapName() {
        return mapName;
    }

    public Long getPoints() {
        return points;
    }

    public Date getEndGame() {
        return endGame;
    }

    @Override
    public String toString() {
        return "UsersStatsDTO{" +
                "mapName='" + mapName + '\'' +
                ", points=" + points +
                ", endGame=" + endGame +
                '}';
    }
}
