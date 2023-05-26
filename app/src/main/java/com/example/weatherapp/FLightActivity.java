package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FLightActivity extends AppCompatActivity {
    private  final String url="https://test.api.amadeus.com/v2/shopping/flight-offers";
    private final String appid="xGG5LnmAwRQUfzhpZWWGvTPqSFrMFS0t";
    EditText e1,e2,e3,e4;
TextView textView; //////not worikign
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_light);
        e1=(EditText)findViewById(R.id.ed);
        e2=(EditText)findViewById(R.id.ed2);
        e3=(EditText)findViewById(R.id.ed3);
        e4=(EditText)findViewById(R.id.ed4);
        textView=findViewById(R.id.textView2);

    }

    public void getFlightDetails(View view) {
        String tempUrl="";
    String source = e1.getText().toString();
    String dst=e2.getText().toString();
    String date=e3.getText().toString();
     String cnt=e4.getText().toString();
     if (source.equals("")||dst.equals("")||date.equals("")||cnt.equals("")){
         Toast.makeText(getApplicationContext(),"Enter all the required fields",Toast.LENGTH_SHORT).show();
     }else {
           tempUrl=url+"?originLocationCode="+source+"&destinationLocationCode="+dst+"&departureDate="+date+"&adults="+cnt;
         StringRequest stringRequest=new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 String output=""; String price="",count="";
                 JSONObject jsonObject= null;
                 String src[] = new String[Integer.MAX_VALUE];
                    String arr[] = new String[Integer.MAX_VALUE];
                String stops[] = new String[Integer.MAX_VALUE];
                 try {
                     jsonObject = new JSONObject(response);
                     JSONArray jsonArray=jsonObject.getJSONArray("data");
                     for(int i=0;i<jsonArray.length();i++){
                         JSONObject jsonObject1=jsonArray.getJSONObject(i);
                         JSONArray jsonArray1 =jsonObject1.getJSONArray("itineraries");
                         JSONObject jsonObject2=jsonArray1.getJSONObject(0);
                         JSONArray jsonArray2=jsonObject2.getJSONArray("segments");
                         for (int j=0;j<jsonArray2.length();j++){
                             JSONObject jsonObject3=jsonArray2.getJSONObject(j);
                             JSONObject jsonObject4=jsonObject3.getJSONObject("departure");
                             String src1= jsonObject4.getString("iataCode");
                             JSONObject jsonObject5=jsonObject3.getJSONObject("arrival");
                             String arr1 = jsonObject5.getString("iataCode");
                             String stops1=jsonObject3.getString("numberOfStops");
                             src[j]=src1;
                             arr[j]=arr1;
                             stops[j]=stops1;
                         }
                         JSONObject jsonObject3 =new JSONObject("price");
                         price=jsonObject3.getString("total");
                          count=jsonObject1.getString("numberOfBookableSeats");
                     }
                     textView.setTextColor(Color.rgb(68,134,199));
                     String sourcePoint=src[0];
                     String destinationPoint=arr[0];
                     String terminals="";
                     for (int k=0;k<src.length;k++){
                         terminals+= src[k]+","+arr[k]+",";
                     }
                     output+="Departing From "+sourcePoint+"\n Departing To " +destinationPoint+"\n No of intermediate stops "+terminals
                             +"\n Total Fare :"+price+"\n Total Passengers :"+count;
                     textView.setText(output);
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(FLightActivity.this,error.toString().trim(),Toast.LENGTH_LONG).show();

             }
         }
         ){
             @Override
             public Map<String, String> getHeaders() throws AuthFailureError {
                 Map<String, String>  params = new HashMap<>();
                 params.put("X-RapidAPI-Host", "https://test.api.amadeus.com/v2/shopping/flight-offers");
                 params.put("X-RapidAPI-Key", "xGG5LnmAwRQUfzhpZWWGvTPqSFrMFS0t");   //changedkey
                 return params;
             };
         };
         RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
         requestQueue.add(stringRequest);
     }
    }
}