package com.star.account.biz.model;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器 详细属性请看 @{link https://mp.baomidou.com/config/generator-config.html#controllermappinghyphenstyle}
 *
 * @author zx
 * @date 2019/2/11
 */
public class CodeGenerator {

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/account?useUnicode=true&useSSL=false&characterEncoding=utf8";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String USER = "root";
    public static final String PW = "123456";
    /**
     * 包路径
     */
    public static final String COM_ZX_HOUSE = "com.star.account";
    public static final String CONTROLLER = "controller";
    public static final String SERVICE = "service";
    public static final String SERVICE_IMPL = "service.impl";
    public static final String MODEL_ENTITY = "model.entity";
    public static final String MAPPER = "mapper";
    /**
     * 要生成的表
     */
    public static String[] TABLES = {"user","account_detail"};
    /**
     * mapper路径
     */
    private static String MAPPER_PATH = "account-biz/src/main/resources/mapper/";;
    /**
     * JAVA代码路径
     */
    private static String JAVA_PATH = "/account-biz/src/main/java";;
    private static String AUTH_OTHER = "AUTH_OTHER";


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + JAVA_PATH);
        gc.setAuthor(AUTH_OTHER);
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(JDBC_URL);
        dsc.setDriverName(DRIVER);
        dsc.setUsername(USER);
        dsc.setPassword(PW);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(COM_ZX_HOUSE);
        pc.setController(CONTROLLER);
        pc.setService(SERVICE);
        pc.setServiceImpl(SERVICE_IMPL);
        pc.setEntity(MODEL_ENTITY);
        pc.setMapper(MAPPER);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
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
        strategy.setEntityLombokModel(true);
        strategy.setSkipView(true);
        strategy.setRestControllerStyle(true);
        // 需要生成的表
        strategy.setInclude(TABLES);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }

}