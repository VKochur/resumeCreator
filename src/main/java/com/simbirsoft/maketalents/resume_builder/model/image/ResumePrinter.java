package com.simbirsoft.maketalents.resume_builder.model.image;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;

/**
 * Interface prints resume
 */
public interface ResumePrinter {

    void print(ResumeDao resumeDao) throws Exception;
}
