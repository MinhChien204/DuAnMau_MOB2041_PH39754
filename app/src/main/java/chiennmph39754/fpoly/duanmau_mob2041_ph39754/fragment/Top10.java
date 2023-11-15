package chiennmph39754.fpoly.duanmau_mob2041_ph39754.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import chiennmph39754.fpoly.duanmau_mob2041_ph39754.R;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.adapter.TopAdapter;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.dao.PhieuMuonDao;
import chiennmph39754.fpoly.duanmau_mob2041_ph39754.model.Top;


public class Top10 extends Fragment {

ListView lv;
ArrayList<Top> list;
TopAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top10, container, false);
        lv = view.findViewById(R.id.lv_Top10);
        PhieuMuonDao phieuMuonDao = new PhieuMuonDao(getActivity());
        list =(ArrayList<Top>) phieuMuonDao.getTop();
        adapter = new TopAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return view;
    }
}