package com.example.kimsoohyun.planiotver01.Item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kimsoohyun on 2017-09-05.
 */

public class DicItem implements Parcelable{
    private String DicPlantImage;
    private String name;
    private String Explanation;
    private float humidity;
    private float light;
    private float temperature;


    protected DicItem(Parcel in) {
        DicPlantImage = in.readString();
        name = in.readString();
        Explanation = in.readString();
        humidity = in.readFloat();
        light = in.readFloat();
        temperature = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(DicPlantImage);
        dest.writeString(name);
        dest.writeString(Explanation);
        dest.writeFloat(humidity);
        dest.writeFloat(light);
        dest.writeFloat(temperature);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DicItem> CREATOR = new Creator<DicItem>() {
        @Override
        public DicItem createFromParcel(Parcel in) {
            return new DicItem(in);
        }

        @Override
        public DicItem[] newArray(int size) {
            return new DicItem[size];
        }
    };

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


    public DicItem(){
        this.DicPlantImage = null;
        this.name = null;
        this.Explanation = null;
        this.humidity = 0;
        this.light=0;
        this.temperature=0;
    }

    public DicItem(String dicPlantImage, String name, String explanation,float humidity, float light, float temperature) {
        DicPlantImage = dicPlantImage;
        this.name = name;
        Explanation = explanation;
        this.temperature = temperature;
        this.light = light;
        this.humidity=humidity;


    }

    public String getDicPlantImage() {
        return DicPlantImage;
    }

    public void setDicPlantImage(String dicPlantImage) {
        DicPlantImage = dicPlantImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        Explanation = explanation;
    }
}
