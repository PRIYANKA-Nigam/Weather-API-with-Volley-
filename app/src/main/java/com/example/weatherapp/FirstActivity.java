package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.FlingAnimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.weatherapp.Food.FoodActivity;
import com.example.weatherapp.Food.RawFood.FoodDatabaseActivity;
import com.example.weatherapp.Music.SongActivity;
import com.example.weatherapp.News.NewsAPIActivity;
import com.example.weatherapp.PNR_Enquiry.RailwayActivity;
import com.example.weatherapp.Sensor.SensorActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void checkWeather(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void checkNews(View view) {
        startActivity(new Intent(this, NewsAPIActivity.class));
    }

    public void checkFood(View view) { startActivity(new Intent(this, FoodActivity.class));
    }

    public void checkFoodDB(View view) { startActivity(new Intent(this, FoodDatabaseActivity.class));
    }

    public void checkSong(View view) { startActivity(new Intent(this, SongActivity.class));
    }

    public void checkFlight(View view) { startActivity(new Intent(this, RailwayActivity.class));
    }


    public void checkSensors(View view) { startActivity(new Intent(this, SensorActivity.class));
    }

//    public void checkFlights(View view) {  startActivity(new Intent(this, FLightActivity.class));
//    }
}