package chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.database.DbHelper;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThanhVien;


public class ThanhVienDao {
    SQLiteDatabase db;

    public ThanhVienDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertThanhVien(ThanhVien tv){
        ContentValues values = new ContentValues();
        values.put("hoten", tv.getHoten());
        values.put("namsinh", tv.getNamsinh());
        return db.insert("THANHVIEN", null, values);
    }
    public int update(ThanhVien tv){
        ContentValues values = new ContentValues();
        values.put("hoten", tv.getHoten());
        values.put("namsinh", tv.getNamsinh());
        return db.update("THANHVIEN",values,"matv=?",new String[]{String.valueOf(tv.getMatv())});
    }
    public int delete(String id){
        return db.delete("THANHVIEN","matv=?",new String[]{id});
    }
    //get tất cả data
    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM THANHVIEN";
        return getData(sql);
    }

    public ThanhVien getID(String id){
        String sql = "SELECT * FROM THANHVIEN WHERE matv=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }
    public int checkLogin(String ma ,String matkhau){
        String  sql ="SELECT * FROM THANHVIEN WHERE matv = ? AND matkhau = ?";
        List<ThanhVien> list = getData(sql, ma, matkhau);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    @SuppressLint("Range")
    private List<ThanhVien> getData(String sql, String... SelectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, SelectionArgs);
        while (c.moveToNext()) {
            ThanhVien tv = new ThanhVien();
            tv.matv = (c.getInt(c.getColumnIndex("matv")));
            tv.hoten = c.getString(c.getColumnIndex("hoten"));
            tv.namsinh = c.getString(c.getColumnIndex("namsinh"));
            list.add(tv);
        }
        return list;
    }
}
