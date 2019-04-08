package com.simbirsoft.maketalents.resume_builder.service.impl;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.dto.Util;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;

@Service("resumeServiceForDb")
public class DbResumeService implements ResumeService {

    @Autowired
    @Qualifier("resumeDaoForDb")
    ResumeDao resumeDao;

    @Autowired
    Util util;

    @Override
    public ResumeDto getResumeDto(String id) throws Exception {
        Resume resume = resumeDao.getResume(id);
        return util.getDtoByResume(resumeDao.getResume(id));
    }

    @Override
    public ResumeDto saveResumeDto(ResumeDto resumeDto){
        try {
            Resume resume = resumeDao.saveResume(util.getResumeByDTO(resumeDto));
            return util.getDtoByResume(resume);
        } catch (NotSupportedException e) {
            throw new IllegalStateException("occured NotSupportedException", e);
        }
    }
}
