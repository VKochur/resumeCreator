package com.simbirsoft.maketalents.resume_builder.service;

import com.simbirsoft.maketalents.resume_builder.dal.impl.PropertiesFileLoader;
import com.simbirsoft.maketalents.resume_builder.dal.impl.TagTypes;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumeCodeCreator;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.image.impl.TemplateReplacer;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import org.apache.log4j.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;

/**
 * Service for building html resume by file .properties
 * writes log in "executable dir/resume_builder.log"
 */
public class SummaryService {

    private static Logger logger;
    static {
        logger = Logger.getLogger(SummaryService.class);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));
        try {
            logger.addAppender(new FileAppender(new SimpleLayout(), Util.getPathExecutableDir() + "\\resume_builder.log", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String pathDirPropertiesFile;
    private String propertiesFileName;
    private String pathDirHtmlFile;
    private String htmlFileName;

    public SummaryService(String pathDirPropertiesFile, String propertiesFileName, String pathDirHtmlFile, String htmlFileName) {
        this.pathDirPropertiesFile = pathDirPropertiesFile;
        this.propertiesFileName = propertiesFileName;
        this.pathDirHtmlFile = pathDirHtmlFile;
        this.htmlFileName = htmlFileName;
    }

    public void buildResume() {
        logger.info("Name properties file: " + this.propertiesFileName);
        logger.info("Dir properties file: " + this.pathDirPropertiesFile);
        logger.info("Name html file: " + this.htmlFileName);
        logger.info("Dir html file: " + this.pathDirHtmlFile);

        try {
            HtmlResumeCodeCreator htmlResumeCodeCreator = new TemplateReplacer("html/template.html");
            htmlResumeCodeCreator.setProvider(new PropertiesFileLoader(pathDirPropertiesFile + "\\" + propertiesFileName));
            HtmlResumePrinter htmlResumePrinter = new HtmlResumePrinter();
            htmlResumePrinter.setHtmlResumeCodeCreator(htmlResumeCodeCreator);
            htmlResumePrinter.setPathDirToFile(pathDirHtmlFile);
            htmlResumePrinter.setNameFile(htmlFileName);
            htmlResumePrinter.print();
            logger.info("success");

        } catch (InvalidPropertiesFormatException e) {
            logger.log(Priority.ERROR, "Illegal properties file. Encode file must be UTF-8 without BOM." +
                    " Legal tags: " + Arrays.toString(TagTypes.values()) + "\n " + e.getMessage(), e);
        } catch (IOException e) {
            logger.log(Priority.ERROR, e.getMessage(), e);
        }
    }
}
