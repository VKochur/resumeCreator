package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.service.impl.SummaryServiceImpl;

import java.io.File;


/**
 * Main class for running from IDE
 * creates "summary.html" in "src\main\webapp\" by "person.properties" located in "src\main\resources\"
 */
public class MainForIde {

    public static void main(String[] args) {
        SummaryServiceImpl summaryService = new SummaryServiceImpl();
        summaryService.setPathDirHtmlFile(new File("").getAbsolutePath() + "\\src\\main\\webapp");
        summaryService.setHtmlFileName("summary");
        summaryService.buildResume(new File("").getAbsolutePath() + "\\src\\main\\resources\\person.properties");
    }
}
