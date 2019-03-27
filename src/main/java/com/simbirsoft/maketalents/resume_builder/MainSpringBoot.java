package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.service.SummaryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * program prints resume's html code in stdout
 * data about resume getting from resources/concurrently/person1 and person2.properties
 * main thread runs separate threads for reading properties files, join them and collects resulting resume
 */
@SpringBootApplication
public class MainSpringBoot {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(MainSpringBoot.class, args);
        System.out.println("------------------");
        SummaryService summaryService = (SummaryService) context.getBean("serviceGetsResumeByPartsAndPrintToStOut");
        summaryService.buildResume();
        System.out.println("------------------");
    }
}
