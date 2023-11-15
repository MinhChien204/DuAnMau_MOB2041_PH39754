package chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter.LoaiSachSpinnerAdapter;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter.SachAdapter;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.LoaiSachDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.SachDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.LoaiSach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Sach;


public class QlSach extends Fragment {
    ListView lvSach;
    SachDao sachDao;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue,edsoluong;
    CheckBox chkkinhdoanh;
    Spinner spinner;
    Button save, cancel;
    SachAdapter adapter;
    Sach item;
    List<Sach> list;
    LoaiSachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<LoaiSach> listLS;
    LoaiSachDao loaiSachDao;
    LoaiSach loaiSach;
    int maloaisach, position;

    SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_sach, container, false);
        lvSach = view.findViewById(R.id.lv_sach);
        fab =view.findViewById(R.id.fabSach);
        sachDao = new SachDao(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog(getActivity(),0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item =list.get(i);
                opendialog(getActivity(),1);
                return false;
            }
        });
        capnhatLV();
        return view;
    }

    void capnhatLV() {
        list = (List<Sach>) sachDao.getAll();
        adapter = new SachAdapter(getActivity(), this, list);
        lvSach.setAdapter(adapter);
    }

    public void xoa(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sachDao.delete(id);
                capnhatLV();
                dialogInterface.cancel();
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    protected void opendialog(Context context,int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_sach);
        edMaSach = dialog.findViewById(R.id.edtmasach);
        edTenSach = dialog.findViewById(R.id.edttensach);
        edGiaThue = dialog.findViewById(R.id.edtgia);
        chkkinhdoanh = dialog.findViewById(R.id.chkkinhdoanh);
        edsoluong =dialog.findViewById(R.id.edtsoluong);
        spinner = dialog.findViewById(R.id.spnloaiSach);
        save = dialog.findViewById(R.id.btnsaveSach);
        cancel = dialog.findViewById(R.id.btnCancelSach);

        listLS = new ArrayList<>();
        loaiSachDao = new LoaiSachDao(context);
        listLS =(ArrayList<LoaiSach>) loaiSachDao.getAll();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setCancelable(true);

        sachSpinnerAdapter = new LoaiSachSpinnerAdapter(context,listLS);
        spinner.setAdapter(sachSpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maloaisach =listLS.get(i).getMaLSach();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edMaSach.setEnabled(false);
        if(type !=0){
            edMaSach.setText(String.valueOf(item.getMasach()));
            edTenSach.setText(item.getTensach());
            edGiaThue.setText(String.valueOf(item.getGiathue()));
            edsoluong.setText(String.valueOf(item.getSoluong()));
            for(int i = 0;i<listLS.size();i++)
                if(item.getMaloai() ==(listLS.get(i).getMaLSach())){
                    position =1;
                }
            if(item.getKinhdoanh() ==1){
                chkkinhdoanh.setChecked(true);
            }else{
                chkkinhdoanh.setChecked(false);
            }
            Log.i("demo","posSach "+position);
            spinner.setSelection(position);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new Sach();
                item.setTensach(edTenSach.getText().toString());
                item.setGiathue(Integer.parseInt(edGiaThue.getText().toString()));
                item.setSoluong(Integer.parseInt(edsoluong.getText().toString()));
                item.setMaloai(maloaisach);
                if (chkkinhdoanh.isChecked()) {
                    item.setKinhdoanh(1);
                } else {
                    item.setKinhdoanh(0);
                }
                if(validate()>0){
                    if(type ==0){
                        if(sachDao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMasach(Integer.parseInt(edMaSach.getText().toString()));
                        if(sachDao.update(item)>0){
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capnhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if(edTenSach.getText().length()==0||edGiaThue.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }
        return check;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.drawer_menu,menu);
        SearchManager searchManager =(SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchitem = menu.findItem(R.id.search);
        searchView = (SearchView) searchitem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                timkiem(s);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }
    public void timkiem(String query){
        ArrayList<Sach> list2= new ArrayList<>();
        try {
            int luong = Integer.parseInt(query);
            for(Sach sh : list){
                if(sh.getSoluong() <=luong){
                    list2.add(sh);
                }
            }
            adapter = new SachAdapter(getContext(),this,list2);
            lvSach.setAdapter(adapter);
        }catch (NumberFormatException e){
            String NormalQuery = khongdau(query.toLowerCase());
            for(Sach sh:list){
                String tensacha = khongdau(sh.getTensach().toLowerCase());
                if(tensacha.contains(NormalQuery)){
                    list2.add(sh);
                }
            }
            adapter = new SachAdapter(getContext(),this,list2);
            lvSach.setAdapter(adapter);
        }

    }
    public String khongdau(String str){
        String bodau = Normalizer.normalize(str,Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCOMBINING_DIACRITICAL_MARKS}+");
        return pattern.matcher(bodau).replaceAll("");
    }
}