package com.yst.fira.repo.user.impl;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.yst.entity.pojo.Student;
import com.yst.fira.dto.SorcesnoMapper;
import com.yst.fira.dto.StudentMapper;
import com.yst.fira.repo.user.IUserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @creator: ly-yangst
 * @date: 2022/4/17
 */
@Service
public class UserRespository implements IUserRespository {

    @Autowired
    private SorcesnoMapper sorcesnoMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List selectUser(Student dto) {
        return null;
    }

    @Override
    public Student selectWithSorces(Student student) {
        studentMapper.selectById(student);
        //全局统一的定时任务调度
        final String schedule = CronUtil.schedule("*/2 * * * * *", (Task) () -> System.out.println("执行定时任务"));
        CronUtil.setMatchSecond(true);
        CronUtil.start();

        System.out.println("post task");

        return null;
    }

    @Override
    @Transactional
    public int updateParams(Student student) {
//        UpdateWrapper wrapper = new UpdateWrapper();
//        wrapper.set("sorcesMsg",student.getLikes().toString());
        int updateit = sorcesnoMapper.updateit(student);
//        int a = 1/0;
        int i = sorcesnoMapper.deleteSorces(student);
        return i;
    }

    @Override
    public List importExcel(MultipartFile file) throws IOException {

        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream, 0);
        List<List<Object>> read = reader.read();

        return null;
    }
}
