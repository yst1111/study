package com.yst.app.conf;

import com.yst.app.conf.configBean.initializerTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 初始化器
 * @creator: ly-yangst
 * @date: 2022/6/26
 */
public class YstApplicationContextInitializer implements ApplicationContextInitializer {
    //还没开启容器,就注入一些bean
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //getBeanFactory()表示从spring.factorys文件中去拿,而getBean是拿不到的
        applicationContext.getBeanFactory().registerSingleton("yst",new initializerTest());
    }
}
