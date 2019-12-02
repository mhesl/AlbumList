package com.example.alnumlist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alnumlist.R;

public class PhotosFragment extends Fragment {
    RecyclerView photosRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.photos_fragment , container , false);
        photosRecyclerView = view.findViewById(R.id.photosRecyclerView);







        return view;
    }
}
