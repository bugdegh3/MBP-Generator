package com.bugdegh3.generator;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * &#064;Author  cyz
 *
 * @Date 2023/3/30 16:32
 * @Description:
 */
public class Generator {

    private static final String url = "test";
    private static final String userName = "test";
    private static final String password = "test";
    private static final String table = "test";

    static void createBe() {
        //创建一个代码生成器
        FastAutoGenerator
                .create(url,userName,password) //数据库连接
                //全局配置(GlobalConfig)
                .globalConfig(builder -> {
                    builder.author("bugdegh3") // 设置作者，可以写自己名字
                            .enableSwagger() // 开启 swagger 模式，这个是接口文档生成器，如果开启的话，就还需要导入swagger依赖
                            .dateType(DateType.TIME_PACK) //时间策略
                            .commentDate("yyyy-MM-dd") //注释日期
                            .outputDir("E:\\workSpace\\Generator\\src\\main\\java"); // 指定输出目录，一般指定到java目录
                })
                // 自定义配置(InjectionConfig)
                /*.injectionConfig(
                    consumer -> {
                        Map<String, String> customFile = new HashMap<>();
                        customFile.put("DTO.java", "/templates/entityDTO.java.ftl"); //自定义模版引擎
                        consumer.customFile(customFile);
                })*/
                //包配置(PackageConfig)
                .packageConfig(builder -> {
                    builder.parent("com.bugdegh3.generator") // 设置父包名
                            .moduleName("") // 设置父包模块名，这里一般不设置
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\workSpace\\Generator\\src\\main\\java\\com\\ruoyi\\xml")); // 设置mapperXml生成路径，这里是Mapper配置文件的路径，建议使用绝对路径
                })
                //策略配置(StrategyConfig)
                .strategyConfig(builder -> {
                    builder.addInclude(table); // 设置需要生成的表名
                    //.addTablePrefix("tbl_"); // 设置过滤表前缀
                    builder.serviceBuilder() // service相关配置
                            .formatServiceFileName("%sService") //设置service的命名策略,没有这个配置的话，生成的service和serviceImpl类前面会有一个I，比如IUserService和IUserServiceImpl
                            .enableFileOverride() //开启文件覆盖
                            .formatServiceImplFileName("%sServiceImpl"); //设置serviceImpl的命名策略
                    builder.controllerBuilder() //controller相关配置
                            .enableFileOverride() // 开启文件覆盖
                            .enableRestStyle(); // 开启生成@RestController 控制器，不配置这个默认是Controller注解，RestController是返回Json字符串的，多用于前后端分离项目。
                    builder.mapperBuilder() // mapper相关配置
                            .mapperAnnotation(org.apache.ibatis.annotations.Mapper.class) //开启 @Mapper 注解，也就是在dao接口上添加一个@Mapper注解，这个注解的作用是开启注解模式，就可以在接口的抽象方法上面直接使用@Select和@Insert和@Update和@Delete注解。
                            .enableBaseResultMap() // 启用 BaseResultMap 生成
                            .enableFileOverride(); //覆盖文件
                    builder.entityBuilder() // entity相关配置
                            .enableFileOverride() //开启文件覆盖
                            .enableLombok(); // 开启lombok
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute(); //执行以上配置
    }


    public static void main(String[] args) {
        createBe();
    }
}
