package com.liamahu.bahasaureng.ui.kosakata;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.adapter.KosaKataAdapter;
import com.liamahu.bahasaureng.databinding.FragmentKosaKataBinding;
import com.liamahu.bahasaureng.model.SoalKataResponse;
import com.liamahu.bahasaureng.model.kosakata.KosaKataModel;
import com.liamahu.bahasaureng.model.kosakata.KosaKataResponse;
import com.liamahu.bahasaureng.webservice.ApiClient;
import com.liamahu.bahasaureng.webservice.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KosaKataFragment extends Fragment {

    FragmentKosaKataBinding binding;
    private KosaKataAdapter adapter;
    String TAG = "liamahu";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kosa_kata, container, false);
        binding.setLifecycleOwner(this);

        binding.shimmerkosakata.startShimmer();

        getkosakata();
        return binding.getRoot();

    }

    private void getkosakata() {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<KosaKataResponse> call = apiInterface.kosakata();
        call.enqueue(new Callback<KosaKataResponse>() {
            @Override
            public void onResponse(Call<KosaKataResponse> call, Response<KosaKataResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        Log.d(TAG, "onResponse: " + response.message());
                        binding.rvkosakata.setVisibility(View.VISIBLE);
                        binding.shimmerkosakata.setVisibility(View.GONE);
                        binding.shimmerkosakata.stopShimmer();
                        List<KosaKataModel> DataWisataModel = response.body().getData();
                        generateDataList(DataWisataModel);

                    } else {
                        Toast.makeText(requireActivity(), "Response salah", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.d("sarah", "onResponse:  " + e);
                    getkosakata();
                }
            }

            @Override
            public void onFailure(Call<KosaKataResponse> call, Throwable t) {

            }
        });
    }


    private void generateDataList(List<KosaKataModel> dataWisataModels) {
        try {
            adapter = new KosaKataAdapter(dataWisataModels);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());

            binding.rvkosakata.setLayoutManager(layoutManager);

            binding.rvkosakata.setAdapter(adapter);


            binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    dataWisataModels.clear();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    getsearch(s, dataWisataModels);
                    return false;
                }
            });

        } catch (Exception e) {
            return;
        }

        adapter.setDialog(new KosaKataAdapter.Dialog() {
            @Override
            public void onClick(int position, KosaKataModel dataList) {

            }


        });


    }

    private void getsearch(String s, List<KosaKataModel> dataWisataModels) {
        dataWisataModels.clear();
        if (!TextUtils.isEmpty(s)){
            String searchtext =s.substring(0,1).toUpperCase() + s.substring(1);
            ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
            Call<KosaKataResponse> call = apiInterface.carikosakata(searchtext);
            call.enqueue(new Callback<KosaKataResponse>() {
                @Override
                public void onResponse(Call<KosaKataResponse> call, Response<KosaKataResponse> response) {
                    Log.d(TAG, "onResponse: " + response.message());
                    binding.rvkosakata.setVisibility(View.VISIBLE);
                    binding.shimmerkosakata.setVisibility(View.GONE);
                    binding.shimmerkosakata.stopShimmer();
                    List<KosaKataModel> DataWisataModel = response.body().getData();
                    generateDataList(DataWisataModel);

                }

                @Override
                public void onFailure(Call<KosaKataResponse> call, Throwable t) {

                }
            });

        }
    }

}