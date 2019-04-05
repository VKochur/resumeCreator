package com.simbirsoft.maketalents.resume_builder.dao.db;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("resumeDaoForDb")
public class DbResumeDaoImpl implements ResumeDao {

    @Autowired
    Repository repository;

    @Override
    public Resume getResume(String id) throws Exception {
        Long idInRepository = Long.parseLong(id);
        return repository.getOne(idInRepository);
    }

    public Resume saveResume(Resume resume){
        return repository.save(resume);
    }
}
