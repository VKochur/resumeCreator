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

@Component("generatorMultiThreadsHtmlFromProperties")
public class MultiThreadingHtmlGenerator implements Generator {

    public MultiThreadingHtmlGenerator() {
        logger = Logger.getLogger(MultiThreadingHtmlGenerator.class);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));
    }

    private Logger logger;

    @Autowired
    @Qualifier("resumeServiceForPropertiesFileMultiThreading")
    private ResumeService resumeService;

    @Autowired
    @Qualifier("createrHtml")
    private Presenter presenter;

    public void generate(String compositePath, String pathHtmlFile) {
        String[] paths = compositePath.split(",");

        logger.info("Path to first file: '" + paths[0] + "'");
        logger.info("Path to second file: '" + paths[1] + "'");
        logger.info("Path to html: '" + pathHtmlFile + "'");

        try {
            presenter.present(resumeService.getResumeDto(compositePath), pathHtmlFile);
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
