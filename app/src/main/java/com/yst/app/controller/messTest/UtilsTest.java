package com.yst.app.controller.messTest;

import com.baomidou.mybatisplus.extension.api.R;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        long round = Math.round(-1.3);
        int max = Math.max(1, 3);
        int min = Math.min(-1, -8);
        double exp = Math.exp(1);//e的1次方
        double sqrt = Math.sqrt(2);//2的算术平方根
        double sin = Math.sin(90);

        double random = Math.random(); //
        System.out.println("ceil "+ ceil+" floor"+floor+" rint "+rint+" round "+round);
        System.out.println("max: "+max);
        System.out.println("min: "+min);
        System.out.println("exp: "+exp);
        System.out.println("sqrt: "+sqrt);
        System.out.println("sin: "+sin);
        System.out.println("random "+random);

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

    @Test
    //格式化数字
    void numberFormat(){
        //DecimalFormat #数字占位,0用0占位,.小数点,,逗号分隔,%显示百分值 \u2030表示千分值
        DecimalFormat format = new DecimalFormat("0000.##\u2030");
        String num1 = format.format(123.12);
        System.out.println("123.12: "+num1);
        //科学计数法
        DecimalFormat format1 = new DecimalFormat("0.##E0");
        String num2 = format1.format(12345);
        System.out.println("num2: "+num2);
        //货币 人民币
        DecimalFormat format2 = new DecimalFormat("\u00A4#0.##");
        String num3 = format2.format(0.3333);
        System.out.println("num3 "+num3);
        //$
        DecimalFormat format3 = new DecimalFormat(Currency.getInstance(Locale.JAPAN)+"$00.00");
        String money = format3.format(100000);
        System.out.println("money "+money);

    }

    @Test
    //Random 随机生成(各种数据类型的数字)
    void random(){
        Random random = new Random();
        //随机生成 true,false
        boolean ranBoolean = random.nextBoolean();
        System.out.println(ranBoolean);

        System.out.println(random.nextDouble()*5);
        System.out.println(random.nextLong());//随机生成Long
        System.out.println(random.nextInt(101));//随机int 0~101-1
        System.out.println(random.nextDouble());

        Random random1 = new Random();
        System.out.println(random1.nextInt(11));


    }

    @Test
    //List
    void List(){
        String[] arr = {"1","2","san","四"};
        List<String> list = Arrays.asList(arr);
        ArrayList<String> list0 = new ArrayList<>(list);//用这种方式,使得Arrays.asList来的list可以add()
        ArrayList<String> arrList = new ArrayList<>();

        //list.isEmpty()
        boolean empty = list.isEmpty();
        System.out.println(empty);

        //add
        String a = "1";
        arrList.add(a); //new出来的ArrayList可以扩容,而Array.asList来的ArrayList不可扩容
        arrList.add(0,"0");
        list0.add("2");
        System.out.println(arrList);
        System.out.println(list0);

        //iterator() 返回迭代器,按顺序
        Iterator<String> iterator = list0.iterator();
        while (iterator.hasNext()) {
            System.out.println("it --"+iterator.next());
        }

        ListIterator<String> listIterator = list0.listIterator(1);
        while (listIterator.hasNext()) {
            System.out.println("itList --"+listIterator.next());
        }

        //lastIndexOf() 最后一次出现的索引
        int last1 = list0.lastIndexOf("1");
        int last4 = list0.lastIndexOf("4");
        System.out.println(last1+" "+last4);

        //list的hashCode() 来判断两个list是否相同
        int list0Code = list0.hashCode();
        int listCode = list.hashCode();

        System.out.println("if hashcode equal ? "+(list0Code == listCode));

        //清空list
        System.out.println("begin "+list0);
//        list0.clear();
        System.out.println("after "+list0);

        //contains() 判断是否包含 某元素
        System.out.println("contains 1 ? "+list0.contains("1"));
        System.out.println("contains 8 ? "+list0.contains("8"));

        //containsAll() 一个list包含另一个list ?
        System.out.println("list0"+list0);
        System.out.println("list"+list);
        System.out.println("list0 containsAll list ? "+list0.containsAll(list));

        //equals() 与比较hashCode() 相同
        System.out.println("list == list ? "+list.equals(list));
        System.out.println("list == list0 ? "+list.equals(list0));

        //get()
        System.out.println("list.get(0) "+list.get(0));

        //set()
        System.out.println(list0.set(1, "21"));
        System.out.println(list0);

        //size()
        System.out.println(list0.size());

        //subList()截取两个index之间的元素,组成新list
        List<String> sublist = list0.subList(1, 2);
        System.out.println(sublist);

        //toArray
        Object[] objects = list0.toArray();
        System.out.println(objects);
        for (Object object : objects) {
            System.out.println(object);
        }
            //用来将object[] 转化为 String[]
        String[] array = list0.toArray(new String[1]);
        System.out.println(array);
        System.out.println("--------------------");
        System.out.println("toArray");
        for (String s : array) {
            System.out.println(s);
        }

    }

    @Test
    //Map
    void MapTest(){
        // 一个<String,Object>类型的 HashMap value可以存各种数据类型
        HashMap<String, Object> map = new HashMap<>();
        map.put("a",1);
        map.put("b","2");
        System.out.println(map);

        //clear()
        System.out.println("---clear---");
//        map.clear();
        System.out.println(map);
        System.out.println("------");

        //compute() 将一个element插入map,map的key与element比较,新value存在则返回新v,新v为空则删除map中这一组
        //数组中元素的重复性
        Integer[] intArr = {1,2,3,1,4};
        String[] stringArr = {"1","2","3","1","4"};
        Map<Integer, Integer> mapInteger = new HashMap<>();
        Map<String, Integer> mapString = new HashMap<>();
        Arrays.asList(stringArr).forEach(element ->{
            mapString.compute(element,(k,v) -> {
                //定义新value如何返回
                if(v == null){
                    return 1;
                }else {
                    return v+1;
                }
            });
        });

        System.out.println("----key is stringArr, value is num----");
        mapString.forEach((k,v) -> System.out.println(k+" "+v));

        //删除某元素
        Map<String,String> mapForBase = new HashMap<>();
        mapForBase.put("1","A");
        mapForBase.put("2","B");
        mapForBase.put("5","C");
        mapForBase.put("6","D");
        Arrays.asList(stringArr).forEach(ele -> {
            mapForBase.compute(ele,(k,v) ->{
                return null;
            });
        });
        System.out.println("----remove what stringArr have ---");
        mapForBase.forEach((k,v)-> System.out.println(k+" "+v));

        System.out.println("----remove '5'---");
        mapForBase.compute("5",(k,v) ->{
            return null;
        });
        mapForBase.forEach((k,v)-> System.out.println(k+" "+v));

        //List去重
        System.out.println("---- listForDedup ---");
        ArrayList<String> listForDedup = new ArrayList<>();
        listForDedup.add("1");
        listForDedup.add("2");
        listForDedup.add("1");

    }


}
