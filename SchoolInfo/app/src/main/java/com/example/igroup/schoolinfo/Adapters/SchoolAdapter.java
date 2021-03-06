package com.example.igroup.schoolinfo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.igroup.schoolinfo.Activities.MainActivity;
import com.example.igroup.schoolinfo.Extras.MyApplication;
import com.example.igroup.schoolinfo.Pojo.School;
import com.example.igroup.schoolinfo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by iGroup on 9/1/2017.
 */

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolderSchool> {

    private LayoutInflater layoutInflater;
    List<School> schoolList;
    MyApplication myApplication;
    public SchoolAdapter(List<School> list, MainActivity mainActivity)
    {
        schoolList = list;
        myApplication = new MyApplication(mainActivity);
    }
    @Override
    public SchoolAdapter.ViewHolderSchool onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_school_list1,parent,false);
        ViewHolderSchool viewHolderSchool = new ViewHolderSchool(v);
        return viewHolderSchool;
    }

    @Override
    public void onBindViewHolder(SchoolAdapter.ViewHolderSchool holder, int position) {

        holder.schoolname.setText(schoolList.get(position).getName());
        holder.address.setText(schoolList.get(position).getAddress());
//        holder.email.setText(schoolList.get(position).getEmail()[0]);

    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

    public class ViewHolderSchool extends RecyclerView.ViewHolder {

        @BindView(R.id.schoolName) TextView schoolname;
        @BindView(R.id.address) TextView address;
        @BindView(R.id.email) TextView email;
       // public TextView schoolname,address,email;
        public ViewHolderSchool(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            myApplication.setTypeface(schoolname);
            myApplication.setTypeface(address);
            myApplication.setTypeface(email);
         /*       schoolname =(TextView)itemView.findViewById(R.id.schoolName);
                address =(TextView)itemView.findViewById(R.id.address);
                email = (TextView)itemView.findViewById(R.id.email);
*/
        }
    }
}
