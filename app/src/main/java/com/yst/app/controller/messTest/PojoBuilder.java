package com.yst.app.controller.messTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @creator: ly-yangst
 * @date: 2022/6/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PojoBuilder implements Serializable {
    //成员变量
    private String name;
    private String age;
    private String work;
    private Boolean isUsed;

    private static final long serialVersionUID = 6135423866861206530L;
    static final String IDEA = "PojoBuilder Test";

    //这个静态方法,new一个对象,创建但不初始化
    public static PojoBuilder create(){
        return new PojoBuilder();
    }

    //这个方法
    public PojoBuilder set(String name,String age){
        this.name = name;
        this.age = age;
        return this;
    }

}
