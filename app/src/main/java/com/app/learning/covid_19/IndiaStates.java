package com.app.learning.covid_19;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class IndiaStates
{
    private ArrayList<Example_Item> arr = new ArrayList<>();
    private String codes[] = new String[36];
    private String state_name[] = new String[36];

    public String Wc,Wr,Wd,Wdc,Wdr,Wdd;

    private void init(){
        codes[0] = "AN"; state_name[0] = "Andaman and Nicobar Islands";
        codes[1] = "AP"; state_name[1] = "Andhra Pradesh";
        codes[2] = "AR"; state_name[2] = "Arunachal Pradesh";
        codes[3] = "AS"; state_name[3] = "Assam";
        codes[4] = "BR"; state_name[4] = "Bihar";
        codes[5] = "CH"; state_name[5] = "Chandigarh";

        codes[6] = "CT"; state_name[6] = "Chattisgarh";
        codes[7] = "DL"; state_name[7] = "Delhi";
        codes[8] = "DN"; state_name[8] = "Dadra & Nagar Haveli and Daman & Diu";
        codes[9] = "GA"; state_name[9] = "Goa";
        codes[10] = "GJ"; state_name[10] = "Gujarat";
        codes[11] = "HP"; state_name[11] = "Himachal Pradesh";

        codes[12] = "HR"; state_name[12] = "Haryana";
        codes[13] = "JH"; state_name[13] = "Jharkhand";
        codes[14] = "JK"; state_name[14] = "Jammu and Kashmir";
        codes[15] = "KA"; state_name[15] = "Karnataka";
        codes[16] = "KL"; state_name[16] = "Kerala";
        codes[17] = "LA"; state_name[17] = "Ladakh";

        codes[18] = "LD"; state_name[18] = "Lakshadweep";
        codes[19] = "MH"; state_name[19] = "Maharashtra";
        codes[20] = "ML"; state_name[20] = "Meghalaya";
        codes[21] = "MN"; state_name[21] = "Manipur";
        codes[22] = "MP"; state_name[22] = "Madhya Pradesh";
        codes[23] = "MZ"; state_name[23] = "Mizoram";

        codes[24] = "NL"; state_name[24] = "Nagaland";
        codes[25] = "OR"; state_name[25] = "Odisha";
        codes[26] = "PB"; state_name[26] = "Punjab";
        codes[27] = "PY"; state_name[27] = "Pondicherry";
        codes[28] = "RJ"; state_name[28] = "Rajasthan";
        codes[29] = "SK"; state_name[29] = "Sikkim";

        codes[30] = "TG"; state_name[30] = "Telangana";
        codes[31] = "TN"; state_name[31] = "Tamil Nadu";
        codes[32] = "TR"; state_name[32] = "Tripura";
        codes[33] = "UP"; state_name[33] = "Uttar Pradesh";
        codes[34] = "UT"; state_name[34] = "Uttarakhand";
        codes[35] = "WB"; state_name[35] = "West Bengal";
    }


    public static IndiaStates fromJSON(JSONObject jsonObject)
    {
        IndiaStates mIndia=new IndiaStates();

        int size = 36;
        mIndia.init();

        ArrayList<Example_Item> temp = new ArrayList<>();
        SortedMap<Integer, Integer> sm = new TreeMap<Integer, Integer>(Collections.reverseOrder());

        for(int i = 0 ;i < size; i++){
            String state = mIndia.state_name[i];

            String Confirmed,Recovered,Deceased,dConfirmed,dRecovered,dDeceased;
            int con = 0;
            try{
                Confirmed= putComma(jsonObject.getJSONObject(mIndia.codes[i]).getJSONObject("total").getString("confirmed"));
                con = Integer.parseInt(jsonObject.getJSONObject(mIndia.codes[i]).getJSONObject("total").getString("confirmed"));
            }
            catch (Exception e){
                Confirmed = "0";
            }

            sm.put(con,i);

            try{
                Recovered=putComma(jsonObject.getJSONObject(mIndia.codes[i]).getJSONObject("total").getString("recovered"));
            }
            catch(Exception e){
                Recovered = "0";
            }

            try{
                Deceased=putComma(jsonObject.getJSONObject(mIndia.codes[i]).getJSONObject("total").getString("deceased"));
            }
            catch(Exception e){
                Deceased = "0";
            }

            try{
                dConfirmed=putComma("+"+jsonObject.getJSONObject(mIndia.codes[i]).getJSONObject("delta").getString("confirmed"));
            }
            catch(Exception e){
                dConfirmed = "+0";
            }

            try{
                dRecovered=putComma("+"+jsonObject.getJSONObject(mIndia.codes[i]).getJSONObject("delta").getString("recovered"));
            }
            catch(Exception e){
                dRecovered = "+0";
            }

            try{
                dDeceased=putComma("+"+jsonObject.getJSONObject(mIndia.codes[i]).getJSONObject("delta").getString("deceased"));
            }
            catch(Exception e){
                dDeceased = "+0";
            }

            if(state.equalsIgnoreCase(Frag_India.state))
            {
                mIndia.Wc=Confirmed;
                mIndia.Wr=Recovered;
                mIndia.Wd=Deceased;
                mIndia.Wdc=dConfirmed;
                mIndia.Wdr=dRecovered;
                mIndia.Wdd=dDeceased;
            }


            temp.add(new Example_Item(state,Confirmed,Recovered,Deceased,dConfirmed,dRecovered,dDeceased));
            }

        Set s = sm.entrySet();
        Iterator i = s.iterator();
        while (i.hasNext()) {
            Map.Entry m = (Map.Entry) i.next();

            int val = (Integer)m.getValue();
            mIndia.arr.add(temp.get(val));
        }

        return mIndia;
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

    public ArrayList<Example_Item> getArr() {
        return arr;
    }
}
