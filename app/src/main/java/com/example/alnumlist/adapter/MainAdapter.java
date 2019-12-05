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
import com.example.alnumlist.database.favorite.FavoriteDataSource;
import com.example.alnumlist.models.Album_Model;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private Context context;
    private List<Album_Model> album_models;
    public addListener addListener;



    public MainAdapter(Context context, List<Album_Model> album_models, addListener addListener) {
        this.context = context;
        this.album_models = album_models;
        this.addListener = addListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_model, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setClickListener();
        holder.albumTitle.setText(album_models.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return album_models.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView albumTitle;
        private ImageView imageView;
        private TextView addToFavorite;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            albumTitle = itemView.findViewById(R.id.albumTitle);
            imageView = itemView.findViewById(R.id.imageView);
            addToFavorite = itemView.findViewById(R.id.addToFavorite);
            addToFavorite.setOnClickListener(this);
            albumTitle.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

        void setClickListener() {
            addToFavorite.setOnClickListener(this);
            albumTitle.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.albumTitle || view.getId() == R.id.imageView) {
                addListener.showPhotos(getAdapterPosition());
            }
            if(view.getId()==R.id.addToFavorite){
                FavoriteDataSource.initialize(context);
                FavoriteDataSource.getInstance().open();
                FavoriteDataSource.getInstance().addSingleAlbum(album_models.get(getAdapterPosition()));
                addListener.setNotify();
            }
        }
    }

    public interface addListener {
        void showPhotos(int adapterPosition);
        void setNotify();
    }
}
