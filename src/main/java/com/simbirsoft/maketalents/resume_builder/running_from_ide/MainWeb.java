package com.simbirsoft.maketalents.resume_builder.running_from_ide;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.dto.Util;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.launcher.impl.LauncherWeb;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.util.*;

@EnableTransactionManagement
@ComponentScan("com.simbirsoft.maketalents.resume_builder")
@EnableJpaRepositories(basePackages = {"com.simbirsoft.maketalents.resume_builder.dao.db"})
@EntityScan(basePackages = {"com.simbirsoft.maketalents.resume_builder.entity"})
@SpringBootApplication
public class MainWeb {
    public static void main(String[] args) throws Exception {

        new LauncherWeb().launch(args);
        /*
        ApplicationContext context = SpringApplication.run(MainWeb.class, args);
        ResumeService resumeService = (ResumeService) context.getBean("resumeServiceForDb");
        Util utilDto = context.getBean(Util.class);

        ResumeService resumeService1 = (ResumeService) context.getBean("resumeServiceForPropertiesFile");
        Resume resume = utilDto.getResumeByDTO(resumeService1.getResumeDto(DataHouse.PATH_TO_SOURCE + File.separator + "basic" + File.separator + "person.properties"));
        System.out.println(resume);
        ResumeDto resumeDto = resumeService.saveResumeDto(utilDto.getDtoByResume(resume));
        System.out.println(resumeDto.getId());
        resumeDto = resumeService.saveResumeDto(utilDto.getDtoByResume(resume));
        System.out.println(resumeDto.getId());
        resume.setEmails(new ArrayList<>());
        resumeDto = resumeService.getResumeDto( String.valueOf(resumeDto.getId()) );
        resume = utilDto.getResumeByDTO(resumeDto);
        System.out.println(resume);
        */
    }
}
