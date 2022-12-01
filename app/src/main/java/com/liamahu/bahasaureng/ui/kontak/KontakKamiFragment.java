package com.liamahu.bahasaureng.ui.kontak;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liamahu.bahasaureng.MainActivity;
import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.databinding.FragmentKontakKamiBinding;


public class KontakKamiFragment extends Fragment {

    FragmentKontakKamiBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kontak_kami,container,false);
        binding.setLifecycleOwner(this);

        binding.btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawer.openDrawer(GravityCompat.START);

            }
        });

        binding.txthome.setText("TENTANG KAMI");

        return binding.getRoot();
    }
}