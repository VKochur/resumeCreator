package com.simbirsoft.maketalents.resume_builder.service.impl;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("resumeServiceForPropertiesFile")
public class ResumeServiceImpl implements ResumeService{

    @Autowired
    @Qualifier("resumeDaoFromPropertiesFile")
    private ResumeDao resumeDao;

    /**
     * Method gets resume by file's name
     * @param id path to file
     * @return resume
     * @throws Exception
     */
    @Override
    public Resume getResume(String id) throws Exception {
        return resumeDao.getResume(id);
    }
}
