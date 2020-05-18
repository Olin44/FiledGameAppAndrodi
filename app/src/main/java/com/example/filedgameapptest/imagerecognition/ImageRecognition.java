package com.example.filedgameapptest.imagerecognition;

public class ImageRecognition {

    private boolean foundObject;

    public ImageRecognition() {
        this.foundObject = true;
    }

    public boolean isFoundObject() {
        return foundObject;
    }

    public void setFoundObject(boolean foundObject) {
        this.foundObject = foundObject;
    }
}
