package com.simbirsoft.maketalents.resume_builder.service.impl;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.dto.Util;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("resumeServiceForDb")
public class DbResumeService implements ResumeService {

    @Autowired
    @Qualifier("resumeDaoForDb")
    ResumeDao resumeDao;

    @Autowired
    Util util;

    /**
     * @param id unique key, must be parsable to Long
     * @return ResumeDro from db by key
     * throws NoSuchElementException if the Resume by id not found
     */
    @Override
    public ResumeDto getResumeDto(String id) {
        return util.getDtoByResume(resumeDao.getResume(id));
    }

    /**
     * @param resumeDto
     * @return
     * IllegalStateException if db contains resume with key = resumeDto.getId()
     */
    @Override
    public ResumeDto saveResumeDto(ResumeDto resumeDto) {
        Resume resume = resumeDao.createResume(util.getResumeByDTO(resumeDto));
        return util.getDtoByResume(resume);
    }

    /**
     *
     * @return
     */
    @Override
    public List<ResumeDto> getAll() {
        List<ResumeDto> resumeDtoList = new ArrayList<>();
        for (Resume resume : resumeDao.getAll()) {
            resumeDtoList.add(util.getDtoByResume(resume));
        }
        return resumeDtoList;
    }

    /**
     * method update resume in db : resume.getId() == resumeDto.getId()
     * @param resumeDto new date for resume in source
     * @return updated resumeDto from db
     * IllegalStateException if db not contains resume with key = resumeDto.getId()
     */
    @Override
    public ResumeDto updateResume(ResumeDto resumeDto) {
        Resume updatedResume = resumeDao.updateResume(util.getResumeByDTO(resumeDto));
        return util.getDtoByResume(updatedResume);
    }

    /**
     * method delete Resume by unique key
     * @param id unique key for resume
     * @return deleted ResumeDto
     * throws NoSuchElementException if the Resume by id not found
     */
    @Override
    public ResumeDto deleteResume(String id) {
        return util.getDtoByResume(resumeDao.deleteResume(id));
    }
}
