package com.example.sports.module.forecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForecastDataService {
    @Autowired
    private ForecastDataRep forecastDataRep;

    @PersistenceContext
    private EntityManager entityManager;

    public List list(Date startDate, Date endDate, java.util.Date startTime, java.util.Date endTime,Double value){
        String sql = "SELECT a.id,a.bisaileixing,a.changci,a.bisaishijian,real_time,a.zhudui,a.kedui,a.pankou," +
                "a.linchangpankou,sheng_avg,ping_avg,fu_avg,shangmin,xiamin,a.zhubifen,a.kebifen,b.peilv1,b.peilv2," +
                "b.peilv3,a.sheng_count,a.ping_count,a.fu_count FROM forecast_data a INNER JOIN football_data b " +
                "ON a.bisaileixing=b.bisaileixing AND a.bisaishijian=b.bisaishijian AND a.zhudui=b.zhudui AND a.kedui=b.kedui" +
                "  WHERE ((a.bisaishijian>=? AND a.bisaishijian<= ? AND real_time is null) " +
                "OR (real_time is NOT NULL AND real_time>=? AND real_time <= ?)) " +
                "AND (sheng_count>? OR ping_count>? OR fu_count>? ) ORDER BY real_time";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,startDate);
        query.setParameter(2,endDate);
        query.setParameter(3,startTime);
        query.setParameter(4,endTime);
        query.setParameter(5,value);
        query.setParameter(6,value);
        query.setParameter(7,value);
//        query.setParameter(8,value);
//        query.setParameter(9,value);

        List list = query.getResultList();

        return list;
    }
    public Map getPeilv(){
        Map map =new HashMap();
        String sql = "SELECT pankou,ROUND(AVG(peilv1),4),ROUND(AVG(peilv2),4)," +
                "ROUND(AVG(peilv3),4) FROM football_data where bisaishijian<'2019-12-01' GROUP BY pankou ORDER BY pankou";
        Query query = entityManager.createNativeQuery(sql);
        List list = query.getResultList();
        for(Object obj:list){
            Object[] obs = (Object[])obj;
            String pk = obs[0].toString();
            Double pv1 = Double.valueOf(obs[1].toString());
            Double pv2 = Double.valueOf(obs[2].toString());
            Double pv3 = Double.valueOf(obs[3].toString());


            map.put(pk,new Double[]{pv1,pv2,pv3});

        }
        return map;
    }



    public Integer getNotAnaNum(){
        List list = forecastDataRep.findByStartAnalysis(0);
        return list.size();
    }

}
