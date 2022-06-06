package com.yst.app.conf;


import com.yst.app.AppApplication;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @creator: ly-yangst
 * @date: 2022/4/16
 */
@Configuration
@EnableSwagger2
public class swaggerConfiguration {
    @Bean
    public Docket webApiConfig(){
        Logger logg = Logger.getLogger(AppApplication.class);
        //日志
        if (logg.isInfoEnabled()) {
            logg.info("------  swagger start success！  ------");
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("yst")
                .apiInfo(webApiInfo())
                .select()
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("yst学习swagger")
                .description("yst-2022-4-16")
                .version("1.0")
                .contact(new Contact("yst","www.baidu.com","384861505@qq.com"))
                .build();
    }
}
