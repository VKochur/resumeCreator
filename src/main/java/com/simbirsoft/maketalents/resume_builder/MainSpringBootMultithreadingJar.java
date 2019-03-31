package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.model.core.SummaryService;
import com.simbirsoft.maketalents.resume_builder.util.Util;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;

/**
 * Main class for building jar
 * writes log in "executable dir/resume_builder.log"
 * <p>
 * Reads 2 files properties in different threads, creates html
 */
@ComponentScan("com.simbirsoft.maketalents.resume_builder")
@SpringBootApplication
public class MainSpringBootMultithreadingJar {

    private static Logger logger;

    static {
        logger = Logger.getLogger(MainSpringBootMultithreadingJar.class);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));
        try {
            logger.addAppender(new FileAppender(new SimpleLayout(), Util.getPathExecutableDir() + File.separator + "resume_builder.log", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String pathFirst = Util.getPathExecutableDir() + File.separator + "person1.properties";
    private static String pathSecond = Util.getPathExecutableDir() + File.separator + "person2.properties";
    private static String pathHtml = Util.getPathExecutableDir() + File.separator + "createdFromPerson1Person2.html";

    /**
     * Creates resume.html by data from 2 files properties.
     * Data in fist file properties more important that data from second file
     *
     * @param args paths to files
     *             args[0] - path to first properties file, that contains data about resume
     *             args[1] - path to second properties file, that contains data about resume
     *             arg[2] - path to html file for creation resume
     *             <p>
     *             if args.lenght = 0, uses default:
     *             first file = executable dir/person1.properties
     *             second file = executable dir/person2.properties
     *             html file = executable dir/createdFromPerson1Person2.html
     */
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(MainSpringBootMultiThreadingForIde.class, args);
        SummaryService summaryService = (SummaryService) context.getBean("serviceGetsResumeByPartsAndCreatesHtml");
        if (args.length == 3) {
            pathFirst = args[0];
            pathSecond = args[1];
            pathHtml = args[2];
        }

        logger.info("Path to first file: '" + pathFirst + "'");
        logger.info("Path to second file: '" + pathSecond + "'");
        logger.info("Path to html: '" + pathHtml + "'");
        try {
            summaryService.buildResume( pathFirst + "," + pathSecond, pathHtml);
            logger.info("success");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
