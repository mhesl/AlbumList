package com.example.alnumlist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alnumlist.R;
import com.example.alnumlist.adapter.PhotosAdapter;
import com.example.alnumlist.models.Album_Details_Model;

import java.util.List;

public class PhotosFragment extends Fragment {
    private RecyclerView photosRecyclerView;
    private PhotosAdapter photosAdapter;
    private List<Album_Details_Model> models;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.photos_fragment , container , false);
        photosRecyclerView = view.findViewById(R.id.photosRecyclerView);
        photosAdapter = new PhotosAdapter(models , getActivity());
        photosRecyclerView.setAdapter(photosAdapter);
        photosRecyclerView.setLayoutManager(new GridLayoutManager(getActivity() , 2));
        return view;
    }
}
