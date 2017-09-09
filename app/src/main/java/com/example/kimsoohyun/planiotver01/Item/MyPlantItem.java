package com.example.kimsoohyun.planiotver01.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kimsoohyun on 2017-09-09.
 */

public class MyPlantItem {
    String uid;
    String myPlantImg;

    public MyPlantItem(String myPlantImg, String myPlantName, String originalPlantName, String plantDate) {
        this.myPlantImg = myPlantImg;
        this.myPlantName = myPlantName;
        this.originalPlantName = originalPlantName;
        this.plantDate = plantDate;
    }

    String myPlantName;
    String originalPlantName;
    String plantDate;

    float myPlantHumidity;
    float myPlantLight;

    public String getOriginalPlantName() {
        return originalPlantName;
    }

    public void setOriginalPlantName(String originalPlantName) {
        this.originalPlantName = originalPlantName;
    }

    public String getPlantDate() {
        return plantDate;
    }

    public void setPlantDate(String plantDate) {
        this.plantDate = plantDate;
    }

    float myPlantTemperatuer;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMyPlantImg() {
        return myPlantImg;
    }

    public void setMyPlantImg(String myPlantGallery) {
        this.myPlantImg = myPlantGallery;
    }

    public String getMyPlantName() {
        return myPlantName;
    }

    public void setMyPlantName(String myPlantName) {
        this.myPlantName = myPlantName;
    }

    public float getMyPlantHumidity() {
        return myPlantHumidity;
    }

    public void setMyPlantHumidity(float myPlantHumidity) {
        this.myPlantHumidity = myPlantHumidity;
    }

    public float getMyPlantLight() {
        return myPlantLight;
    }

    public void setMyPlantLight(float myPlantLight) {
        this.myPlantLight = myPlantLight;
    }

    public float getMyPlantTemperatuer() {
        return myPlantTemperatuer;
    }

    public void setMyPlantTemperatuer(float myPlantTemperatuer) {
        this.myPlantTemperatuer = myPlantTemperatuer;
    }



    public MyPlantItem(String uid, String myPlantGallery, String myPlantName, float myPlantHumidity, float myPlantLight, float myPlantTemperatuer) {
        this.uid = uid;
        this.myPlantImg = myPlantGallery;
        this.myPlantName = myPlantName;
        this.myPlantHumidity = myPlantHumidity;
        this.myPlantLight = myPlantLight;
        this.myPlantTemperatuer = myPlantTemperatuer;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap();
        result.put("uid",uid);
        result.put("myPlantName",myPlantName);
        result.put("myPlantGallery",myPlantImg);
        result.put("myPlantHumidity",myPlantHumidity);
        result.put("myPlantLight",myPlantLight);
        result.put("myPlantTemperature",myPlantTemperatuer);
        return result;
    }


}

