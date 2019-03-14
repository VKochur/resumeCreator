package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.image.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.image.impl.FileCreator;

import java.io.*;

/**
 * Creates file html.
 */
public class HtmlResumePrinter implements ResumePrinter, FileCreator {

    private static final String DEFAULT_DIR_FILE = "";
    private static final String DEFAULT_NAME_FILE = "";
    private String pathDirToFile;
    private String nameFile;
    private HtmlResumeCodeCreator htmlResumeCodeCreator;

    public HtmlResumePrinter() {
        pathDirToFile = DEFAULT_DIR_FILE;
        nameFile = DEFAULT_NAME_FILE;
    }

    public void setHtmlResumeCodeCreator(HtmlResumeCodeCreator htmlResumeCodeCreator) {
        this.htmlResumeCodeCreator = htmlResumeCodeCreator;
    }

    /**
     * Method creates file html
     *
     * @throws IOException if not able to create html
     */
    @Override
    public void print(Resume resume) throws IOException {
        htmlResumeCodeCreator.setResume(resume);
        createFile(htmlResumeCodeCreator.getHtmlCode(), "html");
    }

    @Override
    public String getPathDirToFile() {
        return pathDirToFile;
    }

    @Override
    public void setPathDirToFile(String pathDirToFile) {
        this.pathDirToFile = pathDirToFile;
    }

    @Override
    public String getNameFile() {
        return nameFile;
    }

    @Override
    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
