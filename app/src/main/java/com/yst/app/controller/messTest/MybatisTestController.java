package com.yst.app.controller.messTest;

import com.yst.entity.pojo.Student;
import com.yst.fira.repo.user.IMybatisTestRespository;
import com.yst.fira.repo.user.impl.MybatisTestRespository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @creator: ly-yangst
 * @date: 2022/5/5
 */

/**
 * @creator: ly-yangst
 * @date: 2022/5/5
 */
@Api(tags = "myBatis")
@RestController
@RequestMapping(value = "mb")
@ComponentScan({"com.yst.fira.repo"})
public class MybatisTestController {

    public static void main(String[] args) {
        System.out.println("mybatiDemo");
    }

    @Autowired
    private IMybatisTestRespository iMybatisTestRespository;

    //@RequestParam是路径传参,value是传来的参数叫啥,可以映射为另一个名字,defaultValue表示默认值,就是没传的话默认是多少
    //@RequestBody是请求体传参,一般放一个json
    @PostMapping(value = "byID")
    @ApiOperation(value = "根据id查student的所有信息")
    Student selectById(@RequestParam(value = "idd", defaultValue = "1") String id) {
        return iMybatisTestRespository.selectStuById(id);
    }

    //selectByBatchId 入参是ArrayList<String> 也就是["1","2","3"]
    @PostMapping(value = "byBatchId")
    @ApiOperation(value = "id集合批量查询")
    List<Student> selectByBatchId(@RequestBody ArrayList<String> id) {
        return iMybatisTestRespository.selectByBatchId(id);
    }

    //selectByMap 入参是Map  {"name":"张三"}或者{"id":"1"}或者{"name":"张三","id":"2"}就是name="张三"且id="2"
    @PostMapping(value = "byMap")
    @ApiOperation(value = "入参为map")
    List<Student> selectByMap(@RequestBody Map<String, Object> map) {
        return iMybatisTestRespository.selectByMap(map);
    }

    //selectCount 查数量,返回的是int类型,需要toString一下,入参是queryWrapper()
    @PostMapping(value = "selectCount")
    @ApiOperation(value = "入参为map")
    String selectCount(@RequestBody Map<String, Object> map) {
        return iMybatisTestRespository.selectCount(map);
    }

    //Wrapper的用法
    @PostMapping(value = "Wrapper")
    @ApiOperation(value = "wrapper用法")
    String wrapper() {
        Logger logg = Logger.getLogger(MybatisTestController.class);
//        logg.info("wrapper: start!");
        iMybatisTestRespository.wrapper();
        return null;
    }



}
