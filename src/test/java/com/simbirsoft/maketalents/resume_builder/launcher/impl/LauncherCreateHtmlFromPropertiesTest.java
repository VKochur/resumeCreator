package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;


public class LauncherCreateHtmlFromPropertiesTest {

    @Test
    public void testLaunch() throws URISyntaxException, IOException {
        Launcher launcher = new LauncherCreateHtmlFromProperties();
        new GeneralForTestingBasicAndCoreSpringBootLauncher().runTestFor(launcher);
    }
}