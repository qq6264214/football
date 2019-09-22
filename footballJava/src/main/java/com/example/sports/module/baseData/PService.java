package com.example.sports.module.baseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PService {
    @Autowired
    private PRep prep;

    public Profit getProfit(){
        Profit p = prep.findFirstByOrderByIdDesc();
        return p;
    }

    public void addOne(R r){
        Profit lastP = prep.findFirstByOrderByIdDesc();
        Profit curP = new Profit();
        curP.setTime(r.getTime());
        if(r.getBocaiResult()>= 0){
            curP.setMoney(lastP.getMoney()+r.getMoney()*r.getOdds()*r.getBocaiResult()-r.getAttach());
        }else {
            curP.setMoney(lastP.getMoney()+r.getMoney()*r.getBocaiResult()-r.getAttach());
        }
        prep.save(curP);
    }
}
