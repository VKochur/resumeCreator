package com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.tests_util.Util;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CollectorTest {

    /**
     * testing getting resume more than once
     * @throws Exception
     */
    @Test (expected = IllegalThreadStateException.class)
    public void test1GetResume() throws Exception{
        Collector collector = new Collector();
        collector.setProviders(providers());
        collector.getResume("key1,key2");
        collector.getResume("key1,key2");
    }

    @Test
    public void test2GetResume() throws Exception {
        Collector collector = new Collector();
        collector.setProviders(providers());
        String keys = "key1,key2";
        Resume actualResume = collector.getResume(keys);
        check(actualResume);
        assertNull(actualResume.getCareerTarget());
        assertNull(actualResume.getTargets());
        //add reader file
        collector.setProviders(providers());
        collector.getProviders().add(new PropertyReader());
        keys = "key1,key2," + Util.definePathTestClasses() + "\\test2.properties";
        actualResume = collector.getResume(keys);
        check(actualResume);
        assertEquals("login|login2", actualResume.getSkypeLogin());
        assertEquals("https://www04fddb2797c033b087c4247630b2db7.jpg", actualResume.getUrlAvatar());
        assertEquals(Arrays.asList("Устройство Java junior.", "Прохождение курсов IT.Place"), actualResume.getTargets());
        assertEquals(Arrays.asList("организация1", "организация2", "организация3"), actualResume.getExperiences());
        assertEquals(Arrays.asList("образование1", "образование2"), actualResume.getBasicEducations());
        assertEquals(Arrays.asList("доп", "доп2", "доп3"), actualResume.getAdditionalEducations());
        assertEquals("дополнительная информация", actualResume.getOtherInfo());
        assertEquals("должность", actualResume.getCareerTarget());
    }

    private void check(Resume resume) {
        assertEquals("firstName", resume.getName());
        assertEquals("dob", resume.getDateOfBorn());
        assertEquals(Arrays.asList("firstEmail"), resume.getEmails());
        assertEquals(Arrays.asList("222", "2-222"), resume.getPhoneNumbers());
        Map<String, Integer> skills = new HashMap<>();
        skills.put("skill1", 1);
        assertEquals(skills, resume.getSkills());
    }

    private List<Provider> providers() {
        List<Provider> providers = new ArrayList<>();

        Provider provider = new Provider();
        provider.setResumeDao(new ResumeDao() {
            @Override
            public Resume getResume(String idResume) throws Exception {
                Resume resume = new Resume();
                resume.setName("firstName");
                resume.setEmails(Arrays.asList("firstEmail"));
                return resume;
            }
        });
        providers.add(provider);

        provider = new Provider();
        provider.setResumeDao(new ResumeDao() {
            @Override
            public Resume getResume(String idResume) throws Exception {
                Resume resume = new Resume();
                resume.setName("second");
                resume.setDateOfBorn("dob");
                resume.setEmails(Arrays.asList("secondEmail"));
                resume.setPhoneNumbers(Arrays.asList("222", "2-222"));
                Map<String, Integer> map = new HashMap<>();
                map.put("skill1", 1);
                resume.setSkills(map);
                return resume;
            }
        });
        providers.add(provider);

        return providers;
    }
}