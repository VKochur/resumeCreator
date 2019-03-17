package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.tests_util.Util;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

public class HtmlResumePrinterTest {

    @Test
    public void testPrint() throws Exception {
        Resume resume = new Resume();
        String name = "fio";
        String dateOfBorn = "dob";
        resume.setName(name);
        resume.setDateOfBorn(dateOfBorn);

        HtmlResumePrinter resumePrinter = new HtmlResumePrinter();
        resumePrinter.setHtmlResumeCodeCreator(new HtmlResumeCodeCreator() {
            @Override
            public String getHtmlCode() {
                return getResume().getName() + getResume().getDateOfBorn();
            }
        });

        String fileName = "testHtmlResumePrinter";
        File workDir = new File(Util.definePathTestClasses() + "\\work");
        File html = new File(workDir.getAbsolutePath()+"\\" + fileName + ".html");
        if (workDir.exists()){
            throw new IllegalStateException("Cannot run test. Dir already exists: " + workDir);
        } else {
            try {
                workDir.mkdir();
                resumePrinter.setPathDirToFile(workDir.getAbsolutePath());
                resumePrinter.setNameFile(fileName);
                resumePrinter.print(resume);
                Util.checkContentFile(html, "fiodob\n");
            } finally {
                Files.deleteIfExists(html.toPath());
                Files.delete(workDir.toPath());
            }
        }
    }

    @Test(expected = IOException.class)
    public void testPrint2() throws Exception {
        HtmlResumePrinter resumePrinter = new HtmlResumePrinter();
        resumePrinter.setHtmlResumeCodeCreator(new HtmlResumeCodeCreator() {
            @Override
            public String getHtmlCode() {
                return null;
            }
        });
        resumePrinter.setNameFile("test");
        resumePrinter.setPathDirToFile(Util.definePathTestClasses() + "\\work");
        resumePrinter.print(new Resume());
    }
}