package com.example.alnumlist;

import android.os.Bundle;
import android.util.Log;
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
import com.example.alnumlist.webservice.ApiInterface;
import com.example.alnumlist.webservice.NetworkHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private List<Album_Model> album_models;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_recycler_view , container , false);
        recyclerView = view.findViewById(R.id.main_recycler_view);
        //album_models = AlbumDataSource.getInstance().getAlbums();
        mainAdapter = new MainAdapter(getActivity() , album_models);
        ApiInterface apiInterface = NetworkHandler.getRetrofit().create(ApiInterface.class);
        Call<List<Album_Model>> call = apiInterface.getUsers();
        Log.d("retrofit", "onResponse: " + "d");
        call.enqueue(new Callback<List<Album_Model>>() {
            @Override
            public void onResponse(Call<List<Album_Model>> call, Response<List<Album_Model>> response) {
                Log.d("retrofit", "onResponse: " +"g");
                if (response.isSuccessful()) {
                    List<Album_Model> posts = response.body();
                    Log.d("retrofit", "onResponse: " + posts.get(1).getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<Album_Model>> call, Throwable t) {
                Log.d("retrofit", "onFailure: ");
            }
        });
        return view;
    }
}
