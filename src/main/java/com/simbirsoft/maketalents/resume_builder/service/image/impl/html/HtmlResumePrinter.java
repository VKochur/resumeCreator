package com.simbirsoft.maketalents.resume_builder.service.image.impl.html;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.service.image.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.service.image.impl.FileCreator;

import java.io.File;
import java.io.IOException;

/**
 * Creates file html.
 */
public class HtmlResumePrinter implements ResumePrinter {

    private HtmlResumeCodeCreator htmlResumeCodeCreator;
    private FileCreator fileCreator;

    public HtmlResumePrinter() {
        fileCreator = new FileCreator() {

            private String pathDirToFile;
            private String nameFile;

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
        };
    }

    public void setHtmlResumeCodeCreator(HtmlResumeCodeCreator htmlResumeCodeCreator) {
        this.htmlResumeCodeCreator = htmlResumeCodeCreator;
    }

    /**
     * Method creates file html
     *
     * @param resume
     * @param pathHtmlFile pathHtmlFile path to file for creation
     * @throws IOException if not able to create html
     */
    @Override
    public void print(Resume resume, String pathHtmlFile) throws Exception {
        htmlResumeCodeCreator.setResume(resume);
        fileCreator.setNameFile((new File(pathHtmlFile).getName()));
        fileCreator.setPathDirToFile(new File(pathHtmlFile).getParent());
        fileCreator.createFile(htmlResumeCodeCreator.getHtmlCode());
    }
}
