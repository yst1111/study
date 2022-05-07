package com.yst.fira.repo.user;

import com.yst.entity.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @creator: ly-yangst
 * @date: 2022/4/17
 */
public interface IUserRespository {

    List selectUser(Student dto);

    Student selectWithSorces(@Param("student") Student student);

    int updateParams(Student student);

    List importExcel(MultipartFile file) throws IOException;
}
