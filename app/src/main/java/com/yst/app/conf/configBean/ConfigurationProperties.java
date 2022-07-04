package com.yst.app.conf.configBean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @creator: ly-yangst
 * @date: 2022/6/26
 */
//@ConfigurationProperties(prefix = "yst1")//可加前缀进行筛选
@Component
@Data
public class ConfigurationProperties {
    private String passwor;
    private String passwor1;


}
