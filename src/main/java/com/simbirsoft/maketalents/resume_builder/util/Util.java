package com.simbirsoft.maketalents.resume_builder.util;

import com.simbirsoft.maketalents.resume_builder.Main;

import java.io.File;
import java.net.URISyntaxException;

public class Util {

    //get directory of executable file
    public static String getPathExecutableDir() {
        File file = null;
        try {
            file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file.getParent();
    }
}
