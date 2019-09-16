package com.example.sports.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RService {

    @Autowired
    private RRep rRep;
    @Autowired
    private PService pService;

    public void addOne(Map<String,Object>map){
        R r = new R();
        r.setMatchName(map.get("matchName").toString());
        r.setType(map.get("type").toString());
        r.setTime(new Date(Long.valueOf(map.get("time").toString())));
        r.setHomeTeam(map.get("homeTeam").toString());
        r.setVisitingTeam(map.get("visitingTeam").toString());
        r.setContent(map.get("content").toString());
        r.setMathchResult(map.get("mathchResult").toString());
        r.setOdds(Double.valueOf(map.get("odds").toString()));
        r.setMoney(Double.valueOf(map.get("money").toString()));
        r.setAttach(Double.valueOf(map.get("attach").toString()));
        r.setBocaiResult(Double.valueOf(map.get("bocaiResult").toString()));

        rRep.save(r);
        pService.addOne(r);

    }

    public List<Map<String,Object>> getList( Date startTime, Date endTime){
        List<R> list = rRep.findByTimeAfterAndTimeBefore(startTime,endTime);
        List<Map<String,Object>> result = new ArrayList<>();
        for(R obj:list){
            Map<String,Object> map = new HashMap<>();
            Field[] fields = obj.getClass().getDeclaredFields();
            for(int i=0;i<fields.length;i++){
                try {
                    Field f = obj.getClass().getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    if(fields[i].getName().equals("time")){
                        o = (Date)o;
                        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
                        o = sDateFormat.format(o);
                    }
                    map.put(fields[i].getName(), o);
                } catch (NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            result.add(map);
        }
        return result;
    }
}
