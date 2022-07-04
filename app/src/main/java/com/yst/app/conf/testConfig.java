package com.yst.app.conf;

import com.yst.app.conf.configBean.configBeanTest;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * 配置类练习
 * @creator: ly-yangst
 * @date: 2022/6/26
 */
@Configuration(proxyBeanMethods = false) //proxyBeanMethods 是否添加代理
@PropertySource("application.properties")
//@EnableConfigurationProperties(MyProperties.class) //也可注入
//@ConfigurationPropertiesScan("com.xxx.properties")//将这个配置文件包下的所有配置文件注入
public class testConfig {
    @Value("${password}")
    public String password;

    public void test() {
        System.out.println(password);
    }

    //有一个没有任何注释的类,可用通过配置类将其注入容器
    @Bean
    @Profile("dev")//指定dev环境生效
    public configBeanTest testImpotBean(){
        return new configBeanTest();
    }

}
