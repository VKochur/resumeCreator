package com.simbirsoft.maketalents.resume_builder.model.core.data.impl.properties_file_loader;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.tests_util.Util;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ManagerDataSourceImplTest {

    @Test
    public void testGetResume1() throws IOException, URISyntaxException {
        Resume resume = new ManagerDataSourceImpl().getResume(Util.definePathTestClasses() + File.separator + "test.properties");
        assertNull(resume.getName());
        assertEquals("testDOB", resume.getDateOfBorn());
        assertEquals(Arrays.asList("test", "test@gmail.com"), resume.getEmails());
        assertEquals(Collections.singletonList("80011"), resume.getPhoneNumbers());
        assertEquals("", resume.getSkypeLogin());
        assertNull(resume.getUrlAvatar());
        assertNull(resume.getTargets());
        assertNull(resume.getExperiences());
        assertEquals(Collections.singletonList(""), resume.getBasicEducations());
        assertNull(resume.getAdditionalEducations());
        assertNull(resume.getOtherInfo());
        assertNull(resume.getCareerTarget());
        assertNull(resume.getSkills());
    }

    @Test
    public void testGetResume2() throws IOException, URISyntaxException {
        Resume resume = new ManagerDataSourceImpl().getResume(Util.definePathTestClasses() + File.separator + "test2.properties");
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
        expectedSkills.put("java", 18);
        expectedSkills.put("IIdea", 6);
        expectedSkills.put("sql", 24);
        assertEquals("check skills", expectedSkills, resume.getSkills());
    }

    @Test(expected = IOException.class)
    public void testGetResume3() throws IOException, URISyntaxException {
        new ManagerDataSourceImpl().getResume(Util.definePathTestClasses() + File.separator + "notexists.properties");
    }

    @Test
    public void testGetResume4() throws IOException, URISyntaxException {
        Resume resume = new ManagerDataSourceImpl().getResume(Util.definePathTestClasses() + File.separator + "test3.properties");
        Map<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("java", -1);
        expectedSkills.put("sql", -1);
        assertEquals("check skills", expectedSkills, resume.getSkills());
    }

}