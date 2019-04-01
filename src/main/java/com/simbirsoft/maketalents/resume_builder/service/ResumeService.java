package com.simbirsoft.maketalents.resume_builder.service;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;

public interface ResumeService {

    /**
     * method gets ResumeDto by unique key
     * @param id unique key
     * @return
     * @throws Exception
     */
    ResumeDto getResumeDto(String id) throws Exception;
}
