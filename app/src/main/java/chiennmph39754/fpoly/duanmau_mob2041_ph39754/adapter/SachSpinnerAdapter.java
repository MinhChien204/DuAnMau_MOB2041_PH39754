package chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Sach;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    TextView masach,tensach;

    public SachSpinnerAdapter(@NonNull Context context,ArrayList<Sach> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = convertView;
       if(view == null){
           LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view =inflater.inflate(R.layout.item_sach_spinner,null);
       }
       final Sach item = list.get(position);
       if(item!=null){
           masach = view.findViewById(R.id.tvmasachSpn);
           masach.setText(item.getMasach() + ". ");
           tensach = view.findViewById(R.id.tvTensachSpn);
           tensach.setText(item.getTensach());
       }
       return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sach_spinner, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            masach = view.findViewById(R.id.tvmasachSpn);
            masach.setText(item.getMasach() + ". ");
            tensach = view.findViewById(R.id.tvTensachSpn);
            tensach.setText(item.getTensach());
        }
        return view;
    }
}
