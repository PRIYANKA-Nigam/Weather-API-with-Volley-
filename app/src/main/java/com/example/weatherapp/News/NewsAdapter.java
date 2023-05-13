package com.example.weatherapp.News;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{
    Context context;
    ArrayList<NewsModel> arrayList;

    public NewsAdapter(Context context, ArrayList<NewsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.news_item_row,parent,false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NewsModel modal=arrayList.get(position);
        Glide.with(context).load(modal.getImage()).into(holder.imageView);
        holder.t1.setText(modal.getTitle());
        holder.t2.setText(modal.getDate());
        holder.t4.setText(modal.getBulk());
        holder.t3.setText(""+(position+1));
        holder.t5.setText("Description : "+modal.getDescription());
        holder.t6.setText("Content : "+modal.getContent());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,NewsViewActivity.class);
                intent.putExtra("bulk",modal.getBulk());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView t1,t2,t3,t4,t5,t6;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imageView);
            t1=itemView.findViewById(R.id.textView);
            t2=itemView.findViewById(R.id.textView2);
            t3=itemView.findViewById(R.id.tt);
            t4=itemView.findViewById(R.id.t); t4.setVisibility(View.GONE);
            t5=itemView.findViewById(R.id.textView3);
            t6=itemView.findViewById(R.id.textView4);
            cardView = itemView.findViewById(R.id.cd);

        }
    }
}
