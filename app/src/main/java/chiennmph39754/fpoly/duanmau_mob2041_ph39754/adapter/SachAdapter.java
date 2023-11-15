package chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.List;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.LoaiSachDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.QlSach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.LoaiSach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Sach;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private QlSach qlSach;
    private List<Sach> list;
TextView maSach, tenSach,giaThue,maLoai,kinhdoanh,soluong;
ImageView delete;
    public SachAdapter(@NonNull Context context,QlSach qlSach, List<Sach> list) {
        super(context, 0,list);
        this.context = context;
        this.qlSach = qlSach;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v== null){
            LayoutInflater inflater =((Activity)context).getLayoutInflater();
            v =inflater.inflate(R.layout.item_sach,parent,false);
        }
        Sach item = list.get(position);
        if(item!=null){
            LoaiSachDao loaiSachDao = new LoaiSachDao(context);
            LoaiSach loaiSach = loaiSachDao.getID(String.valueOf(item.getMaloai()));
            maSach =v.findViewById(R.id.tvMaSach);
            maSach.setText("Mã sách: "+item.getMasach());
            tenSach =v.findViewById(R.id.tvTenSach);
            tenSach.setText("Tên sách: "+item.getTensach());
            giaThue = v.findViewById(R.id.tvGiaThue);
            giaThue.setText("Giá thuê: "+item.getGiathue());
            maLoai =v.findViewById(R.id.tvLoai);
            maLoai.setText("Loại sách: "+loaiSach.getTenLSach());

            kinhdoanh =v.findViewById(R.id.tvkinhdoanh);
            if(item.getKinhdoanh() ==1){
                kinhdoanh.setTextColor(Color.RED);
                kinhdoanh.setText("Đang kinh doanh");
            }else{
                kinhdoanh.setTextColor(Color.YELLOW);
                kinhdoanh.setText("Ngưng kinh doanh");
            }

            soluong =v.findViewById(R.id.tvsoluong);
            soluong.setText("Số lượng: "+item.getSoluong());

            delete =v.findViewById(R.id.deleteSach);
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlSach.xoa(String.valueOf(item.getMasach()));
            }
        });
        return v;
    }
}
