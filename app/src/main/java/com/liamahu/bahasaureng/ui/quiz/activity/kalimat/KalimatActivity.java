package com.liamahu.bahasaureng.ui.quiz.activity.kalimat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.Utils.Constant;
import com.liamahu.bahasaureng.databinding.ActivityKalimatBinding;
import com.liamahu.bahasaureng.model.JawabanItem;
import com.liamahu.bahasaureng.model.soalkalimat.SoalKalimatModel;
import com.liamahu.bahasaureng.model.soalkalimat.SoalKalimatResponse;
import com.liamahu.bahasaureng.ui.quiz.activity.kata.KataActivity;
import com.liamahu.bahasaureng.webservice.ApiClient;
import com.liamahu.bahasaureng.webservice.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalimatActivity extends AppCompatActivity {
    ActivityKalimatBinding binding;
    Integer jumlah_urutan;
    Integer jumlah_maksimal;
    String TAG = "liamahu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_kalimat);
        binding.getLifecycleOwner();


    }

    private void getsoal() {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<SoalKalimatResponse> call = apiInterface.kuis_kalimat();
        call.enqueue(new Callback<SoalKalimatResponse>() {

            @Override
            public void onResponse(Call<SoalKalimatResponse> call, Response<SoalKalimatResponse> response) {
                if (response.isSuccessful()) {

                    ArrayList<Integer> jawaban = new ArrayList<Integer>();
                    ArrayList<Integer> pilihanganda = new ArrayList<Integer>();
                    binding.shimmersoal.stopShimmer();
                    binding.shimmersoal.setVisibility(View.GONE);
                    binding.lnsoal.setVisibility(View.VISIBLE);
                    SoalKalimatResponse SoalKalimatResponse;
                    List<SoalKalimatModel> SoalKalimatModels = response.body().getData();
                    jumlah_maksimal = response.body().getData().size() - 1;
                    jumlah_urutan = 0;


                    binding.btnnext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    binding.btnkembali.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    binding.btnproses.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });


                } else {

                }
            }

            @Override
            public void onFailure(Call<SoalKalimatResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}