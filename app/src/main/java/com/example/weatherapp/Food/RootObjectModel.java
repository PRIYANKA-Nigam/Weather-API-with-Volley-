package com.example.weatherapp.Food;

import com.example.weatherapp.Food.Image.RecipeModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RootObjectModel {
    @SerializedName("recipe")
    @Expose()
    private RecipeModel recipeModel;

    public RootObjectModel(RecipeModel recipeModel) {
        this.recipeModel = recipeModel;
    }

    public RecipeModel getRecipeModel() {
        return recipeModel;
    }


}
