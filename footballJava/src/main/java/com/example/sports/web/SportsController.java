package com.example.sports.web;

import com.example.sports.module.*;
import com.example.sports.module.baseData.*;
import com.example.sports.module.forecast.FileUploadService;
import com.example.sports.module.forecast.ForecastDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private ForecastDataService forecastDataService;

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

    @PostMapping(value = "/file/upload1Data")
    String upload1DataFile(@RequestParam("file")MultipartFile file,HttpServletRequest req) {


        //将文件缓冲到本地
        boolean localFile = fileUploadService.createLocalFile(file);
        if(!localFile){
            return "Create local file failed!";
        }
        excelReader.update1BData(file.getOriginalFilename());

        return "Create local file successfully";
    }
    @PostMapping(value = "/file/upload3Data")
    String upload3DataFile(@RequestParam("file")MultipartFile file,HttpServletRequest req) {

        //将文件缓冲到本地
        boolean localFile = fileUploadService.createLocalFile(file);
        if(!localFile){
            return "Create local file failed!";
        }
        sanbanReader.update3BData(file.getOriginalFilename());
        return "Create local file successfully";
    }
    @GetMapping(value = "/forecast/list")
    public ReplyInfo forecastList( HttpServletRequest req){
        java.sql.Date startDate = new java.sql.Date(Long.valueOf(req.getParameter("startDate")));
        java.sql.Date endDate = new java.sql.Date(Long.valueOf(req.getParameter("endDate")));
        Date startTime = new Date(Long.valueOf(req.getParameter("startTime")));
        Date endTime = new Date(Long.valueOf(req.getParameter("endTime")));
        Double value = Double.valueOf(req.getParameter("value"));
        List list = forecastDataService.list(startDate,endDate,startTime,endTime,value);

        return new ReplyInfo(true,list);
    }
    @GetMapping(value = "/forecast/needAnaNum")
    public ReplyInfo needAnaNum( HttpServletRequest req){
        Integer num = forecastDataService.getNotAnaNum();
        return new ReplyInfo(true,num);
    }



}
