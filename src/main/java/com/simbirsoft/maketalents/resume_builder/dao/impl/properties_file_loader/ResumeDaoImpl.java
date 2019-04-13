package com.simbirsoft.maketalents.resume_builder.dao.impl.properties_file_loader;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.core.data.ManagerDataSource;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.properties_file_loader.ManagerDataSourceImpl;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Provides info about resume from property file.
 * <p>
 * Encoding file must be UTF-8 without BOM
 * <p>
 * Set of allowed tags is
 * FIO
 * DOB
 * EMAILS
 * PHONES
 * SKYPE
 * URL_AVATAR
 * TARGETS
 * EXPERIENCES
 * BS_EDUCATIONS
 * AD_EDUCATIONS
 * OTHER_INFO
 * CAREER_TARGET
 * SKILLS
 * Separator between key and value is '='
 * In the case of various options for specific key is used '|' as separator
 * In case not found specific tag, the associated context is null
 * <p>
 * SKILLS - info about skills in format like: "skill1:countMonthsInUsing1,skill2:countMonthsInUsing2,..."
 *
 * <p>
 * Example properties file:
 * FIO=Name Second_Name
 * DOB=13.08.1983
 * EMAIL=name@rambler.ru|name@gmail.com
 * PHONE=???7???
 * SKYPE=login
 * TARGET=target1|target2|target3
 * CAREER_TARGET=career target
 * SKILLS=java:12,sql:24,IIdea:6
 */
@Repository("resumeDaoFromPropertiesFile")
public class ResumeDaoImpl implements ResumeDao {

    /**
     * Method returns Resume from properties file
     *
     * @param pathPropertiesFile unique key  -  this is path to properties file
     * @return Resume by data from file
     * @throws IOException
     */
    @Override
    public Resume getResume(String pathPropertiesFile) {
        ManagerDataSource managerDataSource = getManagerDataSource();
        try {
            return managerDataSource.getResume(pathPropertiesFile);
        } catch (Exception e) {
            throw new IllegalStateException("occured exception: " + e.getMessage(), e);
        }
    }

    private ManagerDataSource getManagerDataSource() {
        return new ManagerDataSourceImpl();
    }

    @Override
    public Resume createResume(Resume resume) {
        throw new UnsupportedOperationException("operation not supported by " + this.getClass().getName());
    }

    @Override
    public List<Resume> getAll() {
        throw new UnsupportedOperationException("operation not supported by " + this.getClass().getName());
    }

    @Override
    public Resume updateResume(Resume resume) {
        throw new UnsupportedOperationException("operation not supported by " + this.getClass().getName());
    }

    @Override
    public Resume deleteResume(String id) {
        throw new UnsupportedOperationException("operation not supported by " + this.getClass().getName());
    }
}
