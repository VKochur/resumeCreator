package com.simbirsoft.maketalents.resume_builder.model;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

import java.util.List;

public class ResumeBuilder {

    private Resume resume;

    public ResumeBuilder() {
        resume = new Resume();
    }

    public ResumeBuilder setCareerTarget(String careerTarget){
        resume.setCareerTarget(careerTarget);
        return this;
    }

    public ResumeBuilder setName(String name){
        resume.setName(name);
        return this;
    }

    public ResumeBuilder setDataOfBorn(String dateOfBorn) {
        resume.setDateOfBorn(dateOfBorn);
        return this;
    }

    public ResumeBuilder setPhoneNumbers(List<String> phoneNumbers){
        resume.setPhoneNumbers(phoneNumbers);
        return this;
    }

    public ResumeBuilder setEmails(List<String> emails){
        resume.setEmails(emails);
        return this;
    }

    public ResumeBuilder setSkypeLogin(String skypeLogin){
        resume.setSkypeLogin(skypeLogin);
        return this;
    }

    public ResumeBuilder setUrlAvatar(String url){
        resume.setUrlAvatar(url);
        return this;
    }

    public ResumeBuilder setTargets(List<String> targets){
        resume.setTargets(targets);
        return this;
    }

    public ResumeBuilder setExperiences(List<String> experiences){
        resume.setExperiences(experiences);
        return this;
    }

    public ResumeBuilder setBasicEducations(List<String> educations){
        resume.setBasicEducations(educations);
        return this;
    }

    public ResumeBuilder setAdditionalEdications(List<String> educations){
        resume.setAdditionalEducations(educations);
        return this;
    }

    public ResumeBuilder setOtherInfo(String info){
        resume.setOtherInfo(info);
        return this;
    }

    public Resume build() {
        return resume;
    }
}
