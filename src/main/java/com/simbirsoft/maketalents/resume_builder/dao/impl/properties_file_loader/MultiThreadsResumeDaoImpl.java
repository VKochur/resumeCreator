package com.simbirsoft.maketalents.resume_builder.dao.impl.properties_file_loader;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.core.data.ManagerDataSource;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently.Collector;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently.PropertyReader;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently.Provider;
import org.springframework.stereotype.Repository;

import javax.transaction.NotSupportedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides info about resume from 2 properties files
 * Files are reading in separate threads.
 * Data in fist file mor important that second file.
 * Resulting resume contains by data from first file, and gets from second properties file info,
 * that not specified in first
 */
@Repository("resumeDaoFromPropertiesMultiThreading")
public class MultiThreadsResumeDaoImpl implements ResumeDao {

    /**
     *  Method returns Resume from 2 properties files
     * @param compositeKey format "string: pathToFirstFile,pathToSecondFile",
     *                     example getResume("c:\temp\1.properties,c:\second.properties")
     * @return
     * @throws Exception throws IOException
     */
    @Override
    public Resume getResume(String compositeKey) throws Exception {
        ManagerDataSource managerDataSource = getManagerDataSource();
        return managerDataSource.getResume(compositeKey);
    }

    private ManagerDataSource getManagerDataSource() {
        Collector collector = new Collector();
        List<Provider> providers = new ArrayList<>();
        providers.add(new PropertyReader());
        providers.add(new PropertyReader());
        collector.setProviders(providers);
        return collector;
    }

    @Override
    public Resume saveResume(Resume resume) throws NotSupportedException {
        throw new NotSupportedException("operation not supported by " + this.getClass().getName());
    }

    @Override
    public List<Resume> getAll() throws NotSupportedException {
        throw new NotSupportedException("operation not supported by " + this.getClass().getName());
    }
}
