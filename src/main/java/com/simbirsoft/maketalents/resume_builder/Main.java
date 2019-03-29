package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.model.core.SummaryServiceImpl;
import com.simbirsoft.maketalents.resume_builder.util.Util;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import java.io.IOException;

/**
 * Main class for building jar
 * writes log in "executable dir/resume_builder.log"
 *
 */
public class Main {

    private static Logger logger;
    static {
        logger = Logger.getLogger(Main.class);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));
        try {
            logger.addAppender(new FileAppender(new SimpleLayout(), Util.getPathExecutableDir() + "\\resume_builder.log", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String DEFAULT_NAME_PROPERTY_FILE = "resume.properties";
    private static final String DEFAULT_NAME_HTML_FILE = "resume";

    public static void main(String[] args) {
        buildResume(args);
    }

    /**
     * Creates html file by file .properties
     * @param pathFiles
     * if pathFiles.length <= 2, uses DEFAULT_NAME_PROPERTY_FILE, DEFAULT_NAME_HTML_FILE and executable dir
     * else pathFiles[0] - path to file .properties, pathFiles[1] - path to dir for html file, pathFiles[2] - name for html file
     */
    private static void buildResume(String... pathFiles) {
        SummaryServiceImpl summaryService = new SummaryServiceImpl();
        String pathPropertiesFile;
        String pathDirHtmlFile;
        String htmlFileName;
        if (pathFiles.length > 2) {
            pathPropertiesFile = pathFiles[0];
            pathDirHtmlFile = pathFiles[1];
            htmlFileName = pathFiles[2];
        } else {
            pathPropertiesFile = Util.getPathExecutableDir() + "\\" + DEFAULT_NAME_PROPERTY_FILE;
            pathDirHtmlFile = Util.getPathExecutableDir();
            htmlFileName = DEFAULT_NAME_HTML_FILE;
        }
        summaryService.setLogger(Main.logger);
        summaryService.buildResume(pathPropertiesFile, pathDirHtmlFile + "\\" + htmlFileName + ".html");
    }
}
