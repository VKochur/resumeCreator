package com.simbirsoft.maketalents.resume_builder.running_from_ide;

import java.io.File;

class DataHouse {
    static final String PATH_TO_SOURCE = new File("").getAbsolutePath()
            + File.separator + "src" + File.separator + "main" + File.separator + "temp_for_ide" + File.separator + "source" + File.separator;
    static final String PATH_TO_TARGET = new File("").getAbsolutePath()
            + File.separator + "src" + File.separator + "main" + File.separator + "temp_for_ide" + File.separator + "target" + File.separator;
}
