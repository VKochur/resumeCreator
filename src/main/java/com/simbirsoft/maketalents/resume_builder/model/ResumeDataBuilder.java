package com.simbirsoft.maketalents.resume_builder.model;

import com.simbirsoft.maketalents.resume_builder.entity.ResumeData;

import java.util.List;

public class ResumeDataBuilder {

    private ResumeData resumeData;

    public ResumeDataBuilder() {
        resumeData = new ResumeData();
    }

    public ResumeDataBuilder setCareerTarget(String carrerTarget){
        resumeData.setCareerTarget(carrerTarget);
        return this;
    }

    public ResumeDataBuilder setName(String name){
        resumeData.setName(name);
        return this;
    }

    public ResumeDataBuilder setDataOfBorn(String dateOfBorn) {
        resumeData.setDateOfBorn(dateOfBorn);
        return this;
    }

    public ResumeDataBuilder setPhoneNumbers(List<String> phoneNumbers){
        resumeData.setPhoneNumbers(phoneNumbers);
        return this;
    }

    public ResumeDataBuilder setEmails(List<String> emails){
        resumeData.setEmails(emails);
        return this;
    }

    public ResumeDataBuilder setSkypeLogin(String skypeLogin){
        resumeData.setSkypeLogin(skypeLogin);
        return this;
    }

    public ResumeDataBuilder setUrlAvatar(String url){
        resumeData.setUrlAvatar(url);
        return this;
    }

    public ResumeDataBuilder setTargets(List<String> targets){
        resumeData.setTargets(targets);
        return this;
    }

    public ResumeDataBuilder setExperiences(List<String> experiences){
        resumeData.setExperiences(experiences);
        return this;
    }

    public ResumeDataBuilder setBasicEducations(List<String> educations){
        resumeData.setBasicEducations(educations);
        return this;
    }

    public ResumeDataBuilder setAdditionalEdications(List<String> educations){
        resumeData.setAdditionalEducations(educations);
        return this;
    }

    public ResumeDataBuilder setOtherInfo(String info){
        resumeData.setOtherInfo(info);
        return this;
    }

    public ResumeData build() {
        return resumeData;
    }
}
