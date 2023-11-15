package chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThanhVienDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.QLThanhVien;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThanhVien;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    QLThanhVien qlThanhVien;
    private TextView matv, tentv, namsinh;
    ImageView delete;
    ThanhVienDao tvDao;

    public ThanhVienAdapter(@NonNull Context context, QLThanhVien qlThanhVien, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.qlThanhVien = qlThanhVien;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_thanhvien,null);
        }
        final ThanhVien item = list.get(position);
        if(view!=null){
            matv = view.findViewById(R.id.tv_MTV);
            matv.setText("Mã thành viên:"+item.getMatv());

            tentv = view.findViewById(R.id.tv_TTV);
            tentv.setText("Tên thành viên:"+item.getHoten());

            namsinh = view.findViewById(R.id.tv_NS);
            namsinh.setText("Năm sinh:"+item.getNamsinh());
        }
        delete = view.findViewById(R.id.deletethanhvien);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlThanhVien.xoa(String.valueOf(item.getMatv()));
            }
        });
        return view;
    }
}
