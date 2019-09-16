package com.example.sports.module;

import java.io.Serializable;
import java.sql.Date;

public class priKey implements Serializable {

    private static final long serialVersionUID = -822180384369151535L;

    private Date bisaishijian;

    private String zhudui;

    private String kedui;

    private String bisaileixing;

    public Date getBisaishijian() {
        return bisaishijian;
    }

    public void setBisaishijian( Date bisaishijian ) {
        this.bisaishijian = bisaishijian;
    }

    public String getZhudui() {
        return zhudui;
    }

    public void setZhudui( String zhudui ) {
        this.zhudui = zhudui;
    }

    public String getKedui() {
        return kedui;
    }

    public void setKedui( String kedui ) {
        this.kedui = kedui;
    }

    public String getBisaileixing() {
        return bisaileixing;
    }

    public void setBisaileixing( String bisaileixing ) {
        this.bisaileixing = bisaileixing;
    }
}
