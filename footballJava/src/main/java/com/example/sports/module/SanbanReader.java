package com.example.sports.module;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Service
public class SanbanReader {

    @Autowired
    private Footballrep footballrep;
    @Autowired
    private ExcelReader excelReader;


    private String[] arr = {"lisanzhichu","lisanzhiji","lisanzhichachu","lisanzhichaji","","lisanzhishu","lisanbi","bianyilisanzhishu",
                            "bianyilisanzhishuxia","qujianqiwangchaquan","qujianqiwangchazhu","fc","fd","qiwangfengxianbi","zonghezhishu",
                            "peilvfangchahe","zonghefengxianbi","kailizhishucha","","","pellvqujianchu","peilvqujianji","peilvqujianbi",
                            "","","","","peifuzhishutiaozhengxia1","peifuzhishutiaozhengxia2","peifuzhishutiaozhengxia3",
                            "peifuzhishutiaozhengxia4","peifuzhishutiaozhengxia5","peifuzhishutiaozhengxia6","qiwangfangchabi",
                            "junhengzhishu","lisanzhichuyoubian","lisanzhijiyoubian","lisanzhichachuyoubian","lisanzhichajiyoubian",
                            "lisanzhibianhualvyoubian","lisanzhishuyoubian","lisanbiyoubian","bianyilisanzhishuyoubian",
                            "bianyilisanzhishuxiayoubian","qujianqiwangchaquanyoubian","qujianqiwangchazhuyoubian","fcyoubian",
                            "fdyoubian","qiwangfengxianbiyoubian","zonghezhishuyoubian","peilvfangchaheyoubian","zonghefengxianbiyoubian",
                            "kailizhishuchayoubian","baolengzhishuyoubian","zhibiao1youbian","peilvqujianchuyoubian","peilvqujianjiyoubian",
                            "peilvqujianbiyoubian","fengxianzhishuyoubian","zonghefengxianzhishuyoubian","peifuzhishuyoubian",
                            "peifuzhishutiaozhengyoubian","peifuzhishutiaozhengxia1youbian","peifuzhishutiaozhengxia2youbian",
                            "peifuzhishutiaozhengxia3youbian","peifuzhishutiaozhengxia4youbian","peifuzhishutiaozhengxia5youbian",
                            "peifuzhishutiaozhengxia6youbian","qiwangfangchabiyoubian","junhengzhishuyoubian"};

    public void anaXlsx(){

        File dirs = new File("C:\\Users\\Administrator\\Desktop\\zq\\三版");
        if(!dirs.exists()){
            return;
        }
        File[] files = dirs.listFiles();
        for(File tFile : files){
            try{
                InputStream inputStream = new FileInputStream(tFile.getAbsolutePath());
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                String yearStr = tFile.getName().substring(tFile.getName().lastIndexOf("\\")+1,tFile.getName().lastIndexOf("."));
                for(int j=0;j<workbook.getNumberOfSheets();j++){
                    XSSFSheet sheetAt = workbook.getSheetAt(j);
                    String timeStr = yearStr.substring(0,4+(4-sheetAt.getSheetName().length())) + sheetAt.getSheetName();
                    SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMdd"); //加上时间
                    //必须捕获异常
                    Date date = new Date();
                    try {
                        date=sDateFormat.parse(timeStr);
                    } catch(ParseException px) {
                        px.printStackTrace();
                    }
                    List<FootballAddData> list = footballrep.findByBisaishijianAndFc1IsNullOrderById( new java.sql.Date(date.getTime()));
                    if(null == list || list.isEmpty()){
                        continue;
                    }


                    Integer rowNums = sheetAt.getLastRowNum()+2;
                    Integer maxbisaiPerhang =  sheetAt.getRow(0).getLastCellNum()/7;
                    Integer comNum = (rowNums/43-1)* maxbisaiPerhang
                            + sheetAt.getRow(sheetAt.getLastRowNum()-1).getLastCellNum()/7;
                    int k=0;
                    int p=0;
                    List<FootballAddData> updateList = new ArrayList();
                    for(int m= 0;m<comNum;m++){
                        try{
                            String zhudui = sheetAt.getRow(k*43).getCell(1+7*p).getStringCellValue().replace(" ","");
                            String kedui = "";
                            if(zhudui.indexOf(":")>-1){
                                kedui = zhudui.split(":")[1];
                                zhudui = zhudui.split(":")[0];
                            }else{
                                kedui = sheetAt.getRow(k*43).getCell(3+7*p).getStringCellValue().replace(" ","");
                            }
                            String changci = sheetAt.getRow(k*43).getCell(4+7*p).toString();
                            Double pankou = excelReader.getPankouValue(sheetAt.getRow(k*43+4).getCell(7*p));


                            Iterator<FootballAddData> iterator = list.iterator();
                            while (iterator.hasNext()) {
                                FootballAddData fData = iterator.next();
                                if (zhudui.equals(fData.getZhudui())&& kedui.equals(fData.getKedui())
                                        && changci.equals(fData.getChangci())&& pankou.equals(fData.getPankou())) {
                                    setArrValue(fData,k,p,sheetAt);
                                    updateList.add(fData);
                                    iterator.remove();//使用迭代器的删除方法删除
                                    break;
                                }
                            }
                            if(p<sheetAt.getRow(k*43).getLastCellNum()/7-1){
                                p++;
                            }else{
                                p=0;
                                k++;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                    footballrep.saveAll(updateList);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void setArrValue(FootballAddData obj,Integer k,Integer p,XSSFSheet sheetAt){
        Integer start=  7;
        Integer end = 41;
        Integer offset = 35;
        for(Integer i=0;i<arr.length;i++){
            String name = arr[i];
            Integer j=i+start;//74-101
            Integer h=0;
            if("".equals(name)){
                continue;
            }
            if(j>end){
                j=j-offset;
                h=3;
            }

            excelReader.reflectValue(obj,name+"1",excelReader.getDoubleValue(sheetAt.getRow(k*43+j).getCell(h+1+7*p)));
            excelReader.reflectValue(obj,name+"2",excelReader.getDoubleValue(sheetAt.getRow(k*43+j).getCell(h+2+7*p)));
            excelReader.reflectValue(obj,name+"3",excelReader.getDoubleValue(sheetAt.getRow(k*43+j).getCell(h+3+7*p)));

        }
    }
}
