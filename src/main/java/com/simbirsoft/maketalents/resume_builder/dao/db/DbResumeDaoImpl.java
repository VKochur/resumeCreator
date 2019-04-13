package com.simbirsoft.maketalents.resume_builder.dao.db;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
     * method can throws
     * NumberFormatException if id's value not parsable as Long,
     * NoSuchElementException if the Resume by id not found
     */
    @Override
    public Resume getResume(String id){
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
     * throws IllegalStateException if db contains resumeInDb : resumeInDb.getId() == resume.getId()
     */
    @Override
    public Resume createResume(Resume resume){
        if (resumeRepository.findById(resume.getId()).isPresent()) {
            throw new IllegalStateException("resume with key = " + resume.getId() + " already exists. For updating use method 'updateResume'");
        } else {
            return resumeRepository.save(resume);
        }
    }

    /**
     *
     * @return all resume from db
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

    /**
     *
     * @param resume new data for resume
     * @return updated resume from db
     * throws IllegalStateException if db not contains resumeInDb : resumeInDb.getId() == resume.getId()
     */
    @Override
    public Resume updateResume(Resume resume) {
        if (resumeRepository.findById(resume.getId()).isPresent()) {
            return resumeRepository.save(resume);
        } else {
            throw new IllegalStateException("resume with key = " + resume.getId() + " not found. For creation use method 'createResume'");
        }
    }

    /**
     * method for delete resume in db
     * id must be contain value parsable as Long
     * @param id unique key for resume, which should be removed
     * @return deleted Resume
     * NoSuchElementException if the Resume by id not found
     */
    @Override
    public Resume deleteResume(String id) {
        Resume resumeFormRepository = getResume(id);
        resumeRepository.deleteById(Long.parseLong(id));
        return resumeFormRepository;
    }
}
