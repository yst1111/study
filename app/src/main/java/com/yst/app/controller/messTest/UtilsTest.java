package com.yst.app.controller.messTest;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @creator: ly-yangst
 * @date: 2022/5/10
 */
public class UtilsTest {

    public static void main(String[] args) {
        //Scanner 类
        Scanner scanner = new Scanner(System.in);
        System.out.println("next 方式接收: ");
        if (scanner.hasNext()) {
            String next = scanner.next();
            System.out.println("输入数据为: " + next);
        }


    }

    @Test
    void dateTest(){
        //是当时时间
        Date date = new Date();
        System.out.println(date);
        //按秒来创建时间
        Date date1 = new Date(1000000000000L);
        System.out.println(date1);
        //按年月日时分秒,创建Date 年是1900+,月是从0开始加的
        Date date2 = new Date(122, 5, 10,13,12,10);
        System.out.println(date2);

        //DateFormat 允许日期格式化,date <-> String ,hh表示12小时制,HH表示24小时制
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date3 = new Date();
        String format = fm.format(date3);
        System.out.println(format);
    }

    @Test
    void systemTest(){

    }

    @Test
    void number(){

        Integer x = -5;
        System.out.println(x.toString());
        //Math.abs() 获取绝对值
        double abs = Math.abs(-5.4);
        System.out.println("Math.abs "+abs);

        //Math.ceil() double向右取整, Math.floor() double向左取整
        //Math.rint() 四舍五入
        double floor = Math.floor(12.5);
        double ceil = Math.ceil(12.5);
        double rint = Math.rint(12.54);
        System.out.println("ceil "+ ceil+" floor"+floor+" rint "+rint);

    }

    @Test
    void math(){

        //xxxValue()
        Integer x = 5;
        Long longType = 12L;

        System.out.println(x.intValue());
        long l = x.longValue();
        System.out.println("long类型: "+l);
        short i = x.shortValue();
        System.out.println("short类型: "+i);
        String string = "str";

        //compareTo 输出的int,1为大,-1为小,0为相等
        int compareTo = x.compareTo(4);
        int compareTo1 = longType.compareTo(12L);
        System.out.println("compareTo: "+compareTo1);

        //equals()
        boolean equals = x.equals(12);
        System.out.println("ifEquals: "+equals);

        //valueOf用法  各种数据类型之间的转换,String <-> Long,Integer,int
        String s = String.valueOf(1);
        Integer integer = Integer.valueOf(1);
        Long aLong = Long.valueOf(1000000);
        Integer integer1 = Integer.valueOf("12");
        System.out.println(s+integer+aLong+integer1);
        //Integer.parseInt() 和 Integer.valueOf() 效果相同
        int parse = Integer.parseInt("12");
        System.out.println("parse: "+parse);

    }


}
