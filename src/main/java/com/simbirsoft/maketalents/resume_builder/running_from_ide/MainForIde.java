package com.simbirsoft.maketalents.resume_builder.running_from_ide;

import com.simbirsoft.maketalents.resume_builder.MainJar;
import com.simbirsoft.maketalents.resume_builder.launcher.LauncherTypes;

import java.io.File;

/**
 * Main class for running from IDE
 * creates "summary.html" in "src\main\webapp\" by "person.properties" located in "src\main\resources\"
 * not uses spring-boot
 */
public class MainForIde {

    public static void main(String[] args) {
        String pathPropertiesFile = new File("").getAbsolutePath()
                + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "person.properties";
        String pathHtmlFile = new File("").getAbsolutePath()
                + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator;

        MainJar.main(new String[] {LauncherTypes.BASIC.name(), pathPropertiesFile, pathHtmlFile, "summary"});
    }
}
