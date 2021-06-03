package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;

import Connect.CheckInternetConnection;
import adapter.ExampleAdapter;
import adapter.LoaiSanPhamAdapter;
import model.ExampleItem;
import model.Giohang;
import model.Loaisp;
import model.SanPham;

public class TrangChinh extends AppCompatActivity {

    private String mJSONURLString = "http://192.168.1.8:8090/san-pham-id";
    RecyclerView recyclerView;
    Toolbar toolbar;
    GridLayoutManager gridLayoutManager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    ArrayList<Loaisp> arrayListlsp;
    ArrayList<SanPham> sanPham;
    LoaiSanPhamAdapter loaiSanPhamAdapter;
    NestedScrollView nestedScrollView;
    public static ArrayList<Giohang> arrCart;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;
    ImageSlider slider;
    SwipeRefreshLayout swip;


   



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chinh);
        Anhxa();
        ActionBar();
        ActionMenu();
        CatchOnItemListView();
        Swip();




    }

    private void Swip() {
        swip=findViewById(R.id.swip);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mExampleList.clear();
                swip.setRefreshing(false);
                parseJSON();
            }
        });
    }


    private void CatchOnItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0: {
                        Intent intent = new Intent(TrangChinh.this, TrangChinh.class);
                        startActivity(intent);
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                    case 1:
                    {
                        Intent intent = new Intent(TrangChinh.this, MainActivity.class);
                        startActivity(intent);
                    }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 3: {
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                }




            }


        });
    }

    private void ActionMenu() {
        arrayListlsp = new ArrayList<>();
        arrayListlsp.add(new Loaisp(R.drawable.home, "Trang Chủ"));
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(this,arrayListlsp,R.layout.row_list_category);
        listView.setAdapter(loaiSanPhamAdapter);
    }


    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    private void Anhxa() {
        listView = findViewById(R.id.listviewmanhinhchinh);
        navigationView = findViewById(R.id.navigationviewmanhinhchinh);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();



        recyclerView = findViewById(R.id.rcv_sp);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        mExampleList= new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

        slider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.slider1));
        slideModels.add(new SlideModel(R.drawable.slider2));
        slideModels.add(new SlideModel(R.drawable.slider3));
        slideModels.add(new SlideModel(R.drawable.slider_4));


        slider.setImageList(slideModels, true);







        nestedScrollView=findViewById(R.id.nesrow);
        recyclerView.setNestedScrollingEnabled(false);

        if(arrCart!=null){

        }else {
            arrCart= new ArrayList<>();
        }








    }

    private void parseJSON() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try{
                            for(int i=0;i<response.length();i++){
                                JSONObject sp = response.getJSONObject(i);
                                String  ID = sp.getString("id");
                                String  ProductName = sp.getString("tenSanPham");
                                int Price = sp.getInt("donGia");
                                String Image = sp.getString("hinhAnh");
                                String ProductDetail=sp.getString("moTaChiTiet");

                                        mExampleList.add(new ExampleItem(ID,ProductName,Price, Image,ProductDetail));




                            }
                            mExampleAdapter = new ExampleAdapter(TrangChinh.this, mExampleList);
                            recyclerView.setAdapter(mExampleAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(),Chitietsanpham.class);
                                    intent.putExtra("productData",mExampleList.get(position));
                                    startActivity(intent);
                                }
                            });

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        CheckInternetConnection.ShowToast_Short(getApplicationContext(),error.toString());

                    }
                }
        );
        mRequestQueue.add(jsonArrayRequest);




}





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setBackgroundResource(R.drawable.shapebgsearch);
        searchView.setIconified(true);
        searchView.clearFocus();
        return true;
    }


    public void chitietsanpham(View view) {
        Intent intent = new Intent(TrangChinh.this, Chitietsanpham.class);
        startActivity(intent);

    }

    public void Click1(View view) {
        Intent intent = new Intent(TrangChinh.this, GioHang.class);
        startActivity(intent);
    }


    public void cart(MenuItem item) {
        Intent intent = new Intent(TrangChinh.this, GioHang.class);
        startActivity(intent);
    }
}