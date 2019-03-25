package com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for getting resume from different sources in separate threads
 * Resulting resume bases on list resumes received from sources
 * this.getResume() starts all providers, joins them, and defines resulting resume
 *
 * logic fo defines resulting resume:
 * Data (name, dateOfBorn, emails, basicEducation etc.. ) in list resumes are in order by importance:
 * data from list.get(i) overwritten data from list.get(i+1), if data in list.get(i) not null
 * if data from list.get(i) is null, then this data overwritten by data from list.get(i+1), if it is not null
 *
 * logic for defines resulting resume can be overrided in this.buildResume(List<Resume>)
 */
public class Collector implements ResumeDao {

    private List<? extends Provider> providers;

    @Override
    public Resume getResume() throws Exception {
        startAllProviders();
        joinAllProviders();
        return buidResume(resumeFromProviders());
    }

    /**
     * Method contains logic for define resulting resume by list resume
     * this method may be overrided for custom
     * @param resumesFromProviders list resume
     * @return
     */
    public Resume buidResume(List<Resume> resumesFromProviders){
        Resume resume = resumesFromProviders.get(0).clone();
        //TODO imlementation
        return resumesFromProviders.get(0);
    }

    private List<Resume> resumeFromProviders() throws Exception {
        List<Resume> resumes = new ArrayList<>();
        for (Provider provider : providers) {
            try {
                resumes.add(provider.getResume());
            } catch (Exception e) {
                throw new Exception(e.getMessage() + " " + provider.toString());
            }
        }
        return resumes;
    }

    private void joinAllProviders() throws InterruptedException {
        for (Provider provider : providers) {
            provider.join();
        }
    }

    private void startAllProviders() {
        for (Provider provider : providers) {
            provider.start();
        }
    }

    public List<? extends Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<? extends Provider> providers) {
        this.providers = providers;
    }
}
