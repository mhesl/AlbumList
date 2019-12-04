package com.example.alnumlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alnumlist.R;
import com.example.alnumlist.models.Album_Model;

import java.util.List;

public class FavoriteFragmentAdapter extends RecyclerView.Adapter<FavoriteFragmentAdapter.MyVIewHolder> {
    private Context context;
    private List<Album_Model> models;
    private addListener addListener;

    public FavoriteFragmentAdapter(Context context, List<Album_Model> models ,addListener addListener) {
        this.context = context;
        this.models = models;
        this.addListener=addListener;
    }

    @NonNull
    @Override
    public MyVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_model, parent, false);
        return new MyVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVIewHolder holder, int position) {
        holder.setClickListener();
        holder.albumTitle.setText(models.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyVIewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView albumTitle;
        private ImageView imageView;

        public MyVIewHolder(@NonNull View itemView) {
            super(itemView);
            albumTitle = itemView.findViewById(R.id.albumTitle);
            imageView = itemView.findViewById(R.id.imageView);
            albumTitle.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.albumTitle){
                addListener.showPhotos(getAdapterPosition());
            }
        }

        void setClickListener(){
            albumTitle.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

    }
    public interface addListener {
        void showPhotos(int adapterPosition);
    }
}
