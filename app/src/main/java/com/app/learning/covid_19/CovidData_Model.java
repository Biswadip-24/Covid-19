package com.app.learning.covid_19;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class CovidData_Model {

    private static String mState;
    private static String mConfirmed;
    private static String mRecovered;
    private static String mDeceased;
    private static String mdConfirmed;
    private static String mdRecovered;
    private static String mdDeceased;
    public Example_Item arr[];


    public String Wc,Wr,Wd,Wdc,Wdr,Wdd;
    public String Ic,Ir, Id,Idc,Idr,Idd;

    public static CovidData_Model fromJSON(JSONObject jsonObject)
    {
        CovidData_Model covidData=new CovidData_Model();

        try {
            covidData.arr=new Example_Item[jsonObject.getJSONArray("statewise").length()-2];
            int i,k=0;
            for(i=0;i<covidData.arr.length+2;i++)
            {
                mState =  jsonObject.getJSONArray("statewise").getJSONObject(i).getString("state");
                mConfirmed= jsonObject.getJSONArray("statewise").getJSONObject(i).getString("confirmed");
                mRecovered=jsonObject.getJSONArray("statewise").getJSONObject(i).getString("recovered");
                mDeceased=jsonObject.getJSONArray("statewise").getJSONObject(i).getString("deaths");
                mdConfirmed="+"+jsonObject.getJSONArray("statewise").getJSONObject(i).getString("deltaconfirmed");
                mdRecovered="+"+jsonObject.getJSONArray("statewise").getJSONObject(i).getString("deltarecovered");
                mdDeceased="+"+jsonObject.getJSONArray("statewise").getJSONObject(i).getString("deltadeaths");

                if(mState.equalsIgnoreCase("State Unassigned"))
                    continue;

                if(mState.equalsIgnoreCase("West Bengal"))
                {
                    covidData.Wc=mConfirmed;
                    covidData.Wr=mRecovered;
                    covidData.Wd=mDeceased;
                    covidData.Wdc=mdConfirmed;
                    covidData.Wdr=mdRecovered;
                    covidData.Wdd=mdDeceased;
                }
                if(i==0)
                {
                    covidData.Ic=mConfirmed;
                    covidData.Ir=mRecovered;
                    covidData.Id=mDeceased;
                    covidData.Idc=mdConfirmed;
                    covidData.Idr=mdRecovered;
                    covidData.Idd=mdDeceased;
                    continue;
                }

                covidData.arr[k++] = new Example_Item(mState,mConfirmed,mRecovered,mDeceased,mdConfirmed,mdRecovered,mdDeceased);
                Log.d("Covid","\n"+mState+" "+mConfirmed+" "+mRecovered+" "+mDeceased);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }

        return covidData;
    }
}
