package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.service.SummaryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;

/**
 * program prints resume's html code in stdout
 * data about resume getting from resources/concurrently/person1 and person2.properties
 * main thread runs separate threads for reading properties files, join them and collects resulting resume
 */
@SpringBootApplication
public class MainSpringBootMultiThreading {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(MainSpringBootMultiThreading.class, args);
        SummaryService summaryService = (SummaryService) context.getBean("serviceGetsResumeByPartsAndPrintToStOut");
        String pathFilesProperties = new File("").getAbsolutePath() + "\\src\\main\\resources\\concurrently\\";
        String pathFilesIsKeys = String.format("%s%s,%s%s", pathFilesProperties, "person1.properties", pathFilesProperties, "person2.properties");

        summaryService.buildResume(pathFilesIsKeys, "irrelevant");

    }
}
