package com.example.alnumlist.adapter;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.alnumlist.R;
import com.example.alnumlist.models.Album_Details_Model;

import java.util.List;
import java.util.Random;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> {
    private List<Album_Details_Model> models;
    private Context context;
    private int lastPosition;
    private addListener addListener;
    public PhotosAdapter(List<Album_Details_Model> models, Context context , addListener addListener) {
        this.models = models;
        this.context = context;
        this.addListener = addListener;
    }
    public void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f);
            animation.setDuration(new Random().nextInt(501));
            view.startAnimation(animation);
            lastPosition = position;
        }
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
        setAnimation(holder.itemView , position);
        holder.clickListener();
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView photoModel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            photoModel = itemView.findViewById(R.id.photoModel);
            photoModel.setOnClickListener(this);
        }

        void clickListener(){
            photoModel.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.photoModel){
                addListener.animate(view , models.get(getAdapterPosition()).getUrl());
            }
        }
    }

    public interface addListener{
        void animate(View view ,String url);
    }


}
