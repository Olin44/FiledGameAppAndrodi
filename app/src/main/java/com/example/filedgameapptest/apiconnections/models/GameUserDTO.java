package com.example.filedgameapptest.apiconnections.models;
/**
 * Klasa służąca do przesyłania obiektów z danymi o grze użytkownika.
 */
public class GameUserDTO {
    /**
     * Pole przechowujące autogenerowane na backendzie ID obiektu.
     */
    String id;
    /**
     * Pole przechowujące autogenerowane na backendzie ID mapy, do której przypisana jest rozgrywka.
     */
    String mapId;
    /**
     * Pole przechowujące autogenerowane na backendzie ID użytkownika, do którego przypisana jest rozgrywka.
     */
    String userId;
    /**
     * Pole przechowujące ilość punktów zdobytych w grze.
     */
    Long points;
    /**
     * Pole będące flagą, informującą o tym, czy dana gra się już zakończyła.
     */
    boolean active;
    /**
     * Metoda służąca do ustawiania ID mapy.
     * @param mapId String zawierający ID mapy.
     */
    public void setMapId(String mapId) {
        this.mapId = mapId;
    }
    /**
     * Metoda służąca do ustawiania ID mapy.
     * @param userId String zawierający ID użytkownika.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * Metoda służąca do ustawiania ID mapy.
     * @param points ilość punktów określona wartością Long.
     */
    public void setPoints(Long points) {
        this.points = points;
    }
    /**
     * Metoda służąca do ustawiania czy gra jest aktywna.
     * @param active aktywność określona wartością boolean.
     */
    public void setActive(boolean active) {
        active = active;
    }
    /**
     * Metoda służąca do pobrania ID Mapy.
     * @return zwraca String z ID mapy.
     */
    public String getMapId() {
        return mapId;
    }
    /**
     * Metoda służąca do pobrania ID użytkownika.
     * @return zwraca String z ID użytkownika.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * Metoda służąca do pobrania ilości punktów użytkownika.
     * @return zwraca obiekt Long z ilością użytkownika.
     */
    public Long getPoints() {
        return points;
    }
    /**
     * Metoda służąca do pobrania informacji o tym czy gra jest aktywna.
     * @return zwraca obiekt boolean określający czy gra jest aktywna.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Metoda służąca do ustawienia ID obiektu GameUserDTO.
     * @param id String zawierający ID użytkownika
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Metoda służąca do pobrania ID obiektu GameUserDTO.
     * @return  id String zawierający ID obiektu GameUserDTO.
     */
    public String getId() {
        return id;
    }
    /**
     * Metoda służąca do pobrania Stringa z tekstową reprezentacją klasy.
     * @return id String z tekstową reprezentacją klasy.
     */
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
