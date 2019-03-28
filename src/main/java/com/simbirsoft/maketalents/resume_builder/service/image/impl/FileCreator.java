package com.simbirsoft.maketalents.resume_builder.service.image.impl;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Creates text file encode UTF-8
 */
public interface FileCreator {

    String getPathDirToFile();

    void setPathDirToFile(String pathDirToFile);

    String getNameFile();

    void setNameFile(String nameFile);

    /**
     * Creates text file
     *
     * @param content file content
     * @throws IOException in case if not exists specific directory, or no access
     */
    default void createFile(String content) throws IOException {
        File file = new File(
                String.format("%s%s%s",
                new File(getPathDirToFile()).getAbsolutePath(),
                        System.getProperty("file.separator"),
                        getNameFile()));
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8.name()))) {
            writer.write(content);
            writer.flush();
        }
    }

}
