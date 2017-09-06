package com.example.igroup.schoolinfo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by iGroup on 9/4/2017.
 */

public class Fragment_SchoolInfoForm extends Fragment {
    public static Fragment_SchoolInfoForm  newInstance()
    {
        Fragment_SchoolInfoForm fragment_schoolInfoForm = new Fragment_SchoolInfoForm();
        return fragment_schoolInfoForm;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_school_data_form,container,false);
    }
}
