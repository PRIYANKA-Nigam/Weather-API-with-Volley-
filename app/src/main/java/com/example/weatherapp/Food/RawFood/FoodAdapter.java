package com.example.weatherapp.Food.RawFood;

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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{
    Context context;
    ArrayList<FoodModel> arrayList;

    public FoodAdapter(Context context, ArrayList<FoodModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_row_item,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
     FoodModel model =arrayList.get(position);
        Glide.with(context).load(model.getImage()).into(holder.imageView);
        holder.textView1.setText(model.getName());
        holder.textView2.setText("Category : "+model.getCategory());
        holder.textView3.setText("Fat : "+model.getFat());
        holder.textView4.setText("Fibre : "+model.getFibre());
        holder.textView5.setText("Protein : "+model.getProtein());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setFilter(ArrayList<FoodModel> newList) {
      arrayList=new ArrayList<>();
      arrayList.addAll(newList);
        notifyDataSetChanged();
    }


    class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1,textView2,textView3,textView4,textView5;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imageView2);
            textView1=itemView.findViewById(R.id.tt);
            textView2=itemView.findViewById(R.id.tt2);
            textView3=itemView.findViewById(R.id.tt3);
            textView4=itemView.findViewById(R.id.tt4);
            textView5=itemView.findViewById(R.id.tt5);

        }
    }
}
