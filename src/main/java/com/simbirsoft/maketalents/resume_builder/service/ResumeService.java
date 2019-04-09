package com.simbirsoft.maketalents.resume_builder.service;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;

import javax.transaction.NotSupportedException;
import java.util.List;

public interface ResumeService {

    /**
     * method gets ResumeDto by unique key
     * @param id unique key
     * @return
     * @throws Exception
     */
    ResumeDto getResumeDto(String id) throws Exception;

    /**
     * method for save
     * @param resumeDto
     * @return
     * @throws NotSupportedException
     */
    ResumeDto saveResumeDto(ResumeDto resumeDto) throws NotSupportedException;

    /**
     * method for getting all resumeDto from source
     * @return
     * @throws NotSupportedException
     */
    List<ResumeDto> getAll() throws NotSupportedException;
}
