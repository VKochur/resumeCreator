package com.simbirsoft.maketalents.resume_builder.dao.db;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("resumeDaoForDb")
public class DbResumeDaoImpl implements ResumeDao {

    @Autowired
    ResumeRepository resumeRepository;

    @Override
    public Resume getResume(String id) throws Exception {
        Long idInRepository = Long.parseLong(id);
        Resume resume = resumeRepository.findById(idInRepository).get();

        Hibernate.initialize(resume.getPhoneNumbers());
        Hibernate.initialize(resume.getEmails());
        Hibernate.initialize(resume.getTargets());
        Hibernate.initialize(resume.getExperiences());
        Hibernate.initialize(resume.getBasicEducations());
        Hibernate.initialize(resume.getAdditionalEducations());

        return resume;
    }

    public Resume saveResume(Resume resume){
        return resumeRepository.save(resume);
    }
}
