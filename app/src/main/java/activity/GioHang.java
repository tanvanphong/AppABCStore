package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import adapter.ExampleAdapter;
import adapter.GiohangAdapter;
import model.Giohang;

public class GioHang extends AppCompatActivity  {
    Toolbar toolbargh;
   static ListView lvgh;
    static GiohangAdapter gioHangAdapter;
    RelativeLayout relativeLayout;
    Button btn_tt;
    ArrayList<Giohang> arrayList;
    static  ImageView txtNotify;
    static TextView txtTotalCash;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        Actionbar();
        CheckData();
        EvenUtils();



    }




    private void Actionbar() {
        setSupportActionBar(toolbargh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
    }

    private void Anhxa() {
        toolbargh = findViewById(R.id.toolbarcart);
        setSupportActionBar(toolbargh);
        ActionBar actionBar = getSupportActionBar();

        lvgh = findViewById(R.id.lv_cart_product);
        txtNotify=findViewById(R.id.textviewnotify);
        txtTotalCash = findViewById(R.id.tv_cart_total_price);
        gioHangAdapter= new GiohangAdapter(GioHang.this,TrangChinh.arrCart);
        lvgh.setAdapter(gioHangAdapter);




        btn_tt=findViewById(R.id.btn_cart_order);
        btn_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GioHang.this,Dangnhap.class);
                startActivity(intent);

            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_home, menu);
        return true;
    }




    public void trangchinh(View view) {
        Intent intent =new Intent(GioHang.this,TrangChinh.class);
        startActivity(intent);
    }


    public void dangnhap(View view) {
        Intent intent =new Intent(GioHang.this,DatHang.class);
        startActivity(intent);
    }
    public static void EvenUtils(){
        int totalcash = 0;
        for(int i=0;i<TrangChinh.arrCart.size();i++)
        {
            totalcash += TrangChinh.arrCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotalCash.setText(decimalFormat.format(totalcash)+" Đ");

        }
    public static void CheckData() {
        if(TrangChinh.arrCart.size()<=0)
        {
            gioHangAdapter.notifyDataSetChanged();
            txtNotify.setVisibility(View.VISIBLE);
            lvgh.setVisibility(View.INVISIBLE);
        }else {
            gioHangAdapter.notifyDataSetChanged();
            txtNotify.setVisibility(View.INVISIBLE);
            lvgh.setVisibility(View.VISIBLE);
        }
    }





    public void back(MenuItem item) {
        Intent intent =new Intent(GioHang.this,TrangChinh.class);
        startActivity(intent);
    }


}
