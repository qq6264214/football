package com.example.sports.module;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ExcelReader {

    @Autowired
    private Footballrep footballrep;

    private String[] arr1 = {"peilv","peilvbianhua","qushipianlizhi","shengfuqushicha","zonghefengxianzhishu","zucaizhishu",
            "lengrezhishu","peilvqiwang","peilvqujian","zhibiao","zhibiaoqujianbi","kailifangchabi","lisanzhicha","peifuzhishu",
            "peifuzhishuxin","bocaiyinglizhishu","chuxuanzhishu","xuandanzhishu","peilvlisanzhi1","peilvlisanzhi2","lisanzhibianhualv",
            "dingdanzhishu","baolengzhishu","kailizhishuchu","kailizhishuji","kailibianhualv","kaipeicha","pianlizhi","zhongzhipianlizhi","pianlilv",
            "zonghepianlizhi","fangcha","peilvfanhuanchaqujian","peilvfanhuancha","zonghepingjia1","zonghepingjia2","zonghepingjia3"};

    private String[] arr2 = {"peilvzhongzhi","pingjunpeilv","pingjunpeilvcha","shenglvzhongzhi","pingjunshenglv","pingjunshenglvcha","kailizhishuzhongzhi",
                            "kailizhishu","baolengzhishuxin","peilvqiwangjunzhi","lisanzhi","kaipeifangchahe","kaipeichagaodicha",
                            "youshizhishu1","youshizhishu2","youshizhishu3","youshizhishu4","youshizhishu5","youhuayoushi","zhongjiyoushi","youshicha",
                            "","shaixuanbi","shaixuancha","zhongjishaixuan","","fa","fb","chupeizuigaopeilv","jishizuigaopeilv","zuigaopeilvbianhua",
                            "chupeizuidipeilv","jishizuidipeilv","zuidipeilvbianhua","chupeipingjunpeilv","jishipingjunpeilv","pingjunpeilvbianhua",
                            "chupeizuigaoshenglv","jishizuigaoshenglv","zuigaoshenglvbianhua","chupeizuidishenglv","jishizuidishenglv","zuidishenglvbianhua",
                            "chupeipingjunshenglv","jishipingjunshenglv","pingjunshenglvbianhua","yinglizhishu","fengxianzhishu","youshichayoubian",
                            "weizhi","shaixuanbiyoubian","shaixuanchayoubian","zhongjishaixuanyoubian","","","zonghefangchabi"};



    public Double getDoubleValue (Cell cell){
        try{
            String value = cell.toString();
            if("#DIV/0!".equals(value)){
                return 9999D;
            }else if(value.endsWith("%")){
                value = value.substring(0,value.length() - 1);
                return Double.valueOf(value);
            }else if(value.indexOf("/")>-1){
                String[] vals = value.split("/");
                return (Double.valueOf(vals[0])+Double.valueOf(vals[1]))/2;
            }
            return cell.getNumericCellValue();
        }catch (Exception e){
            return 9999D;
        }

    }

    public Double getPankouValue(Cell cell){

        String value = cell.toString();
        if(value.indexOf("/")>-1){
            String[] vals = value.split("/");
            if(value.indexOf("-")>-1){
                return -(Math.abs(Double.valueOf(vals[0]))+Math.abs(Double.valueOf(vals[1])))/2;
            }

            return (Double.valueOf(vals[0])+Double.valueOf(vals[1]))/2;
        }else if("-".equals(value)){
            return 0D;
        }
        return Double.valueOf(value);

    }

    public void anaXlsx(){

            File dirs = new File("C:\\Users\\Administrator\\Desktop\\zq\\files");
            if(!dirs.exists()){
                return;
            }
            File[] files = dirs.listFiles();
            for(File tFile : files){
                try{
                    InputStream inputStream = new FileInputStream(tFile.getAbsolutePath());
                    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                    String yearStr = tFile.getName().substring(tFile.getName().lastIndexOf("\\")+1,tFile.getName().lastIndexOf("."));

                    for(int j=checkDateNum(yearStr);j<workbook.getNumberOfSheets();j++){
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
                        Integer rowNums = sheetAt.getLastRowNum()+2;
                        Integer maxbisaiPerhang =  sheetAt.getRow(0).getLastCellNum()/7*2;
                        Integer comNum = (rowNums/103-1)* maxbisaiPerhang
                                + sheetAt.getRow(sheetAt.getLastRowNum()-1).getLastCellNum()/7*2;
                        int k=0;
                        int p=0;
                        List<FootballAddData> list = new ArrayList();
                        for(int m= 0;m<comNum;m++){
                            try{
                                FootballAddData fData = new FootballAddData();
                                fData.setBisaishijian(new java.sql.Date(date.getTime()));
                                Integer offset1 = m%2==0?0:3;
                                Integer offset2 = m%2==0?0:31;
                                String zhudui = sheetAt.getRow(k*103).getCell(offset1+1+7*p).getStringCellValue().replace(" ","");
                                if(zhudui.indexOf(":")>-1){
                                    fData.setZhudui(zhudui.split(":")[0]);
                                    fData.setKedui(zhudui.split(":")[1]);
                                }else{
                                    fData.setZhudui(zhudui);
                                    fData.setKedui(sheetAt.getRow(k*103).getCell(offset1+3+7*p).getStringCellValue().replace(" ",""));
                                }
                                fData.setBisaileixing(sheetAt.getRow(k*103+1).getCell(offset1+1+7*p).toString());
                                fData.setChangci(sheetAt.getRow(k*103+2).getCell(offset1+1+7*p).toString());
                                fData.setPankou(getPankouValue(sheetAt.getRow(k*103+41+offset2).getCell(0+7*p)));
                                setArr1Value(fData,k,p,sheetAt,m);
                                setArr2Value(fData,k,p,sheetAt,m);
                                list.add(fData);
                                if(m%2==1){
                                    if(p<sheetAt.getRow(k*103).getLastCellNum()/7-1){
                                        p++;
                                    }else{
                                        p=0;
                                        k++;
                                    }
                                }

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                        footballrep.saveAll(list);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

    }

    public void setArr1Value(FootballAddData obj,Integer k,Integer p,XSSFSheet sheetAt,Integer m){
        m = m%2==0?0:3;
        for(Integer i=0;i<arr1.length;i++){
            String name = arr1[i];
            if(!"shengfuqushicha".equals(name) && !"zonghepingjia2".equals(name)){
                reflectValue(obj,name+"1",getDoubleValue(sheetAt.getRow(k*103+3+i).getCell(m+1+7*p)));
                reflectValue(obj,name+"2",getDoubleValue(sheetAt.getRow(k*103+3+i).getCell(m+2+7*p)));
                reflectValue(obj,name+"3",getDoubleValue(sheetAt.getRow(k*103+3+i).getCell(m+3+7*p)));
            }else{
                reflectValue(obj,name,getDoubleValue(sheetAt.getRow(k*103+3+i).getCell(m+1+7*p)));
            }
        }
    }
    public void setArr2Value(FootballAddData obj,Integer k,Integer p,XSSFSheet sheetAt,Integer m){
        Integer start=  m%2==0?43:74;
        Integer end = m%2==0?70:101;
        Integer offset = 28;
        for(Integer i=0;i<arr2.length;i++){
            String name = arr2[i];
            Integer j=i+start;//74-101
            Integer h=0;
            if("".equals(name)){
                continue;
            }
            if(j>end){
                j=j-offset;
                h=3;
            }
            if(!"weizhi".equals(name)){
                reflectValue(obj,name+"1",getDoubleValue(sheetAt.getRow(k*103+j).getCell(h+1+7*p)));
                reflectValue(obj,name+"2",getDoubleValue(sheetAt.getRow(k*103+j).getCell(h+2+7*p)));
                reflectValue(obj,name+"3",getDoubleValue(sheetAt.getRow(k*103+j).getCell(h+3+7*p)));
            }else{
                reflectValue(obj,name,getDoubleValue(sheetAt.getRow(k*103+j).getCell(h+3+7*p)));
            }
        }
    }


    public void reflectValue(FootballAddData obj,String key,Object value){
        Class<?> c = obj.getClass();
        String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
        Method method;
        try {
            method = c.getMethod(methodName, value.getClass());
            obj = (FootballAddData) method.invoke(obj, value);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        return obj;
    }

    public Integer checkDateNum(String yearStr){

        String timeStr = yearStr+"01";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMdd"); //加上时间
        //必须捕获异常
        Date date = new Date();
        try {
            date=sDateFormat.parse(timeStr);
        } catch(ParseException px) {
            px.printStackTrace();
            return 0;
        }
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.add(Calendar.DAY_OF_MONTH,-1);
        java.sql.Date date1 = new java.sql.Date(cal.getTimeInMillis());

        cal.add(Calendar.MONTH,1);
        cal.add(Calendar.DAY_OF_MONTH,1);
        java.sql.Date date2 = new java.sql.Date(cal.getTimeInMillis());
        FootballAddData f =footballrep.findFirstByBisaishijianAfterAndBisaishijianBeforeOrderByBisaishijianDesc(date1,date2);
        if(null == f){
            return 0;
        }else{
            cal.setTime(f.getBisaishijian());
            return cal.get(Calendar.DAY_OF_MONTH)-1;
        }
    }




    public static void main(String[] args){

    }
}
