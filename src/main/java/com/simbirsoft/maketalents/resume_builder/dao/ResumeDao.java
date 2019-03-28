package com.simbirsoft.maketalents.resume_builder.dao;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

/**
 * Interface provides data about resume
 */
public interface ResumeDao {

    /**
     * method gets Resume by unique key
     * @param id unique key
     * @return
     * @throws Exception
     */
    Resume getResume(String id) throws Exception;

}
