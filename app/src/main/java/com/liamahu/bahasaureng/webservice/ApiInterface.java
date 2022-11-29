package com.liamahu.bahasaureng.webservice;


import com.liamahu.bahasaureng.model.SoalKataResponse;
import com.liamahu.bahasaureng.model.kosakata.KosaKataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("kuis_kata")
    Call<SoalKataResponse> kuis_kata();

    @GET("kosakata")
    Call<KosaKataResponse> kosakata();

    @GET("cari_kosakata")
    Call<KosaKataResponse> carikosakata(
            @Query("kata") String kata

    );


}