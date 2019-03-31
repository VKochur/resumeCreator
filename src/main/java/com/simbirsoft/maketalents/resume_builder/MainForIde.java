package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.model.core.SummaryServiceImpl;

import java.io.File;

/**
 * Main class for running from IDE
 * creates "summary.html" in "src\main\webapp\" by "person.properties" located in "src\main\resources\"
 */
public class MainForIde {

    public static void main(String[] args) {
        SummaryServiceImpl summaryService = new SummaryServiceImpl();
        String pathPropertiesFile = new File("").getAbsolutePath()
                + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "person.properties";
        String pathHtmlFile = new File("").getAbsolutePath()
                + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "summary.html";
        summaryService.buildResume(pathPropertiesFile, pathHtmlFile);
    }
}
