package com.simbirsoft.maketalents.resume_builder.controller;

import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @RequestMapping("/resume/build")
    public String showResume(@RequestParam("path") String pathToFile, Model model){
        System.out.println(pathToFile);
        model.addAttribute("pathFile", pathToFile);
        return "resume";
    }
}
