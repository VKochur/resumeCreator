package com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.core.data.ManagerDataSource;

import java.util.*;
import java.util.function.Predicate;

/**
 * Class for getting resume from different sources in separate threads
 * <p>
 * Resulting resume bases on list resumes received from sources
 * this.getResume(stringKeys) starts all providers, joins them, and defines resulting resume
 * this.getResume(stringKeys) starts providers, because this method throws IllegalThreadStateException,
 * if was called more then once for the same providers
 * <p>
 * method setProviders(List<Provider>) sets list providers for getting resumes from different sources
 * if any provider already terminated getResume(stringKeys) throws IllegalThreadStateException
 * stringKeys - data about resume's id, that providers must used at moment getting resume
 * for getting resume getProviders.get[i] uses keys.get[i]. size of list providers and list keys not checked
 * example format for stringKeys = "key1,key2,key3".
 * logic for calculate List<String> keys is method List<String> defineKeys(String keysForGettingResumes) and may be override
 * <p>
 * logic for defines resulting resume:
 * Data (name, dateOfBorn, emails, basicEducation etc.. ) in list resumes are in order by importance:
 * data from resume1 = list.get(i) overwritten data from resume2 = list.get(i+1), if data in resume1 not null and not empty
 * if data from resume1 is null, then this data overwritten by data from resume2, if it is not null and not empty
 * <p>
 * if data from resume is String, then data is considered as empty if data = null, or data = ""
 * if data is List<String>, then data is considered as empty if data = null, or data.size() == 0, or data.size() == 1,  data.get(1) == (null or "")
 * if data is Map, then data is considered as empty if data = null, or data.size() == 0
 * <p>
 * if not data in list resumes that not null and not empty, the data in resulting resume is null
 * <p>
 * logic can be overrided in this.buildResume(List<Resume>)
 */
public class Collector implements ManagerDataSource {
    private List<Provider> providers;

    @Override
    public Resume getResume(String keysForGettingResumes) throws Exception {
        List<String> keys = defineKeys(keysForGettingResumes);
        startAllProviders(keys);
        joinAllProviders();
        return buidResume(resumeFromProviders(keys));
    }

    /**
     * method for calculate list keys by string
     * Sample: "sfsf gfsg 42w, werwerwer234, 2,3,4" -> "sfsf gfsg 42w", " werwerwer234", "2", "3", "4"
     *
     * @param keysForGettingResumes info about id, which must be used by providers for calculate resumes
     * @return
     */
    public List<String> defineKeys(String keysForGettingResumes) {
        return new ArrayList<>(Arrays.asList(keysForGettingResumes.split(",")));
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
        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getName()));
        if (resumeOptional.isPresent()) {
            resume.setName(resumeOptional.get().getName());
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getDateOfBorn()));
        if (resumeOptional.isPresent()) {
            resume.setDateOfBorn(resumeOptional.get().getDateOfBorn());
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getCareerTarget()));
        if (resumeOptional.isPresent()) {
            resume.setCareerTarget(resumeOptional.get().getCareerTarget());
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getPhoneNumbers()));
        if (resumeOptional.isPresent()) {
            resume.setPhoneNumbers(new ArrayList<>(resumeOptional.get().getPhoneNumbers()));
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getEmails()));
        if (resumeOptional.isPresent()) {
            resume.setEmails(new ArrayList<>(resumeOptional.get().getEmails()));
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getSkypeLogin()));
        if (resumeOptional.isPresent()) {
            resume.setSkypeLogin(resumeOptional.get().getSkypeLogin());
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getUrlAvatar()));
        if (resumeOptional.isPresent()) {
            resume.setUrlAvatar(resumeOptional.get().getUrlAvatar());
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getTargets()));
        if (resumeOptional.isPresent()) {
            resume.setTargets(new ArrayList<>(resumeOptional.get().getTargets()));
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getExperiences()));
        if (resumeOptional.isPresent()) {
            resume.setExperiences(new ArrayList<>(resumeOptional.get().getExperiences()));
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getBasicEducations()));
        if (resumeOptional.isPresent()) {
            resume.setBasicEducations(new ArrayList<>(resumeOptional.get().getBasicEducations()));
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getAdditionalEducations()));
        if (resumeOptional.isPresent()) {
            resume.setAdditionalEducations(new ArrayList<>(resumeOptional.get().getAdditionalEducations()));
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getOtherInfo()));
        if (resumeOptional.isPresent()) {
            resume.setOtherInfo(resumeOptional.get().getOtherInfo());
        }

        resumeOptional = getFirstSuitable(resumesFromProviders, currentResume -> notEmptyNotNull(currentResume.getSkills()));
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

    private boolean notEmptyNotNull(List<String> list) {
        if (list == null) {
            return false;
        } else {
            if (list.isEmpty()) {
                return false;
            } else {
                if (list.size() == 1) {
                    return notEmptyNotNull(list.get(0));
                } else {
                    return true;
                }
            }
        }
    }

    private boolean notEmptyNotNull(Map map) {
        if (map == null) {
            return false;
        } else {
            return !map.isEmpty();
        }
    }

    private Optional<Resume> getFirstSuitable(List<Resume> resumes, Predicate<Resume> predicate) {
        return resumes.stream().filter(predicate).findFirst();
    }

    private List<Resume> resumeFromProviders(List<String> keys) throws Exception {
        List<Resume> resumes = new ArrayList<>();
        for (int i = 0; i < providers.size(); i++) {
            try {
                resumes.add(providers.get(i).getResume(keys.get(i)));
            } catch (Exception e) {
                throw new Exception(e.getMessage() + " " + providers.get(i).toString());
            }
        }
        return resumes;
    }

    private void joinAllProviders() throws InterruptedException {
        for (Provider provider : providers) {
            provider.join();
        }
    }

    private void startAllProviders(List<String> keys) {
        for (int i = 0; i < providers.size(); i++) {
            providers.get(i).startGettingResume(keys.get(i));
        }
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
