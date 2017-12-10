package com.example.igroup.schoolinfo.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.igroup.schoolinfo.Extras.Constants;
import com.example.igroup.schoolinfo.Network.VolleySingleton;
import com.example.igroup.schoolinfo.R;

import org.json.JSONException;
import org.json.JSONObject;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by iGroup on 9/4/2017.
 */

public class Fragment_SchoolInfoForm extends Fragment {
    @BindView(R.id.input_name) EditText input_name;
    @BindView(R.id.input_address) EditText input_address;
    @BindView(R.id.input_city) EditText input_city;
    @BindView(R.id.input_zipcode) EditText input_zipcode;
    @BindView(R.id.input_state) EditText input_state;
    @BindView(R.id.input_country) EditText input_country;
    @BindView(R.id.input_boys) EditText input_boys;
    @BindView(R.id.input_girls) EditText input_girls;
    @BindView(R.id.input_maleteachers) EditText input_maleteachers;
    @BindView(R.id.input_femaleteacher) EditText input_femaleteachers;
    @BindView(R.id.input_website) EditText input_website;
    @BindView(R.id.input_start_time) EditText input_start_time;
    @BindView(R.id.input_end_time) EditText input_end_time;
    @BindView(R.id.input_email) EditText input_email;
    @BindView(R.id.input_phone) EditText input_phone;
    @BindView(R.id.spn_school_type) Spinner spn_school_type;
    @BindView(R.id.chkbx_daycare) CheckBox chkbx_daycare;
    @BindView(R.id.chkbx_kg) CheckBox chkbx_kg;
    @BindView(R.id.chkbx_primary) CheckBox chkbx_primary;
    @BindView(R.id.chkbx_secondary) CheckBox chkbx_secondary;
    @BindView(R.id.chkbx_higher) CheckBox chkbx_higher;
    //@BindView(R.id.btn_save)Button btn_save;
    private Unbinder unbinder;
    Button btn_save;

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
        View view = inflater.inflate(R.layout.activity_school_data_form,container,false);
        ButterKnife.setDebug(true);
        unbinder=ButterKnife.bind(this,view);
        btn_save =(Button)view.findViewById(R.id.btn_save);
       try {

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataToServer();
            }
        });
        }catch (Exception e)
       {
           Log.e("ERROR",e.toString());
       }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*try {

            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postDataToServer();
                }
            });
        }catch (Exception e)
        {
            Log.e("ERROR",e.toString());
        } */}

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

    }

    private void postDataToServer() {
      String school_name=input_name.getText().toString();
              String school_address=input_address.getText().toString();
              String school_city=input_city.getText().toString();
              String school_zipcode=input_zipcode.getText().toString();
      if(!school_name.isEmpty() && !school_address.isEmpty() && !school_city.isEmpty() && !school_zipcode.isEmpty()) {


          JSONObject schoolObject = new JSONObject();
          try {
              schoolObject.put(Constants.SCHOOL_NAME, school_name);
              schoolObject.put(Constants.SCHOOL_ADDRESS, school_address);
              schoolObject.put(Constants.CITY, school_city);
              schoolObject.put(Constants.ZIPCODE, school_zipcode);
              schoolObject.put(Constants.STATE,input_state.getText().toString() );
              schoolObject.put(Constants.COUNTRY, input_country.getText().toString());
              schoolObject.put(Constants.TOTAL_BOYS, input_boys.getText());
              schoolObject.put(Constants.TOTAL_GIRLS, input_girls.getText());
              schoolObject.put(Constants.TOTAL_MALE_TEACHER, input_maleteachers.getText());
              schoolObject.put(Constants.TOTAL_FEMALE_TEACHER, input_femaleteachers.getText());
              schoolObject.put(Constants.SCHOOL_TYPE, spn_school_type.getSelectedItem().toString());
              schoolObject.put(Constants.DAYCARE, chkbx_daycare.isChecked());
              schoolObject.put(Constants.KG, chkbx_kg.isChecked());
              schoolObject.put(Constants.SECONDARY, chkbx_secondary.isChecked());
              schoolObject.put(Constants.PRIMARY, chkbx_primary.isChecked());
              schoolObject.put(Constants.HIGHER, chkbx_higher.isChecked());
              schoolObject.put(Constants.WEBSITE, input_website.getText().toString());
              schoolObject.put(Constants.START_TIME, input_start_time.getText().toString());
              schoolObject.put(Constants.END_TIME, input_end_time.getText().toString());
              schoolObject.put(Constants.PHONE, input_phone.getText());
              schoolObject.put(Constants.EMAIL, input_email.getText().toString());

          } catch (JSONException e) {
              e.printStackTrace();
          }

          JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.API, schoolObject, new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {
                  System.out.println("POST REQUEST RESPONSE:-->" + response.toString());
               try{

                              Snackbar.make(getView(),"Information Added Successfully",Snackbar.LENGTH_LONG).show();
                          }


               catch (Exception e) {
                  e.printStackTrace();
              }



              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

                  System.out.println("Network ERROR:-->"+error);
                  Snackbar.make(getView(),error.toString(),Snackbar.LENGTH_LONG).show();

              }
          });
          VolleySingleton.getmInstance(getActivity()).addToRequestque(jsonObjectRequest);
      } else
      {
          Snackbar.make(getView(),"Please fill required information",Snackbar.LENGTH_LONG).show();
      }
    }

}
