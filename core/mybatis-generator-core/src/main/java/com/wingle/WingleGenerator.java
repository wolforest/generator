package com.wingle;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * com.wingle
 *
 * @author Wingle
 * @since 2019/12/7 11:46 下午
 **/
public class WingleGenerator {
    public static void main(String[] args) throws Exception {
        new WingleGenerator().execute();
    }

    public void execute() throws Exception {
        InputStream configInputStream  =  this.getClass().getResourceAsStream("/generatorConfig.xml");

        ConfigurationParser parser = new ConfigurationParser(null);
        Configuration config = parser.parseConfiguration(configInputStream);

        ShellCallback callback = new DefaultShellCallback(true);
        List<String> warnings = new ArrayList<>();
        MyBatisGenerator generator = new MyBatisGenerator(config, callback,warnings);

        ProgressCallback progressCallback = new ProgressCallback();
        Set<String> contextsToRun = new HashSet<>();
        Set<String> fullyQualifiedTables = new HashSet<>();

        generator.generate(progressCallback, contextsToRun, fullyQualifiedTables);
    }
}
