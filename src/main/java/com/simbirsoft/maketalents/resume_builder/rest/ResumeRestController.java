package com.simbirsoft.maketalents.resume_builder.rest;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/resume")
public class ResumeRestController {

    @Autowired
    @Qualifier("resumeServiceForDb")
    ResumeService resumeService;

    @GetMapping("{id}")
    public ResumeDto getResumeById(@PathVariable Long id) {
        return resumeService.getResumeDto(String.valueOf(id));
    }

    @PostMapping
    public ResumeDto saveResume(@RequestBody ResumeDto resumeDto) {
        return resumeService.saveResumeDto(resumeDto);
    }

    @PutMapping("{id}")
    public ResumeDto updateResumeById(@PathVariable Long id, @RequestBody ResumeDto resumeDto) {
        resumeDto.setId(id);
        return resumeService.updateResume(resumeDto);
    }

    @DeleteMapping("{id}")
    public ResumeDto deleteResumeById(@PathVariable Long id){
        return resumeService.deleteResume(String.valueOf(id));
    }

    @GetMapping
    public List<ResumeDto> getAllResumes(){
        return resumeService.getAll();
    }
}
