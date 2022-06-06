package com.yst.app.controller.messTest;

import com.yst.entity.pojo.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @creator: ly-yangst
 * @date: 2022/4/27
 */
public class StreamTest {

    public static void main(String[] args) {

    }

    @Test
    //流化
    void stream(){

        String[] arr = {"yin","二","3s","二","3s"};
        List<String> strings = Arrays.asList(arr);

        System.out.println(strings);
    }

    @Test
    //过滤
    public void Filter(){

        String[] arr = {"yin","二","3s","二","3s"};
        List<String> strings = Arrays.asList(arr);

        List<String> filterList = strings.stream().filter(s -> s.equals("3s") || s.equals("二")).collect(Collectors.toList());
        System.out.println(filterList);
        List<Object> listBase = new ArrayList<>();

        listBase.add("二");
        listBase.add("1");

        Student student = new Student();
        Student student1 = new Student();
        Student student2 = new Student();
        student.setName("1");
        student.setAge(11);
        student1.setAge(22);
        student1.setName("2");
        student2.setAge(22);
        student2.setName("3");
        List<Student> listStudent = new ArrayList<>();
        listStudent.add(student);
        listStudent.add(student1);
        listStudent.add(student2);

//        Collector<Student, ?, Map<String, Long>> studentMapCollector = Collectors.toMap(Student::getId, Student::getAge, (v1, v2) -> v2);
//        Map<String, Long> collect = listStudent.stream().collect(studentMapCollector);
//        System.out.println("collect"+collect);
//
//        List<String> felterList1 = strings.stream().filter(listBase::contains).collect(Collectors.toList());
//        System.out.println("f1 "+felterList1);

    }


}
