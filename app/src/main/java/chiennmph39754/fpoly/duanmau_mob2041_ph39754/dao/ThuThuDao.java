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
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThuThu;

public class ThuThuDao {
    DbHelper dbHelper;
    SQLiteDatabase db;
    public ThuThuDao(Context context){
        dbHelper = new DbHelper(context);
        db =dbHelper.getWritableDatabase();
    }

    //đăng nhập
    public boolean checkdangnhap(String matt,String matkhau){
        SQLiteDatabase sqLiteDatabase = sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt=? AND matkhau=?",new String[]{matt,matkhau});
        if(cursor.getCount()!=0){
            return true;
        }else{
            return false;
        }
    }
    public long insertThuThu(ThuThu tt){
        ContentValues values = new ContentValues();
        values.put("matt",tt.getMatt());
        values.put("hoten", tt.getHoten());
        values.put("matkhau", tt.getMatkhau());
        return db.insert("THUTHU", null, values);
    }

    public int update(ThuThu tt){
        ContentValues values = new ContentValues();
        values.put("matt",tt.getMatt());
        values.put("hoten", tt.getHoten());
        values.put("matkhau", tt.getMatkhau());
        return db.update("THUTHU",values,"matt=?",new String[]{String.valueOf(tt.getMatt())});
    }

    public int delete(String id){
        return db.delete("THUTHU","matt=?",new String[]{id});
    }

    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM THUTHU";
        return getData(sql);
    }
    public ThuThu getID(String id){
        String sql = "SELECT * FROM THUTHU WHERE matt=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<ThuThu> getData(String sql, String ... SelectionArgs){

        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectionArgs);
        while (c.moveToNext()){
            ThuThu tt = new ThuThu();
            tt.matt = c.getString(c.getColumnIndex("matt"));
            tt.hoten =c.getString(c.getColumnIndex("hoten"));
            tt.matkhau = c.getString(c.getColumnIndex("matkhau"));
            list.add(tt);
        }
        return list;
    }
}
