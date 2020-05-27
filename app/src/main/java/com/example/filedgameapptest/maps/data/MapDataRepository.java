package com.example.filedgameapptest.maps.data;

import com.example.filedgameapptest.apiconnections.ObjectOnMapDetails;
import com.example.filedgameapptest.users.data.UserDataRepository;

import java.util.List;
/**
 * Klasa służąca do przesyłana danych Mapy pomiędzy widokami w aplikacji.
 */
public class MapDataRepository {
    /**
     * Pole przechowujące ID obiektu.
     */
    private String id;
    /**
     * Pole przechowujące nazwę mapy.
     */
    private String name;
    /**
     * Pole przechowujące listę z obiektami ObjectOnMapDetails, które reprezentują obiekty znajdujące się na mapie.
     */
    private List<ObjectOnMapDetails> objectOnMapDetails;
    /**
     * Pole służące do przechowywania instancji obiektu.
     */
    private static MapDataRepository INSTANCE = null;
    /**
     * Metoda służąca do pobrania instancji obiektu.
     * @return zapisany w polu INSTANCE obiekt klasy MapDataRepository.
     */
    public static MapDataRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapDataRepository();
        }
        return INSTANCE;
    }
    /**
     * Metoda służąca do ustawienia instancji obiektu GameDataRepository.
     */
    public static void setINSTANCE(MapDataRepository INSTANCE) {
        MapDataRepository.INSTANCE = INSTANCE;
    }
    /**
     * Metoda służąca do pobrania ID obiektu.
     * @return id String z ID obiektu.
     */
    public String getId() {
        return id;
    }
    /**
     * Metoda służąca do ustawiania ID obiektu.
     * @param  id String z ID obiektu.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Metoda służąca do pobrania nazwy mapy.
     * @return String z nazwą mapy.
     */
    public String getName() {
        return name;
    }
    /**
     * Metoda służąca do ustawiania nazwy mapy.
     * @param name String z nazwą mapy
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Metoda służąca do pobrania listy obiektów na mapie.
     * @return List<ObjectOnMapDetails> lista z obiektami ObjectOnMapDetails
     */
    public List<ObjectOnMapDetails> getObjectOnMapDetails() {
        return objectOnMapDetails;
    }
    /**
     * Metoda służąca do ustawiania listy obiektów na mapie.
     * @param objectOnMapDetails lista z obiektami ObjectOnMapDetails
     */
    public void setObjectOnMapDetails(List<ObjectOnMapDetails> objectOnMapDetails) {
        this.objectOnMapDetails = objectOnMapDetails;
    }
    /**
     * Metoda służąca do ustawienia wszystkich informacji o mapie.
     * @param id ID obiektu
     * @param name nazwa mapy
     * @param objectOnMapDetails lista obiektów na mapie
     */
    public void setAllData(String id, String name, List<ObjectOnMapDetails> objectOnMapDetails){
        this.id = id;
        this.name = name;
        this.objectOnMapDetails = objectOnMapDetails;
    }
    /**
     * Metoda służąca do usunięcia wszystkich informacji o mapie.
     */
    public void deleteAllData(){
        id = null;
        name = null;
        objectOnMapDetails = null;
    }
}
