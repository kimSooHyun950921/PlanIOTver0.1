package com.example.kimsoohyun.planiotver01;

import java.io.File;

/**
 * Created by kimsoohyun on 2017-09-01.
 */

public class DicDatabase {
    public String plantName;
    public String plantExplanation;
    public File myplantImage;
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

    public File getMyplantImage() {
        return myplantImage;
    }

    public void setMyplantImage(File myplantImage) {
        this.myplantImage = myplantImage;
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
        this.myplantImage = null;
        this.plantHumidity = 0;
        this.plantLight = 0;
        this.plantTemperature = 0;
    }
    public DicDatabase(float plantHumidity, float plantLight, float plantTemperature) {

        this.plantHumidity = plantHumidity;
        this.plantLight = plantLight;
        this.plantTemperature = plantTemperature;
    }

    public DicDatabase(String plantName, String plantExplanation, File myplantImage, float plantHumidity, float plantLight, float plantTemperature) {

        this.plantName = plantName;
        this.plantExplanation = plantExplanation;
        this.myplantImage = myplantImage;
        this.plantHumidity = plantHumidity;
        this.plantLight = plantLight;
        this.plantTemperature = plantTemperature;
    }


}
