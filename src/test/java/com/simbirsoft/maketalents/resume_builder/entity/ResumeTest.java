package com.simbirsoft.maketalents.resume_builder.entity;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ResumeTest {

    @Test
    public void testClone() {
        String name = "name";
        List<String> emails = new ArrayList<>(Arrays.asList("email1", "email2"));
        Map<String, Integer> skills = new HashMap<>();
        skills.put("java", 12);
        skills.put("c++", 24);

        Resume resume = new Resume();
        resume.setName(name);
        resume.setEmails(emails);
        resume.setSkills(skills);

        Resume cloneResume = resume.clone();

        assertEquals(name, cloneResume.getName());
        assertEquals(null, cloneResume.getDateOfBorn());
        assertEquals(emails, cloneResume.getEmails());
        assertEquals(skills, cloneResume.getSkills());


        emails.remove(0);
        skills.remove("c++");
        assertNotEquals(emails, cloneResume.getEmails());
        assertNotEquals(skills, cloneResume.getSkills());
        assertEquals(emails, resume.getEmails());
        assertEquals(skills, resume.getSkills());
    }
}