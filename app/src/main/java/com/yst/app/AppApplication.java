package com.yst.app;

import com.yst.app.controller.messTest.MybatisTestController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


@SpringBootApplication
@EnableSwagger2
@MapperScan("com.yst.fira.dto")//将本路径下所有类加上@Mapping
//@ComponentScan({"com.yst.fira.repo"})
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
        Logger logg = Logger.getLogger(AppApplication.class);
        if (logg.isInfoEnabled()) {
            logg.info("------  Application start success！  ------");
        }
    }

}
