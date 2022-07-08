package com.yst.app.controller.user;

import cn.hutool.core.lang.Console;
import com.yst.entity.dto.StudentDto;
import com.yst.entity.pojo.Student;
import com.yst.fira.repo.user.IUserRespository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * @creator: ly-yangst
 * @date: 2022/4/16
 */
@Api(tags = "用户页面")
@RestController
@RequestMapping(value = "user")
@ComponentScan({"com.yst.fira.repo","com.yst.entity.dto"})
public class UserController {

    @Autowired(required = false)
    private IUserRespository iUserRespository;
    @Value("${dog}")
    private String name;

    @PostMapping(value = "/excel")
    @ApiOperation(value = "excel")
    public List excel(@RequestParam(value = "excelFile") MultipartFile file) throws IOException {
        Console.log(name);
        return iUserRespository.importExcel(file);
    }

    @GetMapping(value = "/hello")
    @ApiOperation(value = "根据id查student的所有信息")
    public List hello(Student dto){
        int i = 1/0;
        List list = iUserRespository.selectUser(dto);
        return list;
    }

    @GetMapping(value = "/selectUser")
    @ApiOperation(value = "与sorces联查")
    public Student seleteUser(Student student){
        return iUserRespository.selectWithSorces(student);
    }

    @GetMapping(value = "/updateSorces")
    @ApiOperation(value = "事务操作")
    public int UpdateParams(Student student){
        String id = student.getId();
        student.getLikes();
        return iUserRespository.updateParams(student);
    }


}
