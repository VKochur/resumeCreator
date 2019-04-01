package com.simbirsoft.maketalents.resume_builder.service.impl;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.dto.Util;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("resumeServiceForPropertiesFile")
public class ResumeServiceImpl implements ResumeService{

    @Autowired
    @Qualifier("resumeDaoFromPropertiesFile")
    private ResumeDao resumeDao;

    @Autowired
    Util utilForDTO;

    /**
     * Method gets resumeDTO by properties file
     * @param pathPropertiesFile path to file
     * @return resume
     * @throws Exception
     */
    @Override
    public ResumeDto getResumeDto(String pathPropertiesFile) throws Exception {
        return utilForDTO.getDtoByResume(resumeDao.getResume(pathPropertiesFile));
    }
}
