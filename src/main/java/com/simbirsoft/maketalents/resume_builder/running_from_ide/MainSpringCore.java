package com.simbirsoft.maketalents.resume_builder.running_from_ide;

import com.simbirsoft.maketalents.resume_builder.MainJar;
import com.simbirsoft.maketalents.resume_builder.launcher.LauncherTypes;

import java.io.File;

/**
 * Main class for running from IDE
 * creates "spring_boot_summary.html" in "src\main\temp_for_ide\target\spring-core\" by "spring_boot_person.properties" located in "src\main\temp_for_ide\source\spring-core\"
 * uses spring-boot core
 */

public class MainSpringCore {

    public static void main(String[] args) {
        String pathPropertiesFile = DataHouse.PATH_TO_SOURCE + "spring-core" + File.separator + "spring_boot_person.properties";
        String pathDirHtmlFile = DataHouse.PATH_TO_TARGET + "spring-core" + File.separator;

        MainJar.main(new String[]{LauncherTypes.SPRING.name(), pathPropertiesFile, pathDirHtmlFile, "spring_boot_summary"});
    }
}
