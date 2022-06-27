package com.yst.app.util;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码自动生成工具
 * @creator: ly-yangst
 * @date: 2022/6/26
 */
public class CodeGeneratorUtil {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取项目路径  及代码生成器所在的项目路径
        String projectPath = System.getProperty("user.dir");
        //模块地址
        String modules = "/ok_dev1.0";
        //设置输出文件路径
        gc.setOutputDir(projectPath +modules);//D:\ok_dev1.0\app\src\main\java\com\yst\app\controller //"/src/main/java"
        //作者
        gc.setAuthor("yst");
        //执行完 是否打开输出的目录，默认true
        gc.setOpen(true);
        //覆盖已有的文件，默认false(第一次生成时放开)
//        gc.setFileOverride(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 设置日期类型为Date(若不设置时间类型都会变成LocalDateTime部分连接池例如druid是无法识别的)
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

//        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.41.188:3306/test411?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //模块名称
        //pc.setModuleName(scanner("模块名"));
        //父包
//        pc.setParent("app.src.main.java.com.yst");
        //自定义实体包名(不同的模块自己手动修改)
        pc.setEntity("entry.src.main.java.entity.pojo");
        //自定义mapper包名(不同的模块自己手动修改)
        pc.setMapper("fira.src.main.java.fira.dto");
        //自定义mapper.xml包名(不同的模块自己手动修改)
        pc.setXml("fira.src.main.java.fira.dto");
        //自定义service包名(不同的模块自己手动修改)
        pc.setService("fira.src.main.java.fira.repo.user");
        //自定义serviceImpl包名(不同的模块自己手动修改)
        pc.setServiceImpl("fira.src.main.java.fira.repo.user.impl");
        //自定义controller包名(不同的模块自己手动修改)
        pc.setController("app.src.main.java.app.controller");

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String xmlPath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出  例子如下
        focList.add(new FileOutConfig(xmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath +modules+ "/src/main/resources/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否为lombok模型，默认为false
        strategy.setEntityLombokModel(true);
        //前后端分离时可开启
//        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //RequestMapping驼峰转连字符
//        strategy.setControllerMappingHyphenStyle(true);
        //生成实体时生成生成数据库字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
