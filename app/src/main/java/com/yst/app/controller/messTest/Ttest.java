package com.yst.app.controller.messTest;


import com.yst.entity.pojo.Sorces;
import com.yst.entity.pojo.TPojo;

/**
 * 泛型T的用法
 * @creator: ly-yangst
 * @date: 2022/6/6
 */
public class Ttest {

    public static void main(String[] args) {
        //测试TPojo中的泛型
        TPojo<String> stringTPojo = new TPojo<>();
        TPojo<Integer> integerTPojo = new TPojo<>();

        //私有方法
        int intParams = 2;
        System.out.println("int: "+Tmethod(intParams));
        String stringParams = "cccc";
        System.out.println("string: "+Tmethod(stringParams));

    }

    //固定格式 public <T>
    private static <F> TPojo<F> Tmethod(F f){
        return TPojo.build(f);
    }

}
