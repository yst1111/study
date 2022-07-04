package com.yst.app;

import com.yst.app.conf.configBean.initializerTest;
import com.yst.app.conf.testConfig;
import com.yst.app.conf.testSpringFactoryConfigruation;
import com.yst.app.controller.easyExcel.easyExcelController;
import com.yst.app.controller.messTest.MybatisTestController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.sql.DataSource;
import java.sql.Driver;


@SpringBootApplication//这个是核心注解
@EnableSwagger2
@MapperScan("com.yst.fira.dto")//将本路径下所有类加上@Mapping

//@ComponentScan({"com.yst.fira.repo"})
//@ImportResource("spring.xml") //注入xml的bean
@PropertySource("application.properties")//注入配置文件的东西

public class AppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppApplication.class, args);

        //        DataSource dataSource = applicationContext.getBean(DataSource.class);
//        System.out.println(dataSource);//测试@Bean 注入的datasource
//        System.out.println(applicationContext.getBean(testConfig.class));//测试配置类注入的无注解类
//        System.out.println(applicationContext.getBean(testSpringFactoryConfigruation.class));//测试spring.factories文件注入的condiguration
//        System.out.println(applicationContext.getBean("yst"));//测试初始化器注入的bean,通过名字拿不到
//        System.out.println(applicationContext.getBean(initializerTest.class));//测试初始化器注入的bean,通过名字拿不到

//        //调用这个bean的方法,测试@Value
//        applicationContext.getBean(testConfig.class).test();//成功,这个方法可用

        Logger logg = Logger.getLogger(AppApplication.class);
        if (logg.isInfoEnabled()) {
            logg.info("------  Application start success！  ------");
        }
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        return dataSource;
    }

}
