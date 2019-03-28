package com.simbirsoft.maketalents.resume_builder.model.image;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

/**
 * Interface prints resume
 */
public interface ResumePrinter {

    /**
     * method prints resume
     * @param resume
     * @param additionalInfo info that can be useful for implements method
     *                      (example: file's path if implementation is creating file)
     * @throws Exception
     */
    void print(Resume resume, String additionalInfo) throws Exception;
}
