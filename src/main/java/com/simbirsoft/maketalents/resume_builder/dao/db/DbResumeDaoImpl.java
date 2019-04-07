package com.simbirsoft.maketalents.resume_builder.dao.db;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("resumeDaoForDb")
public class DbResumeDaoImpl implements ResumeDao {

    @Autowired
    ResumeRepository resumeRepository;

    @Override
    public Resume getResume(String id) throws Exception {
        Long idInRepository = Long.parseLong(id);
        return resumeRepository.getOne(idInRepository);
    }

    public Resume saveResume(Resume resume){
        return resumeRepository.save(resume);
    }
}
