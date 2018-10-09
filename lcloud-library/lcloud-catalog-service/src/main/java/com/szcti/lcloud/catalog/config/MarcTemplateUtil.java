package com.szcti.lcloud.catalog.config;

import com.szcti.lcloud.catalog.entity.Book;
import com.szcti.lcloud.common.utils.DateUtils;

public class MarcTemplateUtil {
    public static void setMarcBook(Book book, String code, String value){
        if(code!=null&&!"".equals(code)
                &&book!=null&&value!=null&&!"".equals(value)){
            String[] arry =getValues(value);
            if(code.equals("010")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setIsbn(c.substring(1,c.length()));
                    }
                    if(c.startsWith("b")&& c.length()>1){
                        book.setBinding(c.substring(1,c.length()));
                    }
                    if(c.startsWith("d")&& c.length()>1){
                        book.setPrice(Float.parseFloat(c.substring(1,c.length())));
                    }
                }
            }
            if(code.equals("101")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setLanguage(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("200")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setTitle(c.substring(1,c.length()));
                    }
                    if(c.startsWith("f")&& c.length()>1){
                        book.setFirstDuty(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("205")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setRevision(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("210")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setPublisher(c.substring(1,c.length()));
                    }
                    if(c.startsWith("d")&& c.length()>1){
                        book.setPubdate(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("215")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setPublisher(c.substring(1,c.length()));
                    }
                    if(c.startsWith("d")&& c.length()>1){
                        book.setBookSize(c.substring(1,c.length()));
                    }
                    if(c.startsWith("e")&& c.length()>1){
                        book.setCarrierType(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("225")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setSeriesTitle(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("330")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setSummary(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("606")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setSubjectWord(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("690")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setBookType(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("905")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setCallno(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("510")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        //book.set(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("513")){
                for(String c:arry){
                    if(c.startsWith("a")&& c.length()>1){
                        book.setSecondTitle(c.substring(1,c.length()));
                    }
                }
            }
        }
    }
    private static String[] getValues(String value){
        if(value==null||value.equals("")){
            return null;
        }
        String[] arry=null;
        if(value.contains("■")) {
            arry = value.split("■");
        }
        if(value.contains("@")) {
            arry = value.split("@");
        }
        return arry;
    }
    public static String getMarc(Book book, String code, String value){
        StringBuffer b =new StringBuffer("");
        if(code!=null&&!"".equals(code)
                &&book!=null&&value!=null&&!"".equals(value)){
            if(!value.contains("■")&&!value.contains("@")){
                StringBuffer vc=new StringBuffer();
                for(int c=0;c<value.length();c++){
                        vc.append(value.substring(c,c+1)).append("■");
                }
                value=vc.toString();
            }
            String[] arry =getValues(value);
            String split="■";
            if(value.contains("■")){
                split="■";
            }if(value.contains("@")){
                split="@";
            }
            if(code.equals("010")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getIsbn()==null?"":book.getIsbn());
                    }
                    if(c.startsWith("b")){
                        b.append(split).append("b").append(book.getBinding()==null?"":book.getBinding());
                    }
                    if(c.startsWith("d")){
                        b.append(split).append("d").append(book.getPrice()==null?"":book.getPrice());
                    }
                }
            }
            if(code.equals("101")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getLanguage()==null?"":book.getLanguage());
                    }
                }
            }
            if(code.equals("200")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getTitle()==null?"":book.getTitle());
                    }
                    if(c.startsWith("f")){
                        b.append(split).append("f").append(book.getFirstDuty()==null?"":book.getFirstDuty());
                    }
                }
            }
            if(code.equals("205")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getRevision()==null?"":book.getRevision());
                    }
                }
            }
            if(code.equals("210")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getPublisher()==null?"":book.getPublisher());
                    }
                    if(c.startsWith("d")){
                        b.append(split).append("d").append(book.getPubdate()==null?"":book.getPubdate());
                    }
                }
            }
            if(code.equals("215")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getPages()==null?"":book.getPages());
                    }
                    if(c.startsWith("d")){
                        b.append(split).append("d").append(book.getBookSize()==null?"":book.getBookSize());
                    }
                    if(c.startsWith("e")){
                        book.setCarrierType(c.substring(1,c.length()));
                        b.append(split).append("e").append(book.getCarrierType()==null?"":book.getCarrierType());
                    }
                }
            }
            if(code.equals("225")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getSeriesTitle()==null?"":book.getSeriesTitle());
                    }
                }
            }
            if(code.equals("330")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getSummary()==null?"":book.getSummary());
                    }
                }
            }
            if(code.equals("606")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getSubjectWord()==null?"":book.getSubjectWord());
                    }
                }
            }
            if(code.equals("690")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getBookType()==null?"":book.getBookType());
                    }
                }
            }
            if(code.equals("905")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getCallno()==null?"":book.getCallno());
                    }
                }
            }
            if(code.equals("510")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        //book.set(c.substring(1,c.length()));
                    }
                }
            }
            if(code.equals("513")){
                for(String c:arry){
                    if(c.startsWith("a")){
                        b.append(split).append("a").append(book.getSecondTitle()==null?"":book.getSecondTitle());
                    }
                }
            }
        }
        return b.toString();
    }
}
