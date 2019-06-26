package com.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: mybatis-plus 代码生成器 (添加以下三个依赖)
 *
 *          <dependency>
 *             <groupId>com.baomidou</groupId>
 *             <artifactId>mybatis-plus-boot-starter</artifactId>
 *             <version>3.0.7.1</version>
 *         </dependency>
 *
 *         <dependency>
 *             <groupId>com.baomidou</groupId>
 *             <artifactId>mybatis-plus-generator</artifactId>
 *             <version>3.0.7.1</version>
 *         </dependency>
 *
 *         <dependency>
 *             <groupId>org.freemarker</groupId>
 *             <artifactId>freemarker</artifactId>
 *             <version>2.3.28</version>
 *         </dependency>
 *
 * @author Mr.LB
 * @version 1.0
 * @date 19-1-21 下午2:05
 */
public class MysqlGenerator {

    private static final String DATABASE = "db_gts_fescar_account";
    private static final String url = "jdbc:mysql://localhost:3306/"+DATABASE+"?useUnicode=true&characterEncoding=utf8";
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String userName = "root";
    private static final String password = "123456";

    private static String basePath = "";
    private static String mapperPath = "";
    private static String mapperXmlPath = "";

    private static final Pattern linePattern = Pattern.compile("_(\\w)");

    //TODO 在这个地方添加需要生成的表名
    private final static String[] TABLE_NAMES = {"t_account","undo_log"};

    public static void main(String[] args) {
        generate("Mr.LB","com.main");
    }


    /**
     * 自动生成代码
     * @param author 作者
     * @param packageName 包名
     * @param tablePrefix 表前缀
     */
    public static void generate(String author, String packageName, String... tablePrefix) {

        // 全局配置
        GlobalConfig gc = initGlobalConfig(author, packageName);
        // 数据源配置
        DataSourceConfig dsc = initDataSourceConfig();
        // 包配置
        PackageConfig pc = new PackageConfig().setParent(packageName);
        // 模板引擎配置
        FreemarkerTemplateEngine templateEngine = new FreemarkerTemplateEngine();


        //每一个entity都需要单独设置InjectionConfig, StrategyConfig和TemplateConfig
        Map<String, String> tableNameMap = getEntityNames(TABLE_NAMES);
        if (null == tableNameMap || tableNameMap.isEmpty()) {
            return;
        }
        for (String tableName : tableNameMap.keySet()) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();
            mpg.setGlobalConfig(gc);
            mpg.setDataSource(dsc);
            mpg.setPackageInfo(pc);
            mpg.setTemplateEngine(templateEngine);

            // 自定义配置
            InjectionConfig cfg = initInjectionConfig(packageName);
            mpg.setCfg(cfg);

            // 策略配置
            StrategyConfig strategy = initStrategyConfig(tableName);
            mpg.setStrategy(strategy);

            // 模板配置
            // mapper文件
            String mapperFile = mapperPath
                    + "/" + tableNameMap.get(tableName) + "Mapper" + StringPool.DOT_JAVA;
            TemplateConfig tc = initTemplateConfig(mapperFile);
            mpg.setTemplate(tc);

            //开始执行
            mpg.execute();
        }
    }


    /**
     * 配置数据源
     * @return
     */
    private static DataSourceConfig initDataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUrl(url)
                .setDriverName(driverName)
                .setUsername(userName)
                .setPassword(password);
    }

    /**
     * 全局配置
     * @return
     */
    private static GlobalConfig initGlobalConfig(String author, String packageName) {
        GlobalConfig gc = new GlobalConfig();
        String tmp = MysqlGenerator.class.getResource("").getPath();
        String codeDir = tmp.substring(0, tmp.indexOf("/target"));
        basePath = codeDir + "/src/main/java";
        mapperPath = basePath + "/" + packageName.replace(".", "/") + "/mapper";
        mapperXmlPath = codeDir + "/src/main/resources/mappers";
        System.out.println("basePath = " + basePath + "\nmapperPath = " + mapperPath);
        System.out.println("mapperXmlPath = " + mapperXmlPath);
        gc.setOutputDir(basePath);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setFileOverride(true);

        return gc;
    }

    /**
     * 自定义配置
     * @param packageName
     * @return
     */
    private static InjectionConfig initInjectionConfig(String packageName) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义输入文件名称
                return mapperXmlPath
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        return cfg;
    }

    /**
     * 策略配置
     * @param tableName 数据库表名
     * @return
     */
    private static StrategyConfig initStrategyConfig(String tableName) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setCapitalMode(true);
        //生成字段注解@TableField
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityLombokModel(true);
        //setTablePrefix可以生成@TableName注解
        strategy.setTablePrefix("");
        strategy.setInclude(tableName);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        return strategy;
    }

    /**
     * 覆盖Entity以及xml
     * @param mapperFile
     * @return
     */
    private static TemplateConfig initTemplateConfig(String mapperFile) {
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        //如果当前Entity已经存在,那么仅仅覆盖Entity
        File file = new File(mapperFile);
        if (file.exists()) {
            tc.setController(null);
            tc.setMapper(null);
            tc.setService(null);
            tc.setServiceImpl(null);
            tc.setEntityKt(null);
        }

        return tc;
    }

    /**
     * @param tableNames 数据库表集合
     * @return
     */
    public static Map<String, String> getEntityNames(String[] tableNames) {
        Map<String, String> result = new HashMap<>();
        String entityName;
        for (String tableName : tableNames){
            entityName = underlineToCamel(tableName);
            result.put(tableName, entityName);
        }
        return result;
    }


    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    private static String underlineToCamel(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }
}
