package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import com.simbirsoft.maketalents.resume_builder.model.generator.Generator;
import com.simbirsoft.maketalents.resume_builder.util.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

import static com.simbirsoft.maketalents.resume_builder.launcher.Util.stopWebApplication;

/**
 * Launcher program for creation html by 2 properties files. uses spring-core
 */
@SpringBootApplication
@ComponentScan("com.simbirsoft.maketalents.resume_builder")
@EnableJpaRepositories(basePackages = {"com.simbirsoft.maketalents.resume_builder.dao.db"})
public class LauncherCreateHtmlFromPropertiesMultithreading implements Launcher {

    private static String pathFirst = Util.getPathExecutableDir() + File.separator + "person1.properties";
    private static String pathSecond = Util.getPathExecutableDir() + File.separator + "person2.properties";
    private static String pathHtml = Util.getPathExecutableDir() + File.separator + "createdFromPerson1Person2.html";

    @Override
    public String getDescription() {
        return "Creates html file by 2 files properties. Reads file in separate threads. Uses spring-core" +
                "Creates resume.html by data from 2 files properties.\n" +
                "Data in fist file properties more important that data from second file\n" +
                "args[0] - path to first properties file, that contains data about resume\n" +
                "args[1] - path to second properties file, that contains data about resume\n" +
                "arg[2] - path to html file for creation resume\n" +
                "\n" +
                "if args.length = 0, uses default:\n" +
                "first file = executable dir/person1.properties\n" +
                "second file = executable dir/person2.properties\n" +
                "html file = executable dir/createdFromPerson1Person2.html" +
                "\n" +
                "writes log in 'executable dir/resume_builder.log'";
    }

    @Override
    public void launch(String[] args) {
        ApplicationContext context = null;
        try {
            context = SpringApplication.run(LauncherCreateHtmlFromPropertiesMultithreading.class, args);
            Generator generator = (Generator) context.getBean("generatorMultiThreadsHtmlFromProperties");
            if (args.length == 3) {
                pathFirst = args[0];
                pathSecond = args[1];
                pathHtml = args[2];
            } else {
                pathFirst = Util.getPathExecutableDir() + File.separator + "person1.properties";
                pathSecond = Util.getPathExecutableDir() + File.separator + "person2.properties";
                pathHtml = Util.getPathExecutableDir() + File.separator + "createdFromPerson1Person2.html";
            }

            generator.setLogger(com.simbirsoft.maketalents.resume_builder.launcher.Util.getLogger());
            generator.generate(pathFirst + "," + pathSecond, pathHtml);
        } finally {
            if (context != null) {
                stopWebApplication(context);
            }
        }
    }
}
