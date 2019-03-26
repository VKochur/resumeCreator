package com.simbirsoft.maketalents.resume_builder.entity;

import com.simbirsoft.maketalents.resume_builder.model.ResumeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resume implements Cloneable {

    private String careerTarget;
    private String name;
    private String dateOfBorn;
    private List<String> phoneNumbers;
    private List<String> emails;
    private String skypeLogin;
    private String urlAvatar;
    private List<String> targets;
    private List<String> experiences;
    private List<String> basicEducations;
    private List<String> additionalEducations;
    private String otherInfo;
    private Map<String, Integer> skills;

    public Resume() {
    }

    public String getCareerTarget() {
        return careerTarget;
    }

    public void setCareerTarget(String careerTarget) {
        this.careerTarget = careerTarget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(String dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getSkypeLogin() {
        return skypeLogin;
    }

    public void setSkypeLogin(String skypeLogin) {
        this.skypeLogin = skypeLogin;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public List<String> getTargets() {
        return targets;
    }

    public void setTargets(List<String> targets) {
        this.targets = targets;
    }

    public List<String> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<String> experiences) {
        this.experiences = experiences;
    }

    public List<String> getBasicEducations() {
        return basicEducations;
    }

    public void setBasicEducations(List<String> basicEducations) {
        this.basicEducations = basicEducations;
    }

    public List<String> getAdditionalEducations() {
        return additionalEducations;
    }

    public void setAdditionalEducations(List<String> additionalEducations) {
        this.additionalEducations = additionalEducations;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }

    @Override
    public Resume clone() {
        return new ResumeBuilder().setName(this.name)
                .setDataOfBorn(this.dateOfBorn)
                .setCareerTarget(this.careerTarget)
                .setPhoneNumbers((this.phoneNumbers != null) ? new ArrayList<>(this.phoneNumbers) : null)
                .setEmails((this.emails != null) ? new ArrayList<>(this.emails) : null)
                .setSkypeLogin(this.skypeLogin)
                .setUrlAvatar(this.urlAvatar)
                .setTargets((this.targets != null) ? new ArrayList<>(this.targets) : null)
                .setExperiences((this.experiences != null) ? new ArrayList<>(this.experiences) : null)
                .setBasicEducations((this.basicEducations != null) ? new ArrayList<>(this.basicEducations) : null)
                .setAdditionalEdications((this.additionalEducations != null) ? new ArrayList<>(this.additionalEducations) : null)
                .setOtherInfo(this.otherInfo)
                .setSkills((this.skills != null) ? new HashMap<>(this.skills) : null)
                .build();
    }
}
