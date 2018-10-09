package com.szcti.lcloud.common.utils;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
public class FileUploadUtil {

    //判断导入的文件类型
    public void getFileType(String fileName){
        String[] strArray = fileName.split("\\.");
        int suffixIndex = strArray.length -1;
        if(!"xls".equals(strArray[suffixIndex]) && !"xlsx".equals(strArray[suffixIndex])){
            throw new RuntimeException("不是Excel格式的文件");
        }/*else if(fileName != null || fileName.isEmpty()){
            throw new IOException(filePath + "文件不存在");
        }*/
    }


    //获取项目所在的路径
    String filePath = System.getProperty("user.dir") + "/tmp/import/";
    //String exportPath=System.getProperty("user.dir") + "\\export\\";
    //String filePath="c:\\temp\\";//临时存放目录
    public String uploadStoreFile(MultipartFormDataInput multipartFormDataInput, String fileName)
            throws Exception {
        //获取表单中的数据map
        Map<String, List<InputPart>> dataMaps = multipartFormDataInput.getFormDataMap();

        //根据表单元素名称获取表单元素(需要同前端沟通好表单元素的name)
        List<InputPart> fileParts = dataMaps.get(fileName);//表单元素-文件

        //解析获取表单文件的输入流
        InputStream inputStream;
        String rename;
        try {
            if (fileParts == null || fileParts.isEmpty())
                throw new Exception("请求参数为空!");

            InputPart filePart = fileParts.get(0);
            String preindex=getFileNameByFileInputPart(filePart);
            //preindex=preindex.split(".")[1];
            rename=IdGen.getDateUUId()+preindex;
            inputStream = filePart.getBody(InputStream.class, null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        //保存文件至本地
        String filePathName =filePath+rename;
        System.out.println("upload:"+filePathName);
        //String filePathName = "C:\\temp\\newFile.xls";
        File target = new File(filePathName);
        FileOutputStream fos = null;
        try {
            if (!target.getParentFile().exists())
                target.getParentFile().mkdirs();
            fos = new FileOutputStream(target);
            byte[] b = new byte[1024];
            int readLength;
            while ((readLength = inputStream.read(b)) != -1) {
                fos.write(b, 0, readLength);
            }
        } catch (Exception e) {
            //throw new AssistanceException(e.getMessage());
        } finally {
            if (inputStream != null)
                inputStream.close();
            if (fos != null)
                fos.close();
        }

        return  filePathName;
    }
    /**
     * 从表单文件元素中提取文件名
     *
     * @param filePart
     * @return
     * @throws Exception
     */
    public  String getFileNameByFileInputPart(InputPart filePart) throws Exception {
        String[] contentDispositionHeader = filePart.getHeaders().getFirst("Content-Disposition").split(";");
        for (String fileName : contentDispositionHeader) {
            if ((fileName.trim().startsWith("filename"))) {
                String[] tmp = fileName.split("=");
                String fileNameStr = tmp[1].trim().replaceAll("\"", "");
                return fileNameStr;
            }
        }
        return null;
    }

    /**
     * 从表单元素中获取字串文本并以UTF-8编码
     *
     * @param inputPart
     * @return
     * @throws Exception
     */
    public static String getInputPartAsString(InputPart inputPart) throws Exception {
        if (inputPart == null)
            return null;
        String nameString = inputPart.getBodyAsString();
        if (nameString == null || nameString.isEmpty())
            return null;
        return URLDecoder.decode(nameString, StandardCharsets.UTF_8.name());
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}