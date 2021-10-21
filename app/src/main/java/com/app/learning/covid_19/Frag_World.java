package com.app.learning.covid_19;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Frag_World extends Fragment {
    private View RootView;
    ArrayList<Example_Item> ExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mWrld_con;
    private TextView mWrld_rec;
    private TextView mWrld_dec;
    private TextView mWrld_dcon;
    private TextView mWrld_drec;
    private TextView mWrld_ddec;
    Switch mSwitch;
    private String URL = "https://disease.sh/v3/covid-19/countries?yesterday=false&sort=cases";
    String Covid_URL_all="https://disease.sh/v3/covid-19/all?yesterday=false";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ExampleList =new ArrayList<>();
        RootView = inflater.inflate(R.layout.fragworld_layout, container, false);
        try
        {
            letsDoSomeNetworking();
            letsDoSomeMoreNetworking();
        }
        catch(Exception e)
        {
            Toast.makeText(RootView.getContext(),"Request Failed",Toast.LENGTH_SHORT).show();

        }

        mWrld_con = RootView.findViewById(R.id.con2);
        mWrld_rec = RootView.findViewById(R.id.rec2);
        mWrld_dec = RootView.findViewById(R.id.dec2);
        mWrld_dcon = RootView.findViewById(R.id.dcon2);
        mWrld_drec = RootView.findViewById(R.id.drec2);
        mWrld_ddec = RootView.findViewById(R.id.ddec2);


        mSwitch = RootView.findViewById(R.id.switch1);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                     URL = "https://disease.sh/v3/covid-19/countries?yesterday=true&sort=cases";
                     Covid_URL_all="https://disease.sh/v3/covid-19/all?yesterday=true";

                } else {
                    URL = "https://disease.sh/v3/covid-19/countries?yesterday=false&sort=cases";
                    Covid_URL_all="https://disease.sh/v3/covid-19/all?yesterday=false";
                }
                try

                {
                    Refresh();
                    letsDoSomeMoreNetworking();
                    final LoadingDialog ld=new LoadingDialog(getActivity());
                    ld.startLoadingDialog();
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ld.dismissDialog();
                        }
                    },3000);

                }
                catch(Exception e)
                {
                    Toast.makeText(RootView.getContext(),"Request Failed",Toast.LENGTH_SHORT).show();

                }

            }
        });


        return RootView;
    }

    private void letsDoSomeNetworking() throws JSONException {

        HttpsTrustManager.allowAllSSL();
        RequestQueue requestQueue = Volley.newRequestQueue(RootView.getContext());

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Covid", "SUCCESS! JSON: " + response.toString());
                        CovidData_Countries covidData = CovidData_Countries.fromJSON(response);
                        if(covidData==null) {
                        }
                        else {
                            int len = covidData.arr.length;
                            for (int i = 0; i < len ; i++) {
                                ExampleList.add(covidData.arr[i]);
                            }
                            mRecyclerView = RootView.findViewById(R.id.RecyclerView1);
                            mRecyclerView.setHasFixedSize(true);
                            mLayoutManager = new LinearLayoutManager(getActivity());

                            mAdapter = new ExampleAdapter(ExampleList);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.setFocusable(false);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Covid", "Fail " + error.toString());
                        Toast.makeText(RootView.getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(arrayRequest);
    }

    public void letsDoSomeMoreNetworking()throws Exception
    {
        HttpsTrustManager.allowAllSSL();
        RequestQueue requestQueue= Volley.newRequestQueue(RootView.getContext());

        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                Covid_URL_all,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Covid", "SUCCESS! JSON: " + response.toString());
                        CovidData_World covidData = CovidData_World.fromJSON(response);
                        if(covidData==null) {
                            Toast.makeText(RootView.getContext(),"No data",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mWrld_con.setText(covidData.getmConfirmed());
                            mWrld_rec.setText(covidData.getmRecovered());
                            mWrld_dec.setText(covidData.getmDeceased());
                            mWrld_dcon.setText(covidData.getMdConfirmed());
                            mWrld_drec.setText(covidData.getMdRecovered());
                            mWrld_ddec.setText(covidData.getMdDeceased());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Covid","Fail "+error.toString());
                        Toast.makeText(RootView.getContext(),"Request Failed",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(objectRequest);


    }
    public void Refresh() throws Exception
    {
        ExampleList.clear();
        HttpsTrustManager.allowAllSSL();
        RequestQueue requestQueue = Volley.newRequestQueue(RootView.getContext());

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Covid", "SUCCESS! JSON: " + response.toString());
                        CovidData_Countries covidData = CovidData_Countries.fromJSON(response);
                        if(covidData!=null) {
                            int len = covidData.arr.length;
                            for (int i = 0; i < len ; i++) {
                                ExampleList.add(covidData.arr[i]);
                            }
                            mAdapter.notifyDataSetChanged();
                            Log.d("Covid","Finished Loading");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Covid", "Fail " + error.toString());
                        Toast.makeText(RootView.getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(arrayRequest);

    }
}