package chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment.Top10;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Top;

public class TopAdapter extends ArrayAdapter<Top> {
    Context context;
    Top10 fragment;
    ArrayList<Top> list;
    TextView tvSach, tvSl;
    ImageView delete;

    public TopAdapter(@NonNull Context context, Top10 fragment, ArrayList<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v== null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_top,null);
        }
        final Top item = list.get(position);
        if(item!= null){
            tvSach =v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: "+item.getTensach());

            tvSl = v.findViewById(R.id.tvSL);
            tvSl.setText("Số lượng: "+item.getSoluong());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        return v;
    }
}
