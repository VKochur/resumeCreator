package com.simbirsoft.maketalents.resume_builder.dao.impl.properties_file_loader;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

import com.simbirsoft.maketalents.resume_builder.tests_util.Util;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ResumeDaoImplTest {


    @Test
    public void testGetResume1() throws IOException, URISyntaxException {
        Resume resume = new ResumeDaoImpl(Util.definePathTestClasses() + "\\test.properties").getResume();
        assertEquals("", resume.getName());
        assertEquals("testDOB", resume.getDateOfBorn());
        assertEquals(Arrays.asList("test", "test@gmail.com"), resume.getEmails());
        assertEquals(Collections.singletonList("80011"), resume.getPhoneNumbers());
        assertEquals("", resume.getSkypeLogin());
        assertEquals("", resume.getUrlAvatar());
        assertEquals(Collections.singletonList(""), resume.getTargets());
        assertEquals(Collections.singletonList(""), resume.getExperiences());
        assertEquals(Collections.singletonList(""), resume.getBasicEducations());
        assertEquals(Collections.singletonList(""), resume.getBasicEducations());
        assertEquals(Collections.singletonList(""), resume.getAdditionalEducations());
        assertEquals("", resume.getOtherInfo());
        assertEquals("", resume.getCareerTarget());
        assertEquals("check skills", new HashMap<>(), resume.getSkills());
    }

    @Test
    public void testGetResume2() throws IOException, URISyntaxException {
        Resume resume = new ResumeDaoImpl(Util.definePathTestClasses() + "\\test2.properties").getResume();
        assertEquals("testFIO", resume.getName());
        assertEquals("testDOB", resume.getDateOfBorn());
        assertEquals(Arrays.asList("test", "test@gmail.com"), resume.getEmails());
        assertEquals(Collections.singletonList("80011"), resume.getPhoneNumbers());
        assertEquals("login|login2", resume.getSkypeLogin());
        assertEquals("https://www04fddb2797c033b087c4247630b2db7.jpg", resume.getUrlAvatar());
        assertEquals(Arrays.asList("Устройство Java junior.", "Прохождение курсов IT.Place"), resume.getTargets());
        assertEquals(Arrays.asList("организация1", "организация2", "организация3"), resume.getExperiences());
        assertEquals(Arrays.asList("образование1", "образование2"), resume.getBasicEducations());
        assertEquals(Arrays.asList("доп", "доп2", "доп3"), resume.getAdditionalEducations());
        assertEquals("дополнительная информация", resume.getOtherInfo());
        assertEquals("должность", resume.getCareerTarget());

        Map<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("c++", 12);
        expectedSkills.put("java", 12);
        expectedSkills.put("IIdea", 6);
        assertEquals("check skills", expectedSkills, resume.getSkills());
    }

    @Test (expected = IOException.class)
    public void testGetResume3() throws IOException, URISyntaxException {
        new ResumeDaoImpl(Util.definePathTestClasses() + "\\notexists.properties").getResume();
    }

    @Test
    public void testGetResume4() throws IOException, URISyntaxException {
        Resume resume = new ResumeDaoImpl(Util.definePathTestClasses() + "\\test3.properties").getResume();
        Map<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("java", -1);
        expectedSkills.put("sql", -1);
        assertEquals("check skills", expectedSkills, resume.getSkills());
    }
}
