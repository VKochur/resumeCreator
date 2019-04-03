package com.simbirsoft.maketalents.resume_builder.running_from_ide;

import com.simbirsoft.maketalents.resume_builder.MainJar;
import com.simbirsoft.maketalents.resume_builder.launcher.LauncherTypes;
import java.io.File;

/**
 * program prints resume's html code in stdout
 * data about resume getting from src/main/temp_for_ide/source/threads/person1 and person2.properties
 * main thread runs separate threads for reading properties files, join them and collects resulting multithreading_summary.html
 * located in src/main/temp_for_ide/target/threads/
 */
public class MainSpringBootMultiThreadingForIde {

    public static void main(String[] args) throws Exception {

        String pathFilesProperties = DataHouse.PATH_TO_SOURCE + "threads" + File.separator;

        String pathToHtml =  DataHouse.PATH_TO_TARGET + "threads" + File.separator;

        MainJar.main(new String[]{LauncherTypes.MULTI_THREADS_SPRING.name(),
                pathFilesProperties + "person1.properties", pathFilesProperties + "person2.properties", pathToHtml + "multithreading_summary.html"});
    }
}
