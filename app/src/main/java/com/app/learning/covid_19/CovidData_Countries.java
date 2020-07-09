package com.app.learning.covid_19;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CovidData_Countries {
    private static String mCountry;
    private static String mConfirmed;
    private static String mRecovered;
    private static String mDeceased;
    private static String mdConfirmed;
    private static String mdRecovered;
    private static String mdDeceased;
    public Example_Item arr[];


    public static CovidData_Countries fromJSON(JSONArray jsonArray)
    {
        CovidData_Countries covidData=new CovidData_Countries();

        try {
            covidData.arr=new Example_Item[jsonArray.length()];
            int i,k=0;
            for(i=0;i<covidData.arr.length;i++)
            {
                mCountry =  jsonArray.getJSONObject(i).getString("country");
                mConfirmed= putComma(Long.toString(jsonArray.getJSONObject(i).getLong("cases")));
                mRecovered=putComma(Long.toString(jsonArray.getJSONObject(i).getLong("recovered")));
                mDeceased=putComma(Long.toString(jsonArray.getJSONObject(i).getLong("deaths")));
                mdConfirmed="+"+putComma(Long.toString(jsonArray.getJSONObject(i).getLong("todayCases")));
                mdRecovered="+"+putComma(Long.toString(jsonArray.getJSONObject(i).getLong("todayRecovered")));
                mdDeceased="+"+putComma(Long.toString(jsonArray.getJSONObject(i).getLong("todayDeaths")));


                covidData.arr[k++] = new Example_Item(mCountry,mConfirmed,mRecovered,mDeceased,mdConfirmed,mdRecovered,mdDeceased);
                Log.d("Covid","\n"+mCountry+" "+mConfirmed+" "+mRecovered+" "+mDeceased);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }

        return covidData;
    }
    private static String putComma(String s)
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
