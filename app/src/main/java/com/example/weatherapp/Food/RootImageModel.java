package com.example.weatherapp.Food;

import com.example.weatherapp.Food.Image.LargeImage;
import com.example.weatherapp.Food.Image.RegularImage;
import com.example.weatherapp.Food.Image.SmallImage;
import com.example.weatherapp.Food.Image.ThumbNail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RootImageModel {
    @SerializedName("LARGE")
    @Expose()
    private LargeImage largeImage;
    @SerializedName("SMALL")
    @Expose()
    private SmallImage smallImage;
    @SerializedName("REGULAR")
    @Expose()
    private RegularImage regularImage;
    @SerializedName("THUMBNAIL")
    @Expose()
    private ThumbNail thumbNail;

    public RootImageModel(LargeImage largeImage, SmallImage smallImage, RegularImage regularImage, ThumbNail thumbNail) {
        this.largeImage = largeImage;
        this.smallImage = smallImage;
        this.regularImage = regularImage;
        this.thumbNail = thumbNail;
    }

    public LargeImage getLargeImage() {
        return largeImage;
    }

    public SmallImage getSmallImage() {
        return smallImage;
    }

    public RegularImage getRegularImage() {
        return regularImage;
    }

    public ThumbNail getThumbNail() {
        return thumbNail;
    }
}
