package chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.database.DbHelper;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Sach;

public class SachDao {
    SQLiteDatabase db;
    public SachDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tensach",obj.getTensach());
        values.put("giathue",obj.getGiathue());
        values.put("maloai",obj.getMaloai());
        values.put("kinhdoanh",obj.getKinhdoanh());
        values.put("soluong",obj.getSoluong());
        return db.insert("SACH",null,values);
    }
    public int update(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tensach",obj.getTensach());
        values.put("giathue",obj.getGiathue());
        values.put("maloai",obj.getMaloai());
        values.put("kinhdoanh",obj.getKinhdoanh());
        values.put("soluong",obj.getSoluong());
        return db.update("SACH",values,"masach=?",new String[]{String.valueOf(obj.getMasach())});
    }
    public int delete(String id){
        return db.delete("SACH","masach=?",new String[]{id});
    }

    @SuppressLint("Range")
    public List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<>();
        Cursor c= db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Sach obj = new Sach();
            obj.setMasach(c.getInt(c.getColumnIndex("masach")));
            obj.setTensach(c.getString(c.getColumnIndex("tensach")));
            obj.setGiathue(c.getInt(c.getColumnIndex("giathue")));
            obj.setMaloai(c.getInt(c.getColumnIndex("maloai")));
            obj.setKinhdoanh(c.getInt(c.getColumnIndex("kinhdoanh")));
            obj.setSoluong(c.getInt(c.getColumnIndex("soluong")));
            list.add(obj);
        }
        return list;
    }
    public List<Sach> getAll(){
        String sql = "select * from SACH";
        return getData(sql);
    }
    public Sach getID(String id){
        String sql = "select * from SACH where masach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }
}
