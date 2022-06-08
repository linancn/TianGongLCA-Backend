package org.tiangonglca.backend.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.SQLException;

public class Generator {

    /**
     * Run
     */
    public static void main(String[] args) throws SQLException {
        DataSourceConfig dsc = new DataSourceConfig
                .Builder("jdbc:postgresql://localhost:55434/postgres?currentSchema=public",
                "postgres", "1357_Qetu")
                .build();
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator(dsc);
 
        // String moduleName = "";
        // String projectPath = System.getProperty("user.dir");
 
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig
                .Builder()
                .outputDir("src/main/java")
                .author("TianGongLCA")
                // .fileOverride()
                .build();
 
        // 包配置
        PackageConfig packageConfig = new PackageConfig
                .Builder()
                .parent("org.tiangonglca.backend")
                // .moduleName(moduleName)
                .build();
 
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig
                .Builder()
                .mapperXml(null)
                .build();
 
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig
                .Builder()
                .addInclude("edge_process_flow")
                .entityBuilder()
                .naming(NamingStrategy.underline_to_camel)
                // .enableLombok()
                .controllerBuilder()
                .enableRestStyle()
                .build();

        // System.out.println(strategyConfig.isSkipView());

        autoGenerator.global(globalConfig);
        autoGenerator.packageInfo(packageConfig);
        autoGenerator.template(templateConfig);
        autoGenerator.strategy(strategyConfig);
        autoGenerator.execute(new FreemarkerTemplateEngine());
    }
}
