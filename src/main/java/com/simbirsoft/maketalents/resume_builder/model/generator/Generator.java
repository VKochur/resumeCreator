package com.simbirsoft.maketalents.resume_builder.model.generator;

import org.apache.log4j.Logger;

public interface Generator {

    void generate(String idForResume, String additionalInfoForGeneration);

    void setLogger(Logger logger);
}
