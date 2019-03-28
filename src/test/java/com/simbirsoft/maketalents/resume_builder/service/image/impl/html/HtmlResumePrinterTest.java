package com.simbirsoft.maketalents.resume_builder.service.image.impl.html;

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

        String fileName = "testHtmlResumePrinter.html";
        File workDir = new File(Util.definePathTestClasses() + "\\work");
        File html = new File(workDir.getAbsolutePath()+"\\" + fileName);
        if (workDir.exists()){
            throw new IllegalStateException("Cannot run test. Dir already exists: " + workDir);
        } else {
            try {
                workDir.mkdir();
                resumePrinter.print(resume, workDir + "\\" + fileName);
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
        resumePrinter.print(new Resume(), Util.definePathTestClasses() + "\\work\\test");
    }
}