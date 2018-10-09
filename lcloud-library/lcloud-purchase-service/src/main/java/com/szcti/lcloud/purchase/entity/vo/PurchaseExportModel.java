/**
 * @author SargerasWang
 */
package com.szcti.lcloud.purchase.entity.vo;

import com.szcti.lcloud.common.poi.ExcelCell;

/**
 * The <code>PurchaseExportModel</code>
 * 
 */
public class PurchaseExportModel {
    @ExcelCell(index = 0)
    private String a;
    @ExcelCell(index = 1)
    private String b;
    @ExcelCell(index = 2)
    private String c;
    @ExcelCell(index = 3)
    private String d;
    @ExcelCell(index = 4)
    private String e;
    @ExcelCell(index = 5)
    private String f;
    @ExcelCell(index = 6)
    private String g;
    @ExcelCell(index = 7)
    private String h;
    @ExcelCell(index = 8)
    private String i;
    @ExcelCell(index = 9)
    private Integer j;
    @ExcelCell(index = 10)
    private String k;


    public PurchaseExportModel(String purchaseCode, String orderCode,String title,
                               String author, String isbn,String bookType,
                               String pubdate,String publisher,String price,Integer bookQty,String totalPrice) {
        this.a = purchaseCode;
        this.b = orderCode;
        this.c = title;
        this.d = author;
        this.e = isbn;
        this.f = bookType;
        this.g = pubdate;
        this.h = publisher;
        this.i = price;
        this.j = bookQty;
        this.k = totalPrice;
    }
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }
}
