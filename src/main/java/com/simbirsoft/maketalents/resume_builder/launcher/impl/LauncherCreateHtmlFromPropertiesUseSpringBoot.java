package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import com.simbirsoft.maketalents.resume_builder.model.generator.Generator;
import com.simbirsoft.maketalents.resume_builder.util.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

import static com.simbirsoft.maketalents.resume_builder.launcher.Util.stopWebApplication;

/**
 * Launcher program for creates html by properties. uses spring-core
 */
@SpringBootApplication
@ComponentScan("com.simbirsoft.maketalents.resume_builder")
@EnableJpaRepositories(basePackages = {"com.simbirsoft.maketalents.resume_builder.dao.db"})
public class LauncherCreateHtmlFromPropertiesUseSpringBoot implements Launcher {

    private static final String DEFAULT_NAME_PROPERTY_FILE = "resume.properties";
    private static final String DEFAULT_NAME_HTML_FILE = "resume";

    @Override
    public String getDescription() {
        return "Creates html file by file properties. Uses spring-core. Dependence injection\n" +
                "if args.length <= 2, uses DEFAULT_NAME_PROPERTY_FILE = 'resume.properties', DEFAULT_NAME_HTML_FILE = 'resume' and executable dir\n" +
                "else args[0] - path to file .properties, args[1] - path to dir for html file, args[2] - name for html file.\n" +
                "\n" +
                "writes log in 'executable dir/resume_builder.log'";
    }

    @Override
    public void launch(String[] args) {
        ApplicationContext context = null;
        try {
            context = SpringApplication.run(LauncherCreateHtmlFromPropertiesUseSpringBoot.class, args);
            Generator generator = (Generator) context.getBean("generatorHtmlFromProperties");
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
            generator.setLogger(com.simbirsoft.maketalents.resume_builder.launcher.Util.getLogger());
            generator.generate(pathPropertiesFile, pathDirHtmlFile + File.separator + htmlFileName + ".html");
        } finally {
            if (context != null) {
                stopWebApplication(context);
            }
        }
    }
}
