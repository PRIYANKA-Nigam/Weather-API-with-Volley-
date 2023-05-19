package com.example.weatherapp.Food.Response;

import com.example.weatherapp.Food.RootObjectModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class SearchRecipe {
    private int from;
    private int to;
    private int count;
    @SerializedName("hits")
    @Expose()
    private RootObjectModel[] foodRecipe;

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCount() {
        return count;
    }

    public RootObjectModel[] getFoodRecipe() {
        return foodRecipe;
    }

    @Override
    public String toString() {
        return "SearchRecipe{" +
                "from=" + from +
                ", to=" + to +
                ", count=" + count +
                ", foodRecipe=" + Arrays.toString(foodRecipe) +
                '}';
    }
}
