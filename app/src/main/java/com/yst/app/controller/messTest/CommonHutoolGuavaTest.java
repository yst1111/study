package com.yst.app.controller.messTest;


import ch.qos.logback.core.db.DriverManagerConnectionSource;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.FIFOCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.*;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.extra.pinyin.PinyinUtil;
//import cn.hutool.extra.pinyin.TinyPinyin;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.yst.entity.pojo.Student;
import com.yst.entity.pojo.TPojo;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.dbcp.*;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.commons.util.IdUtils;

//import org.apache.commons.dbutils.DbUtils;

import javax.imageio.ImageIO;
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
    @SneakyThrows
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

        //isMatch()   ReUtil.isMatch判断文字
        boolean isChinese = ReUtil.isMatch(ReUtil.RE_CHINESES, "走走走");
        System.out.println("isChinese: "+isChinese);

        //集合工具  CollUtil
        //快速创建各种集合 CollUtil.newXXX()
        HashSet<String> hashSetForUtilTest = CollUtil.newHashSet("1", "2", "三");
        List<String> listForUtilTest = CollUtil.newArrayList("1", "2", "5", "8");
        //两个集合取交集
        Collection<String> intersection = CollUtil.intersection(hashSetForUtilTest, listForUtilTest);
        System.out.println("intersection: "+intersection);
        //两个集合取并集
        Collection<String> union = CollUtil.union(hashSetForUtilTest, listForUtilTest);
        System.out.println("union: "+union);
        //两个集合取并集 除公共部分以外得所有
        Collection<String> disjunction = CollUtil.disjunction(hashSetForUtilTest, listForUtilTest);
        System.out.println("disjunction: "+disjunction);
        //判断集合是否为空 CollUtil.isEmpty()
        List<Object> listEmpty = CollUtil.newArrayList();
        System.out.println(CollUtil.isEmpty(hashSetForUtilTest));
        System.out.println(CollUtil.isEmpty(listEmpty));//里面又空串,null都不算Empty

        //快速创建Map
        Map<String, Object> mapForUtil = MapUtil.<String, Object>builder()
                .put("key1", "value1")
                .put("key2", "value2")
                .build();

        System.out.println("mapForUtil: "+mapForUtil);

        //好用的场景

        //根据身份证号获取各种信息
        String idCardNum = "420529199607040033";
        String cityCodeByIdCard = IdcardUtil.getCityCodeByIdCard(idCardNum);
        System.out.println(cityCodeByIdCard);//城市号
        String hide = IdcardUtil.hide(idCardNum, 2, 15);
        System.out.println(hide);//遮挡x~y字符
        String convert15To18 = IdcardUtil.convert15To18(idCardNum);
        System.out.println(convert15To18);
        boolean IsValidCard = IdcardUtil.isValidCard15(idCardNum);
        System.out.println("IsValidCard: "+IsValidCard);

        String provinceByIdCard = IdcardUtil.getProvinceByIdCard(idCardNum);
        System.out.println(provinceByIdCard);

        String randomCreditCode = CreditCodeUtil.randomCreditCode();
        System.out.println(randomCreditCode);//随机获取社会信用代码
        boolean IsCreditCode = CreditCodeUtil.isCreditCode(randomCreditCode);
        System.out.println(IsCreditCode);

        String pinyin = PinyinUtil.getPinyin("中国");
        System.out.println("中国: "+pinyin);

        //将字符生成二维码
//        BufferedImage generate = QrCodeUtil.generate("www.baidu.com", QrConfig.create());
//        ImageIO.write(generate, "png", new File("a.png"));

        //uuid工具
        String randomUUID = IdUtil.randomUUID();
        String simpleUUID = IdUtil.simpleUUID();
        String fastUUID = IdUtil.fastUUID();
        String fastSimpleUUID = IdUtil.fastSimpleUUID();
         //雪花算法
        Snowflake snowflake = IdUtil.createSnowflake(11, 3);
        long dataCenterId = snowflake.getDataCenterId(2);
        long generateDateTime = snowflake.getGenerateDateTime(2);
        long workerId = snowflake.getWorkerId(2);
        long nextId = snowflake.nextId();  //主要方法
        System.out.println("snowflake: "+snowflake+"\n"+
                "dataCenterId: "+dataCenterId+"\n"+
                "generateDateTime: "+generateDateTime+"\n"+
                "workerId: "+workerId+"\n"+
                "nextId: "+nextId+"\n");
        System.out.println("randomUUID: "+randomUUID);
        System.out.println("simpleUUID: "+simpleUUID);

        //全局统一的定时任务调度
        final String schedule = CronUtil.schedule("*/2 * * * * *", (Task) () -> System.out.println("执行定时任务"));
        CronUtil.setMatchSecond(true);
        CronUtil.start();

        System.out.println("post task");

        // 生成线段干扰的验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 5, 3);
        // 生成圆圈干扰的验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(100, 100, 5, 3);
        // 生成扭曲干扰的验证码
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);

        //id生成策略
        Date pictureDate = new Date();
        SimpleDateFormat pictureFormat = new SimpleDateFormat("YYYYMMDDHHMMSS");
        String substringUUid = IdUtil.simpleUUID().substring(0, 4);
        String pictureName = pictureFormat.format(pictureDate)+substringUUid;

        lineCaptcha.write("D:/ok_dev1.0/app/src/main/resources/direc/zhixian"+pictureName+".png");
        circleCaptcha.write("D:/ok_dev1.0/app/src/main/resources/direc/yuanquan"+pictureName+".png");
        shearCaptcha.write("D:/ok_dev1.0/app/src/main/resources/direc/niuqu"+pictureName+".png");

        //缓存
        FIFOCache<String, Object> cache = CacheUtil.newFIFOCache(1000, 3000 * 10);
        cache.put("a","1");
        System.out.println(cache.get("a"));

    }

    @Test
    void HutoolExcel(){
        // 将文件转换为ExcelReader  读
        ExcelReader reader = ExcelUtil.getReader("C:/Users/ly-yangst/Desktop/常用工具/客户账户.xlsx");
        System.out.println("reader: "+reader);
        // 读取所有行和列的数据
        List<List<Object>> data = reader.read();
        System.out.println("data: "+data);
        // 读取为Map列表，默认excel第一行为标题行，Map中的key为标题，value为标题对应的单元格值。
        List<Map<String,Object>> dataMap = reader.readAll();
        System.out.println("dataMap: "+dataMap);


        //Writer  写
        ExcelWriter writer = ExcelUtil.getWriter("D:/客户账户.xlsx");
        BigExcelWriter bigWriter = ExcelUtil.getBigWriter("D:/客户账户.xlsx");//大数据量

        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");
        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5); //这个创建太好用了

        bigWriter.write(rows,true);


    }

    @Test
    void HutoolHttp(){


    }



    }
