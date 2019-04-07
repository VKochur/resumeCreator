package com.simbirsoft.maketalents.resume_builder.running_from_ide;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.dto.Util;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.core.ResumeBuilder;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.NotSupportedException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ComponentScan("com.simbirsoft.maketalents.resume_builder")
@EnableJpaRepositories(basePackages = {"com.simbirsoft.maketalents.resume_builder.dao.db"})
@EntityScan(basePackages = {"com.simbirsoft.maketalents.resume_builder.entity"} )
@SpringBootApplication
public class MainWeb {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(MainWeb.class, args);
        ResumeService resumeService = (ResumeService) context.getBean("resumeServiceForDb");
        Util utilDto = context.getBean(Util.class);
        Map<String, Integer> skills = new HashMap<>();
        skills.put("idea", 6);
        skills.put("java", 12);
        Resume resume = new ResumeBuilder()
                .setCareerTarget("рассматриваемая должность")
                .setName("ФИО")
                .setEmails(Arrays.asList("email1, email2"))
                .setSkills(skills)
                .build();
        ResumeDto resumeDto = resumeService.saveResumeDto(utilDto.getDtoByResume(resume));
        resumeService.saveResumeDto(utilDto.getDtoByResume(resume));
        resumeDto = resumeService.getResumeDto( String.valueOf(resumeDto.getId()) );
    }
}
