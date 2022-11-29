package com.liamahu.bahasaureng.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.liamahu.bahasaureng.R;
import com.liamahu.bahasaureng.Utils.Constant;
import com.liamahu.bahasaureng.model.kosakata.KosaKataModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class KosaKataAdapter extends RecyclerView.Adapter<KosaKataAdapter.FoodViewHolder> {


    private List<KosaKataModel> dataList;
    private Dialog dialog;


    public interface Dialog {
        void onClick(int position, KosaKataModel dataList);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    public KosaKataAdapter(List<KosaKataModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_kosakata, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        holder.txtkata.setText(dataList.get(position).getKata());
        holder.txtbahasa.setText(dataList.get(position).getBahasa());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.onClick(holder.getLayoutPosition(),dataList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView txtkata, txtbahasa;

        public FoodViewHolder(View itemView) {
            super(itemView);
            txtkata = (TextView) itemView.findViewById(R.id.txtkata);
            txtbahasa = (TextView) itemView.findViewById(R.id.txtbahasa);



        }
    }
}