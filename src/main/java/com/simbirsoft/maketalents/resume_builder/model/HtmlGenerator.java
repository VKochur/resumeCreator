package com.simbirsoft.maketalents.resume_builder.model;

import com.simbirsoft.maketalents.resume_builder.dto.Util;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.core.image.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.SimpleLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class HtmlGenerator{

    public HtmlGenerator() {
        logger = Logger.getLogger(HtmlGenerator.class);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));
    }

    private Logger logger;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    @Qualifier("resumePrinterByReplaceTemplate")
    private ResumePrinter resumePrinter;

    @Autowired
    Util util;

    public void print(String pathPropertiesFile, String pathHtmlFile) {
        logger.info("Name properties file: " + new File(pathPropertiesFile).getName());
        logger.info("Dir properties file: " + new File(pathPropertiesFile).getParent());
        logger.info("Name html file: " + new File(pathHtmlFile).getName());
        logger.info("Dir html file: " + new File(pathHtmlFile).getParent());

        try {
            Resume resume = util.getResumeByDTO(resumeService.getResumeDto(pathPropertiesFile));
            resumePrinter.print(resume, pathHtmlFile);
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
