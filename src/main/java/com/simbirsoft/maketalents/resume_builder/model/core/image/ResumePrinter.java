package com.simbirsoft.maketalents.resume_builder.model.core.image;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

/**
 * Interface prints resume
 */
public interface ResumePrinter {

    /**
     * method prints resume
     * @param resume data for print
     * @param additionalInfo info that can be useful for implements method
     *                      (example: file's path if implementation is creating file)
     * @throws Exception implementations may throws
     */
    void print(Resume resume, String additionalInfo) throws Exception;
}
