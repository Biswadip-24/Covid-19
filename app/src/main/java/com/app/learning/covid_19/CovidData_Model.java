package com.app.learning.covid_19;

import org.json.JSONException;
import org.json.JSONObject;

public class CovidData_Model {

    private long mTotal;
    private long mRecovered;
    private long mDeaths;
    private long mNewCases;
    private long mNewDeaths;
    private long mtotal_tests;


    public static CovidData_Model fromJSON(JSONObject jsonObject)
    {
        CovidData_Model covidData=new CovidData_Model();

        try {
            covidData.mTotal = jsonObject.getLong("cases");
            covidData.mDeaths=jsonObject.getLong("deaths");;
            covidData.mNewDeaths = jsonObject.getLong("todayDeaths");
            covidData.mNewCases= jsonObject.getLong("todayCases");
            covidData.mRecovered=jsonObject.getLong("recovered");
            covidData.mtotal_tests=jsonObject.getLong("tests");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }

        return covidData;
    }

    public long getTotal() {
        return mTotal;
    }

    public long getRecovered() {
        return mRecovered;
    }

    public long getDeaths() {
        return mDeaths;
    }

    public long getNewCases() {
        return mNewCases;
    }

    public long getNewDeaths() {
        return mNewDeaths;
    }

    public long getMtotal_tests(){return mtotal_tests;}
}
