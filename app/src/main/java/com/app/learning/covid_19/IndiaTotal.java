package com.app.learning.covid_19;

import android.util.Log;

import org.json.JSONObject;

public class IndiaTotal {
    private  String mConfirmed;
    private  String mRecovered;
    private  String mDeceased;
    private  String mdConfirmed;
    private  String mdRecovered;
    private  String mdDeceased;

    public static IndiaTotal fromJSON(JSONObject jsonObject)
    {
        IndiaTotal mIndia=new IndiaTotal();

            String Confirmed,Recovered,Deceased,dConfirmed,dRecovered,dDeceased;
            try{
                Confirmed= putComma(jsonObject.getJSONObject("TT").getJSONObject("total").getString("confirmed"));
            }
            catch (Exception e){
                Confirmed = "0";
            }

            try{
                Recovered=putComma(jsonObject.getJSONObject("TT").getJSONObject("total").getString("recovered"));
            }
            catch(Exception e){
                Recovered = "0";
            }

            try{
                Deceased=putComma(jsonObject.getJSONObject("TT").getJSONObject("total").getString("deceased"));
            }
            catch(Exception e){
                Deceased = "0";
            }

            try{
                dConfirmed=putComma("+"+jsonObject.getJSONObject("TT").getJSONObject("delta").getString("confirmed"));
            }
            catch(Exception e){
                dConfirmed = "+0";
            }

            try{
                dRecovered=putComma("+"+jsonObject.getJSONObject("TT").getJSONObject("delta").getString("recovered"));
            }
            catch(Exception e){
                dRecovered = "+0";
            }

            try{
                dDeceased=putComma("+"+jsonObject.getJSONObject("TT").getJSONObject("delta").getString("deceased"));
            }
            catch(Exception e){
                dDeceased = "+0";
            }


            mIndia.mConfirmed=Confirmed;
            mIndia.mRecovered=Recovered;
            mIndia.mDeceased=Deceased;
            mIndia.mdConfirmed=dConfirmed;
            mIndia.mdRecovered=dRecovered;
            mIndia.mdDeceased=dDeceased;

        return mIndia;
    }

    public String getConfirmed() {
        return mConfirmed;
    }

    public String getRecovered() {
        return mRecovered;
    }

    public String getDeceased() {
        return mDeceased;
    }

    public String getMdConfirmed() {
        return mdConfirmed;
    }

    public String getMdRecovered() {
        return mdRecovered;
    }

    public String getMdDeceased() {
        return mdDeceased;
    }

    private static String putComma(String s)
    {
        char sgn = s.charAt(0);
        s=s.substring(1);
        Log.d("comma","String s : "+s);
        int l=s.length();int k=0;
        String ns="";
        for(int i=l-1;i>=0;i--) {
            if (k % 3 == 0 && k!=0) {
                ns = s.charAt(i) + "," + ns;
            } else
                ns = s.charAt(i) + ns;

            k++;
        }

        return sgn + ns;
    }
}
