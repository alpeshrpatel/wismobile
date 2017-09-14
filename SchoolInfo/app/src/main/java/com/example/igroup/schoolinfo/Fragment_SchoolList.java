package com.example.igroup.schoolinfo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

/**
 * Created by iGroup on 9/5/2017.
 */

public class Fragment_SchoolList extends Fragment {

    private RecyclerView recyclerView;
    private  Context context;
    private static List<School> schoolList;



    public static Fragment_SchoolList newInstance(List<School> list)
    {
        schoolList = list;
        Fragment_SchoolList fragment_schoolList = new Fragment_SchoolList();
        return fragment_schoolList;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schoollist,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new SchoolAdapter(schoolList));
        return view;
    }
}
