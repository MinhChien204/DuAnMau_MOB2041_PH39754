package chiennmph39754.fpoly.duanmau_mob2041_ph39754.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "DANGKIMONHOC", null,16
        );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String dbThuThu = "CREATE TABLE THUTHU(matt text primary key, hoten text,matkhau text)";
        sqLiteDatabase.execSQL(dbThuThu);

        String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key autoincrement, hoten text,namsinh text)";
        sqLiteDatabase.execSQL(dbThanhVien);

        String dbLoai = "CREATE TABLE LOAISACH(maloai integer primary key autoincrement, tenloai text)";
        sqLiteDatabase.execSQL(dbLoai);


        String dbSach = "CREATE TABLE SACH(masach integer primary key autoincrement,tensach text,giathue integer,maloai integer references LOAISACH(maloai),kinhdoanh integer,soluong integer)";
        sqLiteDatabase.execSQL(dbSach);

        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement,matv integer references THANHVIEN(matv),matt text references THUTHU(matt), masach integer references SACH(masach),ngay text , trasach integer, tienthue integer)";
        sqLiteDatabase.execSQL(dbPhieuMuon);

        sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES(1,'Thiếu nhi'),(2,'Tình cảm'),(3,'giáo khoa')");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(1,'Hãy đợi đấy',2500,1,0,200),(2,'1 bước yêu vạn dặm ngu',5000,2,1,300),(3,'Lập trình kotlin',10000,1,1,400)");
        sqLiteDatabase.execSQL("INSERT INTO THUTHU VALUES('thuthu1','nguyễn minh chiến','123'),('thuthu2','nguyễn minh Châu','123')");
        sqLiteDatabase.execSQL("INSERT INTO THANHVIEN VALUES(1,'Minh Chiến',2004)");
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(1,1,'thuthu1',1,'17/9/2023',1,4000),(2,1,'thuthu1',2,'25/9/2023',0,5000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            onCreate(sqLiteDatabase);
        }
    }
}
