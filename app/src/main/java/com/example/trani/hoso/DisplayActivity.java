package com.example.trani.hoso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    private TextView hotenTv;
    private TextView mobileTv;
    private TextView emailTv;
    private TextView gioitinhTv;
    private TextView sothichTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        hotenTv = findViewById(R.id.textView6);
        mobileTv = findViewById(R.id.textView7);
        emailTv = findViewById(R.id.textView8);
        gioitinhTv = findViewById(R.id.textView9);
        sothichTv = findViewById(R.id.textView10);

        Intent intent = getIntent();
        String ten = intent.getStringExtra(MainActivity.HO_TEN);
        String mobile = intent.getStringExtra(MainActivity.MOBILE);
        String email = intent.getStringExtra(MainActivity.EMAIL);
        String gioitinh = intent.getStringExtra(MainActivity.GIOI_TINH);
        ArrayList<String> sothich = (ArrayList<String>) intent.getSerializableExtra(MainActivity.SO_THICH);

        hotenTv.setText("Họ tên: " + ten);
        mobileTv.setText("Mobile: " + mobile);
        emailTv.setText("Email: " + email);
        gioitinhTv.setText("Họ tên: " + gioitinh);
        String stTV = "";
        for(String a : sothich){
            stTV += a + "   ";
        }
        sothichTv.setText("Sở thích: " + stTV);
    }
}
