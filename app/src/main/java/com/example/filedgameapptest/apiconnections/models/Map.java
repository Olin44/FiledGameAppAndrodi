package com.example.filedgameapptest.apiconnections.models;

import com.example.filedgameapptest.apiconnections.ObjectOnMapDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
/**
 * Klasa służąca do przesyłana danych Mapy pomiędzy częscią serwerową, a aplikacją mobilną
 */
public class Map {
    /**
     * Pole przechowujące ID obiektu.
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * Pole przechowujące nazwę mapy.
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * Pole przechowujące listę z obiektami ObjectOnMapDetails, które reprezentują obiekty znajdujące się na mapie.
     */
    @SerializedName("objectOnMapDetails")
    @Expose
    private List<ObjectOnMapDetails> objectOnMapDetails;
    /**
     * Metoda służąca do pobrania listy obiektów ObjectOnMapDetails, które reprezentują obiekty znajdujące się na mapie.
     * @return List<ObjectOnMapDetails> lista obiektów ObjectOnMapDetails, które reprezentują obiekty znajdujące się na mapie.
     */
    public List<ObjectOnMapDetails> getObjectOnMapDetails() {
        return objectOnMapDetails;
    }
    /**
     * Metoda służąca do pobrania ID obiektu.
     * @return id String z ID obiektu.
     */
    public String getId() {
        return id;
    }
    /**
     * Metoda służąca do pobrania nazwy mapy.
     * @return String z nazwą mapy.
     */
    public String getName() {
        return name;
    }
}

