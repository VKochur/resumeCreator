package com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.dao.impl.properties_file_loader.ResumeDaoImpl;

/**
 * Class for getting resume from property file in separate thread
 */
public class PropertyReader extends Provider {

    public PropertyReader(String pathPropertyFile){
        super.setResumeDao(new ResumeDaoImpl(pathPropertyFile));
        setDescription("provider gets resume from property file" + " " + pathPropertyFile);
    }

    @Override
    public void setResumeDao(ResumeDao resumeDao) {
        //nothing
    }
}
