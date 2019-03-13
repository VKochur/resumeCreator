package com.simbirsoft.maketalents.resume_builder.model;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

import java.util.List;

public class ResumeBuilder {

    private Resume resumeData;

    public ResumeBuilder() {
        resumeData = new Resume();
    }

    public ResumeBuilder setCareerTarget(String carrerTarget){
        resumeData.setCareerTarget(carrerTarget);
        return this;
    }

    public ResumeBuilder setName(String name){
        resumeData.setName(name);
        return this;
    }

    public ResumeBuilder setDataOfBorn(String dateOfBorn) {
        resumeData.setDateOfBorn(dateOfBorn);
        return this;
    }

    public ResumeBuilder setPhoneNumbers(List<String> phoneNumbers){
        resumeData.setPhoneNumbers(phoneNumbers);
        return this;
    }

    public ResumeBuilder setEmails(List<String> emails){
        resumeData.setEmails(emails);
        return this;
    }

    public ResumeBuilder setSkypeLogin(String skypeLogin){
        resumeData.setSkypeLogin(skypeLogin);
        return this;
    }

    public ResumeBuilder setUrlAvatar(String url){
        resumeData.setUrlAvatar(url);
        return this;
    }

    public ResumeBuilder setTargets(List<String> targets){
        resumeData.setTargets(targets);
        return this;
    }

    public ResumeBuilder setExperiences(List<String> experiences){
        resumeData.setExperiences(experiences);
        return this;
    }

    public ResumeBuilder setBasicEducations(List<String> educations){
        resumeData.setBasicEducations(educations);
        return this;
    }

    public ResumeBuilder setAdditionalEdications(List<String> educations){
        resumeData.setAdditionalEducations(educations);
        return this;
    }

    public ResumeBuilder setOtherInfo(String info){
        resumeData.setOtherInfo(info);
        return this;
    }

    public Resume build() {
        return resumeData;
    }
}
