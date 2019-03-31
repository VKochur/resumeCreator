package com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.tests_util.Util;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PropertyReaderTest {

    @Test(expected = IllegalThreadStateException.class)
    public void testGetResume() throws Exception {
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.getResume(Util.definePathTestClasses() + File.separator + "test.properties");
    }

    @Test (expected = FileNotFoundException.class)
    public void test2GetResume() throws Exception {
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.startGettingResume(Util.definePathTestClasses() + File.separator + "notexists.properties");
        propertyReader.join();
        propertyReader.getResume(Util.definePathTestClasses() + File.separator + "notexists.properties");
    }

    @Test
    public void test3GetResume() throws Exception {
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.startGettingResume(Util.definePathTestClasses() + File.separator +"test2.properties");
        propertyReader.join();
        Resume actualResume = propertyReader.getResume(Util.definePathTestClasses() + File.separator + "test2.properties");
        assertEquals("testFIO", actualResume.getName());
        assertEquals("https://www04fddb2797c033b087c4247630b2db7.jpg", actualResume.getUrlAvatar());
        assertEquals(Arrays.asList("образование1", "образование2"), actualResume.getBasicEducations());
        assertEquals(4, actualResume.getSkills().size());
    }

}