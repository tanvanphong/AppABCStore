package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;

public class DangKi extends AppCompatActivity {
Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        ActionBar1();
        toolbar = findViewById(R.id.toobardk);



    }

    private void ActionBar1() {

        toolbar = findViewById(R.id.toobardk);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    public void dangnhap(View view) {
        Intent intent =new Intent(DangKi.this,Dangnhap.class);
        startActivity(intent);
    }
    public void trangchinh(MenuItem item) {
        Intent intent = new Intent(DangKi.this, TrangChinh.class);
        startActivity(intent);
    }
    public void cart(MenuItem item) {
        Intent intent = new Intent(DangKi.this, GioHang.class);
        startActivity(intent);
    }
}