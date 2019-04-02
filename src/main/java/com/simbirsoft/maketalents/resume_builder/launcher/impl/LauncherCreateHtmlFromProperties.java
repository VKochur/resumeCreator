package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import com.simbirsoft.maketalents.resume_builder.model.core.SummaryServiceImpl;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.File;

/**
 * Launcher program for creates html by properties. not uses spring-core
 */
public class LauncherCreateHtmlFromProperties implements Launcher {

    private static final String DEFAULT_NAME_PROPERTY_FILE = "resume.properties";
    private static final String DEFAULT_NAME_HTML_FILE = "resume";

    @Override
    public String getDescription() {
        return "Creates html file by file properties. Not uses spring-core.\n" +
                "if args.length <= 2, uses DEFAULT_NAME_PROPERTY_FILE = 'resume.properties', DEFAULT_NAME_HTML_FILE = 'resume' and executable dir\n" +
                "else args[0] - path to file .properties, args[1] - path to dir for html file, args[2] - name for html file.\n" +
                "\n" +
                "writes log in 'executable dir/resume_builder.log'";
    }

    @Override
    public void launch(String[] args) {
        SummaryServiceImpl summaryService = new SummaryServiceImpl();
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
        summaryService.setLogger(com.simbirsoft.maketalents.resume_builder.launcher.Util.getLogger());
        summaryService.buildResume(pathPropertiesFile, pathDirHtmlFile + File.separator + htmlFileName + ".html");
    }
}
