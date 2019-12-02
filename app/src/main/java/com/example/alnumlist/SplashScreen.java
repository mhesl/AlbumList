package com.example.alnumlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.alnumlist.database.album.AlbumDataSource;
import com.example.alnumlist.database.photo.PhotoDataSource;
import com.example.alnumlist.models.Album_Details_Model;
import com.example.alnumlist.models.Album_Model;
import com.example.alnumlist.webservice.ApiInterface;
import com.example.alnumlist.webservice.NetworkHandler;
import com.example.alnumlist.webservice.PhotoApiInterface;

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
        AlbumDataSource.getInstance().open();
        PhotoDataSource.initialize(getApplicationContext());
        PhotoDataSource.getInstance();
        if(AlbumDataSource.getInstance().getAlbums().size()==0) {
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
        if(PhotoDataSource.getInstance().getPhotoCounts()==0){
            PhotoApiInterface apiInterface = NetworkHandler.getRetrofit().create(PhotoApiInterface.class);
            Call<List<Album_Details_Model>> call = apiInterface.getUsers();
            Log.d("retrofit", "onResponse: " + "d");
            call.enqueue(new Callback<List<Album_Details_Model>>() {
                @Override
                public void onResponse(Call<List<Album_Details_Model>> call, Response<List<Album_Details_Model>> response) {
                    Log.d("retrofit", "onResponse: " + "g");
                    if (response.isSuccessful()) {
                        List<Album_Details_Model> posts = response.body();
                        PhotoDataSource.getInstance().addAllPhotos(posts);
                    }
                }
                @Override
                public void onFailure(Call<List<Album_Details_Model>> call, Throwable t) {
                    Log.d("retrofit", "onFailure: ");
                }
            });
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,
                        MainActivity.class);
                startActivity(i);
                finish();

            }
        }, 10000);
    }
}
