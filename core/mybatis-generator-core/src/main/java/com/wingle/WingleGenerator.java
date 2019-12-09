/**
 *    Copyright 2006-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
