package chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.MainActivity;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.ManDangNhap;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThuThuDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThuThu;


public class DoiMatKhau extends Fragment {

    ThuThuDao ttdao;
    EditText oldpass, newpass, repass;
    Button btnsave, btncancel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        ttdao = new ThuThuDao(getActivity());
        oldpass = view.findViewById(R.id.fmChangePass_edPassOld);
        newpass = view.findViewById(R.id.fmChangePass_edPassNew);
        repass = view.findViewById(R.id.fmChangePass_edRePass);
        btnsave = view.findViewById(R.id.fmChangePass_btnSave);
        btncancel = view.findViewById(R.id.fmChangePass_btnCancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpass.setText("");
                newpass.setText("");
                repass.setText("");
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("GHINHO_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("TAIKHOAN", "");
                if (validate() > 0) {
                    ThuThu thuthu = ttdao.getID(user);
                    thuthu.setMatkhau(newpass.getText().toString());
                    ttdao.update(thuthu);
                    if (ttdao.update(thuthu) > 0) {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        oldpass.setText("");
                        newpass.setText("");
                        repass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
    public int validate() {
        int check = 1;
        if (oldpass.getText().length() == 0 || newpass.getText().length() == 0 || repass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences pref = getActivity().getSharedPreferences("GHINHO_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("MATKHAU", "");
            String pass = newpass.getText().toString();
            String rePass = repass.getText().toString();
            if (!passOld.equals(oldpass.getText().toString())) {
                Toast.makeText(getActivity(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(getActivity(), "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
            }
        }
        return check;
    }

}