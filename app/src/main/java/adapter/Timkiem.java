package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.List;

import model.SanPham;

public class Timkiem extends ArrayAdapter<SanPham> {
    private List<SanPham> listTimkiem;


    public Timkiem(@NonNull Context context, int resource, @NonNull List<SanPham> objects) {
        super(context, resource, objects);
    }




}

