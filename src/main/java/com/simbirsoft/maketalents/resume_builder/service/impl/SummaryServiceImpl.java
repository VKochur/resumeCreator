package com.simbirsoft.maketalents.resume_builder.service.impl;

import com.simbirsoft.maketalents.resume_builder.config.AppConfig;
import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.dao.impl.properties_file_loader.ResumeDaoImpl;
import com.simbirsoft.maketalents.resume_builder.model.image.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.image.impl.html.HtmlResumeCodeCreator;
import com.simbirsoft.maketalents.resume_builder.model.image.impl.html.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.service.SummaryService;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.SimpleLayout;

/*
 * Service prints html file by file .properties. Writes log.
 * By default writes log in System.out
 *
 */
public class SummaryServiceImpl implements SummaryService {

    private static final Logger DEFAULT_LOGGER = Logger.getLogger(SummaryServiceImpl.class);

    static {
        DEFAULT_LOGGER.addAppender(new ConsoleAppender(new SimpleLayout()));
    }

    private String pathDirPropertiesFile;
    private String propertiesFileName;
    private String pathDirHtmlFile;
    private String htmlFileName;

    private Logger logger;

    public SummaryServiceImpl() {
    }

    public SummaryServiceImpl(String pathDirPropertiesFile, String propertiesFileName, String pathDirHtmlFile, String htmlFileName) {
        this.pathDirPropertiesFile = pathDirPropertiesFile;
        this.propertiesFileName = propertiesFileName;
        this.pathDirHtmlFile = pathDirHtmlFile;
        this.htmlFileName = htmlFileName;
        setLogger(DEFAULT_LOGGER);
    }

    @Override
    public ResumeDao getProviderData() {
        return new ResumeDaoImpl(pathDirPropertiesFile + "\\" + propertiesFileName);
    }

    @Override
    public ResumePrinter getPrinterData() {
        HtmlResumePrinter resumePrinter = new HtmlResumePrinter();
        resumePrinter.setHtmlResumeCodeCreator(getCodeCreator());
        resumePrinter.setPathDirToFile(pathDirHtmlFile);
        resumePrinter.setNameFile(htmlFileName);
        return resumePrinter;
    }

    @Override
    public void buildResume() {
        logger.info("Name properties file: " + this.propertiesFileName);
        logger.info("Dir properties file: " + this.pathDirPropertiesFile);
        logger.info("Name html file: " + this.htmlFileName);
        logger.info("Dir html file: " + this.pathDirHtmlFile);

        try {
            getPrinterData().print(getProviderData().getResume());
            logger.info("success");
        } catch (Exception e) {
            logger.log(Priority.ERROR, e.getMessage(), e);
        }
    }

    /**
     * Creates html cod by replace template
     * <p>
     * template is html code in resources
     * All substrings in template like ${keyValue} replaces to value : getSubstitution().getKey(keyValue) = value
     * <p>
     * <p>
     * warning: code, that returns method getPreCode can be different from code in template
     * if code in template not ends with '\n', getPreCode returns template's code and '\n' at the end
     * if code in template ends with '\n', getPreCode returns template's code
     */
    private HtmlResumeCodeCreator getCodeCreator() {
        return new AppConfig().getCodeCreator();
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
