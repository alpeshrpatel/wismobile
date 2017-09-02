package com.example.igroup.schoolinfo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by iGroup on 9/1/2017.
 */

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolderSchool> {

    private LayoutInflater layoutInflater;

    @Override
    public SchoolAdapter.ViewHolderSchool onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_school_list,parent,false);
        ViewHolderSchool viewHolderSchool = new ViewHolderSchool(v);
        return viewHolderSchool;
    }

    @Override
    public void onBindViewHolder(SchoolAdapter.ViewHolderSchool holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolderSchool extends RecyclerView.ViewHolder {
        public ViewHolderSchool(View itemView) {

            super(itemView);
        }
    }
}
