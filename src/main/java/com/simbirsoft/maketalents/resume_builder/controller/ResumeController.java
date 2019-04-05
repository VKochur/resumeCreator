package com.simbirsoft.maketalents.resume_builder.controller;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

@Controller
public class ResumeController {

    @Autowired
    @Qualifier("resumeServiceForPropertiesFile")
    ResumeService resumeService;

    @RequestMapping("/")
    public String helloPage(){
        return "hello";
    }

    @RequestMapping("/resume/build")
    public String showResume(@RequestParam("path") String pathToFile, Model model) {
        if (pathToFile.isEmpty()){
            model.addAttribute("title", "Illegal path to file");
            model.addAttribute("message", "you must specify path to properties file in browser address bar");
            return "message";
        }
        try {
            ResumeDto resumeDto = resumeService.getResumeDto(pathToFile);
            model.addAttribute("pathFile", pathToFile);
            model.addAttribute("name", resumeDto.getName());
            model.addAttribute("careerTarget", resumeDto.getCareerTarget());
            model.addAttribute("dateOfBorn", resumeDto.getDateOfBorn());
            model.addAttribute("phones", resumeDto.getPhoneNumbers());
            model.addAttribute("emails", resumeDto.getEmails());
            model.addAttribute("skype", resumeDto.getSkypeLogin());
            model.addAttribute("avatarUrl", resumeDto.getUrlAvatar());
            model.addAttribute("targets", resumeDto.getTargets());
            model.addAttribute("experiences", resumeDto.getExperiences());
            model.addAttribute("baseEducations", resumeDto.getBasicEducations());
            model.addAttribute("addedEducations", resumeDto.getAdditionalEducations());
            model.addAttribute("otherInfo", resumeDto.getOtherInfo());
            model.addAttribute("skills", getSkills(resumeDto.getSkills()));
            return "resume";
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            model.addAttribute("title", "Error occured");
            model.addAttribute("message", e.getMessage());
            model.addAttribute("info", sw.toString());
            return "message";
        }
    }

    private List<String> getSkills(Map<String, Integer> skills) {
        List<String> strings = new ArrayList<>();
        if (skills != null) {
            Set<Map.Entry<String, Integer>> sortedSkills = new TreeSet<>((o1, o2) -> o2.getValue() - o1.getValue());
            sortedSkills.addAll(skills.entrySet());
            for (Map.Entry<String, Integer> el : sortedSkills) {
                strings.add(String.format("%s%s%s%s;", el.getKey(), " - ", el.getValue().toString(), "мес."));
            }
        }
        return strings;
    }
}
