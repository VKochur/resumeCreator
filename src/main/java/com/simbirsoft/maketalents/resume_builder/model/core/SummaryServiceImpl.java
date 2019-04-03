package com.simbirsoft.maketalents.resume_builder.model.core;

import com.simbirsoft.maketalents.resume_builder.model.core.data.ManagerDataSource;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.properties_file_loader.ManagerDataSourceImpl;
import com.simbirsoft.maketalents.resume_builder.model.core.image.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.HtmlResumeCodeCreator;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.ReplacerHtmlCodeByTemplate;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.SimpleLayout;

import java.io.File;

/*
 * Service prints html file by file .properties. Writes log.
 * By default writes log in System.out
 *
 */
public class SummaryServiceImpl implements SummaryService {

    private Logger logger;

    public SummaryServiceImpl() {
        logger = Logger.getLogger(SummaryServiceImpl.class);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));
        setLogger(logger);
    }

    @Override
    public ManagerDataSource getProviderData() {
        return new ManagerDataSourceImpl();
    }

    @Override
    public ResumePrinter getPrinterData() {
        HtmlResumePrinter resumePrinter = new HtmlResumePrinter();
        resumePrinter.setHtmlResumeCodeCreator(getCodeCreator());
        return resumePrinter;
    }

    @Override
    public void buildResume(String pathPropertiesFile, String pathHtmlFile) {
        logger.info("Name properties file: " + new File(pathPropertiesFile).getName());
        logger.info("Dir properties file: " + new File(pathPropertiesFile).getParent());
        logger.info("Name html file: " + new File(pathHtmlFile).getName());
        logger.info("Dir html file: " + new File(pathHtmlFile).getParent());

        try {
            getPrinterData().print(getProviderData().getResume(pathPropertiesFile), pathHtmlFile);
            logger.info("success");
        } catch (Exception e) {
            logger.log(Priority.ERROR, e.getMessage(), e);
        }
    }

    private HtmlResumeCodeCreator getCodeCreator() {
        return new ReplacerHtmlCodeByTemplate();
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
