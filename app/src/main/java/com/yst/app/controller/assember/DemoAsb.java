package com.yst.app.controller.assember;

import com.yst.entity.dto.StudentDto;
import com.yst.entity.pojo.Student;
import org.mapstruct.Mapper;

/**
 * @creator: ly-yangst
 * @date: 2022/6/17
 */
@Mapper(componentModel = "spring")
public interface DemoAsb {
    StudentDto poToDto(Student student);

}
