package chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter;

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
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThanhVien;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ArrayList<ThanhVien> list;
TextView matv,tentv;
    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_thanhvienspn, null);
        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            matv = view.findViewById(R.id.tvMatvSpn);
            matv.setText(item.getMatv() + ". ");
            tentv = view.findViewById(R.id.tvTentvSpn);
            tentv.setText(item.getHoten());
        }
        return view;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_thanhvienspn, null);
        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            matv = view.findViewById(R.id.tvMatvSpn);
            matv.setText(item.getMatv() + ". ");
            tentv = view.findViewById(R.id.tvTentvSpn);
            tentv.setText(item.getHoten());
        }
        return view;
    }
}
