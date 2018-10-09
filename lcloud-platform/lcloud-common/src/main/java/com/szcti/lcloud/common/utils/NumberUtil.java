package com.szcti.lcloud.common.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/9/5 14:32
 */
public class NumberUtil {
    /**
　　* 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
　　* @param sourceDate
　　* @param formatLength
　　* @return 重组后的数据
　　*/
    public static String frontCompWith0(int sourceDate,int formatLength){
        /**
     　　* 0 指前面补充零
     　　* formatLength 字符总长度为 formatLength
     　　* d 代表为正数。
     　　*/
        String newString = String.format("%0"+formatLength+"d", sourceDate);
        return newString;
    }

    /**
     * 保留小数点后面n位小数
     * @param value
     * @param formatLength 小数位数
     * @return
     */
    public static String decimalSup0(double value,int formatLength) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(formatLength, RoundingMode.HALF_UP);
        return bd.toString();

    }

    public static void main(String[] args) throws IOException {
        String s = decimalSup0(12.0,5);
        System.out.println(s);
    }

}
