package com.example.weatherapp.News;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.weatherapp.R;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsAPIActivity extends AppCompatActivity {
    private static final String TAG = "NewsAPIActivity";
RecyclerView recyclerView;
    ArrayList<NewsModel> arrayList;
    NewsAdapter adapter;
//    private  final String url="https://newsapi.org/v2/top-headlines";
    private final String appid="8f8a3aa5ca2f4ad19f554c7587321a23";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_a_p_i);
        recyclerView=findViewById(R.id.rec);
        AndroidNetworking.initialize(getApplicationContext());

        // setting the JacksonParserFactory
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initializing the ArrayList of articles
   arrayList=new ArrayList<>();
adapter=new NewsAdapter(this,arrayList);
recyclerView.setAdapter(adapter);
        // calling get_news_from_api()
        getNewsDetail();

    }

    private void getNewsDetail() {
        AndroidNetworking.get("https://newsapi.org/v2/top-headlines")
                .addQueryParameter("country", "in")
                .addQueryParameter("apiKey",appid)
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        // disabling the progress bar
//                        mProgressBar.setVisibility(View.GONE);

                        // handling the response
                        try {

                            // storing the response in a JSONArray
                            JSONArray articles=response.getJSONArray("articles");

                            // looping through all the articles
                            // to access them individually
                            for (int j=0;j<articles.length();j++)
                            {
                                // accessing each article object in the JSONArray
                                JSONObject article=articles.getJSONObject(j);

                                // initializing an empty ArticleModel
//                                NewsArticle currentArticle=new NewsArticle();

                                // storing values of the article object properties
//                                String author=article.getString("author");
                                String title=article.getString("title");
                                String description=article.getString("description");
                                String url=article.getString("url");
                                String urlToImage=article.getString("urlToImage");
                                String publishedAt=article.getString("publishedAt");
                                String[] s=publishedAt.split("T");
                                String date=s[0];
                                String content=article.getString("content");
                                String bulk=url;
                                NewsModel model=new NewsModel(urlToImage,title,date,bulk,description,content);
                                arrayList.add(model);

                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            // logging the JSONException LogCat
                            Log.d(TAG,"Error : "+e.getMessage());
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        // logging the error detail and response to LogCat
                        Log.d(TAG,"Error detail : "+error.getErrorDetail());
                        Log.d(TAG,"Error response : "+error.getResponse());
                    }
                });
    }



//
}