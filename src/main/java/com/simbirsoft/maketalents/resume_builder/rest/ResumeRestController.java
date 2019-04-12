package com.simbirsoft.maketalents.resume_builder.rest;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/Resume")
public class ResumeRestController {

    @Autowired
    @Qualifier("resumeServiceForDb")
    ResumeService resumeService;

    @GetMapping(value = "id")
    public ResumeDto getResumeDtoById(@RequestParam Long id) throws Exception {
        return resumeService.getResumeDto(String.valueOf(id));
    }

}
