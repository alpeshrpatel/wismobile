package com.example.igroup.schoolinfo.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.igroup.schoolinfo.Extras.CheckNetwork;
import com.example.igroup.schoolinfo.Extras.Constants;
import com.example.igroup.schoolinfo.Fragments.Fragment_SchoolInfoForm;
import com.example.igroup.schoolinfo.Fragments.Fragment_SchoolList;
import com.example.igroup.schoolinfo.Fragments.Fragment_School_Search;
import com.example.igroup.schoolinfo.R;
import com.example.igroup.schoolinfo.Pojo.School;
import com.example.igroup.schoolinfo.Network.VolleyErrorHandler;
import com.example.igroup.schoolinfo.Network.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    public boolean serverError = false;
    ProgressDialog progressDialog;
    Fragment selectedFragment= null;
    List<School> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


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

        StringRequest request = new StringRequest(Request.Method.GET, Constants.API, new Response.Listener<String>() {
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

                handleVolleyError(error);
                progressDialog.dismiss();

            }
        });

        VolleySingleton.getmInstance(this).addToRequestque(request);
    }


    private void handleVolleyError(VolleyError error) {
        VolleyErrorHandler volleyErrorHandler = new VolleyErrorHandler(MainActivity.this);
        String errorMessage = volleyErrorHandler.handleVolleyError(error);

        setContentView(R.layout.main_acivity);
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        RelativeLayout error_layout = (RelativeLayout)findViewById(R.id.layout_error);
        error_layout.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.GONE);
        TextView txt_error = (TextView)findViewById(R.id.txt_error);
        Button btn_retry = (Button)findViewById(R.id.btn_retry);
        txt_error.setText(errorMessage);
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadActivity();
            }
        });
        //Snackbar.make(getCurrentFocus(),errorMessage,Snackbar.LENGTH_LONG).show();
    }

    private void setLayouts() {

        //setContentView(R.layout.activity_main);

        setContentView(R.layout.main_acivity);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(navigation.getSelectedItemId() == R.id.navigation_search)
        {
            selectedFragment = new Fragment_School_Search().newInstance();
            setFragment(selectedFragment);

        }
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_dashboard:
                       // setTitle(R.string.title_dashboard);
                        selectedFragment = new Fragment_SchoolList().newInstance(list,MainActivity.this);
                        break;
                    case R.id.navigation_add_entry:
                        //setTitle(R.string.title_entry_form);
                        selectedFragment = new Fragment_SchoolInfoForm().newInstance();
                        break;
                    case R.id.navigation_description:
                        //setTitle(R.string.title_Description);
                        Toast.makeText(getApplicationContext(),"Under Process",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.navigation_search:
                        selectedFragment = new Fragment_School_Search().newInstance();
                        //Toast.makeText(getApplicationContext(),"Under Process",Toast.LENGTH_LONG).show();
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
