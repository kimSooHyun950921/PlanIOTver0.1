package com.example.kimsoohyun.planiotver01.Item;

/**
 * Created by kimsoohyun on 2017-09-05.
 */

public class DicItem {
    private String DicPlantImage;
    private String name;
    private String Explanation;

    public DicItem(){
        this.DicPlantImage = null;
        this.name = null;
        this.Explanation = null;
    }

    public DicItem(String dicPlantImage, String name, String explanation) {
        DicPlantImage = dicPlantImage;
        this.name = name;
        Explanation = explanation;


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
