package com.simbirsoft.maketalents.resume_builder.tests_util;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class Util {

    public static String definePathTestClasses() throws URISyntaxException {
        return (new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI())).getAbsolutePath();
    }

    public static void checkContentFile(File html, String expectedContent) throws IOException {
        StringBuilder actualContent = new StringBuilder();
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(html), StandardCharsets.UTF_8.name()))){
            while ((line = bufferedReader.readLine()) != null) {
                actualContent.append(line).append("\n");
            }
            assertEquals(expectedContent, actualContent.toString());
        }
    }
}
