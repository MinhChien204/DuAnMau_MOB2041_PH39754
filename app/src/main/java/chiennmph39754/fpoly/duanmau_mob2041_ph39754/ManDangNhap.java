package chiennmph39754.fpoly.duanmau_mob2041_ph39754;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThanhVienDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.ThuThuDao;

public class ManDangNhap extends AppCompatActivity {
    TextInputLayout edtTk,edtMk;
    Button btndangnhap,btnhuy;
    CheckBox chkluu;
    ThuThuDao ttdao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_nhap);
        edtTk = findViewById(R.id.edtTaiKhoan);
        edtMk = findViewById(R.id.edtMatKhau);
        chkluu =findViewById(R.id.chkLuu);
        btndangnhap = findViewById(R.id.btnDangNhap);
        btnhuy =findViewById(R.id.btnHuy);
        ImageView logo = findViewById(R.id.logo_login);

        ttdao = new ThuThuDao(this);
        Glide.with(this).load(R.mipmap.giphy_signup).into(logo);

        SharedPreferences pref = getSharedPreferences("GHINHO_FILE", MODE_PRIVATE);
        edtTk.getEditText().setText(pref.getString("TAIKHOAN",""));
        edtMk.getEditText().setText(pref.getString("MATKHAU",""));
        chkluu.setChecked(pref.getBoolean("NHO",false));
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              checklogin();
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTk.getEditText().setText("");
                edtMk.getEditText().setText("");

            }
        });
    }
    public void checklogin(){
        String User = edtTk.getEditText().getText().toString();
        String Pass = edtMk.getEditText().getText().toString();
        if(User.isEmpty() || Pass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
        }else{
            if(ttdao.checkdangnhap(User,Pass)){
                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                Remember(User,Pass,chkluu.isChecked());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",User);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void Remember(String u,String m  ,boolean status){
        SharedPreferences preferences= getSharedPreferences("GHINHO_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit =preferences.edit();

        if (!status){
            edit.clear();
        }else {
            edit.putString("TAIKHOAN",u);
            edit.putString("MATKHAU",m);
            edit.putBoolean("NHO",status);
        }
        edit.commit();

    }
}