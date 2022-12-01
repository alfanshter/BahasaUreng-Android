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

        getsoal();

    }

    private void getsoal() {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<SoalKalimatResponse> call = apiInterface.kuis_kalimat();
        call.enqueue(new Callback<SoalKalimatResponse>() {

            @Override
            public void onResponse(Call<SoalKalimatResponse> call, Response<SoalKalimatResponse> response) {
                if (response.isSuccessful()) {

                    ArrayList<Integer> id_kalimat = new ArrayList<Integer>();
                    ArrayList<String> jawaban = new ArrayList<String>();
                    binding.shimmersoal.stopShimmer();
                    binding.shimmersoal.setVisibility(View.GONE);
                    binding.lnsoal.setVisibility(View.VISIBLE);
                    SoalKalimatResponse SoalKalimatResponse;
                    List<SoalKalimatModel> SoalKalimatModels = response.body().getData();
                    jumlah_maksimal = response.body().getData().size() - 1;
                    jumlah_urutan = 0;

                    binding.txtsoal.setText(SoalKalimatModels.get(0).getSoal());


                    binding.btnnext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (binding.edtjawab.getText().toString().isEmpty()){
                                Toast.makeText(KalimatActivity.this, "Jangan kosongi jawaban", Toast.LENGTH_SHORT).show();
                            }else {
                                //cek data array
                                if (jawaban.size() == jumlah_urutan){
                                    id_kalimat.add(jumlah_urutan,SoalKalimatModels.get(jumlah_urutan).getId());
                                    jawaban.add(jumlah_urutan,binding.edtjawab.getText().toString());
                                    binding.edtjawab.setText("");

                                }else {
                                    id_kalimat.set(jumlah_urutan,SoalKalimatModels.get(jumlah_urutan).getId());
                                    jawaban.set(jumlah_urutan,binding.edtjawab.getText().toString());
                                    Log.d(TAG, "urutan: " + jumlah_urutan);
                                    Log.d(TAG, "urutan jawaban: " + jawaban.size());

                                }


                                jumlah_urutan = jumlah_urutan + 1;
                                binding.txtsoal.setText(SoalKalimatModels.get(jumlah_urutan).getSoal());

                                //jika jumlah soal lebih besar sama dengan 0 DAN jumlah soal kurang dari jumlah maksima soal
                                //maka tombol next di aktifkan
                                //jika tidak tombol next di non aktifkan
                                if (jumlah_urutan >=0 && jumlah_urutan <jumlah_maksimal){
                                    binding.btnnext.setVisibility(View.VISIBLE);
                                }else {
                                    binding.btnnext.setVisibility(View.GONE);
                                }
                                //jika jumlah soal sudah melebihi jumlah soal maksimal
                                // maka tombol proses di nyalakan
                                if (jumlah_urutan.equals(jumlah_maksimal)){
                                    binding.btnproses.setVisibility(View.VISIBLE);
                                }else{
                                    binding.btnproses.setVisibility(View.GONE);
                                }

                                if (jumlah_urutan >0){
                                    binding.btnkembali.setVisibility(View.VISIBLE);
                                }else {
                                    binding.btnkembali.setVisibility(View.GONE);
                                }

                            }

                        }
                    });
                    binding.btnkembali.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            jumlah_urutan = jumlah_urutan - 1;
                            binding.edtjawab.setText(jawaban.get(jumlah_urutan));

                            //jika jumlah soal lebih besar sama dengan 0 DAN jumlah soal kurang dari jumlah maksima soal
                            //maka tombol next di aktifkan
                            //jika tidak tombol next di non aktifkan
                            if (jumlah_urutan >=0 && jumlah_urutan <jumlah_maksimal){
                                binding.btnnext.setVisibility(View.VISIBLE);
                            }else {
                                binding.btnnext.setVisibility(View.GONE);
                            }
                            //jika jumlah soal sudah melebihi jumlah soal maksimal
                            // maka tombol proses di nyalakan
                            if (jumlah_urutan.equals(jumlah_maksimal)){
                                binding.btnproses.setVisibility(View.VISIBLE);
                            }else{
                                binding.btnproses.setVisibility(View.GONE);
                            }

                            if (jumlah_urutan >0){
                                binding.btnkembali.setVisibility(View.VISIBLE);
                            }else {
                                binding.btnkembali.setVisibility(View.GONE);
                            }



                        }
                    });
                    binding.btnproses.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d(TAG, "Id kalimat: " + id_kalimat);
                            Log.d(TAG, "Jawaban: " + jawaban);


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