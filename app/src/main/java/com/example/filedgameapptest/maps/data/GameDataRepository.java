package com.example.filedgameapptest.maps.data;

public class GameDataRepository {
    private String id;
    private String mapId;
    private String userId;
    private Long points;
    private Boolean isActive;
    private static GameDataRepository INSTANCE = null;

    public static GameDataRepository getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GameDataRepository();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(GameDataRepository INSTANCE){
        GameDataRepository.INSTANCE = INSTANCE;
    }

    public void setAllData(String id, String mapId, String userId, Long points, Boolean isActive){
        this.id = id;
        this.mapId = mapId;
        this.userId = userId;
        this.points = points;
        this.isActive = isActive;
    }

    public void deleteAllData(){
        mapId = null;
        userId = null;
        points = null;
        isActive = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


}
