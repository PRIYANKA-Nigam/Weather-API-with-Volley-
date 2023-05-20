package com.example.weatherapp.Food.RawFood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodDatabaseActivity extends AppCompatActivity {
    private  final String url="https://api.edamam.com/api/food-database/v2/parser?app_id=3c0f087d&app_key=f2d9862fe8e8f4cdb0277c168842ea5a&nutrition-type=cooking";
RecyclerView recyclerView;
FoodAdapter adapter;
SearchView searchView;
ArrayList<FoodModel> arrayList;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_database);
        seeView();
        searchView=findViewById(R.id.see);
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                s=s.toLowerCase();
                ArrayList<FoodModel> newList=new ArrayList<>();
                for (FoodModel dish : arrayList){ String name=dish.getName().toLowerCase();
                    if (name.contains(s))
                        newList.add(dish);
                }
                adapter.setFilter(newList);
                return true;
            }
        });

    }

    private void seeView() {
        requestQueue = Volley.newRequestQueue(this);
        recyclerView= findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList=new ArrayList<>();
        adapter=new FoodAdapter(FoodDatabaseActivity.this,arrayList);
        recyclerView.setAdapter(adapter);
        callApi(url);
    }

    private void callApi(String url) {
        StringRequest stringRequest =new StringRequest(Request.Method.GET,url, response -> {
            extract(response);

        },error -> Log.d("error",error.toString()));
        requestQueue.add(stringRequest);
    }

    private void extract(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray movies = jsonObject.getJSONArray("hints");
            for (int i=0; i<movies.length(); i++){
                JSONObject childObj = movies.getJSONObject(i);

               JSONObject food = childObj.getJSONObject("food");
                String name=food.getString("knownAs");
                JSONObject f =food.getJSONObject("nutrients");
                String protein = f.getString("PROCNT");
                String fat = f.getString("FAT");
                String fibre = f.getString("FIBTG");
                String image;
               String category = food.getString("category");
                if(food.has("image"))
                 image = food.getString("image");
                else
                 image =null;


           FoodModel model =new FoodModel(image,name,category,fat,fibre,protein);
//                    FoodModel model = new FoodModel(image, category);
                    arrayList.add(model);

            }
            adapter.notifyDataSetChanged();
        } catch (Exception jsonException) {
            jsonException.printStackTrace();
        }
    }
}