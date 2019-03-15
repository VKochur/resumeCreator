package com.simbirsoft.maketalents.resume_builder.dao.impl.properties_file_loader;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ResumeDaoImplTest {

    private String definePathTestClasses() throws URISyntaxException {
        return (new File(ResumeDaoImplTest.class.getProtectionDomain().getCodeSource().getLocation().toURI())).getAbsolutePath();
    }

    @Test
    public void testGetResume1() throws IOException, URISyntaxException {
        Resume resume = new ResumeDaoImpl(definePathTestClasses() + "\\test.properties").getResume();
        assertEquals("", resume.getName());
        assertEquals("testDOB", resume.getDateOfBorn());
        assertEquals(Arrays.asList("test", "test@gmail.com"), resume.getEmails());
        assertEquals(Arrays.asList("80011"), resume.getPhoneNumbers());
        assertEquals("", resume.getSkypeLogin());
        assertEquals("", resume.getUrlAvatar());
        assertEquals(Arrays.asList(""), resume.getTargets());
        assertEquals(Arrays.asList(""), resume.getExperiences());
        assertEquals(Arrays.asList(""), resume.getBasicEducations());
        assertEquals(Arrays.asList(""), resume.getBasicEducations());
        assertEquals(Arrays.asList(""), resume.getAdditionalEducations());
        assertEquals("", resume.getOtherInfo());
        assertEquals("", resume.getCareerTarget());
    }

    @Test
    public void testGetResume2() throws IOException, URISyntaxException {
        Resume resume = new ResumeDaoImpl(definePathTestClasses() + "\\test2.properties").getResume();
        assertEquals("testFIO", resume.getName());
        assertEquals("testDOB", resume.getDateOfBorn());
        assertEquals(Arrays.asList("test", "test@gmail.com"), resume.getEmails());
        assertEquals(Arrays.asList("80011"), resume.getPhoneNumbers());
        assertEquals("login|login2", resume.getSkypeLogin());
        assertEquals("https://www04fddb2797c033b087c4247630b2db7.jpg", resume.getUrlAvatar());
        assertEquals(Arrays.asList("Устройство Java junior.", "Прохождение курсов IT.Place"), resume.getTargets());
        assertEquals(Arrays.asList("организация1", "организация2", "организация3"), resume.getExperiences());
        assertEquals(Arrays.asList("образование1", "образование2"), resume.getBasicEducations());
        assertEquals(Arrays.asList("доп", "доп2", "доп3"), resume.getAdditionalEducations());
        assertEquals("дополнительная информация", resume.getOtherInfo());
        assertEquals("должность", resume.getCareerTarget());
    }

    @Test (expected = IOException.class)
    public void testGetResume3() throws IOException, URISyntaxException {
        new ResumeDaoImpl(definePathTestClasses() + "\\notexists.properties");
    }
}
