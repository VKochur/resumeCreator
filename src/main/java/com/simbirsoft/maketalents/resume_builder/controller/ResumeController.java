package com.simbirsoft.maketalents.resume_builder.controller;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.NotSupportedException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

@Controller
public class ResumeController {

    @Autowired
    @Qualifier("resumeServiceForPropertiesFile")
    ResumeService resumeServiceFromFile;

    @Autowired
    @Qualifier("resumeServiceForDb")
    ResumeService resumeServiceFromDb;


    @RequestMapping("/")
    public String helloPage() {
        return "hello";
    }

    @RequestMapping("/resume")
    public String showListResumesFromDb(Model model) {
        try {
            model.addAttribute("resumes", resumeServiceFromDb.getAll());
            model.addAttribute("title", "All resumes from db");
        } catch (NotSupportedException e) {
            throw new IllegalStateException("Unexpected exception " + e.getMessage(), e);
        }
        return "resume_list";
    }

    @RequestMapping("/resume/db")
    public ModelAndView showResumeByIdFormDb(@RequestParam("id") String id) {
        if (id.isEmpty()) {
            return showInfoOnMessagePage("Illegal id", "you must specify id for resume in browser address bar");
        }

        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("idResume", "id = " + id);
            configureModel(resumeServiceFromDb, id, modelAndView);
            return modelAndView;
        } catch (Exception e) {
            return showExceptionOnMessagePage(e);
        }
    }

    private ModelAndView showInfoOnMessagePage(String title, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", title);
        modelAndView.addObject("message", message);
        modelAndView.setViewName("message");
        return modelAndView;
    }

    @RequestMapping("/resume/build")
    public ModelAndView showResumeFormFile(@RequestParam("path") String pathToFile) {
        if (pathToFile.isEmpty()) {
            return showInfoOnMessagePage("Illegal path to file", "you must specify path to properties file in browser address bar");
        }

        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("idResume", pathToFile);
            configureModel(resumeServiceFromFile, pathToFile, modelAndView);
            return modelAndView;
        } catch (Exception e) {
            return showExceptionOnMessagePage(e);
        }
    }

    private ModelAndView showExceptionOnMessagePage(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        modelAndView.setViewName("message");
        modelAndView.addObject("title", "Error occured");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("info", sw.toString());
        return modelAndView;
    }

    private void configureModel(ResumeService resumeService, String idResume, ModelAndView model) throws Exception {
        ResumeDto resumeDto = resumeService.getResumeDto(idResume);
        model.addObject("name", resumeDto.getName());
        model.addObject("careerTarget", resumeDto.getCareerTarget());
        model.addObject("dateOfBorn", resumeDto.getDateOfBorn());
        model.addObject("phones", resumeDto.getPhoneNumbers());
        model.addObject("emails", resumeDto.getEmails());
        model.addObject("skype", resumeDto.getSkypeLogin());
        model.addObject("avatarUrl", resumeDto.getUrlAvatar());
        model.addObject("targets", resumeDto.getTargets());
        model.addObject("experiences", resumeDto.getExperiences());
        model.addObject("baseEducations", resumeDto.getBasicEducations());
        model.addObject("addedEducations", resumeDto.getAdditionalEducations());
        model.addObject("otherInfo", resumeDto.getOtherInfo());
        model.addObject("skills", getSkills(resumeDto.getSkills()));
        model.setViewName("resume");
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
