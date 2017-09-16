package com.example.kimsoohyun.planiotver01.Item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by kimsoohyun on 2017-09-09.
 */

public class MyPlantItem implements Parcelable {
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

    public MyPlantItem() {
        this.myPlantImg = null;
        this.myPlantName =null;
        this.originalPlantName = null;
        this.plantDate = null;
    }

    protected MyPlantItem(Parcel in) {
        uid = in.readString();
        myPlantImg = in.readString();
        myPlantName = in.readString();
        originalPlantName = in.readString();
        plantDate = in.readString();
        myPlantHumidity = in.readFloat();
        myPlantLight = in.readFloat();
        myPlantTemperatuer = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(myPlantImg);
        dest.writeString(myPlantName);
        dest.writeString(originalPlantName);
        dest.writeString(plantDate);
        dest.writeFloat(myPlantHumidity);
        dest.writeFloat(myPlantLight);
        dest.writeFloat(myPlantTemperatuer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MyPlantItem> CREATOR = new Creator<MyPlantItem>() {
        @Override
        public MyPlantItem createFromParcel(Parcel in) {
            return new MyPlantItem(in);
        }

        @Override
        public MyPlantItem[] newArray(int size) {
            return new MyPlantItem[size];
        }
    };

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

