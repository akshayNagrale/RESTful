package com.example.restful;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientApi {

    @GET("marvel")
    Call<List<Data>> getData();
}
