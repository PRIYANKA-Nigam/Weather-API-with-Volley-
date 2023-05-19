package com.example.weatherapp.Food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.FoodViewHolder>{
Context context;
ArrayList<RootObjectModel> arrayList;

    public RecipeAdapter(Context context, ArrayList<RootObjectModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_entries,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
     RootObjectModel model=arrayList.get(position);
     holder.textView1.setText("Label : "+model.getRecipeModel().getLabel());
     holder.textView2.setText("Source : "+model.getRecipeModel().getSource());
     holder.textView3.setText("Yield : "+String.valueOf(model.getRecipeModel().getYield()));
     holder.textView4.setText("Calories : "+String.valueOf(model.getRecipeModel().getCalories()));
     holder.textView5.setText("Weight : "+String.valueOf(model.getRecipeModel().getTotalWeight()));
        Glide.with(context).load(model.getRecipeModel().getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (arrayList!=null){
            return arrayList.size();
        }
        return 0;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{
ImageView imageView;
TextView textView1,textView2,textView3,textView4,textView5;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.img);
            textView1=itemView.findViewById(R.id.tt);
            textView2=itemView.findViewById(R.id.tt2);
            textView3=itemView.findViewById(R.id.tt3);
            textView4=itemView.findViewById(R.id.tt4);
            textView5=itemView.findViewById(R.id.tt5);

        }
    }
}
