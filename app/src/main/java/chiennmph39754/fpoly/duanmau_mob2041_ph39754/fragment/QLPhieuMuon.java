package chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter.PhieuMuonAdapter;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter.SachSpinnerAdapter;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter.ThanhVienSpinnerAdapter;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.PhieuMuonDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.SachDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThanhVienDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.PhieuMuon;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Sach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThanhVien;


public class QLPhieuMuon extends Fragment {
    ListView lvphieumuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDao dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText mapm;
    Spinner spnTV, spnSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listthanhvien;
    ThanhVienDao thanhVienDao;
    ThanhVien thanhVien;
    int mathanhvien;

    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listsach;
    SachDao sachDao;
    Sach sach;
    int masach, tienthue;
    int positionTV, positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_phieu_muon, container, false);
        lvphieumuon = view.findViewById(R.id.lvphieumuon);
        dao = new PhieuMuonDao(getActivity());
        fab = view.findViewById(R.id.fabPhieuMuon);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog(getActivity(), 0);
            }
        });
        lvphieumuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                opendialog(getActivity(), 1);
                return false;
            }
        });
        capnhatLV();
        return view;
    }

    void capnhatLV() {
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lvphieumuon.setAdapter(adapter);
    }

    protected void opendialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_phieumuon);
        mapm = dialog.findViewById(R.id.edtmaPM);
        mapm.setEnabled(false);
        spnTV = dialog.findViewById(R.id.spnMaTV);
        spnSach = dialog.findViewById(R.id.spnMaSach);
        tvNgay = dialog.findViewById(R.id.tvngaydialog);
        tvTienThue = dialog.findViewById(R.id.tvtienthuedialog);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnSave = dialog.findViewById(R.id.btnsavePM);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        //set ngày
        tvNgay.setText("Ngày thuê: " + sdf.format(new Date()));

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setCancelable(true);

        thanhVienDao = new ThanhVienDao(context);
        listthanhvien = new ArrayList<>();
        listthanhvien = (ArrayList<ThanhVien>) thanhVienDao.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listthanhvien);
        spnTV.setAdapter(thanhVienSpinnerAdapter);
        spnTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                mathanhvien = listthanhvien.get(position).getMatv();
//                Toast.makeText(context, "Chọn "+listthanhvien.get(i).getHoten(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sachDao = new SachDao(context);
        listsach = new ArrayList<>();
        listsach = (ArrayList<Sach>) sachDao.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listsach);
        spnSach.setAdapter(sachSpinnerAdapter);
        spnSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                masach = listsach.get(position).getMasach();
                tienthue = listsach.get(position).getGiathue();
                tvTienThue.setText("Tiền thuê: " + tienthue);
//                Toast.makeText(context, "Chọn "+listsach.get(i).getTensach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //edit set data lên form
        if (type != 0) {
            mapm.setText(String.valueOf(item.getMapm()));
            for (int i = 0; i < listthanhvien.size(); i++)
                if (item.getMatv() == (listthanhvien).get(i).getMatv()) {
                    positionTV = i;
                }

            spnTV.setSelection(positionTV);

            for (int i = 0; i < listsach.size(); i++)
                if (item.getMasach() == (listsach).get(i).getMasach()) {
                    positionSach = i;
                }
            spnSach.setSelection(positionSach);

            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            tvTienThue.setText("Tiền thuê: "+item.getTienthue());
            if(item.getTrasach() ==1){
                chkTraSach.setChecked(true);
            }else{
                chkTraSach.setChecked(false);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new PhieuMuon();
                item.setMasach(masach);
                item.setMatv(mathanhvien);
                item.setNgay(new Date());
                item.setTienthue(tienthue);
                if (chkTraSach.isChecked()) {
                    item.setTrasach(1);
                } else {
                    item.setTrasach(0);
                }
                if (type == 0) {
                    //type = 0 thì insert
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //type = 1 thì update
                    item.setMapm(Integer.parseInt(mapm.getText().toString()));
                    if (dao.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capnhatLV();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(id);
                capnhatLV();
                dialog.cancel();
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        builder.show();
    }
}