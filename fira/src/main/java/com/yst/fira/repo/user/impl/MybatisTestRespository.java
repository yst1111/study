package com.yst.fira.repo.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sun.xml.internal.bind.v2.TODO;
import com.yst.entity.pojo.Student;
import com.yst.fira.dto.MybatisTest;
import com.yst.fira.repo.user.IMybatisTestRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * @creator: ly-yangst
 * @date: 2022/5/5
 */
@Service
public class MybatisTestRespository implements IMybatisTestRespository {

    @Autowired
    private MybatisTest mybatisTest;

    @Override
    public Student selectStuById(String id) {
        return mybatisTest.selectById(id);
    }

    @Override
    public List<Student> selectByBatchId(List idList) {
        return mybatisTest.selectBatchIds(idList);
    }

    @Override
    public List<Student> selectByMap(Map<String, Object> map) {
        return mybatisTest.selectByMap(map);
    }

    //wrapper.eq() 两个.eq() 是AND关系 SELECT COUNT( 1 ) FROM student WHERE (id = ? AND name = ?)
    @Override
    public String selectCount(Map<String, Object> map) {
        //TODO yst
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        for (String key : map.keySet()) {
            wrapper.eq(key,map.get(key));
        }
        return mybatisTest.selectCount(wrapper).toString();
    }

    @Override
    public void wrapper() {
        Logger logg = Logger.getLogger(MybatisTestRespository.class);

        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        //eq()与eq()之间是AND关系,即两个条件都符合
//        wrapper.eq("id","1");

        //allEq()相当于多个eq()用AND连接
//        HashMap<String, String> map = new HashMap<>();
//        map.put("id","1");
//        map.put("name","张三");
//        wrapper.allEq(map,true);

        //ne是不等于的意思
//        wrapper.ne("id","1");

        //gt大于,ge大于等于,lt小于,le小于等于
//        wrapper.le("age",18);

        //between 在"",""之间 String也可以,包含上下限
//        wrapper.between("id","1","3");

        //notBetween 不在"",""之间 String和int,Double都行,SELECT id,country,name,age,likes FROM student WHERE (id NOT BETWEEN ? AND ?)
//        wrapper.notBetween("id",1.1,"2");

        //模糊查询 like,notLike,likeLeft,likeRight
//        wrapper.like("name","张");//SELECT id,country,name,age,likes FROM student WHERE (name LIKE ?) ,Parameters: %张%(String)
//        wrapper.notLike("name","张");//SELECT id,country,name,age,likes FROM student WHERE (name NOT LIKE ?) ,Parameters: %张%(String)
//        wrapper.likeRight("name","张");//SELECT id,country,name,age,likes FROM student WHERE (name LIKE ?) ,张%(String)
//        wrapper.likeLeft("name","三");//SELECT id,country,name,age,likes FROM student WHERE (name LIKE ?) ,%三(String)

        //isNull 为空 isNotNull不为空
//        wrapper.isNotNull("UPDATE_TIME");

        //in,notin  SELECT id,country,name,age,likes FROM student WHERE (id IN (?,?))
        //orderByDesc降序 orderByAsc升序 SELECT id,country,name,age,likes FROM student WHERE (id IN (?,?,?,?)) ORDER BY id ASC
//        String[] arr = {"1","2","3","5"};
//        List<String> list = Arrays.asList(arr);
//        wrapper.in("id", list);
//        wrapper.orderByDesc("id");
//        wrapper.orderByAsc("id");

        //having
//        wrapper.in("id","1","2","3","5");
//        wrapper.gt("age","12");
//        wrapper.having("sum(id) > 3");
//        Integer integer = mybatisTest.selectCount(wrapper);
//        logg.info("count :"+integer);

        //or & and
//        wrapper.eq("id","2")
//                .and(i->i.eq("name","lisi"))
//                .or().eq("id","1")
//                .or().eq("id","3");

        //nested
//        wrapper.nested(i->i.eq("id","1").eq("name","张三"))
//                .or().eq("id","2");   //SELECT * FROM student WHERE (( (id = ? AND name = ?) ) OR id = ?)

        //apply 这个好用,写固定格式
//        wrapper.apply("id = '1' AND name ='张三' OR id='3'"); //SELECT * FROM student WHERE (id = '1' AND name ='张三' OR id='3')

        //last 相当于在后边拼接一个String字符串,多个.last()的话以最后一个为准
//        wrapper.apply("id = '1' AND name ='张三' OR id='3'");
//        wrapper.last("OR id = '4'"); //SELECT * FROM student WHERE (id = '1' AND name ='张三' OR id='3') OR id = '4'
//        wrapper.last("limit 1");

        //notExists,exists
//        wrapper.exists("select * from sorces  where student.id= sorces.student_id");

        //select 选择要查询的字段
//        wrapper.select("id","name");
//        wrapper.nested(i->i.eq("id","1"))
//                .or().eq("id","2");
//        wrapper.orderByDesc("age");
//        wrapper.last("limit 1");

//        List<Student> students = mybatisTest.selectList(wrapper);
//        for (Student student : students) {
//            logg.info("result: "+ student.getName());
//        }


        //UpdateWrapper 用于"改" 1.实体类set 2.updateWrapper.eq()传入条件 实体类必要用,但同时可以用.set()或者.setsql()
        //实体类最好不要用基本数据类型,而用包装类型
//        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
//        Student student = new Student();
//        student.setName("zzs");
//        student.setLikes("game");
//
//        updateWrapper.set("country","中国");
//        updateWrapper.like("name","z"); //UPDATE student SET name=?, likes=?, country=?,age=?, age = 19 WHERE (name LIKE ? AND id = ?)
//        updateWrapper.set("age","18");
//        updateWrapper.eq("id","1");
//        updateWrapper.setSql(" age = 19");
//        mybatisTest.update(student,updateWrapper);

        //Lambda表达式 LambdaQueryWrapper
        LambdaQueryWrapper<Student> lambda = new QueryWrapper<Student>().lambda();
        lambda.eq(Student::getId,1);

//        lambda.ge(Student::getAge,"12")
//                .le(Student::getAge,"15")
//                .like(Student::getName,"z")
//                .orderByAsc(Student::getId);

        List<Student> students = mybatisTest.selectList(lambda);


    }



}
