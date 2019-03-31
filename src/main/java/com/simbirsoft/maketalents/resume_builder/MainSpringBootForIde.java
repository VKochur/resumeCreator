package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.model.HtmlGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

/**
 * main for task5 (multithreading) is MainSpringBootMultithreading
 * <p>
 * Main class for running from IDE
 * creates "spring_boot_summary.html" in "src\main\webapp\springboot\" by "spring_boot_person.properties" located in "src\main\resources\springboot\"
 */
@ComponentScan("com.simbirsoft.maketalents.resume_builder")
@SpringBootApplication
public class MainSpringBootForIde {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainSpringBootForIde.class, args);
        HtmlGenerator htmlGenerator = context.getBean(HtmlGenerator.class);
        String pathPropertiesFile = new File("").getAbsolutePath() +
                File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "springboot" + File.separator + "spring_boot_person.properties";
        String pathHtmlFile = new File("").getAbsolutePath() +
                File.separator + "src" + File.separator + "main" + File.separator + "webapp" +
                File.separator + "springboot" + File.separator + "spring_boot_summary.html";
        htmlGenerator.print(pathPropertiesFile, pathHtmlFile);
    }
}
