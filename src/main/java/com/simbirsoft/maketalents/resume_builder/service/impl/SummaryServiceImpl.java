package com.simbirsoft.maketalents.resume_builder.service.impl;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.dao.impl.properties_file_loader.ResumeDaoImpl;
import com.simbirsoft.maketalents.resume_builder.model.image.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.image.impl.html.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.image.impl.html.ReplacerCodeByTemplate;
import com.simbirsoft.maketalents.resume_builder.service.SummaryService;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import org.apache.log4j.*;

import java.io.IOException;

/*
 * Service prints html file by file .properties
 * writes log in "executable dir/resume_builder.log"
 */
public class SummaryServiceImpl implements SummaryService {

    private static Logger logger;
    static {
        logger = Logger.getLogger(SummaryServiceImpl.class);
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

    public SummaryServiceImpl(String pathDirPropertiesFile, String propertiesFileName, String pathDirHtmlFile, String htmlFileName) {
        this.pathDirPropertiesFile = pathDirPropertiesFile;
        this.propertiesFileName = propertiesFileName;
        this.pathDirHtmlFile = pathDirHtmlFile;
        this.htmlFileName = htmlFileName;
    }

    @Override
    public ResumeDao getProviderData() {
        return new ResumeDaoImpl(pathDirPropertiesFile + "\\" + propertiesFileName);
    }

    @Override
    public ResumePrinter getPrinterData() {
        HtmlResumePrinter resumePrinter = new HtmlResumePrinter();
        resumePrinter.setHtmlResumeCodeCreator(new ReplacerCodeByTemplate("html/template.html"));
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
}
