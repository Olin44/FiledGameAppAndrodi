package com.example.filedgameapptest.maps.data;
/**
 * Klasa, będąca Singletonem, służąca do przesyłania informacji o grze pomiędzy widokami.
 */
public class GameDataRepository {
    /**
     * Pole przechowujące ID obiektu.
     */
    private String id;
    /**
     * Pole przechowujące ID mapy, do której przypisana jest rozgrywka.
     */
    private String mapId;
    /**
     * Pole przechowujące ID użytkownika, do którego przypisana jest rozgrywka.
     */
    private String userId;
    /**
     * Pole przechowujące ilość punktów zdobytych w grze.
     */
    private Long points;
    /**
     * Pole będące flagą, informującą o tym, czy dana gra się już zakończyła.
     */
    private Boolean isActive;
    /**
     * Pole służące do przechowywania instancji obiektu.
     */
    private static GameDataRepository INSTANCE = null;
    /**
     * Metoda służąca do pobrania instancji obiektu.
     * @return zapisany w polu INSTANCE obiekt klasy GameDataRepository.
     */
    public static GameDataRepository getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GameDataRepository();
        }
        return INSTANCE;
    }
    /**
     * Metoda służąca do ustawienia instancji obiektu GameDataRepository.
     */
    public static void setINSTANCE(GameDataRepository INSTANCE){
        GameDataRepository.INSTANCE = INSTANCE;
    }
    /**
     * Metoda służąca do ustawienia wszystkich informacji o grze.
     * @param id ID obiektu
     * @param mapId ID mapy
     * @param userId ID użytkownika
     * @param points ilość punktów
     * @param isActive boolean określający czy gra jest aktywna
     */
    public void setAllData(String id, String mapId, String userId, Long points, Boolean isActive){
        this.id = id;
        this.mapId = mapId;
        this.userId = userId;
        this.points = points;
        this.isActive = isActive;
    }
    /**
     * Metoda służąca do usunięcia wszystkich informacji o grze.
     */
    public void deleteAllData(){
        mapId = null;
        userId = null;
        points = null;
        isActive = null;
    }
    /**
     * Metoda służąca do pobrania ID obiektu.
     * @return  id String zawierający ID obiektu.
     */
    public String getId() {
        return id;
    }
    /**
     * Metoda służąca do ustawienia ID obiektu.
     * @param id String zawierający ID użytkownika
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Metoda służąca do pobrania ID Mapy.
     * @return zwraca String z ID mapy.
     */
    public String getMapId() {
        return mapId;
    }
    /**
     * Metoda służąca do ustawienia ID mapy.
     * @param mapId ID mapy
     */
    public void setMapId(String mapId) {
        this.mapId = mapId;
    }
    /**
     * Metoda służąca do pobrania ID użytkownika.
     * @return zwraca String z ID użytkownika.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * Metoda służąca do ustawienia ID użytkownika.
     * @param userId ID użytkownika.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * Metoda służąca do pobrania ilości punktów użytkownika.
     * @return zwraca obiekt Long z ilością użytkownika.
     */
    public Long getPoints() {
        return points;
    }
    /**
     * Metoda służąca do ustawienia ilości punktów.
     * @param points ilość punktów
     */
    public void setPoints(Long points) {
        this.points = points;
    }
    /**
     * Metoda służąca do pobrania informacji o tym czy gra jest aktywna.
     * @return zwraca obiekt boolean określający czy gra jest aktywna.
     */
    public Boolean getActive() {
        return isActive;
    }
    /**
     * Metoda służąca do określenia czy gra jest aktywna.
     * @param active boolean określający czy gra jest aktywna.
     */
    public void setActive(Boolean active) {
        isActive = active;
    }


}
