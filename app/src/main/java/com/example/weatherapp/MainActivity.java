package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
EditText e1,e2;
//private  final String url="https://api.openweathermap.org/data/3.0/weather";
private  final String url="https://api.openweathermap.org/data/2.5/weather";
            private final String appid="c60c1b6e6238de0f4bcfe6a727c3009c";
            DecimalFormat decimalFormat=new DecimalFormat("0.00");
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.ed);
        e2=(EditText)findViewById(R.id.ed2);
        textView=(TextView)findViewById(R.id.textView2);
    }

    public void getWeatherDetails(View view) {
        String tempurl="";
        String city=e1.getText().toString().trim();
        String country=e2.getText().toString().trim();
        if (city.equals("")){
            textView.setText("City field cannot be Empty");
        }else {
            if (country.equals("")){
                tempurl=url+"?q="+city+","+country+"&appid="+appid;
            }else {
                tempurl=url+"?q="+city+"&appid="+appid;
            }
            StringRequest stringRequest=new StringRequest(Request.Method.POST, tempurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   // Log.d("response",response);
                    String output="";
                    JSONObject jsonObject= null;
                    try {
                        jsonObject = new JSONObject(response);
                        JSONArray jsonArray=jsonObject.getJSONArray("weather");
                        JSONObject jsonObject1=jsonArray.getJSONObject(0);
                        String description=jsonObject1.getString("description");
                        JSONObject jsonObject2=jsonObject.getJSONObject("main");
                        double temp=jsonObject2.getDouble("temp");
                        double feelsLike=jsonObject2.getDouble("feels_like")-273.15;
                        float pressure=jsonObject2.getInt("pressure");
                        int humidity=jsonObject2.getInt("humidity");
                        JSONObject jsonObject3=jsonObject.getJSONObject("wind");
                        String wind=jsonObject3.getString("speed");
                        JSONObject jsonObject4=jsonObject.getJSONObject("sys");
                        String country=jsonObject4.getString("country");
                        String city=jsonObject.getString("name");
                      JSONObject clouds=jsonObject.getJSONObject("clouds");
                      String cl =clouds.getString("all");
                        textView.setTextColor(Color.rgb(68,134,199));
                        output+="Current Weather of "+city+"\n(country code: " +country+")"+"\n temperature: "+decimalFormat.format(temp)+"K"+
                                "\n Feels Like :"+decimalFormat.format(feelsLike)+"C"
                                +"\n pressure :"+pressure+"atm."+"\n humidity :"+humidity+"%."
                                +"\n wind :"+wind+"miles/hour."+"\n Weather Type :"+description+".\n"+" cloudy : "+cl
                                +"%\n (in city "+city+")";
                        textView.setText(output);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.toString().trim(),Toast.LENGTH_LONG).show();
                }
            }
            );
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}