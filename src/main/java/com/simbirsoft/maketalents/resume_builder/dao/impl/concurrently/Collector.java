package com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;

import java.util.*;
import java.util.function.Predicate;

/**
 * Class for getting resume from different sources in separate threads
 * Resulting resume bases on list resumes received from sources
 * this.getResume() starts all providers, joins them, and defines resulting resume
 * if any provider already terminated getResume() throws IllegalThreadStateException
 * <p>
 * logic fo defines resulting resume:
 * Data (name, dateOfBorn, emails, basicEducation etc.. ) in list resumes are in order by importance:
 * data from list.get(i) overwritten data from list.get(i+1), if data in list.get(i) not null and not empty
 * if data from list.get(i) is null, then this data overwritten by data from list.get(i+1), if it is not null and not empty
 * if not data in resumes not null and not empty, the data in resulting resume is null
 * <p>
 * logic can be overrided in this.buildResume(List<Resume>)
 */
public class Collector implements ResumeDao {

    private List<Provider> providers;

    @Override
    public Resume getResume() throws Exception {
        startAllProviders();
        joinAllProviders();
        return buidResume(resumeFromProviders());
    }

    /**
     * Method contains logic for define resulting resume by list resume
     * this method may be overrided
     *
     * @param resumesFromProviders list resume
     * @return resulting resume
     */
    //TODO: need simplification
    public Resume buidResume(List<Resume> resumesFromProviders) {
        Resume resume = new Resume();

        Optional<Resume> resumeOptional;
        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getName()));
        if (resumeOptional.isPresent()) {
            resume.setName(resumeOptional.get().getName());
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getDateOfBorn()));
        if (resumeOptional.isPresent()) {
            resume.setDateOfBorn(resumeOptional.get().getDateOfBorn());
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getCareerTarget()));
        if (resumeOptional.isPresent()) {
            resume.setCareerTarget(resumeOptional.get().getCareerTarget());
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getPhoneNumbers()));
        if (resumeOptional.isPresent()) {
            resume.setPhoneNumbers(new ArrayList<>(resumeOptional.get().getPhoneNumbers()));
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getEmails()));
        if (resumeOptional.isPresent()) {
            resume.setEmails(new ArrayList<>(resumeOptional.get().getEmails()));
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getSkypeLogin()));
        if (resumeOptional.isPresent()) {
            resume.setSkypeLogin(resumeOptional.get().getSkypeLogin());
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getUrlAvatar()));
        if (resumeOptional.isPresent()) {
            resume.setUrlAvatar(resumeOptional.get().getUrlAvatar());
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getTargets()));
        if (resumeOptional.isPresent()) {
            resume.setTargets(new ArrayList<>(resumeOptional.get().getTargets()));
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getExperiences()));
        if (resumeOptional.isPresent()) {
            resume.setExperiences(new ArrayList<>(resumeOptional.get().getExperiences()));
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getBasicEducations()));
        if (resumeOptional.isPresent()) {
            resume.setBasicEducations(new ArrayList<>(resumeOptional.get().getBasicEducations()));
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getAdditionalEducations()));
        if (resumeOptional.isPresent()) {
            resume.setAdditionalEducations(new ArrayList<>(resumeOptional.get().getAdditionalEducations()));
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getOtherInfo()));
        if (resumeOptional.isPresent()) {
            resume.setOtherInfo(resumeOptional.get().getOtherInfo());
        }

        resumeOptional = getFirstSiutable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getSkills()));
        if (resumeOptional.isPresent()) {
            resume.setSkills(new HashMap<>(resumeOptional.get().getSkills()));
        }

        return resume;
    }

    private boolean notEmptyNotNull(String str) {
        if (str == null) {
            return false;
        } else {
            return !"".equals(str);
        }
    }

    private boolean notEmptyNotNull(List list) {
        if (list == null) {
            return false;
        } else {
            return !list.isEmpty();
        }
    }

    private boolean notEmptyNotNull(Map map) {
        if (map == null) {
            return false;
        } else {
            return !map.isEmpty();
        }
    }

    private Optional<Resume> getFirstSiutable(List<Resume> resumes, Predicate<Resume> predicate) {
        return resumes.stream().filter(predicate).findFirst();
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

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
