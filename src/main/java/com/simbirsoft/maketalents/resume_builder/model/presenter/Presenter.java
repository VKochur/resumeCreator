package com.simbirsoft.maketalents.resume_builder.model.presenter;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;

/**
 * Interface presents resume
 */
public interface Presenter {

    void present(ResumeDto resumeDto, String additionalInfo) throws Exception;
}
