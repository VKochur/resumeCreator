package com.simbirsoft.maketalents.resume_builder.model.core;

import com.simbirsoft.maketalents.resume_builder.model.core.data.ManagerDataSource;
import com.simbirsoft.maketalents.resume_builder.model.core.image.ResumePrinter;

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
    ManagerDataSource getProviderData();

    /**
     * method builds resume
     */
    default void buildResume(String idResume, String additionalInfoForPrinter) throws Exception {
        getPrinterData().print(getProviderData().getResume(idResume), additionalInfoForPrinter);
    }
}
