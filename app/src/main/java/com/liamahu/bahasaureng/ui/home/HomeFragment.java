package com.liamahu.bahasaureng.ui.home;

import static com.liamahu.bahasaureng.MainActivity.mAppBarConfiguration;
import static com.liamahu.bahasaureng.MainActivity.navController;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.NavigationUI;

import com.liamahu.bahasaureng.MainActivity;
import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.databinding.FragmentHomeBinding;

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

        return  binding.getRoot();
    }

}