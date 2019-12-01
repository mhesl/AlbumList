package com.example.alnumlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.alnumlist.database.album.AlbumDataSource;
import com.example.alnumlist.models.Album_Model;
import com.example.alnumlist.webservice.ApiInterface;
import com.example.alnumlist.webservice.NetworkHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AlbumDataSource.initialize(getApplicationContext());
        if(AlbumDataSource.getInstance().getAlbums().size()>0) {
            ApiInterface apiInterface = NetworkHandler.getRetrofit().create(ApiInterface.class);
            Call<List<Album_Model>> call = apiInterface.getUsers();
            Log.d("retrofit", "onResponse: " + "d");
            call.enqueue(new Callback<List<Album_Model>>() {
                @Override
                public void onResponse(Call<List<Album_Model>> call, Response<List<Album_Model>> response) {
                    Log.d("retrofit", "onResponse: " + "g");
                    if (response.isSuccessful()) {
                        List<Album_Model> posts = response.body();
                        AlbumDataSource.getInstance().addAllAlbums(posts);
                    }
                }
                @Override
                public void onFailure(Call<List<Album_Model>> call, Throwable t) {
                    Log.d("retrofit", "onFailure: ");
                }
            });
        }

    }
}
