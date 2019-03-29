package com.simbirsoft.maketalents.resume_builder.model;

import com.simbirsoft.maketalents.resume_builder.model.core.SummaryServiceImpl;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.HtmlResumeCodeCreator;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.HtmlResumePrinter;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.SimpleLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class HtmlGenerator{

    private static final Logger DEFAULT_LOGGER = Logger.getLogger(SummaryServiceImpl.class);

    static {
        DEFAULT_LOGGER.addAppender(new ConsoleAppender(new SimpleLayout()));
    }

    public HtmlGenerator() {
        setLogger(DEFAULT_LOGGER);
    }

    private Logger logger;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private HtmlResumeCodeCreator htmlResumeCodeCreator;

    public void print(String pathPropertiesFile, String pathHtmlFile) {
        HtmlResumePrinter htmlResumePrinter = new HtmlResumePrinter();
        htmlResumePrinter.setHtmlResumeCodeCreator(htmlResumeCodeCreator);

        logger.info("Name properties file: " + new File(pathPropertiesFile).getName());
        logger.info("Dir properties file: " + new File(pathPropertiesFile).getParent());
        logger.info("Name html file: " + new File(pathHtmlFile).getName());
        logger.info("Dir html file: " + new File(pathHtmlFile).getParent());

        try {
            htmlResumePrinter.print(resumeService.getResume(pathPropertiesFile), pathHtmlFile);
            logger.info("success");
        } catch (Exception e) {
            logger.log(Priority.ERROR, e.getMessage(), e);
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
