package com.yst.app.controller.messTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @creator: ly-yangst
 * @date: 2022/5/11
 */
public class IteratorTest {

    public static void main(String[] args) {

        String[] arr = {"1","2","5","6","8","7","2"};
        List<String> listString = Arrays.asList(arr);

        ArrayList<String> list = new ArrayList<>();
        list.addAll(listString);
        System.out.println("list "+list);
        //list 的 remove 会使iterator.next()产生 并发修改异常
        Iterator<String> iterator = list.iterator();
        //有remove的list操作尽量避免使用iterator
//        list.remove(0);
//        iterator.remove();
        System.out.println(list);
        System.out.println("iterator firts "+iterator.next());

        int i = 0;
        while (iterator.hasNext()){
//            System.out.println("iterator remain "+i+++" "+iterator.next());
            Integer num = Integer.valueOf(iterator.next());
            if(num > 6){
                iterator.remove(); //删掉大于 6 的 //在while(hasNext)里边使用
            }
        }
        System.out.println(list);

        //打印迭代器的地址
//        System.out.println("iterator: "+iterator);
//        System.out.println("iterator.toString(): "+iterator.toString());



    }

}
