package com.simbirsoft.maketalents.resume_builder.dto;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.core.ResumeBuilder;
import org.springframework.stereotype.Component;

@Component
public class Util {

    public Resume getResumeByDTO(ResumeDto resumeDto) {
        return new ResumeBuilder()
                .setIdResume(resumeDto.getId())
                .setCareerTarget(resumeDto.getCareerTarget())
                .setName(resumeDto.getName())
                .setDataOfBorn(resumeDto.getDateOfBorn())
                .setEmails(resumeDto.getEmails())
                .setPhoneNumbers(resumeDto.getPhoneNumbers())
                .setSkypeLogin(resumeDto.getSkypeLogin())
                .setUrlAvatar(resumeDto.getUrlAvatar())
                .setTargets(resumeDto.getTargets())
                .setExperiences(resumeDto.getExperiences())
                .setBasicEducations(resumeDto.getBasicEducations())
                .setAdditionalEdications(resumeDto.getAdditionalEducations())
                .setOtherInfo(resumeDto.getOtherInfo())
                .setSkills(resumeDto.getSkills())
                .build();
    }

    public ResumeDto getDtoByResume(Resume resume) {
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setId(resume.getId());
        resumeDto.setCareerTarget(resume.getCareerTarget());
        resumeDto.setName(resume.getName());
        resumeDto.setDateOfBorn(resume.getDateOfBorn());
        resumeDto.setEmails(resume.getEmails());
        resumeDto.setPhoneNumbers(resume.getPhoneNumbers());
        resumeDto.setSkypeLogin(resume.getSkypeLogin());
        resumeDto.setUrlAvatar(resume.getUrlAvatar());
        resumeDto.setTargets(resume.getTargets());
        resumeDto.setExperiences(resume.getExperiences());
        resumeDto.setBasicEducations(resume.getBasicEducations());
        resumeDto.setAdditionalEducations(resume.getAdditionalEducations());
        resumeDto.setOtherInfo(resume.getOtherInfo());
        resumeDto.setSkills(resume.getSkills());
        return resumeDto;
    }
}
