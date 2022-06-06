package com.yst.app.controller.messTest;


import ch.qos.logback.core.db.DriverManagerConnectionSource;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.yst.entity.pojo.Student;
import com.yst.entity.pojo.TPojo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.dbcp.*;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.junit.jupiter.api.Test;

//import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;

/**
 * @creator: ly-yangst
 * @date: 2022/6/6
 */
public class CommonHutoolGuavaTest {

    public static void main(String[] args) {

        //apache Commons

        //BeanUtils.cloneBean 对Bean进行克隆
        Student student = new Student("1", "zs", 21, "中国", "吃饭", "睡觉");
        TPojo<Student> studentTPojo = TPojo.build(student);
        System.out.println("studentTPojo: " + studentTPojo);

        TPojo<Student> studentTPojo1 = new TPojo<>();
        try {
            studentTPojo1 = (TPojo<Student>) BeanUtils.cloneBean(studentTPojo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("studentTPojo1: " + studentTPojo1);

        //BeanUtils.populate将Map转为Bean,但Map的key必须与Bean的字段一致
        Map hashMap = new HashMap();
        hashMap.put("id", "1");
        hashMap.put("age", 1);
        Student studentForMap = new Student();
        try {
            BeanUtils.populate(studentForMap, hashMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("populate map: " + hashMap);
        System.out.println("studentForMap: " + studentForMap.getId());

        //Bean，并将它转化为XML
//        StringWriter outputWriter = new StringWriter();
//        BeanWriter beanWriter = new BeanWriter(outputWriter);

        //将XML转化为JavaBean
//        BeanReader beanReader  = new BeanReader();

        //Codec,公共的编解码实现，比如Base64, Hex, MD5,Phonetic and URLs等等。

    }

    @Test
    void Collections() throws ClassNotFoundException {
        //apache Commons

        //与HashMap比较
        Map map = new HashMap<>();
        map.put("map-key1", "value1");
        map.put("map-key2", "value2");
        map.put("map-key3", "value3");
        for (int i = 0; i < map.entrySet().size(); i++) {

        }

        //Commons-LinkedMap
        LinkedMap linkedMap = new LinkedMap();
        linkedMap.put("a", "value1");
        linkedMap.put("b", "value2");
        linkedMap.put("c", "value1");
        linkedMap.forEach((k, v) -> linkedMap.put(k, v + "0"));
        linkedMap.forEach((k, v) -> System.out.println(k + " " + v));
        for (int i = 0; i < linkedMap.entrySet().size(); i++) {
            System.out.println(linkedMap.get(i));
            System.out.println(linkedMap.getValue(i));
        }

        System.out.println("linkedMap: " + linkedMap);
        Object o = linkedMap.firstKey();
        System.out.println("firstKey: " + o);//获取首个key
        System.out.println(linkedMap.nextKey("b"));//"b"的下一个key是?

        //Commons-TreeBidiMap
        TreeBidiMap treeBidiMap = new TreeBidiMap();
        treeBidiMap.put("key1", "value1");
        treeBidiMap.put("key2", "value2");
        System.out.println("treeBidiMap: " + treeBidiMap);

        Object value1 = treeBidiMap.get("key1");
        Object key1 = treeBidiMap.getKey("value1"); //方便获取key和value
        System.out.println("value1: " + value1 + "\n" + "key1: " + key1);
        BidiMap bidiMap = treeBidiMap.inverseBidiMap();//key和value调换
        System.out.println(bidiMap);

        //CollectionUtils.retainAll 取两个list的公共部分
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("1");
        list1.add("3");
        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("5");
        Collection retain = CollectionUtils.retainAll(list1, list2);
        System.out.println(retain);

        //Compress 打包,压缩库类

        //Configuration 配置文件的处理
        //DBCP 数据库连接池
        System.out.println("加载jdbc驱动..");
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Done ..");
        System.out.println("设置数据库");
        DataSource dataSource = setupDataSource("jdbc:mysql://192.168.41.188:3306/test411");
        System.out.println("Done ..");


    }

    public static DataSource setupDataSource(String connectURI) {
//        //设置连接地址
//        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI, null);
//        //创建连接工厂
//        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(null,connectionFactory);
//        //获取连接池实例
//        ObjectPool objectPool = new GenericObjectPool(poolableConnectionFactory);
//        //创建poolingDriver
//        PoolingDataSource dataSource = new PoolingDataSource(objectPool);
//
//        return dataSource;
        return null;
    }

    @Test
    void DbUtils(){
//        Connection conn = null;
//        String url = "jdbc:mysql://192.168.41.188:3306/test411";
//        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
//        String user = "root";
//        String password = "root";
//
//        DbUtils.loadDriver(jdbcDriver);

    }

    @Test
    void Email() {
//        Email email = new SimpleEmail();
//        email.setHostName("smtp.googlemail.com");
//        email.setSmtpPort(465);
//        email.setAuthenticator(new DefaultAuthenticator("username", "password"));
//        email.setSSLOnConnect(true);
//        email.setFrom("user@gmail.com");
//        email.setSubject("TestMail");
//        email.setMsg("This is a test mail ... :-)");
//        email.addTo("foo@bar.com");
//        email.send();
    }

    //HttpClient

    /*
    * 难得Hutool
    */
    @Test
    void Hutool(){
        //DateUtil
        int year = DateUtil.year(new Date());
        System.out.println(year);//2022
        String today = DateUtil.today();
        System.out.println("today: "+today);//2022-06-06

        String chineseZodiac = DateUtil.getChineseZodiac(year);
        System.out.println(chineseZodiac); //生肖: 虎

        String formatBetween = DateUtil.formatBetween(12323232);//毫秒变 小时制
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format1 = format.format(formatBetween);
//        System.out.println(format1);
        System.out.println(formatBetween);//3小时25分23秒232毫秒

        // 转为农历日期
        ChineseDate chineseDate = new ChineseDate(new Date());
        System.out.println(chineseDate);

        // 农历年份，如2021
        final int chineseYear = chineseDate.getChineseYear();
        // 农历月份，如腊月
        final String chineseMonthName = chineseDate.getChineseMonthName();
        // 农历日期，如初三
        final String chineseDay = chineseDate.getChineseDay();
        // 方便地将Date转换为LocalDateTime
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String formatDate = format.format(date);
        System.out.println("formatDate: "+formatDate);
        final LocalDateTime localDateTime = LocalDateTimeUtil.of(date);
        System.out.println("localDateTime: "+localDateTime);// 方便地将Date转换为LocalDateTime

        // 获取一天开始时间
        LocalDateTime localbeginOfDay = LocalDateTimeUtil.beginOfDay(localDateTime);
        DayOfWeek dayOfWeek = localbeginOfDay.getDayOfWeek();
        int dayOfMonth = localbeginOfDay.getDayOfMonth();
//        String formatLocalbeginOfDay = format.format(localbeginOfDay);
        System.out.println("localbeginOfDay "+localbeginOfDay);
        System.out.println("localbeginOfDay-dayOfWeek "+dayOfWeek);
        System.out.println("localbeginOfDay-dayOfMonth "+dayOfMonth);
        // 获取一天结束时间
        LocalDateTime endOfDay = LocalDateTimeUtil.endOfDay(localDateTime);
//        String formatEndOfDay = format.format(endOfDay);
        System.out.println("endOfDay "+endOfDay);

        //IoUtil 复制文件
        BufferedInputStream in = FileUtil.getInputStream("D:/1.jpg");
        BufferedOutputStream out = FileUtil.getOutputStream("D:/2.jpg");

        IoUtil.copy(in,out);


        //StrUtil 字符处理工具
        String strEmpty = " ";
        System.out.println("is empty ?"+StrUtil.isEmpty(strEmpty));//是否为null或空串
        String strBlank = " ";
        boolean blank = StrUtil.isBlank(strBlank);//为null或空串或空白字符
        System.out.println("is Blank ?"+blank);

        String fillAfter = StrUtil.fillAfter(strEmpty, '*', 10);//将字符串用指定字符填充到指定长度 往后填充
        System.out.println("fillAfter "+fillAfter);

        //通过{}占位,书写模板
        String formatStrUtil = StrUtil.format("a的值为{}, b的值为{}", "aaa", "bbb");
        System.out.println(formatStrUtil);

        //isMatch()


    }



    }
