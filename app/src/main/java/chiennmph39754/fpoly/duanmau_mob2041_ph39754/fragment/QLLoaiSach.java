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
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter.LoaiSachAdapter;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter.ThanhVienAdapter;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.LoaiSachDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThanhVienDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.LoaiSach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThanhVien;


public class QLLoaiSach extends Fragment {
ListView listView;
FloatingActionButton fabloaisach;
Dialog dialog;
EditText maloai, tenlsach;
Button btnsave,btncancel;
ArrayList<LoaiSach> list;
static LoaiSachDao dao;
LoaiSachAdapter adapter;
LoaiSach item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_q_l_loai_sach, container, false);
        listView = view.findViewById(R.id.lv_loaisach);
        fabloaisach = view.findViewById(R.id.fabLoaiSach);
        dao = new LoaiSachDao(getActivity());
        capNhatLv();
        fabloaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogLoaiSach(getActivity(),0);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                opendialogLoaiSach(getContext(),1);

                return false;
            }
        });
        return view;
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
                    Toast.makeText(getActivity(),"Xóa loại sách thành công ",Toast.LENGTH_SHORT).show();
                    capNhatLv();
                }else{
                    Toast.makeText(getActivity(),"Xóa loại sách thất bại ",Toast.LENGTH_SHORT).show();
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
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }
    protected void opendialogLoaiSach(final Context context, final int type) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loaisach);
        maloai=dialog.findViewById(R.id.edtmaloai);
        tenlsach = dialog.findViewById(R.id.edttenloaisach);
        btnsave = dialog.findViewById(R.id.btnsaveloaisach);
        btncancel = dialog.findViewById(R.id.btncancelloaisach);
        maloai.setEnabled(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setCancelable(true);

         // Thêm dòng này để khởi tạo đối tượng item

        if (type != 0) {
            maloai.setText(String.valueOf(item.getMaLSach()));
            tenlsach.setText(item.getTenLSach());
        }
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new LoaiSach();
                item.setTenLSach(tenlsach.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaLSach(Integer.parseInt(maloai.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public int validate(){
        int check = 1;
        if(tenlsach.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập thể loại sách", Toast.LENGTH_SHORT).show();
            check =-1;
        }
        return check;
    }
}