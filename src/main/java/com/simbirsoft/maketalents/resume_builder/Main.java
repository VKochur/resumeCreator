package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.service.impl.SummaryServiceImpl;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.File;

/**
 * Main class for building jar
 */
public class Main {

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
        SummaryServiceImpl summaryService;
        if (pathFiles.length > 2) {
            summaryService = new SummaryServiceImpl(new File(pathFiles[0]).getParent(), new File(pathFiles[0]).getName(), pathFiles[1], pathFiles[2]);
        } else {
            summaryService = new SummaryServiceImpl(Util.getPathExecutableDir(), DEFAULT_NAME_PROPERTY_FILE, Util.getPathExecutableDir(), DEFAULT_NAME_HTML_FILE);
        }
        summaryService.buildResume();
    }
}
