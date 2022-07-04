package com.yst.app.controller.messTest;


import org.hibernate.validator.internal.util.StringHelper;
import org.testng.annotations.Test;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    void dateTest() {
        //是当时时间
        Date date = new Date();
        System.out.println(date);
        //按秒来创建时间
        Date date1 = new Date(1000000000000L);
        System.out.println(date1);
        //按年月日时分秒,创建Date 年是1900+,月是从0开始加的
        Date date2 = new Date(122, 5, 10, 13, 12, 10);
        System.out.println(date2);

        //DateFormat 允许日期格式化,date <-> String ,hh表示12小时制,HH表示24小时制
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date3 = new Date();
        String format = fm.format(date3);
        System.out.println(format);
    }

    private static final String PROPERTIES = "pro";
    @Test
    void systemTest() {
        Long a = 1L;
        String s = a.toString().trim();
        System.out.println(s);
        int pro = 9;
        String str = "str";
        System.out.println("is str null or empty ? "+StringHelper.isNullOrEmptyString(str));

        boolean empty = "as".isEmpty();
        System.out.println("str is String ? ");

        String inEvaluate = "  cdc  ";
        String evaluate = evaluate(inEvaluate);

        Boolean aTrue = Boolean.valueOf(System.getProperty(String.valueOf(pro)));
        System.out.println(aTrue);

    }

    @Test
    void number() {

        Integer x = -5;
        System.out.println(x.toString());
        //Math.abs() 获取绝对值
        double abs = Math.abs(-5.4);
        System.out.println("Math.abs " + abs);

        //Math.ceil() double向右取整, Math.floor() double向左取整
        //Math.rint() 四舍五入
        double floor = Math.floor(12.5);
        double ceil = Math.ceil(12.5);
        double rint = Math.rint(12.54);//就近
        long round = Math.round(-1.3);//四舍五入
        int max = Math.max(1, 3);
        int min = Math.min(-1, -8);
        double exp = Math.exp(1);//e的1次方
        double sqrt = Math.sqrt(2);//2的算术平方根
        double sin = Math.sin(90);

        double random = Math.random(); //
        System.out.println("ceil " + ceil + " floor" + floor + " rint " + rint + " round " + round);
        System.out.println("max: " + max);
        System.out.println("min: " + min);
        System.out.println("exp: " + exp);
        System.out.println("sqrt: " + sqrt);
        System.out.println("sin: " + sin);
        System.out.println("random " + random);

    }

    @Test
    void math() {

        //xxxValue()
        Integer x = 5;
        Long longType = 12L;
        String name = x.getClass().getName();
        System.out.println("x.name : "+name);
        System.out.println(x.intValue());
        long l = x.longValue();
        System.out.println("long类型: " + l);
        short i = x.shortValue();
        System.out.println("short类型: " + i);
        String string = "str";

        //compareTo 输出的int,1为大,-1为小,0为相等
        int compareTo = x.compareTo(4);
        int compareTo1 = longType.compareTo(12L);
        System.out.println("compareTo: " + compareTo1);

        //equals()
        boolean equals = x.equals(12);
        System.out.println("ifEquals: " + equals);

        //valueOf用法  各种数据类型之间的转换,String <-> Long,Integer,int
        String s = String.valueOf(1);
        Integer integer = Integer.valueOf(1);
        Long aLong = Long.valueOf(1000000);
        Integer integer1 = Integer.valueOf("12");
        System.out.println(s + integer + aLong + integer1);
        //Integer.parseInt() 和 Integer.valueOf() 效果相同
        int parse = Integer.parseInt("12");
        System.out.println("parse: " + parse);

    }

    @Test
        //格式化数字
    void numberFormat() {
        //DecimalFormat #数字占位,0用0占位,.小数点,,逗号分隔,%显示百分值 \u2030表示千分值
        DecimalFormat format = new DecimalFormat("0000.##\u2030");
        String num1 = format.format(123.12);
        System.out.println("123.12: " + num1);
        //科学计数法
        DecimalFormat format1 = new DecimalFormat("0.##E0");
        String num2 = format1.format(12345);
        System.out.println("num2: " + num2);
        //货币 人民币
        DecimalFormat format2 = new DecimalFormat("\u00A4#0.##");
        String num3 = format2.format(0.3333);
        System.out.println("num3 " + num3);
        //$
        DecimalFormat format3 = new DecimalFormat(Currency.getInstance(Locale.JAPAN) + "$00.00");
        String money = format3.format(100000);
        System.out.println("money " + money);

    }

    @Test
        //Random 随机生成(各种数据类型的数字)
    void random() {
        Random random = new Random();
        //随机生成 true,false
        boolean ranBoolean = random.nextBoolean();
        System.out.println(ranBoolean);

        System.out.println(random.nextDouble() * 5);
        System.out.println(random.nextLong());//随机生成Long
        System.out.println(random.nextInt(101));//随机int 0~101-1
        System.out.println(random.nextDouble());

        Random random1 = new Random();
        System.out.println(random1.nextInt(11));


    }

    @Test
        //List
    void List() {

        //List.of
        
        
        String[] strArr = {"1","2","3","5"};
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i]);
        }

//        int a1 = 2;
//        int i = a1 >> 1;
//        System.out.println(i);
//
//        if (a1 > 2)
//            System.out.println("a1 > 2");
//        else
//            System.out.println("a1 <= 2");

        String[] arr = {"1", "2", "san", "四"};
//        String[] copy = new String[10];
//        System.arraycopy(arr,1,copy,3,3);
//        System.out.println("-- System.arraycopy --");
//        for (String s : copy) {
//            System.out.println(s);
//        }

        List<String> list = Arrays.asList(arr);
        boolean b = list instanceof List;
        System.out.println("list is ArrayList ? "+b);
        System.out.println("---get---");
        System.out.println(list.get(0));
        //list的clone

        ArrayList<String> list0 = new ArrayList<>(list);//用这种方式,使得Arrays.asList来的list可以add()
        ArrayList<String> arrList = new ArrayList<>();

        //list.isEmpty()
        boolean empty = list.isEmpty();
        System.out.println(empty);

        //add
        String a = "1";
        arrList.add(a); //new出来的ArrayList可以扩容,而Array.asList来的ArrayList不可扩容
        arrList.add(0, "0");
        list0.add("2");
        System.out.println(arrList);
        System.out.println(list0);

        //iterator() 返回迭代器,按顺序
        Iterator<String> iterator = list0.iterator();
        while (iterator.hasNext()) {
            System.out.println("it --" + iterator.next());
        }

        ListIterator<String> listIterator = list0.listIterator(1);
        while (listIterator.hasNext()) {
            System.out.println("itList --" + listIterator.next());
        }

        //lastIndexOf() 最后一次出现的索引
        int last1 = list0.lastIndexOf("1");
        int last4 = list0.lastIndexOf("4");
        System.out.println(last1 + " " + last4);

        //list的hashCode() 来判断两个list是否相同
        int list0Code = list0.hashCode();
        int listCode = list.hashCode();

        System.out.println("if hashcode equal ? " + (list0Code == listCode));

        //清空list
        System.out.println("begin " + list0);
//        list0.clear();
        System.out.println("after " + list0);

        //contains() 判断是否包含 某元素
        System.out.println("contains 1 ? " + list0.contains("1"));
        System.out.println("contains 8 ? " + list0.contains("8"));

        //containsAll() 一个list包含另一个list ?
        System.out.println("list0" + list0);
        System.out.println("list" + list);
        System.out.println("list0 containsAll list ? " + list0.containsAll(list));

        //equals() 与比较hashCode() 相同
        System.out.println("list == list ? " + list.equals(list));
        System.out.println("list == list0 ? " + list.equals(list0));

        //get()
        System.out.println("list.get(0) " + list.get(0));

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
    void MapTest() {
        // 一个<String,Object>类型的 HashMap value可以存各种数据类型
        //initialCapacity 乘以 0.75 = 扩容大小,合理的initialCapacity可以节省性能
        HashMap<String, Object> map = new HashMap<>(8, 1);
        //put()
        map.put("a", 1);
        map.put("b", "2");
        //putAll() 将一个map放入另一个map
        System.out.println("---putAll---");
        HashMap<String, Object> mapForPutAll = new HashMap<>();
        mapForPutAll.put("e", "6");
        mapForPutAll.put("f", "7");
        map.putAll(mapForPutAll);
        System.out.println(map);
        //putIfAbsent() 如果存在这个key,则不操作,如果不存在,则插入这个k,v   和computeIfAbsent() 异曲同工
        System.out.println("---putIfAbsent---");
        map.putIfAbsent("g", "8");
        map.putIfAbsent("e", "9");//已存在"e"
        System.out.println(map);
        //containsKey()
        System.out.println("key has 't' ? " + map.containsKey("t"));
        System.out.println("Value has '2' ? " + map.containsValue("2"));

        //map.get()
        System.out.println("---map.get---");
        System.out.println("---map.remove---");
        map.remove("a");
        System.out.println(map);
        System.out.println(map.get("a"));
        System.out.println(map);
        //clone() ,拷贝完之后,两个map就没关系了
        System.out.println("---map.clone---");
        HashMap<String, Object> clone = (HashMap<String, Object>) map.clone();
        System.out.println(clone);
        clone.put("c", "3");
        map.put("d", "4");
        System.out.println("clone " + clone + " map " + map);
        //replace()

        //getOrDefault 输入指定key,有则返回v,无则返回default
        System.out.println("---map.getOrDefault---");
        System.out.println(map.getOrDefault("b", "没有"));
        System.out.println(map.getOrDefault("aa", "没有"));

        //map.forEach
        System.out.println("---map.forEach---");
        map.put("qw", null);
        map.put("oo", null);
        System.out.println(map);

        map.forEach((k, v) -> {
            //不能改变map
            v = "1";
        });
        System.out.println(map);

        //entrySet() 包含key和value,可以通过entry去获取 key和value,进行操作
        System.out.println("---map.entrySet---");
        System.out.println(map.entrySet());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry);
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        //keySet()获取key的集合
        System.out.println("---map.keySet---");
        System.out.println(map.keySet());
        //values()获取value的集合
        System.out.println("---map.values---");
        System.out.println(map.values());

        //merge() key存在则 公式操作 , key 不存在 则 本new v
        System.out.println("---map.merge()---");
        map.merge("b","new v",(ov,nv)-> nv+" 1 "+ov);//存在,则 操作
        map.merge("ewe","new v",(ov,nv)-> nv+" 1 "+ov);//不存在则插入
        System.out.println(map);

        //clear()
        System.out.println("---clear---");
//        map.clear();
        System.out.println(map);
        System.out.println("------");

        //compute() 将一个element与map的key进行比较,map的key与element比较,新value存在则返回新v,新v为空则删除map中这一组
        //数组中元素的重复性
        Integer[] intArr = {1, 2, 3, 1, 4};
        String[] stringArr = {"1", "2", "3", "1", "4"};
        Map<Integer, Integer> mapInteger = new HashMap<>();
        Map<String, Integer> mapString = new HashMap<>();
        Arrays.asList(stringArr).forEach(element -> {
            mapString.compute(element, (k, v) -> {
                //定义新value如何返回
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }
            });
        });

        System.out.println("----key is stringArr, value is num----");
        mapString.forEach((k, v) -> System.out.println(k + " " + v));

        //删除某元素
        Map<String, String> mapForBase = new HashMap<>();
        mapForBase.put("1", "A");
        mapForBase.put("2", "B");
        mapForBase.put("5", "C");
        mapForBase.put("6", "D");
        Arrays.asList(stringArr).forEach(ele -> {
            mapForBase.compute(ele, (k, v) -> {
                return null;
            });
        });
        System.out.println("----remove what stringArr have ---");
        mapForBase.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("----remove '5'---");
        mapForBase.compute("5", (k, v) -> {
            return null;
        });
        mapForBase.forEach((k, v) -> System.out.println(k + " " + v));

        //List去重
        System.out.println("---- listForDedup ---");
        ArrayList<String> listForDedup = new ArrayList<>();
        listForDedup.add("1");
        listForDedup.add("2");
        listForDedup.add("1");
        //转为Set<String> 或者 stream().distinct()去重
        Set<String> setDistinct = listForDedup.stream().collect(Collectors.toSet());
        System.out.println(setDistinct);

        //computeIfAbsent()  就是 putIfAbsent()
        System.out.println("---- computeIfAbsent ---");
        HashMap<String, String> mapForComputeIfAbsent = new HashMap<>();
        mapForComputeIfAbsent.put("foot", "ball");
        mapForComputeIfAbsent.put("baseket", "ball");
        mapForComputeIfAbsent.put("fly", "man");
        System.out.println(mapForComputeIfAbsent);
        //输入不存在的key,将key,value插入进去
        String only = mapForComputeIfAbsent.computeIfAbsent("only", key -> "one");
        System.out.println("only " + only);
        System.out.println(mapForComputeIfAbsent);
        //输入已存在的key,不对map产生任何影响
        System.out.println(mapForComputeIfAbsent.computeIfAbsent("foot", k -> "ba"));
        System.out.println("exist key " + mapForComputeIfAbsent);

        mapForComputeIfAbsent.put(null, "1");
        mapForComputeIfAbsent.put(null, "2");
        System.out.println("map的键可以为空,但只能有一个null键 " + mapForComputeIfAbsent);


    }

    @Test
    void Set(){
        //无序不重复
        HashSet<java.lang.String> stringSet = new HashSet<>();
        //add()
        stringSet.add("1");
        stringSet.add("1");
        stringSet.add("二");
        stringSet.add("三");
        System.out.println("--- add ---");
        System.out.println("stringSet: "+stringSet);
        System.out.println();

        //可以通过addAll实现 set和get的转换
        List<java.lang.String> listForAdd = new ArrayList<>();
        listForAdd.add("1");
        listForAdd.add("四");
        listForAdd.add("五");
        listForAdd.addAll(stringSet);
        System.out.println("--- set -> list ---");
        System.out.println("listForAdd : "+listForAdd);
        System.out.println();

        //addAll()  可以加set
        stringSet.addAll(listForAdd);
        System.out.println("--- addAll ---");
        System.out.println("stringSet -- addAll : "+stringSet);
        System.out.println();

        //contains
        System.out.println("--- contains ---");
        System.out.println("contains 1 ? "+stringSet.contains("1"));
        System.out.println();

        //containsAll
        System.out.println("--- containsAll ---");
        List<String> listForContainsAll = new ArrayList<>();
        listForContainsAll.add("1");
        listForContainsAll.add("二");
        listForContainsAll.add("2");
        System.out.println("contains list ? "+stringSet.containsAll(listForContainsAll));
        System.out.println();

        //equals
        System.out.println("--- equals ---");
        System.out.println("equals ? "+stringSet.equals(stringSet));
        System.out.println();

        //hashCode
        System.out.println("--- hashCode ---");
        System.out.println("stringSet  hashCode "+stringSet.hashCode());
        System.out.println();

        //isEmpty
        System.out.println("--- isEmpty ---");
        System.out.println("stringSet  isEmpty ? "+stringSet.isEmpty());
        System.out.println();

        //iterator
        System.out.println("--- iterator ---");
        Iterator<java.lang.String> iterator = stringSet.iterator();
        System.out.println("stringSet  iterator  " + iterator);
        System.out.println("stringSet  next  " + iterator.next());
        System.out.println("stringSet  next  " + iterator.next());
        System.out.println();

        //remove
        System.out.println("--- remove ---");
        System.out.println("stringSet  remove 二 "+stringSet.remove("二"));
        System.out.println("stringSet  after remove "+stringSet);
        System.out.println();

        //removeAll
        System.out.println("--- removeAll ---");
        List<String> listForRemoveAll = new ArrayList<>();
        listForRemoveAll.add("三");
        listForRemoveAll.add("四");
        listForRemoveAll.add("3");
        System.out.println("stringSet  removeAll list "+stringSet.removeAll(listForRemoveAll));
        System.out.println("stringSet  after removeAll "+stringSet);
        System.out.println();

        //retainAll 仅保存 ()集合中已存在的
        stringSet.add("2");
        stringSet.add("3");
        stringSet.add("四");
        System.out.println("--- retainAll ---");
        List<java.lang.String> listForRetainAll = new ArrayList<>();
        listForRetainAll.add("1");
        listForRetainAll.add("2");
        System.out.println("stringSet  retainAll  "+stringSet.retainAll(listForRetainAll));
        System.out.println("stringSet after retainAll  "+stringSet);
        System.out.println();

        //size
        stringSet.add("2");
        stringSet.add("3");
        stringSet.add("四");
        System.out.println("--- size ---");
        System.out.println("stringSet size  "+stringSet.size());
        System.out.println();

        //spliterator()
        System.out.println("--- spliterator ---");
        Spliterator<java.lang.String> spliterator = stringSet.spliterator();
        System.out.println("stringSet spliterator  "+spliterator);
        System.out.println();

        //toArray
        System.out.println("--- toArray ---");
        Object[] toArray = stringSet.toArray();
        for (Object o : toArray) {
            System.out.println(o);
        }
        System.out.println("stringSet toArray  "+toArray);
        System.out.println();

        //toArray(T[] a)  toArray转换为指定类型的Array
        System.out.println("--- toArray(T[] a) ---");
        String[] arr = new String[0];
        String[] toArrayT = stringSet.toArray(arr);
        for (Object o : toArrayT) {
            System.out.println(o);
        }
        System.out.println("stringSet toArray  "+toArray);
        System.out.println();

    }

    @Test
    void UUID(){
        //randomUUID
        System.out.println("--- randomUUID ---");
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid "+uuid);
        System.out.println();

        //compareTo 比较
        System.out.println("--- compareTo ---");
        UUID uuid1 = UUID.randomUUID();
        System.out.println("uuid1 "+uuid1);
        System.out.println("compareTo "+uuid.compareTo(uuid1));
        System.out.println();

    }

    @Test
    void Arrays(){

        //创建Arr
        //占了位的arr的元素为 null
        String[] arr0 = new String[3];
        String[] arr = {"1","2","三"};
        System.out.println("--- new Arrays ---");
        System.out.println("arr0");
        for (String s : arr0) {
            System.out.println(s);
        }
        System.out.println("arr");
        for (String s : arr) {
            System.out.println(s);
        }
        System.out.println("arr0 "+arr0);
        System.out.println("arr "+arr);
        System.out.println();

        //asList
        System.out.println("--- asList ---");
        List<String> asList = Arrays.asList(arr);
        System.out.println(asList);
        System.out.println();

        //binarySearch
        System.out.println("--- binarySearch ---");
        byte[] bytes = new byte[1];
        byte key = 1;
        int i = Arrays.binarySearch(bytes, key);
        System.out.println();
        System.out.println();

    }

    @Test
    void LinkedList(){

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");

        //element 检索但不删除此列表的头
        System.out.println(" --- element --- ");
        String element = linkedList.element();
        System.out.println(element);
        System.out.println();

        //get
        System.out.println(" --- get --- ");
        String get2 = linkedList.get(2);
        System.out.println(get2);
        System.out.println();

        //

    }

//    @Test
//    void

    public String evaluate(String str){
        System.out.println(str.trim());
        return str+" !! ";
    }

}
