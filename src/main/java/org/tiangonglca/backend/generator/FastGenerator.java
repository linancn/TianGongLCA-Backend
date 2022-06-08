package org.tiangonglca.backend.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.SQLException;
import java.util.Collections;

public class FastGenerator {

    /**
     * DB Config
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder("jdbc:postgresql://localhost:55434/postgres?currentSchema=public", "postgres", "1357_Qetu");

    /**
     * Run
     */
    public static void main(String[] args) throws SQLException {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG).globalConfig(builder -> {
            builder.author("TianGongLCA")
                    // .fileOverride() // force overwrite files
                    .outputDir("src/main/java/");
        }).packageConfig(builder -> {
            builder.parent("org.tiangonglca.backend").pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src/main/resources/mapper"));
        }).strategyConfig(builder -> {
            builder.enableSchema().addInclude("processes");
            // builder.addInclude("edge_process");
            // builder.addInclude("parameter");
            // builder.addInclude("measurement_flow");
            // builder.addInclude("measurement_base");
            // builder.addInclude("flow_base");
            // builder.addInclude("process");
            // builder.addInclude("plan");
            // builder.addInclude("project");
            // builder.addInclude("elcd__processes"); // table name
            // .addTablePrefix("elcd_"); // table filter
        }).templateEngine(new FreemarkerTemplateEngine()).execute();
    }
}
