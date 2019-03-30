package com.simbirsoft.maketalents.resume_builder.util;

import com.simbirsoft.maketalents.resume_builder.Main;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Util {

    //get directory of executable file (.class or .jar)
    public static String getPathExecutableDir() {
        try {
            URL url = Main.class.getProtectionDomain().getCodeSource().getLocation();
            switch (url.getProtocol()) {
                case "file":
                    File file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
                    return file.getParent();
                case "jar":
                    return solveDirectoryToJar(url.getPath());
                default:
                    throw new IllegalStateException("program is not 'class' file and not 'jar', not able solve executable path");
            }
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }


    private static String solveDirectoryToJar(String path) throws URISyntaxException {
        String cleanFromExclamationMark = path.substring(0, path.indexOf(".jar!"));
        URI uri = new URI(cleanFromExclamationMark);
        return new File(uri).getParent();
    }
}
