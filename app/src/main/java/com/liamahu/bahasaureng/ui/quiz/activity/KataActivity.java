package com.liamahu.bahasaureng.ui.quiz.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liamahu.bahasaureng.MainActivity;
import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.databinding.ActivityKataBinding;
import com.liamahu.bahasaureng.databinding.FragmentQuizBinding;
import com.liamahu.bahasaureng.model.SoalKataResponse;
import com.liamahu.bahasaureng.webservice.ApiClient;
import com.liamahu.bahasaureng.webservice.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KataActivity extends AppCompatActivity {
    ActivityKataBinding binding;

    String TAG = "liamahu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_kata);
        binding.setLifecycleOwner(this);

        binding.shimmergambar.startShimmer();

        getsoal();
        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        binding.txthome.setText("KUIS KATA");

    }

    private void getsoal() {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<SoalKataResponse> call = apiInterface.kuis_kata();
        call.enqueue(new Callback<SoalKataResponse>(){

            @Override
            public void onResponse(Call<SoalKataResponse> call, Response<SoalKataResponse> response) {
                if (response.isSuccessful()){
                    binding.shimmergambar.stopShimmer();
                    binding.shimmergambar.setVisibility(View.GONE);
                    binding.imgkata.setVisibility(View.VISIBLE);
                }else {

                }
            }

            @Override
            public void onFailure(Call<SoalKataResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }


}