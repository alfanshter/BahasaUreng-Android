package com.liamahu.bahasaureng.ui.quiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liamahu.bahasaureng.MainActivity;
import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.databinding.FragmentQuizBinding;
import com.liamahu.bahasaureng.ui.quiz.activity.KataActivity;

public class QuizFragment extends Fragment {

    FragmentQuizBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_quiz,container,false);
        binding.setLifecycleOwner(this);


        binding.btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawer.openDrawer(GravityCompat.START);

            }
        });

        binding.txthome.setText("KUIS");

        binding.btnkata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((requireContext()), KataActivity.class);
                startActivity(intent);

            }
        });

        return  binding.getRoot();

    }
}