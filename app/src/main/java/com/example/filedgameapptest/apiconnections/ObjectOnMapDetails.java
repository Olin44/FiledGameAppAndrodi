package com.example.filedgameapptest.apiconnections;

import com.example.filedgameapptest.apiconnections.models.ObjectType;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
/**
 * Klasa przechowująca informacje o obiekcie na mapie.
 */
public class ObjectOnMapDetails {
    /**
     * Pole przechowujące autogenerowane na backendzie ID obiektu.
     */
    @SerializedName("id")
    private String id;
    /**
     * Pole przechowujące informację o obiekcie jaki zależy znaleźć w danym miejscu na mapie.
     */
    @SerializedName("objectType")
    @Expose
    private ObjectType objectType;
    /**
     * Pole przechowujące informację o lokalizacji obiektu na mapie.
     */
    @SerializedName("latLng")
    @Expose
    private LatLng latLng;
    /**
     * Pole przechowujące wskazówkę jaki obiekt jest szukany,
     */
    @SerializedName("hint")
    @Expose
    private String hint;
    /**
     * Metoda służąca do pobrania ID obiektu.
     * @return  id String zawierający ID obiektu GameUserDTO.
     */
    public String getId() {
        return id;
    }
    /**
     * Metoda służąca do pobrania ID obiektu.
     * @return  ObjectType informacja o tym jaki obiekt jest szukany.
     */
    public ObjectType getObjectType() {
        return objectType;
    }
    /**
     * Metoda służąca do pobrania ID obiektu.
     * @return  LatLng obiekt klasy LatLng, w którym znajduje się dokładna lokalizacja obiektu.
     */
    public LatLng getLatLng() {
        return latLng;
    }
    /**
     * Metoda służąca do pobrania ID obiektu.
     * @return Sting ze wskazówką.
     */
    public String getHint() {
        return hint;
    }

}
