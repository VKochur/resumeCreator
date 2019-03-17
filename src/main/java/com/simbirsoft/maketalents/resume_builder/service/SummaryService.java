package com.simbirsoft.maketalents.resume_builder.service;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.model.image.ResumePrinter;

/**
 * Service for building resume
 *
 */
public interface SummaryService {

    /**
     * @return printer data about Resume
     */
    ResumePrinter getPrinterData();

    /**
     * @return provider data about Resume
     */
    ResumeDao getProviderData();

    /**
     * method builds resume
     */
    default void buildResume() throws Exception {
        getPrinterData().print(getProviderData().getResume());
    }
}
