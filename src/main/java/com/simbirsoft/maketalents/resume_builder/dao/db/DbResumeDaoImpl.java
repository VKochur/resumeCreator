package com.simbirsoft.maketalents.resume_builder.dao.db;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.NotSupportedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides info about Resume from db
 * properties for connection db : resources/application.properties
 */
@Transactional
@Component("resumeDaoForDb")
public class DbResumeDaoImpl implements ResumeDao {

    @Autowired
    ResumeRepository resumeRepository;

    /**
     * Get Resume by id
     * id must be contain value parsable as Long
     * @param id unique key.
     * @return Resume
     * @throws Exception SQLException,
     * NumberFormatException if id's value not parsable as Long,
     * NoSuchElementException if the Resume by id not found
     */
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
        Hibernate.initialize(resume.getSkills());

        return resume;
    }

    /**
     * method for saving resume in db
     * @param resume data
     * @return saved resume with specific id
     */
    @Override
    public Resume saveResume(Resume resume){
        return resumeRepository.save(resume);
    }

    /**
     *
     * @return all resume from db
     * @throws NotSupportedException
     */
    @Override
    public List<Resume> getAll(){
        Iterable<Resume> iterable = resumeRepository.findAll();
        List<Resume> resumeList = new ArrayList<>();
        for (Resume resume : iterable) {
            resumeList.add(resume);
        }
        return resumeList;
    }
}
