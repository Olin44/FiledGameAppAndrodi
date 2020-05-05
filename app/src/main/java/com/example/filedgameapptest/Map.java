package com.example.filedgameapptest;

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
    String id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("objectOnMapDetails")
    @Expose
    List<ObjectOnMapDetails> objectOnMapDetails;

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

