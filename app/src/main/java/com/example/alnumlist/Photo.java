package com.example.alnumlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.alnumlist.adapter.PhotosAdapter;
import com.example.alnumlist.database.photo.PhotoDataSource;
import com.example.alnumlist.models.Album_Details_Model;

import java.util.List;

public class Photo extends AppCompatActivity {
    private RecyclerView photosRecyclerView;
    private PhotosAdapter photosAdapter;
    private List<Album_Details_Model> models;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        photosRecyclerView =findViewById(R.id.photosRecyclerView);
        String id = (getIntent().getStringExtra("id"));
        models = PhotoDataSource.getInstance().getAlbumPhotos(Integer.valueOf(id));
        photosAdapter = new PhotosAdapter(models , this);
        photosRecyclerView.setAdapter(photosAdapter);
        photosRecyclerView.setLayoutManager(new GridLayoutManager(this , 2));
    }
}
