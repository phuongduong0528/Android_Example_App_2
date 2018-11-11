package com.example.trani.hoso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String HO_TEN = "hoten";
    public static final String MOBILE = "mobile";
    public static final String EMAIL = "email";
    public static final String GIOI_TINH = "gioitinh";
    public static final String SO_THICH = "sothich";
    public  static final int REQUEST_CODE_GALLERY = 100;
    private EditText pName;
    private EditText mobile;
    private EditText email;
    private RadioGroup gioitinh;
    private CheckBox[] soThich;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pName = findViewById(R.id.editText); //LẤY THÔNG TIN ID EDITTEXT VÀ CÁC ĐIỀU KHIỂN KHÁC => findViewById(int id)
        mobile = findViewById(R.id.editText5);
        email = findViewById(R.id.editText6);
        gioitinh = findViewById(R.id.radioGroup);
        soThich = new CheckBox[]{findViewById(R.id.checkBox), findViewById(R.id.checkBox2), findViewById(R.id.checkBox3)};
        image = findViewById(R.id.imageView);
    }

    public void onClick(View view){
        Intent intent = new Intent(this,DisplayActivity.class);
        intent.putExtra(HO_TEN, pName.getText().toString());//LẤY THÔNG TIN TEXT TỪ EDITTEXT => VD: String text = editText.getText().toString()
        intent.putExtra(MOBILE, mobile.getText().toString());
        intent.putExtra(EMAIL, email.getText().toString());

        int rbid = gioitinh.getCheckedRadioButtonId();
        RadioButton rb_nam = findViewById(R.id.radioButton7);
        RadioButton rb_nu  = findViewById(R.id.radioButton8);
        if(rbid == rb_nam.getId())
            intent.putExtra(GIOI_TINH, rb_nam.getText().toString());
        else
            intent.putExtra(GIOI_TINH, rb_nu.getText().toString());

        ArrayList<String> sothichList = new ArrayList<>();
        for(CheckBox cb : soThich){
            if(cb.isChecked())
                sothichList.add(cb.getText().toString());
        }

        intent.putExtra(SO_THICH, sothichList);
        startActivity(intent);
    }

    public void onClick_choosePhoto(View view){
        choosePhotoFromAlbum();
    }

    private void choosePhotoFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == this.RESULT_CANCELED)
            return;
        if(requestCode == REQUEST_CODE_GALLERY){
            if(data != null){
                Uri contentUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentUri);
                    image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
