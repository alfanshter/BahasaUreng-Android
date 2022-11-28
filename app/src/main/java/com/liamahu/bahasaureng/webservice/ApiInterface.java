package com.liamahu.bahasaureng.webservice;


import com.liamahu.bahasaureng.model.SoalKataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("kuis_kata")
    Call<SoalKataResponse> kuis_kata();


}