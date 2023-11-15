package chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.database.DbHelper;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.LoaiSach;

public class LoaiSachDao {
  SQLiteDatabase db;
  public LoaiSachDao(Context context){
      DbHelper dbHelper = new DbHelper(context);
      db =dbHelper.getWritableDatabase();
  }
  public long insert(LoaiSach obj){
      ContentValues values = new ContentValues();
      values.put("tenloai",obj.getTenLSach());
      return db.insert("LOAISACH",null,values);
  }
  public int update(LoaiSach obj){
      ContentValues values = new ContentValues();
      values.put("tenloai",obj.getTenLSach());
      return db.update("LOAISACH",values,"maloai=?",new String[]{String.valueOf(obj.getMaLSach())});
  }
  public int delete(String id){
      return db.delete("LOAISACH","maloai=?",new String[]{id});
  }
  @SuppressLint("Range")
  private List<LoaiSach> getData(String sql, String...selectionArgs){
      List<LoaiSach> list = new ArrayList<>();
      Cursor c= db.rawQuery(sql,selectionArgs);
      while (c.moveToNext()){
          LoaiSach obj = new LoaiSach();
          obj.setMaLSach(Integer.parseInt(c.getString(c.getColumnIndex("maloai"))));
          obj.setTenLSach(c.getString(c.getColumnIndex("tenloai")));
          list.add(obj);
      }
      return list;

  }
  public List<LoaiSach> getAll(){
      String sql = "select * from LOAISACH";
      return getData(sql);
  }
  public LoaiSach getID(String id){
      String sql = "select * from LOAISACH where maloai=?";
      List<LoaiSach> list = getData(sql,id);
      return list.get(0);
  }
}