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
public class Map {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("objectOnMapDetails")
    @Expose
    private List<ObjectOnMapDetails> objectOnMapDetails;

    public List<ObjectOnMapDetails> getObjectOnMapDetails() {
        return objectOnMapDetails;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

