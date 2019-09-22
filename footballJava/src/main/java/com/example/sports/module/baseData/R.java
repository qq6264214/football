package com.example.sports.module.baseData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "r")
public class R implements Serializable {
    private static final long serialVersionUID = -822180306369151530L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 20)
    private Integer id;

    private String type;

    private String matchName;

    private Date time;

    private String homeTeam;

    private String visitingTeam;

    private String content;

    private String mathchResult;

    private double odds;

    private double money;

    private double attach;

    private double bocaiResult;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName( String matchName ) {
        this.matchName = matchName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime( Date time ) {
        this.time = time;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam( String homeTeam ) {
        this.homeTeam = homeTeam;
    }

    public String getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam( String visitingTeam ) {
        this.visitingTeam = visitingTeam;
    }

    public String getMathchResult() {
        return mathchResult;
    }

    public void setMathchResult( String mathchResult ) {
        this.mathchResult = mathchResult;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds( double odds ) {
        this.odds = odds;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney( double money ) {
        this.money = money;
    }

    public double getBocaiResult() {
        return bocaiResult;
    }

    public void setBocaiResult( double bocaiResult ) {
        this.bocaiResult = bocaiResult;
    }

    public String getContent() {
        return content;
    }

    public void setContent( String content ) {
        this.content = content;
    }

    public double getAttach() {
        return attach;
    }

    public void setAttach( double attach ) {
        this.attach = attach;
    }
}
