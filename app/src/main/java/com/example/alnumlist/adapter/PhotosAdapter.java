package com.example.alnumlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.alnumlist.R;
import com.example.alnumlist.models.Album_Details_Model;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> {
    private List<Album_Details_Model> models;
    private Context context;

    public PhotosAdapter(List<Album_Details_Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_detail_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(models.get(position).getUrl()).into(holder.photoModel);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView photoModel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            photoModel = itemView.findViewById(R.id.photoModel);
        }
    }
}
