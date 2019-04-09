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
 * Service for working with 2 properties files in separate threads
 */
@Service("resumeServiceForPropertiesFileMultiThreading")
public class MultiThreadingResumeServiceImpl implements ResumeService {

    @Autowired
    Util utilForDTO;
    @Autowired
    @Qualifier("resumeDaoFromPropertiesMultiThreading")
    private ResumeDao resumeDao;

    /**
     * Method gets resumeDTO by 2 properties file
     * Data in fist file mor important that second file.
     * Resulting resume contains by data from first file, and gets from second properties file info,
     * that not specified in first
     *
     * @param compositeKey format "string: pathToFirstFile,pathToSecondFile",
     *                     *                     example getResume("c:\temp\1.properties,c:\second.properties")
     * @return resume
     * @throws Exception
     */
    @Override
    public ResumeDto getResumeDto(String compositeKey) throws Exception {
        return utilForDTO.getDtoByResume(resumeDao.getResume(compositeKey));
    }

    @Override
    public ResumeDto saveResumeDto(ResumeDto resumeDto) throws NotSupportedException {
        throw new NotSupportedException("operation not supported by " + this.getClass().getName());
    }

    @Override
    public List<ResumeDto> getAll() throws NotSupportedException {
        throw new NotSupportedException("operation not supported by " + this.getClass().getName());
    }
}
