package chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.LoaiSach;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> list;
    TextView tvMaLoai, tvTenLoai;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.loaisach_item_spinner, null);
        }

        LoaiSach loaiSach = list.get(position);
        if (loaiSach != null){
            tvMaLoai = convertView.findViewById(R.id.tvMaLoaiSachSpn);
            tvMaLoai.setText(loaiSach.getMaLSach() + ". ");

            tvTenLoai = convertView.findViewById(R.id.tvTenLoaiSachSpn);
            tvTenLoai.setText(loaiSach.getTenLSach());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.loaisach_item_spinner, null);
        }

        LoaiSach loaiSach = list.get(position);
        if (loaiSach != null){
            tvMaLoai = convertView.findViewById(R.id.tvMaLoaiSachSpn);
            tvMaLoai.setText(loaiSach.getMaLSach() + ". ");

            tvTenLoai = convertView.findViewById(R.id.tvTenLoaiSachSpn);
            tvTenLoai.setText(loaiSach.getTenLSach());
        }
        return convertView;
    }
}
