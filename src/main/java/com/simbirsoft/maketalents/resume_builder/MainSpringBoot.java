package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.model.HtmlGenerator;
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
 */
@ComponentScan("com.simbirsoft.maketalents.resume_builder")
@SpringBootApplication
public class MainSpringBoot {
/*
    todo удалить
    статический логгер, так
    как контекст
    спринга вне
    зависимости от
    прямого вызова
    */
    private static Logger logger;

    static {
        logger = Logger.getLogger(MainSpringBoot.class);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));
        try {
            logger.addAppender(new FileAppender(new SimpleLayout(), Util.getPathExecutableDir() + File.separator + "resume_builder.log", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String DEFAULT_NAME_PROPERTY_FILE = "resume.properties";
    private static final String DEFAULT_NAME_HTML_FILE = "resume";

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(MainSpringBoot.class, args);
        HtmlGenerator htmlGenerator = context.getBean(HtmlGenerator.class);

        String pathPropertiesFile;
        String pathDirHtmlFile;
        String htmlFileName;

        if (args.length > 2) {
            pathPropertiesFile = args[0];
            pathDirHtmlFile = args[1];
            htmlFileName = args[2];
        } else {
            pathPropertiesFile = Util.getPathExecutableDir() + File.separator + DEFAULT_NAME_PROPERTY_FILE;
            pathDirHtmlFile = Util.getPathExecutableDir();
            htmlFileName = DEFAULT_NAME_HTML_FILE;
        }

        htmlGenerator.setLogger(MainSpringBoot.logger);
        htmlGenerator.print(pathPropertiesFile, pathDirHtmlFile + File.separator + htmlFileName + ".html");
    }
}
