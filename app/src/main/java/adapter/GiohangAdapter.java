package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import activity.Chitietsanpham;
import activity.GioHang;
import activity.TrangChinh;
import model.ExampleItem;
import model.Giohang;


public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arrCart;



    public GiohangAdapter(Context context, ArrayList<Giohang> arrCart) {
        this.context = context;
        this.arrCart = arrCart;
    }

    public GiohangAdapter(GioHang gioHang) {
    }

    @Override
    public int getCount() {
        return arrCart.size();
    }

    @Override
    public Object getItem(int position) {
        return arrCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder {
        public TextView txtcartname, txtcartcost, txtprice;
        public ImageView imgcart;
        public Button btnminus, btnvalues, btnplus;
        public ImageButton remove;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_cart, null);
            viewHolder.txtcartname = (TextView) convertView.findViewById(R.id.tv_name_product_cart);
            viewHolder.txtprice = (TextView) convertView.findViewById(R.id.tv_price_product_cart);
            viewHolder.txtcartcost = (TextView) convertView.findViewById(R.id.tv_cart_total_price);
            viewHolder.imgcart = (ImageView) convertView.findViewById(R.id.img_photo_cart);
            viewHolder.btnminus = (Button) convertView.findViewById(R.id.buttonminus);
            viewHolder.btnvalues = (Button) convertView.findViewById(R.id.buttonvalue);
            viewHolder.btnplus = (Button) convertView.findViewById(R.id.buttonplus);
            viewHolder.remove=(ImageButton)convertView.findViewById(R.id.remove) ;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Giohang cart = (Giohang) getItem(position);
        viewHolder.txtcartname.setText(cart.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtprice.setText(decimalFormat.format(cart.getPrice()) + " Đ");

        Picasso.get().load(cart.getProductImage())
                .error(R.drawable.error)
                .into(viewHolder.imgcart);
        viewHolder.btnvalues.setText(cart.productNumber + "");
        int sl = Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if (sl >= 9) {
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        } else if (sl <= 1) {
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        } else if (sl > 1) {
            viewHolder.btnminus.setVisibility(View.VISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newsl = Integer.parseInt(finalViewHolder.btnvalues.getText().toString()) + 1;
                int cursl = (int) TrangChinh.arrCart.get(position).getProductNumber();
                int giaht = (int) TrangChinh.arrCart.get(position).getPrice();
                TrangChinh.arrCart.get(position).setProductNumber(newsl);
                int newcash = (giaht * newsl) / cursl;
                TrangChinh.arrCart.get(position).setPrice(newcash);
                DecimalFormat decimalFormat = new DecimalFormat();
                finalViewHolder.txtprice.setText(decimalFormat.format(newcash) + " Đ");
                GioHang.EvenUtils();
                if (newsl > 9) {
                    finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(newsl));
                } else {
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(newsl));
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newsl = Integer.parseInt(finalViewHolder.btnvalues.getText().toString()) - 1;
                int cursl = (int) TrangChinh.arrCart.get(position).getProductNumber();
                int giaht = (int) TrangChinh.arrCart.get(position).getPrice();
                TrangChinh.arrCart.get(position).setProductNumber(newsl);
                int newcash = (giaht * newsl) / cursl;
                TrangChinh.arrCart.get(position).setPrice(newcash);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtprice.setText(decimalFormat.format(newcash) + " Đ");
                GioHang.EvenUtils();
                if (newsl < 2) {
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(newsl));
                } else {
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(newsl));
                }
            }
        });
        finalViewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Xóa sản phẩm ?");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này.");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(TrangChinh.arrCart.size()<=0)
                        {
                          GioHang.CheckData();
                        }
                        else {
                            arrCart.remove(position);
                            GioHang.EvenUtils();
                            if(TrangChinh.arrCart.size()<=0){
                                GioHang.CheckData();
                            }
                            else{
                               GioHang.CheckData();
                                GioHang.EvenUtils();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        GioHang.EvenUtils();
                    }
                });
                builder.show();

            }
        });

        return convertView;
    }



}
