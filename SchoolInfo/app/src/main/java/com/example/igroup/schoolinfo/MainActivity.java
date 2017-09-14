package com.example.igroup.schoolinfo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Fragment selectedFragment= null;
    List<School> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadActivity();


    }

    private void loadActivity() {
        progressDialog = ProgressDialog.show(this,"Data Loading","",true);
        progressDialog.show();

        if(CheckNetwork.isNetworkAvailable(getApplicationContext())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                try{
                    fetchJsonResponse();
                }catch (Exception e)
                {
                    System.out.println("Exception at line 89 MainActivity.java"+e.toString());
                }
                }
            }).start();
        }
        else{


        }
    }

    private void fetchJsonResponse() {

        StringRequest request = new StringRequest(Request.Method.GET, "http://192.168.0.20:4000/api/schools", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //System.out.println("GET CALL JSON:"+response.toString());

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                list = Arrays.asList(gson.fromJson(response,School[].class));
                System.out.println("GSON:"+list.get(1).getAddress().toLowerCase());
     /*Set Layout for Activity*/
                setLayouts();

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR:"+error);
            }
        });

        VolleySingleton.getmInstance(this).addToRequestque(request);
    }

    private void setLayouts() {

        //setContentView(R.layout.activity_main);
        setContentView(R.layout.main_acivity);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(navigation.getSelectedItemId() == R.id.navigation_dashboard)
        {
            selectedFragment = new Fragment_SchoolList().newInstance(list);
            setFragment(selectedFragment);

        }
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_dashboard:
                        setTitle(R.string.title_dashboard);
                        selectedFragment = new Fragment_SchoolList().newInstance(list);
                        break;
                    case R.id.navigation_add_entry:
                        setTitle(R.string.title_entry_form);
                        selectedFragment = new Fragment_SchoolInfoForm().newInstance();
                        break;
                    case R.id.navigation_description:
                        setTitle(R.string.title_Description);
                        Toast.makeText(getApplicationContext(),"Under Process",Toast.LENGTH_SHORT).show();
                        break;

                }

                setFragment(selectedFragment);

                return true;
            }


        });



    }

    private void setFragment(Fragment selectedFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,selectedFragment);
        transaction.commitAllowingStateLoss();
    }
}
