package com.example.alnumlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alnumlist.R;
import com.example.alnumlist.models.Album_Model;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
 private Context context;
 private List<Album_Model> album_models;

    public MainAdapter(Context context, List<Album_Model> album_models) {
        this.context = context;
        this.album_models = album_models;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout)
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return album_models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
