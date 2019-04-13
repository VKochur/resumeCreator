package com.simbirsoft.maketalents.resume_builder.dao;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

import java.util.List;

/**
 * Interface provides data about resume
 */
public interface ResumeDao {

    /**
     * method gets Resume by unique key from storage
     * @param id unique key
     * @return
     */
    Resume getResume(String id);

    /**
     * method for saving Resume
     * @param resume
     * @return
     */
    Resume createResume(Resume resume);

    /**
     * method for getting all resume from source
     * @return
     */
    List<Resume> getAll();

    /**
     * method for updating Resume in source
     * @param resume new data for resume
     * @return updated resume from source
     */
    Resume updateResume(Resume resume);

    /**
     * method for delete resume from source by id
     * @param id
     * @return
     */
    Resume deleteResume(String id);
}
