package com.app.learning.covid_19;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.Volley;
        import com.google.firebase.database.Query;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    String Covid_URL_all="https://corona.lmao.ninja/v2/all?";
    String Covid_URL_country="https://corona.lmao.ninja/v2/countries/India";
    String country_ID="India";
    boolean yesterday=false;



    String sort="cases";
    TextView mtot_world;
    TextView mdeath_world;
    TextView mnewCase_world;
    TextView mRecovered_world;
    TextView mnewDeath_world;

    TextView mtot_world2;
    TextView mdeath_world2;
    TextView mnewCase_world2;
    TextView mnewCase_world3;
    TextView mRecovered_world2;
    TextView mnewDeath_world2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtot_world=(TextView) findViewById(R.id.world_total);
        mdeath_world=(TextView) findViewById(R.id.world_deaths);
        mnewCase_world=(TextView) findViewById(R.id.world_new_cases);
        mnewDeath_world=(TextView) findViewById(R.id.world_new_deaths);
        mRecovered_world=(TextView) findViewById(R.id.world_recovered);

        mtot_world2=(TextView) findViewById(R.id.world_total2);
        mdeath_world2=(TextView) findViewById(R.id.world_deaths2);
        mnewCase_world2=(TextView) findViewById(R.id.world_new_cases2);
        mnewDeath_world2=(TextView) findViewById(R.id.world_new_deaths2);
        mRecovered_world2=(TextView) findViewById(R.id.world_recovered2);
        mnewCase_world3=(TextView) findViewById(R.id.world_new_cases3);

        try

        {
            //letsDOSomeMoreNetworking();
            //letsDoSomeNetworking();

        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this,"Request Failed",Toast.LENGTH_SHORT).show();

        }

    }

    private void letsDoSomeNetworking() throws JSONException
    {



        /*JSONArray jsonArray=new JSONArray();
        jsonArray.put(yesterday);
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                Covid_URL,
                jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Covid","SUCCESS! JSON: "+response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Covid","Fail "+error.toString());
                        Toast.makeText(MainActivity.this,"Request Failed",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(arrayRequest);*/

        /*
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("yesterday",yesterday);

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                Covid_URL_all,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Covid", "SUCCESS! JSON: " + response.toString());
                        CovidData_Model covidDataModel=CovidData_Model.fromJSON(response);
                        mtot_world.setText(("TOTAL: "+putComma(Long.toString(covidDataModel.getTotal()))));
                        mdeath_world.setText("DEATHS: "+putComma(Long.toString(covidDataModel.getDeaths())));
                        mnewCase_world.setText("NEW CASES: "+putComma(Long.toString(covidDataModel.getNewCases())));
                        mnewDeath_world.setText("NEW DEATHS: "+putComma(Long.toString(covidDataModel.getNewDeaths())));
                        mRecovered_world.setText("RECOVERED: "+putComma(Long.toString(covidDataModel.getRecovered())));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Covid","Fail "+error.toString());
                        Toast.makeText(MainActivity.this,"Request Failed",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(objectRequest);

    }


    private void letsDOSomeMoreNetworking()throws JSONException
    {

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("yesterday",yesterday);
        jsonObject.put("sort",sort);

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                Covid_URL_country,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Covid", "SUCCESS! JSON: " + response.toString());
                        CovidData_Model covidDataModel=CovidData_Model.fromJSON(response);
                        mtot_world2.setText(("TOTAL: "+putComma(Long.toString(covidDataModel.getTotal()))));
                        mdeath_world2.setText("DEATHS: "+putComma(Long.toString(covidDataModel.getDeaths())));
                        mnewCase_world2.setText("NEW CASES: "+putComma(Long.toString(covidDataModel.getNewCases())));
                        mnewDeath_world2.setText("NEW DEATHS: "+putComma(Long.toString(covidDataModel.getNewDeaths())));
                        mRecovered_world2.setText("RECOVERED: "+putComma(Long.toString(covidDataModel.getRecovered())));
                        mnewCase_world3.setText("TOTAL TESTS: "+putComma(Long.toString(covidDataModel.getMtotal_tests())));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Covid","Fail "+error.toString());
                        error.printStackTrace();
                        Toast.makeText(MainActivity.this,"Request Failed",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(objectRequest);

        /*JSONArray jsonArray=new JSONArray();
        jsonArray.put(country_ID);
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                Covid_URL_country,
                jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Covid","SUCCESS! JSON: "+response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Covid","Fail "+error.toString());
                        Toast.makeText(MainActivity.this,"Request Failed",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(arrayRequest);*/
    }
    private String putComma(String s)
    {
        int l=s.length();int k=0;
        String ns="";
        for(int i=l-1;i>=0;i--) {
            if (k % 3 == 0 && k!=0) {
                ns = s.charAt(i) + "," + ns;
            } else
                ns = s.charAt(i) + ns;

            k++;
        }
        return ns;
    }

}
