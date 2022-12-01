package com.liamahu.bahasaureng.ui.quiz.activity.kata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.databinding.ActivityHasilKuisKataBinding;

public class HasilKuisKataActivity extends AppCompatActivity {

    ActivityHasilKuisKataBinding binding;
    Integer nilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_hasil_kuis_kata);
        binding.getLifecycleOwner();

        Intent intent = getIntent();
        nilai = intent.getIntExtra("nilai",0);

        binding.txtnilai.setText(nilai.toString());

        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}