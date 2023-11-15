package chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThuThuDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThuThu;


public class TaoTaiKhoan extends Fragment {

TextInputLayout user,hoten,matkhau,repass;
Button btnsave;
ThuThuDao thuThuDao;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user =view.findViewById(R.id.edUsername);
        hoten =view.findViewById(R.id.edTenThanhVien);
        matkhau =view.findViewById(R.id.edPassword);
        repass =view.findViewById(R.id.edRepass);
        btnsave= view.findViewById(R.id.btn_SaveTV);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuThu tt = new ThuThu();
                tt.matt = user.getEditText().getText().toString();
                tt.hoten = hoten.getEditText().getText().toString();
                tt.matkhau = matkhau.getEditText().getText().toString();
                thuThuDao = new ThuThuDao(getContext());
                if(validate()>0){
                    if(thuThuDao.insertThuThu(tt)>0){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public int validate(){
        int check = 1;
        if(user.getEditText().getText().toString().length()==0|| hoten.getEditText().getText().toString().length()==0 || matkhau.getEditText().getText().toString().length()==0 || repass.getEditText().getText().toString().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }else if(!matkhau.getEditText().getText().toString().equals(repass.getEditText().getText().toString())){
            Toast.makeText(getContext(), "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
            check =-1;
        }
        return check;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tao_tai_khoan, container, false);
        return view;
    }
}