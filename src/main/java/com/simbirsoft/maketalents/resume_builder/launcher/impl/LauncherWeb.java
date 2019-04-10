package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Launcher web application
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.simbirsoft.maketalents.resume_builder")
@EnableJpaRepositories(basePackages = {"com.simbirsoft.maketalents.resume_builder.dao.db"})
@EntityScan(basePackages = {"com.simbirsoft.maketalents.resume_builder.entity"})
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
