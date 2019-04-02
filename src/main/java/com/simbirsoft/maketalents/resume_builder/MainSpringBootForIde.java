package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.launcher.LauncherTypes;

import java.io.File;

/**
 * Main class for running from IDE
 * creates "spring_boot_summary.html" in "src\main\webapp\springboot\" by "spring_boot_person.properties" located in "src\main\resources\springboot\"
 * uses spring-boot core
 */

public class MainSpringBootForIde {

    public static void main(String[] args) {
        String pathPropertiesFile = new File("").getAbsolutePath() +
                File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "springboot" + File.separator + "spring_boot_person.properties";
        String pathDirHtmlFile = new File("").getAbsolutePath() +
                File.separator + "src" + File.separator + "main" + File.separator + "webapp" +
                File.separator + "springboot" + File.separator;

        MainJar.main(new String[]{LauncherTypes.SPRING.name(), pathPropertiesFile, pathDirHtmlFile, "spring_boot_summary"});
    }
}
