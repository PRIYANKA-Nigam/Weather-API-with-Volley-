package com.example.weatherapp.Food.RawFood;

public class FoodModel {
    String image,name,category,fat,fibre,protein;

    public FoodModel(String image, String name, String category, String fat, String fibre, String protein) {
        this.image = image;
        this.name = name;
        this.category = category;
        this.fat = fat;
        this.fibre = fibre;
        this.protein = protein;
    }

    public FoodModel(String image,String category) {
        this.category = category;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getFat() {
        return fat;
    }

    public String getFibre() {
        return fibre;
    }

    public String getProtein() {
        return protein;
    }
}
