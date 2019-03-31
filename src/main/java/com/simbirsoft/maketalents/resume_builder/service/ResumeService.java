package com.simbirsoft.maketalents.resume_builder.service;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

public interface ResumeService {

    /**
     * method gets Resume by unique key
     * @param id unique key
     * @return
     * @throws Exception
     */
    Resume getResume(String id) throws Exception;
}
