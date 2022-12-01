package com.liamahu.bahasaureng.webservice;


import com.liamahu.bahasaureng.model.SoalKataResponse;
import com.liamahu.bahasaureng.model.jawabpilihanganda.JawabPilihanGandaResponse;
import com.liamahu.bahasaureng.model.kosakata.KosaKataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("jawab_kuis_kata")
    Call<JawabPilihanGandaResponse> jawab_kuis_kata(
            @Field("jawaban[]") ArrayList<Integer> jawaban,
            @Field("id_pilihanganda[]") ArrayList<Integer> id_pilihanganda
    );


}