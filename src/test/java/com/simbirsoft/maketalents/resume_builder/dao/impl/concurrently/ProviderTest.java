package com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProviderTest {

    /**
     * testing get resume before running thread
     * @throws Exception
     */
    @Test (expected = IllegalThreadStateException.class)
    public void testProcessing() throws Exception {
        Provider provider = new Provider();
        provider.getResume("");
    }

    /**
     * testing get idCalculatedResume before running thread
     * @throws Exception
     */
    @Test (expected = IllegalStateException.class)
    public void testIdCalculated() throws Exception {
        Provider provider = new Provider();
        provider.getIdCalculatedResume();
    }

    /**
     * testing get idCalculatedResume after running thread by start()
     * @throws Exception
     */
    @Test
    public void test2IdCalculated() throws Exception {
        Provider provider = new Provider();
        provider.setResumeDao(new ResumeDao() {
            @Override
            public Resume getResume(String id) throws Exception {
                return new Resume();
            }
        });
        provider.start();
        provider.join();
        assertNull(provider.getIdCalculatedResume());
    }


    /**
     * testing get resume before setting resumeDao
     * @throws Exception
     */
    @Test (expected = NullPointerException.class)
    public void test2Processing() throws Exception {
        Provider provider = new Provider();
        String idResume = "id";
        provider.startGettingResume(idResume);
        provider.join();
        provider.getResume(idResume);
    }

    /**
     * testing throws exception from resumeDao to method getting resume from provider
     * @throws Exception
     */
    @Test (expected = ArithmeticException.class)
    public void test3Processing() throws Exception {
        Provider provider = new Provider();
        provider.setResumeDao((idResume) -> {
            throw new ArithmeticException();
        });
        String idResume = "id";
        provider.startGettingResume(idResume);
        provider.join();
        provider.getResume(idResume);
    }

    /**
     * testing getting resume after that, how resume in source was changed
     * getting resume must returns the same result - resume calculated at moment start thread
     * @throws Exception
     */
    @Test
    public void testGetResume() throws Exception {
        Resume resumeOuter = new Resume();
        resumeOuter.setName("testName");
        Provider provider = new Provider();
        provider.setResumeDao((idResume) -> {
            Resume resume = new Resume();
            resume.setName(resumeOuter.getName());
            return resume;
        });
        String idResume = "id";
        provider.startGettingResume(idResume);
        provider.join();
        assertEquals("testName", provider.getResume(idResume).getName());
        assertEquals("testName", provider.getResumeDao().getResume(idResume).getName());
        resumeOuter.setName("testName2");
        assertEquals("testName", provider.getResume(idResume).getName());
        assertEquals("testName2", provider.getResumeDao().getResume(idResume).getName());
    }

    /**
     * testing getting resume by id different from id specified at moment start thread
     * getting resume must returns the same result - resume calculated at moment start thread
     * @throws Exception
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetResumeByOtherId() throws Exception {
        Provider provider = new Provider();
        provider.setResumeDao((idResume) -> {
            Resume resume = new Resume();
            resume.setName("fio");
            return resume;
        });
        String resumeId = "id";
        provider.startGettingResume(resumeId);
        provider.join();
        assertEquals("fio", provider.getResume(resumeId).getName());
        provider.getResume("otherId");
    }
}