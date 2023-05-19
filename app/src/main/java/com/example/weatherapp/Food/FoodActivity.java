package com.example.weatherapp.Food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.Food.Response.SearchRecipe;
import com.example.weatherapp.api.APIClient;
import com.example.weatherapp.Food.utils.APICredentials;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodActivity extends AppCompatActivity {
private static final String TAG ="\t"+FoodActivity.class.getSimpleName();
private RecyclerView recyclerView;
private ArrayList<RootObjectModel> recipes;
private RecipeAdapter adapter;
private SearchView searchView;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recyclerView =findViewById(R.id.rec);
        recipes=new ArrayList<>();
        textView=findViewById(R.id.t);
        searchView=findViewById(R.id.see);
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                textView.setVisibility(View.GONE);
                searchRecipes(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                textView.setVisibility(View.GONE);
                searchRecipes(s);
                return true;
            }
        });
    }
    private void searchRecipes(String query){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APICredentials.baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        APIClient apiClient = retrofit.create(APIClient.class);
        Call<SearchRecipe> searchRecipeCall =apiClient.searchRecipe(APICredentials.type,query,APICredentials.appId,APICredentials.appKey);
        searchRecipeCall.enqueue(new Callback<SearchRecipe>() {
            @Override
            public void onResponse(Call<SearchRecipe> call, Response<SearchRecipe> response) {
                if (response.isSuccessful() && response.body()!=null){
                    recipes =new ArrayList<>(Arrays.asList(response.body().getFoodRecipe()));
                    for (int i=0;i<recipes.size();i++){
                        RootObjectModel rootObjectModel = recipes.get(i);
                    }
                  recyclerView.setLayoutManager(new LinearLayoutManager(FoodActivity.this));
                    recyclerView.setHasFixedSize(true);
                    adapter=new RecipeAdapter(FoodActivity.this,recipes);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<SearchRecipe> call, Throwable t) {
                Log.v("Tag"+TAG,"onFailure()"+t.getMessage());
            }
        });
    }
}