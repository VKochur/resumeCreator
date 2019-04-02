package com.simbirsoft.maketalents.resume_builder.launcher;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;

public class Util {
    private static Logger logger;

    static {
        logger = Logger.getLogger(Util.class);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));
        try {
            logger.addAppender(new FileAppender(
                    new SimpleLayout(),
                    com.simbirsoft.maketalents.resume_builder.util.Util.getPathExecutableDir()
                            + File.separator + "resume_builder.log",
                    false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void stopWebApplication(ApplicationContext context) {
        ((ConfigurableApplicationContext) context).close();
    }
}
