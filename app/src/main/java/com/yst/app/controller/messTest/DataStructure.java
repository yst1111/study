package com.yst.app.controller.messTest;


import org.junit.Test;

import java.util.*;

/**
 * @creator: ly-yangst
 * @date: 2022/5/13
 */

public class DataStructure {

    public static void main(String[] args) {
//        Base64 base64 = new Base64();


        Enumeration<String> trees;
        Vector<String> treesVe = new Vector<>();//相当于迭代器
        treesVe.add("apple");
        treesVe.add("banana");
        trees = treesVe.elements();
        while (trees.hasMoreElements()){
            System.out.println(trees.nextElement());
        }

        //测试 stack
        System.out.println();
        System.out.println("----stack-----");
        System.out.println();

        Stack<Integer> st = new Stack<Integer>();
        System.out.println("stack: " + st);
        System.out.println();
        showpush(st, 42);
        showpush(st, 66);
        showpush(st, 99);
        showpop(st);
        showpop(st);
        showpop(st);
        try {
            showpop(st);
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
    }

    //stack
    static void showpush(Stack<Integer> st,int a){
        st.push(new Integer(a));
        System.out.println("push "+a);
        System.out.println("stack "+st);
        System.out.println();
    }
    //stack
    static void showpop(Stack<Integer> st) {
        System.out.print("pop -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
    }

    @Test
    void BitSet(){
        //Integer.MAX_VALUE  java.lang.OutOfMemoryError: Java heap space
        BitSet bitSet = new BitSet();//hashcode的值域
        String url = "abcd";
        String url1 = "ccc1";
        int hashcode = url.hashCode();
        int hashcode1 = url1.hashCode();
        System.out.println(hashcode+" "+hashcode1);
        bitSet.set(hashcode);
        bitSet.set(hashcode1);

        System.out.println(bitSet.cardinality());//着色个数
        boolean ifEx = bitSet.get(hashcode);
        boolean ifEx1 = bitSet.get(hashcode1);
        System.out.println(ifEx);

        //核心 方法 set() 和 get() ,其中get判断存在与否
        //有哈希冲突的可能性
        //BitSet 能够保证“如果判定结果为 false，那么数据一定是不存在；但是如果结果为 true，可能数据存在，也可能不存在(冲突覆盖)”
    }

    @Test
    //向量
    void Vector(){
        //可变集合
        //初始大小为3,其后每次扩容2位
        Vector<Object> v = new Vector<>(3, 2);
        v.add("1");
        v.add("2");
        v.add("2");
        v.add("2");
        System.out.println(v);
        System.out.println(v.capacity());
        //toArray()转为 数组
        v.toArray();

    }

    @Test
    //集合 Collection
    //Collection的子接口 List和Set
    //List 有序可重复  Set无序不可重复(SortedSet 有序集合)
    //Map key和value的映射
    void Collection(){

    }

}
