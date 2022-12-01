package com.liamahu.bahasaureng.ui.quiz.activity.kata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.Utils.Constant;
import com.liamahu.bahasaureng.databinding.ActivityKataBinding;
import com.liamahu.bahasaureng.model.DataItem;
import com.liamahu.bahasaureng.model.JawabanItem;
import com.liamahu.bahasaureng.model.SoalKataResponse;
import com.liamahu.bahasaureng.model.jawabpilihanganda.JawabPilihanGandaResponse;
import com.liamahu.bahasaureng.webservice.ApiClient;
import com.liamahu.bahasaureng.webservice.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KataActivity extends AppCompatActivity {
    ActivityKataBinding binding;

    String TAG = "liamahu";
    Integer jumlah_urutan;
    Integer jumlah_maksimal;
    Integer id_jawaban;
    Integer id_pilihanganda;
    //loading
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kata);
        binding.setLifecycleOwner(this);

        progressDialog = new ProgressDialog(this);

        binding.shimmergambar.startShimmer();

        getsoal();
        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showalert();

            }
        });

        binding.txthome.setText("KUIS KATA");

    }

    private void getsoal() {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<SoalKataResponse> call = apiInterface.kuis_kata();
        call.enqueue(new Callback<SoalKataResponse>() {

            @Override
            public void onResponse(Call<SoalKataResponse> call, Response<SoalKataResponse> response) {
                if (response.isSuccessful()) {

                    ArrayList<Integer> jawaban = new ArrayList<Integer>();
                    ArrayList<Integer> pilihanganda = new ArrayList<Integer>();
                    binding.shimmergambar.stopShimmer();
                    binding.shimmergambar.setVisibility(View.GONE);
                    binding.imgkata.setVisibility(View.VISIBLE);
                    SoalKataResponse soalKataResponse;
                    List<DataItem> dataItems = response.body().getData();
                     jumlah_maksimal = response.body().getData().size() - 1;
                     jumlah_urutan = 0;

                    //tombol radio group
                    binding.radioGroup.setOrientation(LinearLayout.VERTICAL);
                    response.body().getData().get(jumlah_urutan).getJawaban().size();
                    RadioButton rdbtn = null;
                    for (final JawabanItem data :  dataItems.get(jumlah_urutan).getJawaban()){
                        rdbtn = new RadioButton(KataActivity.this);
                        rdbtn.setId(data.getId());
                        rdbtn.setText( data.getJawaban());
                        binding.radioGroup.addView(rdbtn);
                        rdbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                id_jawaban = data.getId();
                                id_pilihanganda = data.getIdPilihanganda();
                            }
                        });

                    }

                    //menampilkan gambar
                    Picasso.get().load(new Constant().storage + response.body().getData().get(0).getGambar()).fit().centerCrop().into(binding.imgkata);
                    //ketika tombol next ditekan
                    RadioButton finalRdbtn = rdbtn;
                    binding.btnnext.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             if (binding.radioGroup.getCheckedRadioButtonId() == -1){
                                 Toast.makeText(KataActivity.this, "Tidak di cek", Toast.LENGTH_SHORT).show();
                             }
                             else {
                                 binding.radioGroup.removeAllViews();
                                 //cek data array
                                 if (jawaban.size() == jumlah_urutan){
                                     jawaban.add(jumlah_urutan,id_jawaban);
                                     pilihanganda.add(jumlah_urutan,id_pilihanganda);
                                 }else {
                                     jawaban.set(jumlah_urutan,id_jawaban);
                                     pilihanganda.set(jumlah_urutan,id_pilihanganda);
                                 }
                                 Log.d(TAG, "onClick: " + jawaban);



                                 jumlah_urutan = jumlah_urutan + 1;
                                 Picasso.get().load(new Constant().storage + response.body().getData().get(jumlah_urutan).getGambar()).fit().centerCrop().into(binding.imgkata);
                                 Log.d(TAG, "Urutan Next: " + jumlah_urutan);

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

                                 //pilihan ganda
                                 for (final JawabanItem data :  dataItems.get(jumlah_urutan).getJawaban()){
                                     RadioButton rdbtn = new RadioButton(KataActivity.this);
                                     rdbtn.setId(data.getId());
                                     rdbtn.setText( data.getJawaban());
//                                     Log.d(TAG, "for urutan: " + jumlah_urutan);
//                                     Log.d(TAG, "for data: " + data.getId());
                                     //cek size jawaban
                                     Integer jumlah_size = jawaban.size() -1;
                                     if (jumlah_size >=jumlah_urutan){
//                                         Log.d(TAG, "for jaban: " + jawaban.get(jumlah_urutan));
                                         if (rdbtn.getId() == jawaban.get(jumlah_urutan)) {
                                             rdbtn.setChecked(true);
                                             id_jawaban = jawaban.get(jumlah_urutan);
                                             id_pilihanganda = pilihanganda.get(jumlah_urutan);

                                         }

                                     }
//                                     Log.d(TAG, "for jumlah size: " + jawaban.size());

                                     binding.radioGroup.addView(rdbtn);
//                                     Log.d(TAG, "onClick: "+rdbtn.getId());
                                     rdbtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             id_jawaban = data.getId();
                                             id_pilihanganda = data.getIdPilihanganda();
                                         }
                                     });
                                 }



                             }


                         }
                     });

                     binding.btnkembali.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             binding.radioGroup.removeAllViews();

                             jumlah_urutan = jumlah_urutan - 1;
                             Log.d(TAG, "onClick: " + jawaban);
                             Log.d(TAG, "Uruta: " + jumlah_urutan);
                             Log.d(TAG, "urutan_jawaban: " + jawaban.get(0));
                             id_jawaban = jawaban.get(jumlah_urutan);
                             id_jawaban = pilihanganda.get(jumlah_urutan);
                             Picasso.get().load(new Constant().storage + response.body().getData().get(jumlah_urutan).getGambar()).fit().centerCrop().into(binding.imgkata);

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

                             //pilihan ganda
                             for (final JawabanItem data :  dataItems.get(jumlah_urutan).getJawaban()){
                                 RadioButton rdbtn = new RadioButton(KataActivity.this);
                                 rdbtn.setId(data.getId());
                                 if (rdbtn.getId() == jawaban.get(jumlah_urutan)){
                                     rdbtn.setChecked(true);

                                 }
                                 rdbtn.setText( data.getJawaban());
                                 binding.radioGroup.addView(rdbtn);
                                 rdbtn.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         id_jawaban = data.getId();
                                         id_pilihanganda = data.getIdPilihanganda();
                                     }
                                 });
                             }

                         }
                     });

                     binding.btnproses.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             if (jawaban.size() == jumlah_urutan){
                                 jawaban.add(jumlah_urutan,id_jawaban);
                                 pilihanganda.add(jumlah_urutan,id_pilihanganda);
                             }else {
                                 jawaban.set(jumlah_urutan,id_jawaban);
                                 pilihanganda.set(jumlah_urutan,id_pilihanganda);
                             }

                             progressDialog.setMessage("Loading...");
                             progressDialog.setTitle("Mengirim jawaban");
                             progressDialog.setCanceledOnTouchOutside(false);
                             progressDialog.show();

                             //proses jawaban
                             proses(jawaban,pilihanganda);


                             Log.d(TAG, "Jawaban: " + jawaban);
                             Log.d(TAG, "Pilihan Ganda: " + pilihanganda);
                         }
                     });
                } else {

                }
            }

            @Override
            public void onFailure(Call<SoalKataResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void proses(ArrayList<Integer> jawaban, ArrayList<Integer> pilihanganda) {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<JawabPilihanGandaResponse> call = apiInterface.jawab_kuis_kata(jawaban, pilihanganda);
        call.enqueue(new Callback<JawabPilihanGandaResponse>() {
            @Override
            public void onResponse(Call<JawabPilihanGandaResponse> call, Response<JawabPilihanGandaResponse> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    Intent intent = new Intent(KataActivity.this, HasilKuisKataActivity.class);
                    intent.putExtra("nilai" ,response.body().getNilaiTotal());
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<JawabPilihanGandaResponse> call, Throwable t) {
                    progressDialog.dismiss();
                Toast.makeText(KataActivity.this, "Silahkan coba lagi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        showalert();
    }

    private void showalert() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title dialog
            alertDialogBuilder.setTitle("Keluar dari quiz ?");

            // set pesan dari dialog
            alertDialogBuilder
                    .setMessage("Klik Ya untuk keluar!")
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // jika tombol diklik, maka akan menutup activity ini
                            KataActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // jika tombol ini diklik, akan menutup dialog
                            // dan tidak terjadi apa2
                            dialog.cancel();
                        }
                    });

            // membuat alert dialog dari builder
            AlertDialog alertDialog = alertDialogBuilder.create();

            // menampilkan alert dialog
            alertDialog.show();

    }


}