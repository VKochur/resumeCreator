package com.simbirsoft.maketalents.resume_builder.controller;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {

    @Autowired
    @Qualifier("resumeServiceForPropertiesFile")
    ResumeService resumeService;

    @RequestMapping("/resume/build")
    public String showResume(@RequestParam("path") String pathToFile, Model model) {
        try {
            model.addAttribute("pathFile", pathToFile);
            ResumeDto resumeDto = resumeService.getResumeDto(pathToFile);
            model.addAttribute("name", resumeDto.getName());
            model.addAttribute("careerTarget", resumeDto.getCareerTarget());
            model.addAttribute("dateOfBorn", resumeDto.getDateOfBorn());
            model.addAttribute("phone", resumeDto.getPhoneNumbers());
            model.addAttribute("email", resumeDto.getEmails());
            model.addAttribute("skype", resumeDto.getSkypeLogin());
            model.addAttribute("avatarUrl", resumeDto.getUrlAvatar());
            model.addAttribute("target", resumeDto.getTargets());
            model.addAttribute("experience", resumeDto.getExperiences());
            model.addAttribute("baseEducation", resumeDto.getBasicEducations());
            model.addAttribute("addedEducation", resumeDto.getAdditionalEducations());
            model.addAttribute("otherInfo", resumeDto.getOtherInfo());
            model.addAttribute("skills", resumeDto.getSkills());
            return "resume";
        } catch (Exception e) {
            model.addAttribute("exception", e.getMessage());
            e.printStackTrace();
            return "errorMessage";
        }
    }
}
