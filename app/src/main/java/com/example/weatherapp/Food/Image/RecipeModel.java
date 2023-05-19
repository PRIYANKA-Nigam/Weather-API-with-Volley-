package com.example.weatherapp.Food.Image;

import com.example.weatherapp.Food.RootImageModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeModel {
    private String label,source;
    private String  image;
    private float yield,calories,totalWeight;
    @SerializedName("images")
    @Expose()
    private RootImageModel rootImageModel;

    private RecipeModel(String label, String image, String source, float yield, float calories, float totalWeight, RootImageModel rootImageModel) {
        this.label = label;
        this.image = image;
        this.source = source;
        this.yield = yield;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.rootImageModel = rootImageModel;
    }

    public RecipeModel() {
        new RecipeModel(label,image,source,yield,calories,totalWeight,rootImageModel);
    }

    public RootImageModel getRootImageModel() {
        return rootImageModel;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }

    public String getSource() {
        return source;
    }

    public float getYield() {
        return yield;
    }

    public float getCalories() {
        return calories;
    }

    public float getTotalWeight() {
        return totalWeight;
    }
}
