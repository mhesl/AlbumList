package com.example.alnumlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alnumlist.R;
import com.example.alnumlist.database.favorite.FavoriteDataSource;
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
        View view = LayoutInflater.from(context).inflate(R.layout.album_model_favorite, parent, false);
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
        private TextView delete;
        private ConstraintLayout layout;
        public MyVIewHolder(@NonNull View itemView) {
            super(itemView);
            albumTitle = itemView.findViewById(R.id.albumTitleFavorite);
            imageView = itemView.findViewById(R.id.imageViewFavorite);
            delete = itemView.findViewById(R.id.deleteFromFavorite);
            layout = itemView.findViewById(R.id.layout_favorite);
            albumTitle.setOnClickListener(this);
            imageView.setOnClickListener(this);
            delete.setOnClickListener(this);
            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.albumTitleFavorite ||view.getId() == R.id.imageViewFavorite || view.getId() == R.id.layout_favorite ){
                addListener.showPhotos(getAdapterPosition());
            }
            if(view.getId() == R.id.deleteFromFavorite){
                int adapterPosition = getAdapterPosition();
                Album_Model a = models.get(getAdapterPosition());
                models.remove(a);
                FavoriteDataSource.initialize(context);
                FavoriteDataSource.getInstance().open();
                FavoriteDataSource.getInstance().deleteTitle(a.getId());
                notifyItemRemoved(adapterPosition);
                notifyDataSetChanged();
            }
        }

        void setClickListener(){
            albumTitle.setOnClickListener(this);
            imageView.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

    }
    public interface addListener {
        void showPhotos(int adapterPosition);
    }


}
