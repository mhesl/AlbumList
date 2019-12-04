package com.example.alnumlist.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alnumlist.Photo;
import com.example.alnumlist.R;
import com.example.alnumlist.adapter.FavoriteFragmentAdapter;
import com.example.alnumlist.adapter.MainAdapter;
import com.example.alnumlist.database.album.AlbumDataSource;
import com.example.alnumlist.models.Album_Model;

import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteFragmentAdapter.addListener {
    private RecyclerView recyclerView;
    private FavoriteFragmentAdapter mainAdapter;
    private List<Album_Model> album_models;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_fragment , container , false);
        recyclerView = view.findViewById(R.id.favorite_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity() , 1));

        mainAdapter = new FavoriteFragmentAdapter(getActivity() , album_models , this);
        recyclerView.setAdapter(mainAdapter);
        return view;
    }

    @Override
    public void showPhotos(int adapterPosition) {
        int id = album_models.get(adapterPosition).getId();
        Intent intent = new Intent(getActivity(), Photo.class);
        intent.putExtra("id" , id+"");
        startActivity(intent);
    }
}
