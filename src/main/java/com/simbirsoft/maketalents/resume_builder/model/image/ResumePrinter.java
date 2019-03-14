package com.simbirsoft.maketalents.resume_builder.model.image;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

/**
 * Interface prints resume
 */
public interface ResumePrinter {

    void print(Resume resume) throws Exception;
}
