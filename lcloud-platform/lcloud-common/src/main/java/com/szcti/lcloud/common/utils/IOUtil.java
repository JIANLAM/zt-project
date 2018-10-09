package com.szcti.lcloud.common.utils;
import java.io.*;
public class IOUtil {
    public static String outPutFile(String str,String url) {
        try{
            String path=System.getProperty("user.dir")+url;
            //FileWriter fwriter = null;
            OutputStreamWriter osw = null;
            try {
                osw = new OutputStreamWriter(new FileOutputStream(path),"GBK");
                osw.write(str);
               /* fwriter = new FileWriter(path);
                fwriter.write(str);*/
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    osw.flush();
                    osw.close();
                    /*fwriter.flush();
                    fwriter.close();*/
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (Exception e){e.printStackTrace();}finally{}
        return url;
    }


}
