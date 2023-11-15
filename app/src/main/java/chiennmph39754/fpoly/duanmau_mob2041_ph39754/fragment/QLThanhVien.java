package chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter.ThanhVienAdapter;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThanhVienDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThanhVien;


public class QLThanhVien extends Fragment {
ListView listView;
FloatingActionButton fladd;
Dialog dialog;
EditText edmatv, edTentv,edNamSinh;
Button btnSave,btnCancel;
ArrayList<ThanhVien> list;
static ThanhVienDao dao;
ThanhVienAdapter adapter;
ThanhVien item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_q_l_thanh_vien, container, false);
        listView = view.findViewById(R.id.lv_thanhvien);
        fladd = view.findViewById(R.id.fabThanhVien);
        dao = new ThanhVienDao(getActivity());
        capNhatLv();
        fladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogThanhVien(getActivity(),0);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                opendialogThanhVien(getContext(),1);

                return false;
            }
        });
        return view;
    }
    protected void opendialogThanhVien(final Context context,final int type){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_themthanhvien);
        edmatv =dialog.findViewById(R.id.edtmathanhvien);
        edTentv =dialog.findViewById(R.id.edttenthanhvien);
        edNamSinh = dialog.findViewById(R.id.edtnamsinh);
        btnSave = dialog.findViewById(R.id.btnSaveThanhVien);
        btnCancel = dialog.findViewById(R.id.btnCancelThanhVien);

        edmatv.setEnabled(false);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setCancelable(true);
        if(type!=0){
            edmatv.setText(String.valueOf(item.getMatv()));
            edTentv.setText(item.getHoten());
            edNamSinh.setText(item.getNamsinh());
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new ThanhVien();
                item.hoten = edTentv.getText().toString();
                item.namsinh =edNamSinh.getText().toString();
                if(validate()> 0){
                    if(type ==0){
                        if(dao.insertThanhVien(item)>0){
                            Toast.makeText(context, "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm khách hàng thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMatv(Integer.parseInt(edmatv.getText().toString()));
                        if(dao.update(item)>0){
                            Toast.makeText(context, "sửa khách hàng thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "sửa khách hàng thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("bạn có chắc muốn xóa không ");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(dao.delete(id)>0){
                    Toast.makeText(getActivity(),"Xóa Khách hàng thành công ",Toast.LENGTH_SHORT).show();
                    capNhatLv();

                }else{
                    Toast.makeText(getActivity(),"Xóa Khách hàng không thành công ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog =builder.create();
        builder.show();
    }
    void capNhatLv(){
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }
    public int validate(){
        int check =1;

        String tenthanhvien = edTentv.getText().toString();
        String namsinh = edNamSinh.getText().toString();


        if(tenthanhvien.isEmpty() && namsinh.isEmpty()){
            edTentv.setError("không được để trống");
            edNamSinh.setError("không được để trống");

            check=-1;
        }else if(tenthanhvien.isEmpty() ){
            edTentv.setError("không được để trống");
            edNamSinh.setError(null);
            check=-1;
        }else if(tenthanhvien.isEmpty()){
            edTentv.setError(null);
            edNamSinh.setError("không được để trống");
            check=-1;
        }else{
            try {
                Integer.parseInt(edNamSinh.getText().toString()) ;
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity(),"số điện thoại ko nhận chữ ",Toast.LENGTH_SHORT).show();
                check =-1;
            }
        }



        return check;
    }
}