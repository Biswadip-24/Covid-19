package com.app.learning.covid_19;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CovidData_World {


    private static String mConfirmed;
    private static String mRecovered;
    private static String mDeceased;
    private static String mdConfirmed;
    private static String mdRecovered;
    private static String mdDeceased;
    public static CovidData_World fromJSON(JSONObject jsonObject)
    {
        CovidData_World covidData=new CovidData_World();

        try {
            mConfirmed= putComma(Long.toString(jsonObject.getLong("cases")));
            mRecovered=putComma(Long.toString(jsonObject.getLong("recovered")));
            mDeceased=putComma(Long.toString(jsonObject.getLong("deaths")));
            mdConfirmed="+"+putComma(Long.toString(jsonObject.getLong("todayCases")));
            mdRecovered="+"+putComma(Long.toString(jsonObject.getLong("todayRecovered")));
            mdDeceased="+"+putComma(Long.toString(jsonObject.getLong("todayDeaths")));

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
    public static String getmConfirmed() {
        return mConfirmed;
    }

    public static String getmRecovered() {
        return mRecovered;
    }

    public static String getmDeceased() {
        return mDeceased;
    }

    public static String getMdConfirmed() {
        return mdConfirmed;
    }

    public static String getMdRecovered() {
        return mdRecovered;
    }

    public static String getMdDeceased() {
        return mdDeceased;
    }
}
