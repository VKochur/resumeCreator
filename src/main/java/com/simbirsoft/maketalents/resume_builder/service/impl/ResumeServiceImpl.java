package com.simbirsoft.maketalents.resume_builder.service.impl;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.dto.Util;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
import java.util.List;

/**
 * Service for working with properties file
 */
@Service("resumeServiceForPropertiesFile")
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    @Qualifier("resumeDaoFromPropertiesFile")
    private ResumeDao resumeDao;

    @Autowired
    Util utilForDTO;

    /**
     * Method gets resumeDTO by properties file
     *
     * @param pathPropertiesFile path to file
     * @return resume
     */
    @Override
    public ResumeDto getResumeDto(String pathPropertiesFile) {
        return utilForDTO.getDtoByResume(resumeDao.getResume(pathPropertiesFile));
    }

    @Override
    public ResumeDto saveResumeDto(ResumeDto resumeDto) {
        throw new UnsupportedOperationException("operation not supported by " + this.getClass().getName());
    }

    @Override
    public List<ResumeDto> getAll() {
        throw new UnsupportedOperationException("operation not supported by " + this.getClass().getName());
    }

    @Override
    public ResumeDto updateResume(ResumeDto resumeDto) {
        throw new UnsupportedOperationException("operation not supported by " + this.getClass().getName());
    }

    @Override
    public ResumeDto deleteResume(String id) {
        throw new UnsupportedOperationException("operation not supported by " + this.getClass().getName());
    }
}
