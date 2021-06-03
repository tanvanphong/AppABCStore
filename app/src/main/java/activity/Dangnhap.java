package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;



import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;



import java.util.ArrayList;



import model.TaiKhoan;

public class Dangnhap extends AppCompatActivity {
    Toolbar toolbar;
    EditText name, password;
    CheckBox show_hide_password;
    private ArrayList<TaiKhoan> mTk;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        Anhxa();
        ActionBar1();



    }

    private void ActionBar1() {
        toolbar = findViewById(R.id.toobardn);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private void Anhxa() {
        name = findViewById(R.id.name);
        login = findViewById(R.id.login);
        password = findViewById(R.id.pass);
        password = (EditText) findViewById(R.id.pass);
        show_hide_password = (CheckBox) findViewById(R.id.showPassword);
        show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,  boolean b) {
                if (!b){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else{
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

            }
        });


    }


            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.home_menu, menu);
                return true;
            }


            public void dangki(View view) {
                Intent intent = new Intent(Dangnhap.this, DangKi.class);
                startActivity(intent);
            }



            public void trangchinh(MenuItem item) {
                Intent intent = new Intent(Dangnhap.this, TrangChinh.class);
                startActivity(intent);
            }

            public void cart(MenuItem item) {
                Intent intent = new Intent(Dangnhap.this, GioHang.class);
                startActivity(intent);
            }
        }
