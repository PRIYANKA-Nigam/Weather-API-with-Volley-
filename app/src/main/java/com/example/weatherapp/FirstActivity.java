package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.weatherapp.Food.FoodActivity;
import com.example.weatherapp.Food.RawFood.FoodDatabaseActivity;
import com.example.weatherapp.Music.SongActivity;
import com.example.weatherapp.News.NewsAPIActivity;

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
}