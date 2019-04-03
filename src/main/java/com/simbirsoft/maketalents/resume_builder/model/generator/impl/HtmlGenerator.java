package com.simbirsoft.maketalents.resume_builder.model.generator.impl;

import com.simbirsoft.maketalents.resume_builder.model.generator.Generator;
import com.simbirsoft.maketalents.resume_builder.model.presenter.Presenter;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.SimpleLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;

@Component("generatorHtmlFromProperties")
public class HtmlGenerator implements Generator {

    public HtmlGenerator() {
        logger = Logger.getLogger(HtmlGenerator.class);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));
    }

    private Logger logger;

    @Autowired
    @Qualifier("resumeServiceForPropertiesFile")
    private ResumeService resumeService;

    @Autowired
    @Qualifier("createrHtml")
    private Presenter presenter;

    public void generate(String pathPropertiesFile, String pathHtmlFile) {

        logger.info("Name properties file: " + new File(pathPropertiesFile).getName());
        logger.info("Dir properties file: " + new File(pathPropertiesFile).getParent());
        logger.info("Name html file: " + new File(pathHtmlFile).getName());
        logger.info("Dir html file: " + new File(pathHtmlFile).getParent());

        try {
            presenter.present(resumeService.getResumeDto(pathPropertiesFile), pathHtmlFile);
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
