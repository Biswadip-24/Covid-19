package com.app.learning.covid_19;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CovidData_Countries
{
    public Example_Item arr[];

    public static CovidData_Countries fromJSON(JSONArray jsonArray)
    {
        CovidData_Countries covidData=new CovidData_Countries();

        try {
            covidData.arr=new Example_Item[jsonArray.length()];
            int i,k=0;
            for(i=0;i<covidData.arr.length;i++)
            {
                String country = jsonArray.getJSONObject(i).getString("country");
                String confirmed = putComma(Long.toString(jsonArray.getJSONObject(i).getLong("cases")));
                String recovered = putComma(Long.toString(jsonArray.getJSONObject(i).getLong("recovered")));
                String deceased = putComma(Long.toString(jsonArray.getJSONObject(i).getLong("deaths")));
                String mdConfirmed = "+" + putComma(Long.toString(jsonArray.getJSONObject(i).getLong("todayCases")));
                String mdRecovered = "+" + putComma(Long.toString(jsonArray.getJSONObject(i).getLong("todayRecovered")));
                String mdDeceased = "+" + putComma(Long.toString(jsonArray.getJSONObject(i).getLong("todayDeaths")));


                covidData.arr[k++] = new Example_Item(country, confirmed, recovered, deceased, mdConfirmed, mdRecovered, mdDeceased);
                Log.d("Covid","\n"+ country +" "+ confirmed +" "+ recovered +" "+ deceased);
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
