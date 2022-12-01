package com.liamahu.bahasaureng.ui.home;

import static com.liamahu.bahasaureng.MainActivity.mAppBarConfiguration;
import static com.liamahu.bahasaureng.MainActivity.navController;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.NavigationUI;

import com.liamahu.bahasaureng.MainActivity;
import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.databinding.FragmentHomeBinding;
import com.liamahu.bahasaureng.ui.quiz.activity.kata.KataActivity;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        binding.setLifecycleOwner(this);

        binding.btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawer.openDrawer(GravityCompat.START);

            }
        });

        binding.txthome.setText("Home");

        binding.btnkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showalert();
            }
        });

        return  binding.getRoot();
    }

    private void showalert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(requireActivity());

        // set title dialog
        alertDialogBuilder.setTitle("Keluar dari aplikasi ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk keluar!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        requireActivity().finish();
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