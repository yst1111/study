package com.yst.app.controller.messTest;


import ch.qos.logback.core.db.DriverManagerConnectionSource;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.FIFOCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.LineIter;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.collection.PartitionIter;
import cn.hutool.core.date.*;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.thread.AsyncUtil;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.*;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.extra.pinyin.PinyinUtil;
//import cn.hutool.extra.pinyin.TinyPinyin;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.yst.entity.pojo.Student;
import com.yst.entity.pojo.TPojo;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.security.KeyPair;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.yst.fira.repo.user.impl.MybatisTestRespository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.ajax.JSON;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.scheduling.annotation.Async;
import org.w3c.dom.Document;

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
    void DbUtils() {
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
    void Hutool() {
        //DateUtil
        int year = DateUtil.year(new Date());
        System.out.println(year);//2022
        String today = DateUtil.today();
        System.out.println("today: " + today);//2022-06-06

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
        System.out.println("formatDate: " + formatDate);
        final LocalDateTime localDateTime = LocalDateTimeUtil.of(date);
        System.out.println("localDateTime: " + localDateTime);// 方便地将Date转换为LocalDateTime

        // 获取一天开始时间
        LocalDateTime localbeginOfDay = LocalDateTimeUtil.beginOfDay(localDateTime);
        DayOfWeek dayOfWeek = localbeginOfDay.getDayOfWeek();
        int dayOfMonth = localbeginOfDay.getDayOfMonth();
//        String formatLocalbeginOfDay = format.format(localbeginOfDay);
        System.out.println("localbeginOfDay " + localbeginOfDay);
        System.out.println("localbeginOfDay-dayOfWeek " + dayOfWeek);
        System.out.println("localbeginOfDay-dayOfMonth " + dayOfMonth);
        // 获取一天结束时间
        LocalDateTime endOfDay = LocalDateTimeUtil.endOfDay(localDateTime);
//        String formatEndOfDay = format.format(endOfDay);
        System.out.println("endOfDay " + endOfDay);

        //IoUtil 复制文件
        BufferedInputStream in = FileUtil.getInputStream("D:/1.jpg");
        BufferedOutputStream out = FileUtil.getOutputStream("D:/2.jpg");

        IoUtil.copy(in, out);


        //StrUtil 字符处理工具
        String strEmpty = " ";
        System.out.println("is empty ?" + StrUtil.isEmpty(strEmpty));//是否为null或空串
        String strBlank = " ";
        boolean blank = StrUtil.isBlank(strBlank);//为null或空串或空白字符
        System.out.println("is Blank ?" + blank);

        String fillAfter = StrUtil.fillAfter(strEmpty, '*', 10);//将字符串用指定字符填充到指定长度 往后填充
        System.out.println("fillAfter " + fillAfter);

        //通过{}占位,书写模板
        String formatStrUtil = StrUtil.format("a的值为{}, b的值为{}", "aaa", "bbb");
        System.out.println(formatStrUtil);

        //isMatch()   ReUtil.isMatch判断文字
        boolean isChinese = ReUtil.isMatch(ReUtil.RE_CHINESES, "走走走");
        System.out.println("isChinese: " + isChinese);

        //集合工具  CollUtil
        //快速创建各种集合 CollUtil.newXXX()
        HashSet<String> hashSetForUtilTest = CollUtil.newHashSet("1", "2", "三");
        List<String> listForUtilTest = CollUtil.newArrayList("1", "2", "5", "8");
        //两个集合取交集
        Collection<String> intersection = CollUtil.intersection(hashSetForUtilTest, listForUtilTest);
        System.out.println("intersection: " + intersection);
        //两个集合取并集
        Collection<String> union = CollUtil.union(hashSetForUtilTest, listForUtilTest);
        System.out.println("union: " + union);
        //两个集合取并集 除公共部分以外得所有
        Collection<String> disjunction = CollUtil.disjunction(hashSetForUtilTest, listForUtilTest);
        System.out.println("disjunction: " + disjunction);
        //判断集合是否为空 CollUtil.isEmpty()
        List<Object> listEmpty = CollUtil.newArrayList();
        System.out.println(CollUtil.isEmpty(hashSetForUtilTest));
        System.out.println(CollUtil.isEmpty(listEmpty));//里面又空串,null都不算Empty

        //CollUtil.edit() 编辑List 这个贼好用
        List<String> list = CollUtil.newArrayList("一", "二", "三");
        List<String> edit = (List<String>) CollUtil.edit(list, ele -> ele + ele);
        Console.log("list: {}", list);
        Console.log("edit: {}", edit);

        //分页工具
        PageUtil.setFirstPageNo(1);//设置初始页码
        List<String> page = ListUtil.page(1, 2, list);
        Console.log("page: {}", page);
        page.clear();//清除
        Console.log("page: {}", page);

        //按属性排序
//        ListUtil.sortByProperty()
//        ListUtil.swapTo
//        ListUtil.swapElement()

        //快速创建Map
        Map<String, Object> mapForUtil = MapUtil.<String, Object>builder()
                .put("key1", "value1")
                .put("key2", "value2")
                .build();
        HashMap<Object, Object> map = MapUtil.newHashMap();
        //MapUtil.of
        HashMap<Object, Object> ofMapUtil = MapUtil.of(new String[][]{
                {"1", "张三"},
                {"2", "李四"},
                {"3", "王五"}
//                {null,"赵四"},
//                {"5",null}
        });
        Console.log("ofMapUtil: ", ofMapUtil);

        //MapUtil.toListMap()
        //MapUtil.toMapList
        String join = MapUtil.join(ofMapUtil, " - ", ":");
        Console.log("join: ", join); //将map按一定格式转为String
        String joinIgnoreNull = MapUtil.joinIgnoreNull(ofMapUtil, " - ", ":");
        Console.log("joinIgnoreNull: ", joinIgnoreNull); //忽略key为null 或者 value为null 的
        //用这个就行了
        String sortJoin = MapUtil.sortJoin(ofMapUtil, StrUtil.SPACE, StrUtil.COLON, true);
        Console.log("sortJoin: ", sortJoin); //忽略key为null 或者 value为null 的


//        MapUtil.edit(mapForUtil,)

        System.out.println("mapForUtil: " + mapForUtil);

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
        System.out.println("IsValidCard: " + IsValidCard);

        String provinceByIdCard = IdcardUtil.getProvinceByIdCard(idCardNum);
        System.out.println(provinceByIdCard);

        String randomCreditCode = CreditCodeUtil.randomCreditCode();
        System.out.println(randomCreditCode);//随机获取社会信用代码
        boolean IsCreditCode = CreditCodeUtil.isCreditCode(randomCreditCode);
        System.out.println(IsCreditCode);

        String pinyin = PinyinUtil.getPinyin("中国");
        System.out.println("中国: " + pinyin);

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
        System.out.println("snowflake: " + snowflake + "\n" +
                "dataCenterId: " + dataCenterId + "\n" +
                "generateDateTime: " + generateDateTime + "\n" +
                "workerId: " + workerId + "\n" +
                "nextId: " + nextId + "\n");
        System.out.println("randomUUID: " + randomUUID);
        System.out.println("simpleUUID: " + simpleUUID);

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
        String pictureName = pictureFormat.format(pictureDate) + substringUUid;

        lineCaptcha.write("D:/ok_dev1.0/app/src/main/resources/direc/zhixian" + pictureName + ".png");
        circleCaptcha.write("D:/ok_dev1.0/app/src/main/resources/direc/yuanquan" + pictureName + ".png");
        shearCaptcha.write("D:/ok_dev1.0/app/src/main/resources/direc/niuqu" + pictureName + ".png");

        //缓存
        FIFOCache<String, Object> cache = CacheUtil.newFIFOCache(1000, 3000 * 10);
        cache.put("a", "1");
        System.out.println(cache.get("a"));

    }

    @Test
    void HutoolExcel() {
        // 将文件转换为ExcelReader  读
        ExcelReader reader = ExcelUtil.getReader("C:/Users/ly-yangst/Desktop/常用工具/客户账户.xlsx");
        System.out.println("reader: " + reader);
        // 读取所有行和列的数据
        List<List<Object>> data = reader.read();
        System.out.println("data: " + data);
        // 读取为Map列表，默认excel第一行为标题行，Map中的key为标题，value为标题对应的单元格值。
        List<Map<String, Object>> dataMap = reader.readAll();
        System.out.println("dataMap: " + dataMap);


        //Writer  写
        ExcelWriter writer = ExcelUtil.getWriter("D:/客户账户.xlsx");
        BigExcelWriter bigWriter = ExcelUtil.getBigWriter("D:/客户账户.xlsx");//大数据量

        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");
        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5); //这个创建太好用了

        bigWriter.write(rows, true);


    }

    //类似Python爬虫
    @Test
    void HutoolHttp() {
        Map<String, Object> params = MapUtil.<String, Object>builder().put("a", 1).build();
        //发送http的get请求
//        String getResult = HttpUtil.get("https://www.baidu.com", params);
//        System.out.println("getResult: "+getResult);//搞出来的是html文件
        //发送http的post请求
//        String postResult = HttpUtil.post("https://www.baidu.com", params);
//        System.out.println("postResult: "+postResult);
        //发送http的application/json的post请求
//        String jsonResult = HttpUtil.post("https://www.baidu.com", JSON.toString(params));
//        System.out.println("jsonResult: "+jsonResult);

        //下载文件
//        String fileUrl = "https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=https%3A%2F%2Fss2.baidu.com%2F-vo3dSag_xI4khGko9WTAnF6hhy%2Fexp%2Fwhcrop%3D160%2C120%2Fsign%3Dee0cbe61f8039245a1e0b74de8e499f3%2F810a19d8bc3eb1351e38ff17a21ea8d3fc1f449a.jpg&thumburl=https%3A%2F%2Fimg2.baidu.com%2Fit%2Fu%3D3265033000%2C577978128%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG%3Fw%3D160%26h%3D120";
        String excelUrl = "http://172.26.165.119:29124/gateway/beiservice/excel/template/download?templateCode=BANK_INFO&tenancyId=98336883-bc5c-11ec-998c-005056a87c64&menuId=957ed1d7d31b422f91056c37b9bd32cd&menuName=%E9%93%B6%E8%A1%8C%E4%BF%A1%E6%81%AF%E7%BB%B4%E6%8A%A4&orgTemplateId=98337ab0-bc5c-11ec-998c-005056a87c64&ClientServer=http%3A%2F%2F172.26.165.119%3A29124";
        //这玩意有点牛逼
        HttpUtil.downloadFile(excelUrl, FileUtil.file("e:/"), new StreamProgress() {
            @Override
            public void start() {
                System.out.println("开始下载");

            }

            @Override
            public void progress(long l, long l1) {
//                System.out.println("下载中，已下载" + FileUtil.readableFileSize(progressSize));
                System.out.println("下载中，已下载" + FileUtil.readableFileSize(FileUtil.file("e:/")));

            }

            @Override
            public void finish() {
                System.out.println("下载完成");

            }
        });

    }

    @Test
    void HutoolJiaMi() {
        String word = "assa";
        String MD5 = SecureUtil.md5(word);//md5是一种不可逆的加密算法
        String sha1 = SecureUtil.sha1(word);
        System.out.println("MD5: " + MD5 + "\n" + "sha1: " + sha1 + "\n");

        System.out.println();
        // 生成非对称密钥对
        KeyPair keyPair = SecureUtil.generateKeyPair("RSA");
        String publicKey = Base64Encoder.encode(keyPair.getPublic().getEncoded());
        String privateKey = Base64Encoder.encode(keyPair.getPrivate().getEncoded());
        // 利用公钥加密
        String encryptBase64 = SecureUtil.rsa(privateKey, publicKey).encryptBase64("abc", KeyType.PublicKey);
        // 利用私钥解密
        String decrypt = new String(SecureUtil.rsa(privateKey, publicKey).decrypt(encryptBase64, KeyType.PrivateKey));
        System.out.println("keyPair: " + keyPair + "\n" +
                "publicKey: " + publicKey + "\n" +
                "privateKey: " + privateKey + "\n" +
                "encryptBase64: " + encryptBase64 + "\n" +
                "decrypt: " + decrypt + "\n");

        //签名和验签
        //创建签名
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        //生成签名
        String wordForSign = "aac";
        byte[] signBytes = wordForSign.getBytes();
        byte[] signed = sign.sign(signBytes);
        //验证签名
        boolean verify = sign.verify(signBytes, signed);
        System.out.println(verify);

    }


    @Test
    public void HutoolConsloe() {
        Console.log("你好{},{},{}", "console", " empoy", "dai!");
        Console.log("hi Console");
        Console.error("error");

        Logger logg = Logger.getLogger(CommonHutoolGuavaTest.class);
        logg.error("你好,log4j {}");

    }

    @Test
    //RandomUtil 随机工具类
    public void HutoolRandom() {
        DateTime dateTime = RandomUtil.randomDay(1, 4);
        Console.log(dateTime);

        //随机从集合中去一个元素
        ArrayList<String> list = CollUtil.newArrayList("1", "2", "3");
        String ele = RandomUtil.randomEle(list);
        Console.log(ele);

        double randomDouble = RandomUtil.randomDouble(2.3, 5);
        Console.log(randomDouble);

        //指定长度的随机数
        String randomNumbers = RandomUtil.randomNumbers(3);
        Console.log(randomNumbers);

        //加权随机数
        List<WeightRandom.WeightObj<String>> weightList = new ArrayList<WeightRandom.WeightObj<String>>();
        WeightRandom.WeightObj<String> a = new WeightRandom.WeightObj<String>("A", 1);
        WeightRandom.WeightObj<String> b = new WeightRandom.WeightObj<String>("B", 2);
        WeightRandom.WeightObj<String> c = new WeightRandom.WeightObj<String>("C", 3);
        WeightRandom.WeightObj<String> d = new WeightRandom.WeightObj<String>("D", 4);
        weightList.add(a);
        weightList.add(b);
        weightList.add(c);
        weightList.add(d);
        WeightRandom wr = RandomUtil.weightRandom(weightList);
        Console.log("wr: {}", wr);
        Console.log(wr.next());  //取随机数
        String str = "";
        int num_a = 0, num_b = 0, num_c = 0, num_d = 0;
        for (int i = 0; i < 100000; i++) {
            str = wr.next().toString();
            switch (str) {
                case "A":
                    num_a = num_a + 1;
                    break;
                case "B":
                    num_b = num_b + 1;
                    break;
                case "C":
                    num_c = num_c + 1;
                    break;
                case "D":
                    num_d = num_d + 1;
                    break;
            }
            //System.out.println(str);
        }
        Console.log("A出现的次数: {}", num_a);
        Console.log("B出现的次数: {}", num_b);
        Console.log("C出现的次数: {}", num_c);
        Console.log("D出现的次数: {}", num_d);
//        RandomUtil.weightRandom();

    }

    @Test
        //秒表工具  用于计时
    void HutoolSecondWatch() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("任务名称");

        // 任务1
        stopWatch.start("任务一");
        Thread.sleep(1000);
        stopWatch.stop();

        // 任务2
        stopWatch.start("任务二");
        Thread.sleep(2000);
        stopWatch.stop();

        // 打印出耗时
        Console.log(stopWatch.prettyPrint());
    }

    @Test
    void newClassWrite() {

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        class pojo implements Serializable {
            private String name;
            private String age;
            private String work;

        }

        pojo pojo = new pojo();
        Console.log(pojo);
        pojo pojo1 = new pojo("张三", "19", "码农");
        Console.log(pojo1);


    }

    @Test
        //读取Classpath下的资源
    void HutoolResourceUtil() throws IOException {

        //ResourceUtil.getUtf8Reader() 读取Resource下的文件
        LineIter lineIter = new LineIter(ResourceUtil.getUtf8Reader("application.yml"));
        PartitionIter<String> iter = new PartitionIter<>(lineIter, 1);
        for (List<String> lines : iter) {
            Console.log(lines);
        }

        List<Integer> list = ListUtil.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 0, 12, 45, 12);
        PartitionIter<Integer> iter1 = new PartitionIter<>(list.iterator(), 1);

        for (List<Integer> lines : iter1) {
            Console.log(lines);
        }

    }

    @Test
    void HutoolThreadUtil() {

        //直接在公共线程池中执行线程
//        ThreadUtil.execute(() -> Console.log("gogogo"));

        //ThreadUtil.concurrencyTest()

        //并发测试
        // 此方法用于测试多线程下执行某些逻辑的并发性能
        // 调用此方法会导致当前线程阻塞
        // 结束后可调用ConcurrencyTester.getInterval() 方法获取执行时间
//        ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
//            long delay = RandomUtil.randomLong(100, 1000);
//            ThreadUtil.sleep(delay);
//            Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
//        });
//        Console.log(tester.getInterval());

//        ConcurrencyTester ct = new ConcurrencyTester(5);
//        for (int i = 0; i < 5; i++) {
//            Console.log("第{}个线程开始执行..",i+1);
//            ct.test(() ->{
//                Console.log("当前执行线程: {} 产生时间 {}",Thread.currentThread().getName(),DateUtil.now());
//                try {
//                    Thread.sleep(RandomUtil.randomInt(1000,3000));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });

        StopWatch yst_is_handsome = new StopWatch("yst is handsome");
        yst_is_handsome.start("yst");
        CompletableFuture<String> yst = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(1, TimeUnit.SECONDS);
            return "yst";
        });
        yst_is_handsome.stop();

        yst_is_handsome.start("is");
        CompletableFuture<String> is = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(2, TimeUnit.SECONDS);
            return " is ";
        });
        yst_is_handsome.stop();

        yst_is_handsome.start("handsome");
        CompletableFuture<String> handsome = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(3, TimeUnit.SECONDS);
            return " handsome";
        });
        yst_is_handsome.stop();

        //等待完成
        AsyncUtil.waitAll(yst, is, handsome);
        //获取结果
        Console.log(AsyncUtil.get(yst) + AsyncUtil.get(is) + AsyncUtil.get(handsome));

        Console.error(yst_is_handsome.prettyPrint());

    }

    @Test
    void HutoolStr(){
        // 包含：制表符、英文空格、不间断空白符、全角空格
        String strCleanBlank = "	 你 好　";
        String cleanBlank = StrUtil.cleanBlank(strCleanBlank);
        Console.log(cleanBlank);

        //把字符串切割成数组
        String strCut = "aaabbbcccdddaadfdfsdfsdf0";
        String[] cut = StrUtil.cut(strCut, 4);
        Console.log("cut: {}",cut);
        List<String> cutList = Arrays.asList(cut);
        Console.log("cutList: {}",cutList);

        //根据符合分隔字符串,可选ignore空,可选Trim
        String strSplit = "a,b ,c,d,,e";
        List<String> splitList = StrUtil.split(strSplit, ',', -1, true, false);
        Console.log("splitList: {}",splitList);
        //切成数组
        String[] splitToArrayString = StrUtil.splitToArray("abc/aa", "/");
        Console.log("splitToArrayString: ",splitToArrayString);

    }

    @Test
    void splitEmpty(){

        String strEmpty = "";
        List<String> splitString = StrUtil.split(strEmpty, ",", -1, true, true);
        Console.log(splitString);

        String strSplitToLong = "1,2,3,4, 5";
        long[] splitToLong = StrUtil.splitToLong(strSplitToLong, ',');
        Console.log(splitToLong);

    }

    @Test
    void splitToIntTest(){

        String strSplitToInt = "1,2,3,4, 5";
        int[] intArray = StrUtil.splitToInt(strSplitToInt, ',');
        Console.log("intArray: {}",intArray);

        //replace 替换
        String replaceString = StrUtil.replace("aabbccdd", 2, 6, '*');
        Console.log(replaceString);

        String replaceString2 = StrUtil.replace("aabbccdd", "b", "00");
        Console.log(replaceString2);



    }

    @Test
    void HutoolUrlXml(){

        //规范化 URL 地址
        String url = "/www.hutool.cn//aaa/bbb";
        String normalize = URLUtil.normalize(url);
        Console.log(normalize);

        //URLUtil.encode()
        String body = "a_副本.jpg";
        String encode = URLUtil.encode(body);
        Console.log(encode);

        String encode1 = URLUtil.encodeQuery(body);
        Console.log(encode1);

        //取出请求的部分
        String path = URLUtil.getPath(normalize);
        Console.log(path);

        //XML读取
        String result = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"//
                + "<returnsms>"//
                + "<returnstatus>Success</returnstatus>"//
                + "<message>ok</message>"//
                + "<remainpoint>1490</remainpoint>"//
                + "<taskID>885</taskID>"//
                + "<successCounts>1</successCounts>"//
                + "</returnsms>";
        Document docResult = XmlUtil.parseXml(result);
        String elementText = XmlUtil.elementText(docResult.getDocumentElement(), "message");
        Console.log(elementText);

        //XML的写
//        Document docResult = XmlUtil.parseXml(result);
//        XmlUtil.toFile(docResult, "e:/aaa.xml", "utf-8");




    }

//    @Test
//    void


}

