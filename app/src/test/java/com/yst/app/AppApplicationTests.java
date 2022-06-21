package com.yst.app;

import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.yst.app.controller.assember.DemoAsb;
import com.yst.entity.domain.CompanyDTO;
import com.yst.entity.dto.StudentDto;
import com.yst.entity.pojo.Student;
import com.yst.fira.dto.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.io.IoUtil;
import static cn.hutool.poi.excel.ExcelUtil.getReader;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;

//@SpringBootTest
class AppApplicationTests {

    @Autowired
    StudentMapper studentMapper;

    //main方法
    public static void main(String[] args) {
        System.out.println("helloWorld");
    }

    @Test
    void excel()throws IOException, InvocationTargetException, IllegalAccessException{


        return ;

    }

    @Test
    void testCompany(){

        CompanyDTO company = CompanyDTO.CompanyBuilder.create()
                .addr("武汉")
                .companyNo("123")
                .profession("互联网")
                .build();

        System.out.println(company);

    }

    /**
    * stream流
    * @author yst
    * @date 2022/4/26 15:49
    * @version v1.0
    *
    * @return void
    */
    @Test
    void stream() {

        String[] arr = {"yin","二","3s","二","3s"};

        /*
        流化 stream()
        */
        List<String> list = Arrays.asList(arr);
        Stream<String> streamList = list.stream();
//        System.out.println(streamList);

        /*
        filter()过滤
        */
        List<String> filterYi = streamList.filter(s -> s.equals("yi")).collect(Collectors.toList());
//        System.out.println(filterYi);

        /*
        distinct()去重
        */
        List<String> listDistinct = list.stream().distinct().collect(Collectors.toList());
//        System.out.println(listDistinct);

        /*
        findFirst() 取出第一个元素
        */
        Optional<String> first = list.stream().findFirst();
        Optional<String> any = list.stream().filter(s -> s.equals("二")).findAny();
//        System.out.println("first: "+first);
//        System.out.println("any: "+any);

        /*
        max,min,count
        */
        String max = list.stream().max(Comparator.comparing(String::length)).get().toString();
        String min = list.stream().min(Comparator.comparing(String::length)).get().toString();
//        System.out.println("max: "+max+",min "+min);
        List<String> listF = new ArrayList<>();
        listF.add("yin");
        listF.add("二1");
        long 二 = list.stream().filter(listF::contains).count();
        //两集合交集
        List<String> includeListF = list.stream().filter(listF::contains).collect(Collectors.toList());
//        System.out.println(includeListF);

        /*
        reduce
        */
        Integer[] numArr = new Integer[3];
        Integer[] numArr1 = {1,2,3};
        Integer[] numArr2 = new Integer[]{2,2,5,6};

        List<Integer> listForNum = Arrays.asList(numArr1);

        Integer sum = listForNum.stream().reduce(2,Integer::sum);
        Integer max1 = listForNum.stream().reduce(Integer::max).get();
        System.out.println(sum+"max:"+max1);

    }


    @Test
    void testSelectUser() {
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        String[] s = {"1","2","3","a"};
        String[] s1 = {"a","b","3","c"};
        list = Arrays.asList(s);
        list1 = Arrays.asList(s1);
        ArrayList<Object> list3 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list1.contains(list.get(i))){
                list3.add(list.get(i));
            }
        }
        System.out.println(list3);
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        String[] s = {"1","2","3","a","a"};
        String[] s1 = {"a","b","3","c"};
        list = Arrays.asList(s);
        list1 = Arrays.asList(s1);
        Student student = new Student();
        Student student1 = new Student();
        student.setId("1");
        student1.setId("1");

        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();



        list3.add(student.getId());
        list4.add(student1.getId());
        List<String> collect = list3.stream().filter(list4::contains).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test3(){
        Student student = new Student();
        Student student1 = new Student();
        Student student2 = new Student();
        student.setName("1");
        student.setAge(11);
        student1.setAge(22);
        student1.setName("2");
        student2.setAge(22);
        student2.setName("3");

        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        list.add(student2);
//        List<Long> collect = list.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.counting()))
//                .entrySet().parallelStream().filter(a -> a.getValue() > 1).
//                        map(Map.Entry::getKey).collect(Collectors.toList());
//
//        Map<Long, List<Student>> collect1 = list.stream().collect(Collectors.groupingBy(Student::getAge));
//        System.out.println(collect1);

    }

    @Autowired
    private DemoAsb demoAsb;
    @Test
    void test4(){
        Student student = new Student();
        student.setId("81");
        student.setName("yst");
        student.setAge(12);
        student.setLike("篮球");
        student.setLikes("足球⚽");
        student.setCountry("213");
        StudentDto studentDto = demoAsb.poToDto(student);
        Console.log(studentDto);
    }

}
