package com.example.alnumlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alnumlist.adapter.MainAdapter;
import com.example.alnumlist.database.album.AlbumDataSource;
import com.example.alnumlist.models.Album_Model;

import java.util.List;

public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private List<Album_Model> album_models;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_recycler_view , container , false);
        recyclerView = view.findViewById(R.id.main_recycler_view);
        album_models = AlbumDataSource.getInstance().getAlbums();
        mainAdapter = new MainAdapter(getActivity() , album_models);
        return view;
    }
}
