package chiennmph39754.fpoly.duanmau_mob2041_ph39754;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.PhieuMuonDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThuThuDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.DoanhThu;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.DoiMatKhau;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.QLLoaiSach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.QLPhieuMuon;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.QLThanhVien;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.QlSach;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.TaoTaiKhoan;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.Top10;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.ThuThu;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
View headerview;
TextView tvUser;
ThuThuDao thuThuDao;
PhieuMuonDao phieuMuonDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);

        NavigationView nv = findViewById(R.id.navigationView);
        nv.setItemIconTintList(null);

//        headerview = nv.getHeaderView(0); // Thay R.id.navigationView bằng 0
//        tvUser = headerview.findViewById(R.id.tvUser);
//        Intent i = getIntent();
//        String user = i.getStringExtra("user");
//        thuThuDao = new ThuThuDao(this);
//        ThuThu thuThu = thuThuDao.getID(user);
//        String username = thuThu.getHoten();
//        tvUser.setText("Welcome " + username+ " !");
//
//        if(user.equalsIgnoreCase("admin")){
//            nv.getMenu().findItem(R.id.ThemTaiKhoan).setVisible(true);
//        }

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nv.setItemIconTintList(null);

        setTitle("Thư Viện Phương Nam");
        QLPhieuMuon qlPhieuMuon = new QLPhieuMuon();
        replaceFrg(qlPhieuMuon);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.QLphieumuon) {
                    setTitle("Quản Lí Phiếu Mượn");
                    QLPhieuMuon qlPhieuMuon = new QLPhieuMuon();
                    replaceFrg(qlPhieuMuon);
                } else if (item.getItemId() == R.id.QLloaisach) {
                    setTitle("Quản Lí Loại Sách");
                    QLLoaiSach qlLoaiSach = new QLLoaiSach();
                    replaceFrg(qlLoaiSach);
                } else if (item.getItemId() == R.id.QLsach) {
                    setTitle("Quản Lí Sách");
                    QlSach qlSach = new QlSach();
                    replaceFrg(qlSach);
                } else if (item.getItemId() == R.id.QLthanhvien) {
                    setTitle("Quản Lí Thành Viên");
                    QLThanhVien qlThanhVien = new QLThanhVien();
                    replaceFrg(qlThanhVien);
                } else if (item.getItemId() == R.id.Top10) {
                    setTitle("Top 10 Sách Mượn Nhiều Nhất");
                    Top10 top10 = new Top10();
                    replaceFrg(top10);
                } else if (item.getItemId() == R.id.DoanhThu) {
                    setTitle("Doanh Thu");
                    DoanhThu doanhThu = new DoanhThu();
                    replaceFrg(doanhThu);
                } else if (item.getItemId() ==R.id.ThemTaiKhoan) {
                    setTitle("Tạo Tài Khoản");
                    TaoTaiKhoan taoTaiKhoan = new TaoTaiKhoan();
                    replaceFrg(taoTaiKhoan);
            } else if (item.getItemId() == R.id.Doimatkhau) {
                    setTitle("Đổi Mật Khẩu");
                    DoiMatKhau doiMatKhau = new DoiMatKhau();
                    replaceFrg(doiMatKhau);
                } else if (item.getItemId() == R.id.DangXuat) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Đăng xuất");
                    builder.setMessage("Bạn có muốn đăng xuất không?");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this,ManDangNhap.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Log Out successful", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }


    public void replaceFrg(Fragment frg){
        FragmentManager fragmentManager =getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,frg).commit();
    }
}