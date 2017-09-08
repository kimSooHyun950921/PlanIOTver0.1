package com.example.kimsoohyun.planiotver01;

/**
 * Created by kimsoohyun on 2017-09-01.
 */

public class DicDatabase {
    public String plantName;
    public String plantExplanation;
    public String myplantImageURL;
    public float plantHumidity;
    public float plantLight;
    public float plantTemperature;

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantExplanation() {
        return plantExplanation;
    }

    public void setPlantExplanation(String plantExplanation) {
        this.plantExplanation = plantExplanation;
    }

    public String getMyplantImage() {
        return myplantImageURL;
    }

    public void setMyplantImage(String myplantImage) {
        this.myplantImageURL = myplantImage;
    }

    public float getPlantHumidity() {
        return plantHumidity;
    }

    public void setPlantHumidity(float plantHumidity) {
        this.plantHumidity = plantHumidity;
    }

    public float getPlantLight() {
        return plantLight;
    }

    public void setPlantLight(float plantLight) {
        this.plantLight = plantLight;
    }

    public float getPlantTemperature() {
        return plantTemperature;
    }

    public void setPlantTemperature(float plantTemperature) {
        this.plantTemperature = plantTemperature;
    }
    public DicDatabase() {
        this.plantName = null;
        this.plantExplanation = null;
        this.myplantImageURL = null;
        this.plantHumidity = 0;
        this.plantLight = 0;
        this.plantTemperature = 0;
    }
    public DicDatabase(float plantHumidity, float plantLight, float plantTemperature) {

        this.plantHumidity = plantHumidity;
        this.plantLight = plantLight;
        this.plantTemperature = plantTemperature;
    }

    public DicDatabase(String plantName, String plantExplanation,String myplantImageURL, float plantHumidity, float plantLight, float plantTemperature) {

        this.plantName = plantName;
        this.plantExplanation = plantExplanation;
        this.myplantImageURL = myplantImageURL;
        this.plantHumidity = plantHumidity;
        this.plantLight = plantLight;
        this.plantTemperature = plantTemperature;
    }


}
