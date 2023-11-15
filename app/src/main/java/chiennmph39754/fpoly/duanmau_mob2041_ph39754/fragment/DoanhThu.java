package chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.PhieuMuonDao;


public class DoanhThu extends Fragment {
Button btnTuNgay,btnDenNgay,btnthongke;
EditText tungay,denngay;
TextView doanhthu;
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
int year,month,day;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        tungay =view.findViewById(R.id.edtTuNgay);
        denngay = view.findViewById(R.id.edtDenNgay);
        doanhthu = view.findViewById(R.id.tv_DoanhThu);
        btnTuNgay =view.findViewById(R.id.btn_tuNgay);
        btnDenNgay =view.findViewById(R.id.btn_denNgay);
        btnthongke =view.findViewById(R.id.btn_ThongKe);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month =c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,dateTuNgay,year,month,day);
                d.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month =c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,datedenngay,year,month,day);
                d.show();
            }
        });
        btnthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = tungay.getText().toString();
                String denNgay = denngay.getText().toString();
                PhieuMuonDao phieuMuonDao = new PhieuMuonDao(getActivity());
                doanhthu.setText("Doanh Thu: "+phieuMuonDao.getDoanhThu(tuNgay,denNgay)+ "VND");
            }
        });
        return view;
    }
    DatePickerDialog.OnDateSetListener dateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year =i;
            month = i1;
            day = i2;
            GregorianCalendar c = new GregorianCalendar(year,month,day);
            tungay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener datedenngay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year =i;
            month = i1;
            day = i2;
            GregorianCalendar c = new GregorianCalendar(year,month,day);
            denngay.setText(sdf.format(c.getTime()));
        }
    };

}