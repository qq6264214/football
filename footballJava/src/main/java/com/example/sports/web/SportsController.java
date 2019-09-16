package com.example.sports.web;

import com.example.sports.module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class SportsController {

    @Autowired
    private RService rService;
    @Autowired
    private PService pService;
    @Autowired
    private ExcelReader excelReader;
    @Autowired
    private SanbanReader sanbanReader;

    @GetMapping("/listByDate")
    public ReplyInfo list( HttpServletRequest req){

        Long startTime = Long.valueOf(req.getParameter("startTime"))-1;
        Long endTime = Long.valueOf(req.getParameter("endTime"))+1;
        Date start = new Date(startTime);
        Date end = new Date(endTime);
        List<Map<String,Object>> list =rService.getList(start,end);

        return new ReplyInfo(true,list);
    }

    @PostMapping("/add")
    public ReplyInfo add( HttpServletRequest req, @RequestBody Map<String,Object>map){
        rService.addOne(map);

        return new ReplyInfo(true,"");

    }
    @GetMapping("/getMoney")
    public ReplyInfo getMoney( HttpServletRequest req){

        Profit p = pService.getProfit();

        return new ReplyInfo(true,p);
    }

    @GetMapping("/anaData")
    public ReplyInfo anaData( HttpServletRequest req){

        excelReader.anaXlsx();

        return new ReplyInfo(true,null);
    }

    @GetMapping("/anaSanbanData")
    public ReplyInfo anasanbanData( HttpServletRequest req){

        sanbanReader.anaXlsx();

        return new ReplyInfo(true,null);
    }

}
