package chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.QLLoaiSach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.LoaiSach;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> list;
    QLLoaiSach qlLoaiSach;
     TextView maloai, tenloai;
    ImageView delete;

    public LoaiSachAdapter(@NonNull Context context, QLLoaiSach qlLoaiSach, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.qlLoaiSach = qlLoaiSach;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_loaisach, parent, false);
        }
        final LoaiSach item = list.get(position);
        if (view != null) {
            maloai = view.findViewById(R.id.tvmaloaisach);
            maloai.setText("Mã loại: " + item.getMaLSach());

            tenloai = view.findViewById(R.id.tvTenloaisach);
            tenloai.setText("Tên loại sách: " + item.getTenLSach());
        }

        delete = view.findViewById(R.id.deleteLoaiSach);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlLoaiSach.xoa(String.valueOf(item.getMaLSach()));
            }
        });

        return view;
    }
}