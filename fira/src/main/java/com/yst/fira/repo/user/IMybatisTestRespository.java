package com.yst.fira.repo.user;

import com.yst.entity.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * @creator: ly-yangst
 * @date: 2022/5/5
 */
public interface IMybatisTestRespository  {

    Student selectStuById(String student);

    List<Student> selectByBatchId(List idList);

    List<Student> selectByMap(Map<String, Object> map);

    String selectCount(Map<String, Object> map);

    void wrapper();
}
