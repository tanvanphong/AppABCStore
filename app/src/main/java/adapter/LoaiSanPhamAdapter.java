package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import activity.GioHang;
import activity.TrangChinh;
import model.ExampleItem;
import model.Giohang;
import model.Loaisp;

public class LoaiSanPhamAdapter extends BaseAdapter {
    private Context context;
    private List<Loaisp> listsp;
    private int layout;

    public LoaiSanPhamAdapter(TrangChinh context, List<Loaisp> listsp, int layout) {
        this.context = context;
        this.listsp = listsp;
        this.layout = layout;
    }

    public LoaiSanPhamAdapter(TrangChinh context, int row_list_category, ArrayList<Loaisp> arrayListlsp) {
    }

    public LoaiSanPhamAdapter(ArrayList<Giohang> arrayList) {
    }



    @Override
    public int getCount() {
        return listsp.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView textViewProductType;
        ImageView imageViewProductType;

    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewProductType = view.findViewById(R.id.textviewproducttype);
            viewHolder.imageViewProductType = view.findViewById(R.id.imageviewproducttype);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.textViewProductType.setText(listsp.get(position).name);
        viewHolder.imageViewProductType.setImageResource(listsp.get(position).image);
        return view;
    }

}


