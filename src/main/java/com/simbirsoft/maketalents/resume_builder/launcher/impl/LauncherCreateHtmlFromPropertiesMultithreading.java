package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import com.simbirsoft.maketalents.resume_builder.model.core.SummaryService;
import com.simbirsoft.maketalents.resume_builder.util.Util;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

import static com.simbirsoft.maketalents.resume_builder.launcher.Util.stopWebApplication;

/**
 * Launcher program for creates html by 2 properties files. uses spring-core
 */
@SpringBootApplication
@ComponentScan("com.simbirsoft.maketalents.resume_builder")
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
                "if args.lenght = 0, uses default:\n" +
                "first file = executable dir/person1.properties\n" +
                "second file = executable dir/person2.properties\n" +
                "html file = executable dir/createdFromPerson1Person2.html" +
                "\n" +
                "writes log in 'executable dir/resume_builder.log'";
    }

    @Override
    public void launch(String[] args) {
        ApplicationContext context = SpringApplication.run(LauncherCreateHtmlFromPropertiesMultithreading.class, args);
        try {
            SummaryService summaryService = (SummaryService) context.getBean("serviceGetsResumeByPartsAndCreatesHtml");
            if (args.length == 3) {
                pathFirst = args[0];
                pathSecond = args[1];
                pathHtml = args[2];
            }

            Logger logger = com.simbirsoft.maketalents.resume_builder.launcher.Util.getLogger();
            logger.info("Path to first file: '" + pathFirst + "'");
            logger.info("Path to second file: '" + pathSecond + "'");
            logger.info("Path to html: '" + pathHtml + "'");
            try {
                summaryService.buildResume(pathFirst + "," + pathSecond, pathHtml);
                logger.info("success");
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        } finally {
            stopWebApplication(context);
        }
    }
}
