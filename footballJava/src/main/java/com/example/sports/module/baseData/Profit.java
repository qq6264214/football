package com.example.sports.module.baseData;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "profit")
public class Profit {

    private static final long serialVersionUID = -82218030654651530L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 20)
    private Integer id;

    private Date time;

    private double money;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime( Date time ) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney( double money ) {
        this.money = money;
    }
}
