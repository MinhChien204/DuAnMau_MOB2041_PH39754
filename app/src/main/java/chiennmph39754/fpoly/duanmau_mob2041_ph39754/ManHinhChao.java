package chiennmph39754.fpoly.duanmau_mob2041_ph39754;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ManHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
       ImageView logo = findViewById(R.id.imglogo);
        Glide.with(this).load(R.mipmap.book1).into(logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(ManHinhChao.this, ManDangNhap.class);
                startActivity(i);
            }
        },3000);
    }


}