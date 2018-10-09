package com.szcti.lcloud.common.utils;

import com.szcti.lcloud.common.poi.ExcelLogs;
import com.szcti.lcloud.common.poi.ExcelUtil;
import org.apache.commons.beanutils.BeanUtils;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class POITool {
    String filePath = System.getProperty("user.dir") + "/tmp/upload/";
    String exportPath="/tmp/export/";
    String exportPort="8195";
    public static Collection getDataFrom(String url){
        List<Map>  dataList=null;
        try{
            if(url.toLowerCase().contains(".xls")){
                dataList=importXls(url);
            }if(url.toLowerCase().contains(".xlsx")){
                dataList=importXlsx(url);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        return dataList;
    }
    public static List<Map> importXls(String url) throws FileNotFoundException {
        //url="src/resources/test.xls";
        List<Map> list=new ArrayList<Map>();
        File f=new File(url);
        InputStream inputStream= new FileInputStream(f);

        ExcelLogs logs =new ExcelLogs();
        Collection<Map> dataList = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
        for(Map m : dataList){
            list.add(m);
        }
        return list;
    }

    public static List<Map> importXlsx(String url) throws FileNotFoundException {
        //url="src/resources/test.xlsx";
        List<Map> list=new ArrayList<Map>();
        File f=new File(url);
        InputStream inputStream= new FileInputStream(f);
        ExcelLogs logs =new ExcelLogs();
        Collection<Map> dataList = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
        for(Map m : dataList){
            list.add(m);
        }
        return list;
    }
    public static List<Map> getData(String excelUrl) {
        List<Map> list = new ArrayList<Map>();
        try{
            if(excelUrl.toLowerCase().contains(".xls")){
                list=importXls(excelUrl);
            }if(excelUrl.toLowerCase().contains(".xlsx")){
                list=importXlsx(excelUrl);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        return list;
    }

    public static String ExportData(List<HashMap> list) {
        String url="";
        return url;
    }
    public static String ExportData(Map m,List<Object> list,String url) {
        try{
            //File f= new File("test.xls");
            String path=System.getProperty("user.dir")+url;
            File f= new File(path);
            if(!f.exists()){
                f.createNewFile();
            }
            OutputStream out = new FileOutputStream(f);
            ExcelUtil.exportExcel(m,list, out );
            out.close();
        }catch (Exception e){e.printStackTrace();}finally{}
        return url;
    }
    public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getExportPath() {
        return exportPath;
    }

    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
    }

    public String getExportPort() {
        return exportPort;
    }

    public void setExportPort(String exportPort) {
        this.exportPort = exportPort;
    }
}
