package com.simbirsoft.maketalents.resume_builder.running_from_ide;

import com.simbirsoft.maketalents.resume_builder.MainJar;
import com.simbirsoft.maketalents.resume_builder.launcher.LauncherTypes;

import java.io.File;

/**
 * Main class for running from IDE
 * creates "summary.html" in "src\main\temp_for_ide\target\basic" by "person.properties" located in "src\main\temp_for_ide\source\basic"
 * not uses spring-boot
 */
public class MainBasic {

    public static void main(String[] args) {
        String pathPropertiesFile = DataHouse.PATH_TO_SOURCE + "basic" + File.separator + "person.properties";
        String pathHtmlFile = DataHouse.PATH_TO_SOURCE + "basic" + File.separator;

        MainJar.main(new String[] {LauncherTypes.BASIC.name(), pathPropertiesFile, pathHtmlFile, "summary"});
    }
}
