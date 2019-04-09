package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.dto.Util;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.NotSupportedException;
import java.io.File;
import java.util.ArrayList;

/**
 * Launcher web application
 */
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
        ApplicationContext context = SpringApplication.run(LauncherWeb.class, args);
        fillingData(context);

    }

    private void fillingData(ApplicationContext context) {

        String pathSource = new File("").getAbsolutePath()
                + File.separator + "src" + File.separator + "main" + File.separator + "temp_for_ide" + File.separator + "source" + File.separator;

        ResumeService resumeServiceDb = (ResumeService) context.getBean("resumeServiceForDb");
        Util utilDto = context.getBean(Util.class);

        ResumeService resumeServiceFile = (ResumeService) context.getBean("resumeServiceForPropertiesFile");
        Resume resume = null;
        try {
            resume = utilDto.getResumeByDTO(resumeServiceFile.getResumeDto(pathSource + File.separator + "basic" + File.separator + "person.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resumeServiceDb.saveResumeDto(utilDto.getDtoByResume(resume));
        } catch (NotSupportedException e) {
            e.printStackTrace();
        }
        try {
            resume = utilDto.getResumeByDTO(resumeServiceFile.getResumeDto(pathSource + File.separator + "threads" + File.separator + "person1.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resumeServiceDb.saveResumeDto(utilDto.getDtoByResume(resume));
        } catch (NotSupportedException e) {
            e.printStackTrace();
        }
    }
}
