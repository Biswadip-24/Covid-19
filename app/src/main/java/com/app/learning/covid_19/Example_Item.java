package com.app.learning.covid_19;

public class Example_Item {
    private String State;
    private String conf;
    private String rec;
    private String dec;
    private String dconf;
    private String drec;
    private String ddec;

    public Example_Item(String s,String c,String r,String d,String dc,String dr,String dd)
    {
        this.State=s;
        this.conf=c;
        this.rec=r;
        this.dec=d;
        this.dconf=dc;
        this.drec=dr;
        this.ddec=dd;
    }

    public String getState() {
        return State;
    }

    public String getConf() {
        return conf;
    }

    public String getRec() {
        return rec;
    }

    public String getDec() {
        return dec;
    }

    public String getDconf() {
        return dconf;
    }

    public String getDrec() {
        return drec;
    }

    public String getDdec() {
        return ddec;
    }
}
