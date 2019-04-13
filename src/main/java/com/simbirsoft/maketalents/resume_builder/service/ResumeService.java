package com.simbirsoft.maketalents.resume_builder.service;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;

import java.util.List;

public interface ResumeService {

    /**
     * method gets ResumeDto by unique key
     * @param id unique key
     * @return ResumeDto from source
     */
    ResumeDto getResumeDto(String id);

    /**
     * method for save
     * @param resumeDto
     * @return created ResumeDto from source
     */
    ResumeDto saveResumeDto(ResumeDto resumeDto);

    /**
     * method for getting all resumeDto from source
     * @return list ResumeDto
     */
    List<ResumeDto> getAll();

    /**
     * method for updating resume in source
     * @param resumeDto new date for resume in source with key = resumeDto.getId()
     * @return updated resumeDto from source
     */
    ResumeDto updateResume(ResumeDto resumeDto);

    /**
     * method for delete resume in source by id
     * @param id unique key for resume
     * @return resume from source that was deleted
     */
    ResumeDto deleteResume(String id);
}
