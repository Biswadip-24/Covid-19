package com.app.learning.covid_19;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class Frag_India extends Fragment {

    ArrayList<Example_Item> ExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final String URL="https://api.covid19india.org/data.json";
    private TextView mWest_con;private TextView mWest_rec;private TextView mWest_dec;
    private TextView mWest_dcon;private TextView mWest_drec;private TextView mWest_ddec;
    private TextView mInd_con;private TextView mInd_rec;private TextView mInd_dec;
    private TextView mInd_dcon;private TextView mInd_drec;private TextView mInd_ddec;


    View RootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ExampleList =new ArrayList<>();
        RootView = inflater.inflate(R.layout.fragindia_layout, container, false);

        /*ExampleList.add(new Example_Item("Maharasthra","192990","104687","8376","+6367","+3515","+198"));
        ExampleList.add(new Example_Item("West Bengal","192990","104687","8376","+6367","+3515","+198"));
        ExampleList.add(new Example_Item("Odisha","192990","104687","8376","+6367","+3515","+198"));
        ExampleList.add(new Example_Item("Maharasthra","192990","104687","8376","+6367","+3515","+198"));
        ExampleList.add(new Example_Item("West Bengal","192990","104687","8376","+6367","+3515","+198"));
        ExampleList.add(new Example_Item("Odisha","192990","104687","8376","+6367","+3515","+198"));

        mRecyclerView = RootView.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ExampleAdapter(ExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);*/

        mWest_con=RootView.findViewById(R.id.con1);
        mWest_rec=RootView.findViewById(R.id.rec1);
        mWest_dec=RootView.findViewById(R.id.dec1);
        mWest_dcon=RootView.findViewById(R.id.dcon1);
        mWest_drec=RootView.findViewById(R.id.drec1);
        mWest_ddec=RootView.findViewById(R.id.ddec1);

        mInd_con=RootView.findViewById(R.id.con);
        mInd_rec=RootView.findViewById(R.id.rec);
        mInd_dec=RootView.findViewById(R.id.dec);
        mInd_dcon=RootView.findViewById(R.id.dcon);
        mInd_drec=RootView.findViewById(R.id.drec);
        mInd_ddec=RootView.findViewById(R.id.ddec);


        try {
            letsDoSomeNetworking();
        }
        catch(Exception e)
        {
            Toast.makeText(RootView.getContext(),"Request Failed",Toast.LENGTH_SHORT).show();
        }
        return RootView;
    }
    private void letsDoSomeNetworking() {


        try {
            HttpsTrustManager.allowAllSSL();
            RequestQueue requestQueue = Volley.newRequestQueue(RootView.getContext());

            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    URL,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Covid", "SUCCESS! JSON: " + response.toString());
                            CovidData_Model covidData = CovidData_Model.fromJSON(response);
                            if(covidData==null) {
                                //Toast.makeText(Train_list_activity.this,"No Currently Available Trains",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                int len = covidData.arr.length;
                                for (int i = 0; i < len; i++) {
                                    ExampleList.add(covidData.arr[i]);
                                }
                                mRecyclerView = RootView.findViewById(R.id.RecyclerView);
                                mRecyclerView.setHasFixedSize(true);
                                mLayoutManager = new LinearLayoutManager(getActivity());
                                mAdapter = new ExampleAdapter(ExampleList);
                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mRecyclerView.setAdapter(mAdapter);


                                mWest_con.setText(covidData.Wc);
                                mWest_rec.setText(covidData.Wr);
                                mWest_dec.setText(covidData.Wd);
                                mWest_dcon.setText(covidData.Wdc);
                                mWest_drec.setText(covidData.Wdr);
                                mWest_ddec.setText(covidData.Wdr);

                                mInd_con.setText(covidData.Ic);
                                mInd_rec.setText(covidData.Ir);
                                mInd_dec.setText(covidData.Id);
                                mInd_dcon.setText(covidData.Idc);
                                mInd_drec.setText(covidData.Idr);
                                mInd_ddec.setText(covidData.Idd);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Train_Finder", "Fail " + error.toString());
                            Toast.makeText(RootView.getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            requestQueue.add(objectRequest);
        }
        catch(Exception e)
        {
            Toast.makeText(RootView.getContext(),"Request Failed",Toast.LENGTH_SHORT).show();
        }

    }
}
