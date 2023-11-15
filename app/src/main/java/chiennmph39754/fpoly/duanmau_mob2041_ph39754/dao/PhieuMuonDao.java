package chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.database.DbHelper;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.PhieuMuon;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Sach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Top;

public class PhieuMuonDao {
    SQLiteDatabase db;
    Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public PhieuMuonDao(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("matt",obj.getMatt());
        values.put("matv",obj.getMatv());
        values.put("masach",obj.getMasach());
        values.put("ngay",sdf.format(obj.getNgay()));
        values.put("tienthue",obj.getTienthue());
        values.put("trasach",obj.getTrasach());
        return db.insert("PHIEUMUON",null,values);
    }
    public int update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("matt",obj.getMatt());
        values.put("matv",obj.getMatv());
        values.put("masach",obj.getMasach());
        values.put("ngay",sdf.format(obj.getNgay()));
        values.put("tienthue",obj.getTienthue());
        values.put("trasach",obj.getTrasach());
        return db.update("PHIEUMUON",values,"mapm=?",new String[]{String.valueOf(obj.getMapm())});
    }
    public int delete(String id){
        return db.delete("PHIEUMUON","mapm=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.setMapm(Integer.parseInt(c.getString(c.getColumnIndex("mapm"))));
            obj.setMatt(c.getString(c.getColumnIndex("matt")));
            obj.setMatv(Integer.parseInt(c.getString(c.getColumnIndex("matv"))));
            obj.setMasach(Integer.parseInt(c.getString(c.getColumnIndex("masach"))));
            obj.setTienthue(Integer.parseInt(c.getString(c.getColumnIndex("tienthue"))));
            try {
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            }catch (Exception e){
                e.printStackTrace();
            }
            obj.setTrasach(Integer.parseInt(c.getString(c.getColumnIndex("trasach"))));
            list.add(obj);
        }
        return list;
    }
    public List<PhieuMuon> getAll(){
        String sql = "select * from PHIEUMUON";
        return getData(sql);
    }
    public PhieuMuon getID(String id){
        String sql = "select * from PHIEUMUON where mapm=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    public List<Top> getTop(){
        String sqlTop= "select masach,count(masach) as soluong from PHIEUMUON group by masach order by soluong desc limit 10";
        List<Top> list = new ArrayList<>();
        SachDao sachDao =new SachDao(context);
        Cursor cursor =db.rawQuery(sqlTop,null);

        while (cursor.moveToNext()){
            Top top = new Top();
            Sach sach = sachDao.getID(cursor.getString(cursor.getColumnIndex("masach")));
            top.setTensach(sach.getTensach());
            top.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
            list.add(top);
        }
        return list;
    }
    @SuppressLint("Range")

    public int getDoanhThu(String tuNgay,String denNgay){
        String sqldoanhthu = "select sum(tienthue) as doanhthu from PHIEUMUON where ngay between ? and ?";
        List<Integer> list = new ArrayList<>();
        Cursor c= db.rawQuery(sqldoanhthu,new String[]{tuNgay,denNgay} );
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
