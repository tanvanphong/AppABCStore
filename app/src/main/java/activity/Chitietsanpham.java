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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;


import java.text.DecimalFormat;
import java.util.ArrayList;

import adapter.ExampleAdapter;
import adapter.GiohangAdapter;
import model.ExampleItem;
import model.Giohang;

public class Chitietsanpham extends AppCompatActivity {



    private ExampleAdapter mExampleAdapter;
    Button button;
    Toolbar toolbarctsp;
    private ArrayList<ExampleItem> arrayList;
    private RequestQueue mRequestQueue;
    private ArrayList<ExampleItem> mExampleList;
    GiohangAdapter adapter;
    Spinner spinner;



    String id = "0";
    String Tenchitiet = "";
    int Giachitiet=0;
    String Hinhanhchitiet = "";
    String Motachitiet = "";








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        Actionbar();
        Anhxa();
        CatchEventSpinner();
        GetInformation();
        EventButton();




    }



    private void EventButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TrangChinh.arrCart.size()>0){
                    int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exits = false;
                    for(int i = 0; i < TrangChinh.arrCart.size(); i++){
                        if(TrangChinh.arrCart.get(i).getProductId() == id){
                            TrangChinh.arrCart.get(i).setProductNumber(TrangChinh.arrCart.get(i).getProductNumber() + sl);
                            if(TrangChinh.arrCart.get(i).getProductNumber() >= 10){
                                TrangChinh.arrCart.get(i).setProductNumber(10);
                            }
                            TrangChinh.arrCart.get(i).setPrice(Giachitiet * TrangChinh.arrCart.get(i).getProductNumber());
                            exits = true;
                        }
                    }
                    if(exits == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        int Giamoi=soluong*Giachitiet;
                        TrangChinh.arrCart.add(new Giohang(id,Hinhanhchitiet,Tenchitiet,Giamoi,soluong));
                    }
                }else{
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    int Giamoi=soluong*Giachitiet;
                    TrangChinh.arrCart.add(new Giohang(id,Hinhanhchitiet,Tenchitiet,Giamoi,soluong));
                }
                Intent intent = new Intent(getApplicationContext(),GioHang.class);
                startActivity(intent);
            }
        });

    }

    private void GetInformation() {
        ExampleItem product = (ExampleItem) getIntent().getSerializableExtra("productData");
        id=product.getID();
        Hinhanhchitiet =product.getImage();
        Tenchitiet = product.getProductName();
        Giachitiet = product.getPrice();
        Motachitiet = product.getProductDetail();

        ImageView imageView = findViewById(R.id.img_detail_product_photo);
        TextView textViewName = findViewById(R.id.tv_detail_product_name);
        TextView textViewGia = findViewById(R.id.tv_detail_product_price);
        TextView textViewMoTa = findViewById(R.id.tv_detail_product_description);


        Picasso.get().load(Hinhanhchitiet).fit().centerInside().into(imageView);
        textViewName.setText(Tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewGia.setText("Giá : "+decimalFormat.format(Giachitiet)+" Đ");
        textViewMoTa.setText(Motachitiet);

    }


    private void Anhxa() {

        spinner= (Spinner) findViewById(R.id.spinner);
        button = findViewById(R.id.btn_detail_product_buy);





    }
    private void CatchEventSpinner() {
        Integer[] number = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_gallery_item,number);
        spinner.setAdapter(arrayAdapter);
    }


            private void Actionbar() {
                toolbarctsp = findViewById(R.id.toolbarchitietsanpham);
                setSupportActionBar(toolbarctsp);

                ActionBar actionBar = getSupportActionBar();
                setSupportActionBar(toolbarctsp);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbarctsp.setNavigationOnClickListener(new View.OnClickListener() {
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

    public void trangchinh(MenuItem item) {
        Intent intent = new Intent(Chitietsanpham.this, TrangChinh.class);
        startActivity(intent);
    }

    public void cart(MenuItem item) {
        Intent intent = new Intent(Chitietsanpham.this, GioHang.class);
        startActivity(intent);
    }


}