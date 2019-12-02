package com.example.alnumlist.webservice;

import com.example.alnumlist.models.Album_Details_Model;
import com.example.alnumlist.models.Album_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoApiInterface {

    @GET("photos")
    Call<List<Album_Details_Model>> getUsers() ;

}
