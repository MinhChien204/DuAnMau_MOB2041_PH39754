package chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.SachDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThanhVienDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.QLPhieuMuon;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.PhieuMuon;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Sach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThanhVien;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    QLPhieuMuon fragment;
    private Context context;
    private ArrayList<PhieuMuon> list;
    TextView maPM,tentv, tenSach, tienThue, ngay, traSach;
    ImageView delete;
    SachDao sachDao;
    ThanhVienDao thanhVienDao;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PhieuMuonAdapter(@NonNull Context context, QLPhieuMuon fragment, ArrayList<PhieuMuon> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_recyclerview_phieumuon, null);
        }
        final PhieuMuon item = list.get(position);
        if (item != null) {
            maPM = v.findViewById(R.id.tvMaPhieuPM);
            maPM.setText("Mã Phiếu: "+item.getMapm());

            SachDao sachDao  = new SachDao(context);
            Sach sach = sachDao.getID(String.valueOf(item.getMasach()));
            tenSach =v.findViewById(R.id.tvtensachPM);
            tenSach.setText("Tên sách: "+sach.getTensach());

            thanhVienDao = new ThanhVienDao(context);
            ThanhVien thanhVien =thanhVienDao.getID(String.valueOf(item.getMatv()));
            tentv =v.findViewById(R.id.tvthanhvienPM);
            tentv.setText("Thành viên: "+thanhVien.getHoten());

            tienThue =v.findViewById(R.id.tvtienthuePM);
            tienThue.setText("Tiền thuê: "+item.getTienthue());

            ngay=v.findViewById(R.id.tvngayPM);
            ngay.setText("Ngày thuê:"+sdf.format(item.getNgay()));

            traSach=v.findViewById(R.id.tvtrasachPM);
            if(item.getTrasach()==1){
                traSach.setTextColor(Color.BLUE);
                traSach.setText("Đã trả sách");
            }else{
                traSach.setTextColor(Color.RED);
                traSach.setText("Chưa trả sách");
            }
            delete =v.findViewById(R.id.deletePhieuMuon);
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMapm()));
            }
        });
        return v;
    }
}
