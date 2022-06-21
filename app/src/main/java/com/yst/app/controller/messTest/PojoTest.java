package com.yst.app.controller.messTest;

import cn.hutool.core.lang.Console;
import org.junit.Assert;
import org.junit.jupiter.api.Test;



/**
 * @creator: ly-yangst
 * @date: 2022/6/7
 */
public class PojoTest {

    @Test
    void PojoBuilderTest(){
        PojoBuilder zhangsan = new PojoBuilder("张三","18","程序员",true);
        Console.log(zhangsan);

        //这一套对象创建,用于非全参的对象构建
        PojoBuilder lisi = PojoBuilder.create().set("李四",null);
        Console.log(lisi);

        

    }

}
