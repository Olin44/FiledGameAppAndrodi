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
public class ObjectOnMapDetails {
    @SerializedName("id")
    private String id;

    @SerializedName("objectType")
    @Expose
    private ObjectType objectType;

    @SerializedName("latLng")
    @Expose
    private LatLng latLng;

    @SerializedName("hint")
    @Expose
    private String hint;

    public String getId() {
        return id;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getHint() {
        return hint;
    }

}
