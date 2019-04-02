package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.launcher.LauncherTypes;
import java.io.File;

/**
 * program prints resume's html code in stdout
 * data about resume getting from resources/concurrently/person1 and person2.properties
 * main thread runs separate threads for reading properties files, join them and collects resulting resume
 */
public class MainSpringBootMultiThreadingForIde {

    public static void main(String[] args) throws Exception {

        String pathFilesProperties = new File("").getAbsolutePath() +
                File.separator +
                "src" +
                File.separator +
                "main" +
                File.separator +
                "resources" +
                File.separator +
                "concurrently" +
                File.separator;

        String pathToHtml =  new File("").getAbsolutePath() +
                File.separator +
                "src" +
                File.separator +
                "main" +
                File.separator +
                "webapp" +
                File.separator +
                "concurrently" +
                File.separator;

        MainJar.main(new String[]{LauncherTypes.MULTI_THREADS_SPRING.name(),
                pathFilesProperties + "person1.properties", pathFilesProperties + "person2.properties", pathToHtml + "multithreading_summary.html"});
    }
}
