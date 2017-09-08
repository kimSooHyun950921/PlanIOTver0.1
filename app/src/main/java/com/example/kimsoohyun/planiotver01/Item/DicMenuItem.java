package com.example.kimsoohyun.planiotver01.Item;

/**
 * Created by kimsoohyun on 2017-09-01.
 */

public class DicMenuItem {
    public String plantName;
    public String plantExplanation;
    public String DicPlantImage;
    public float humidity;
    public float light;
    public float temperature;

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
        return DicPlantImage;
    }

    public void setMyplantImage(String myplantImage) {
        this.DicPlantImage = myplantImage;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    public DicMenuItem() {
        this.plantName = null;
        this.plantExplanation = null;
        this.DicPlantImage = null;
        this.humidity = 0;
        this.light = 0;
        this.temperature = 0;
    }
    public DicMenuItem(float plantHumidity, float plantLight, float plantTemperature) {

        this.humidity = plantHumidity;
        this.light = plantLight;
        this.temperature = plantTemperature;
    }

    public DicMenuItem(String plantName, String plantExplanation, String myplantImageURL, float plantHumidity, float plantLight, float plantTemperature) {

        this.plantName = plantName;
        this.plantExplanation = plantExplanation;
        this.DicPlantImage = myplantImageURL;
        this.humidity = plantHumidity;
        this.light = plantLight;
        this.temperature = plantTemperature;
    }


}
