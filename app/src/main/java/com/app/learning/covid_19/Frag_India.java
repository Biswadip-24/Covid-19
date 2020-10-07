package com.app.learning.covid_19;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Frag_India extends Fragment {

    ArrayList<Example_Item> ExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final String URL = "https://api.covid19india.org/data.json";
    private TextView mWest_con;
    private TextView mWest_rec;
    private TextView mWest_dec;
    private TextView mWest_dcon;
    private TextView mWest_drec;
    private TextView mWest_ddec;
    private TextView mInd_con;
    private TextView mInd_rec;
    private TextView mInd_dec;
    private TextView mInd_dcon;
    private TextView mInd_drec;
    private TextView mInd_ddec;
    private TextView mState;
    private CovidData_Model covidData;
    public static String country;
    public static String state="Your State";
    FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String TAG = "Location";
    int LOCATION_REQUEST_CODE = 10001;


    View RootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ExampleList = new ArrayList<>();
        RootView = inflater.inflate(R.layout.fragindia_layout, container, false);

        mWest_con = RootView.findViewById(R.id.con1);
        mWest_rec = RootView.findViewById(R.id.rec1);
        mWest_dec = RootView.findViewById(R.id.dec1);
        mWest_dcon = RootView.findViewById(R.id.dcon1);
        mWest_drec = RootView.findViewById(R.id.drec1);
        mWest_ddec = RootView.findViewById(R.id.ddec1);

        mInd_con = RootView.findViewById(R.id.con);
        mInd_rec = RootView.findViewById(R.id.rec);
        mInd_dec = RootView.findViewById(R.id.dec);
        mInd_dcon = RootView.findViewById(R.id.dcon);
        mInd_drec = RootView.findViewById(R.id.drec);
        mInd_ddec = RootView.findViewById(R.id.ddec);
        mState = RootView.findViewById(R.id.textView5);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(RootView.getContext());

        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(RootView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastLocation();
        } else {
            Log.d(TAG,"Need to ask permission");
            askLocationPermission();
        }
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
                            covidData = CovidData_Model.fromJSON(response);
                            if (covidData != null) {

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
                                mRecyclerView.setFocusable(false);


                                mWest_con.setText(covidData.Wc);
                                mWest_rec.setText(covidData.Wr);
                                mWest_dec.setText(covidData.Wd);
                                mWest_dcon.setText(covidData.Wdc);
                                mWest_drec.setText(covidData.Wdr);
                                mWest_ddec.setText(covidData.Wdd);

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
                            Log.e("Covid", "Fail " + error.toString());
                            Toast.makeText(RootView.getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            requestQueue.add(objectRequest);
        } catch (Exception e) {
            Toast.makeText(RootView.getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
        }

    }

    private void getLocation() {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(RootView.getContext());
        if (ActivityCompat.checkSelfPermission(RootView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(RootView.getContext(), Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            Log.d("Location", "Hello");
                            String s = addresses.toString();
                            int u = s.indexOf("admin");
                            state = s.substring(s.indexOf('=', u) + 1, s.indexOf(',', u));
                            Log.d("Location", addresses.toString());
                            Log.d("Location", state);
                            mState.setText(state);
                            letsDoSomeNetworking();

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(RootView.getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions((Activity) RootView.getContext(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
        }
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(RootView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RootView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> locationTask = mFusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    try {
                        //We have a location
                        Geocoder geocoder = new Geocoder(RootView.getContext(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Log.d("Location", "Hello");
                        String s = addresses.toString();

                        int u = s.indexOf("admin");
                        state = s.substring(s.indexOf('=', u) + 1, s.indexOf(',', u));

                        int o = s.indexOf("countryName");
                        country = s.substring(s.indexOf('=', o) + 1, s.indexOf(',', o));
                        if(country.equalsIgnoreCase("India") !=true)
                            Toast.makeText(RootView.getContext(), "App might not work correctly as your location is outside India", Toast.LENGTH_LONG).show();

                        Log.d("Location", addresses.toString());
                        Log.d("Location", state);
                        mState.setText(state);
                        letsDoSomeNetworking();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(RootView.getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Log.d(TAG, "onSuccess: Location was null...");
                    Toast.makeText(RootView.getContext(), "Your Location maybe turned off", Toast.LENGTH_LONG).show();
                }
            }
        });

        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }
        });
    }

    private void askLocationPermission() {
        if (ActivityCompat.checkSelfPermission(RootView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            Log.d(TAG,"We have reached here askLocationPermission()");
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG,"We have reached here");
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                Log.d(TAG,"Permission granted");
                getLastLocation();
            } else {

                Toast.makeText(RootView.getContext(), "Location Permission denied, You can't view your state details", Toast.LENGTH_LONG).show();
                letsDoSomeNetworking();
                Log.d(TAG, "Permission not granted");
            }
        }
    }
}
