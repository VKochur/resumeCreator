package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.simbirsoft.maketalents.resume_builder")
public class LauncherWeb implements Launcher {

    @Override
    public String getDescription() {
        return "appllication is available by http://localhost:8080/";
    }

    @Override
    public void launch(String[] args) {
        SpringApplication.run(LauncherWeb.class, args);
    }
}
