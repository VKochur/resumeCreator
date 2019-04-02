package com.simbirsoft.maketalents.resume_builder.launcher;

import com.simbirsoft.maketalents.resume_builder.launcher.impl.LauncherCreateHtmlFromProperties;
import com.simbirsoft.maketalents.resume_builder.launcher.impl.LauncherCreateHtmlFromPropertiesMultithreading;
import com.simbirsoft.maketalents.resume_builder.launcher.impl.LauncherCreateHtmlFromPropertiesUseSpringBoot;
import com.simbirsoft.maketalents.resume_builder.launcher.impl.LauncherWeb;

import java.util.Arrays;

import static com.simbirsoft.maketalents.resume_builder.launcher.LauncherTypes.*;

/**
 * Factory of Launcher
 */
public class LauncherFactory {

    /**
     * Gets instance of launcher
     *
     * @param launcherType
     * @return
     */
    public static Launcher getInstance(LauncherTypes launcherType) {
        switch (launcherType) {
            case BASIC:
                return new LauncherCreateHtmlFromProperties();
            case SPRING:
                return new LauncherCreateHtmlFromPropertiesUseSpringBoot();
            case MULTI_THREADS_SPRING:
                return new LauncherCreateHtmlFromPropertiesMultithreading();
            case WEB:
                return new LauncherWeb();
            default: {
                throw new IllegalArgumentException("Not case for " + launcherType + ". Correct: " + Arrays.toString(getAnalizedTypes()));
            }
        }
    }

    /**
     * method returns info that launcherTypes method getInstance() correct process
     * @return launcherTypes, that method getInstance() correct process
     */
    public static LauncherTypes[] getAnalizedTypes(){
        return new LauncherTypes[] {BASIC, SPRING, MULTI_THREADS_SPRING, WEB};
    }
}
