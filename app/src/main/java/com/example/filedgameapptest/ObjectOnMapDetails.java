package com.example.filedgameapptest;

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
    String id;
    @SerializedName("objectType")
    @Expose
    ObjectType objectType;
    @SerializedName("map")
    @Expose
    Map map;
    @SerializedName("hint")
    @Expose
    String hint;
}
