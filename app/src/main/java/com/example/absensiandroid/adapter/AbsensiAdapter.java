package com.example.absensiandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensiandroid.R;
import com.example.absensiandroid.entity.Absensi;

import java.util.ArrayList;

public class AbsensiAdapter extends RecyclerView.Adapter<AbsensiAdapter.AbsensiViewHolder> {
    private ArrayList<Absensi> absensis;
    Context context;

    public AbsensiAdapter(ArrayList<Absensi> absensis, Context context) {
        this.absensis = absensis;
        this.context = context;
    }

    @NonNull
    @Override
    public AbsensiAdapter.AbsensiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_history, parent, false);
        return new AbsensiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsensiAdapter.AbsensiViewHolder holder, int position) {
        holder.tvTanggal.setText(absensis.get(position).getTanggalMasuk());
        holder.tvMasuk.setText(absensis.get(position).getTanggalMasuk());
        holder.tvKeluar.setText(absensis.get(position).getTanggalKeluar());

    }

    @Override
    public int getItemCount() {
        return absensis.size();
    }

    public class AbsensiViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTanggal, tvMasuk, tvKeluar, tvLembur;
        public AbsensiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal2);
            tvMasuk = itemView.findViewById(R.id.tv_masuk2);
            tvKeluar = itemView.findViewById(R.id.tv_keluar2);
            tvLembur = itemView.findViewById(R.id.tv_lembur2);
        }
    }
}
