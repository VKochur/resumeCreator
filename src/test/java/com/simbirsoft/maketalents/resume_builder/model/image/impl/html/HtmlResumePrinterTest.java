package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import com.simbirsoft.maketalents.resume_builder.dao.impl.properties_file_loader.ResumeDaoImplTest;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class HtmlResumePrinterTest {

    private String definePathTestClasses() throws URISyntaxException {
        return (new File(ResumeDaoImplTest.class.getProtectionDomain().getCodeSource().getLocation().toURI())).getAbsolutePath();
    }

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
        File workDir = new File(definePathTestClasses() + "\\work");
        File html = new File(workDir.getAbsolutePath()+"\\" + fileName + ".html");
        if (workDir.exists()){
            throw new IllegalStateException("Cannot run test. Dir already exists: " + workDir);
        } else {
            try {
                workDir.mkdir();
                resumePrinter.setPathDirToFile(workDir.getAbsolutePath());
                resumePrinter.setNameFile(fileName);
                resumePrinter.print(resume);
                checkContentFile(html, "fiodob");
            } finally {
                Files.deleteIfExists(html.toPath());
                Files.delete(workDir.toPath());
            }
        }
    }

    private void checkContentFile(File html, String expectedContent) throws IOException {
        StringBuilder actualContent = new StringBuilder();
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(html), StandardCharsets.UTF_8.name()))){
            while ((line = bufferedReader.readLine()) != null) {
               actualContent.append(line);
            }
         assertEquals(expectedContent, actualContent.toString());
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
        resumePrinter.setPathDirToFile(definePathTestClasses() + "\\work");
        resumePrinter.print(new Resume());
    }
}