package com.example.filedgameapptest.maps.data;

import com.example.filedgameapptest.apiconnections.ObjectOnMapDetails;
import com.example.filedgameapptest.users.data.UserDataRepository;

import java.util.List;

public class MapDataRepository {

    private String id;
    private String name;
    private List<ObjectOnMapDetails> objectOnMapDetails;
    private static MapDataRepository INSTANCE = null;

    public static MapDataRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapDataRepository();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(MapDataRepository INSTANCE) {
        MapDataRepository.INSTANCE = INSTANCE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ObjectOnMapDetails> getObjectOnMapDetails() {
        return objectOnMapDetails;
    }

    public void setObjectOnMapDetails(List<ObjectOnMapDetails> objectOnMapDetails) {
        this.objectOnMapDetails = objectOnMapDetails;
    }

    public void setAllData(String id, String name, List<ObjectOnMapDetails> objectOnMapDetails){
        this.id = id;
        this.name = name;
        this.objectOnMapDetails = objectOnMapDetails;

    }
    public void deleteAllData(){
        id = null;
        name = null;
        objectOnMapDetails = null;
    }
}
