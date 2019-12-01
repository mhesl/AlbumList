package com.example.alnumlist.webservice;

import com.example.alnumlist.models.Album_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("albums")
    Call<List<Album_Model>> getUsers() ;
}
