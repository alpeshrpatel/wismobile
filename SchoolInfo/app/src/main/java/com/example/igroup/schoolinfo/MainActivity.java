package com.example.igroup.schoolinfo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Fragment selectedFragment= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
       // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(navigation.getSelectedItemId() == R.id.navigation_home)
        {
          selectedFragment = new Fragment_SchoolList().newInstance();
          setFragment(selectedFragment);

        }
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        setTitle(R.string.title_home);
                        selectedFragment = new Fragment_SchoolList().newInstance();
                        break;
                    case R.id.navigation_dashboard:
                        setTitle(R.string.title_dashboard);
                        selectedFragment = new Fragment_SchoolInfoForm().newInstance();
                        break;
                    case R.id.navigation_notifications:
                        setTitle(R.string.title_notifications);
                        Toast.makeText(getApplicationContext(),"Under Process",Toast.LENGTH_SHORT).show();
                        break;

                }

                setFragment(selectedFragment);

                return true;
            }


        });

        fetchJsonResponse();

    }

    private void fetchJsonResponse() {

        StringRequest request = new StringRequest(Request.Method.GET, "http://192.168.0.10:4000/api/schools", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //System.out.println("GET CALL JSON:"+response.toString());

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                List<School> list = Arrays.asList(gson.fromJson(response,School[].class));
                System.out.println("GSON:"+list.get(1));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR:"+error);
            }
        });

        VolleySingleton.getmInstance(this).addToRequestque(request);
    }

    private void setFragment(Fragment selectedFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,selectedFragment);
        transaction.commit();
    }
}
