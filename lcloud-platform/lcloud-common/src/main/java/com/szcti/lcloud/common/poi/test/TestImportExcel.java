/**
 * @author SargerasWang
 */
package com.szcti.lcloud.common.poi.test;

import com.szcti.lcloud.common.poi.ExcelLogs;
import com.szcti.lcloud.common.poi.ExcelUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * 测试导入Excel 97/2003
 */
public class TestImportExcel {

  public void importXls() throws FileNotFoundException {
    File f=new File("src/resources/test.xls");
    InputStream inputStream= new FileInputStream(f);
    ExcelLogs logs =new ExcelLogs();
    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
    for(Map m : importExcel){
      System.out.println(m);
    }
  }

  public void importXlsx() throws FileNotFoundException {
    File f=new File("src/resources/test.xlsx");
    InputStream inputStream= new FileInputStream(f);
    ExcelLogs logs =new ExcelLogs();
    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
    for(Map m : importExcel){
      System.out.println(m);
    }
  }
}
