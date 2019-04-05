package com.simbirsoft.maketalents.resume_builder.dao;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

import javax.transaction.NotSupportedException;

/**
 * Interface provides data about resume
 */
public interface ResumeDao {

    /**
     * method gets Resume by unique key from storage
     * @param id unique key
     * @return
     * @throws Exception
     */
    Resume getResume(String id) throws Exception;

    /**
     * method for saving Resume
     * @param resume
     * @return
     */
    Resume saveResume(Resume resume) throws NotSupportedException;

}
