package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.service.SummaryService;

import java.io.File;


/**
 * Main class for running from IDE
 * creates "summary.html" in "src\main\webapp\" by "person.properties" located in "src\main\resources\"
 */
public class MainForIde {

    public static void main(String[] args) {
        SummaryService summaryService = new SummaryService(
                new File("").getAbsolutePath() + "\\src\\main\\resources",
                "person.properties",
                new File("").getAbsolutePath() + "\\src\\main\\webapp",
                "summary");
        summaryService.buildResume();
    }
}
