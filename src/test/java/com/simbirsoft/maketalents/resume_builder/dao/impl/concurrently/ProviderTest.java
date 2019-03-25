package com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProviderTest {

    @Test (expected = IllegalThreadStateException.class)
    public void testProcessing() throws Exception {
        Provider provider = new Provider();
        provider.getResume();
    }

    @Test (expected = NullPointerException.class)
    public void test2Processing() throws Exception {
        Provider provider = new Provider();
        provider.start();
        provider.join();
        provider.getResume();
    }

    @Test (expected = ArithmeticException.class)
    public void test3Processing() throws Exception {
        Provider provider = new Provider();
        provider.setResumeDao(() -> {
            throw new ArithmeticException();
        });
        provider.start();
        provider.join();
        provider.getResume();
    }

    @Test
    public void testGetResume() throws Exception {
        Resume resumeOuter = new Resume();
        resumeOuter.setName("testName");
        Provider provider = new Provider();
        provider.setResumeDao(() -> {
            Resume resume = new Resume();
            resume.setName(resumeOuter.getName());
            return resume;
        });
        provider.start();
        provider.join();

        assertEquals("testName", provider.getResume().getName());
        assertEquals("testName", provider.getResumeDao().getResume().getName());
        resumeOuter.setName("testName2");
        assertEquals("testName", provider.getResume().getName());
        assertEquals("testName2", provider.getResumeDao().getResume().getName());
    }
}