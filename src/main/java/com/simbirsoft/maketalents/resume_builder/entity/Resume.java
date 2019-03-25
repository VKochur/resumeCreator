package com.simbirsoft.maketalents.resume_builder.entity;

import com.simbirsoft.maketalents.resume_builder.model.ResumeBuilder;

import java.util.*;

public class Resume implements Cloneable{

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
    public Resume clone(){
        return new ResumeBuilder().setName(this.name)
                .setDataOfBorn(this.dateOfBorn)
                .setCareerTarget(this.careerTarget)
                .setPhoneNumbers(new ArrayList<>(this.phoneNumbers))
                .setEmails(new ArrayList<>(this.emails))
                .setSkypeLogin(this.skypeLogin)
                .setUrlAvatar(this.urlAvatar)
                .setTargets(new ArrayList<>(this.targets))
                .setExperiences(new ArrayList<>(this.experiences))
                .setBasicEducations(new ArrayList<>(this.basicEducations))
                .setAdditionalEdications(new ArrayList<>(this.additionalEducations))
                .setOtherInfo(this.otherInfo)
                .setSkills(new HashMap<>(this.skills))
                .build();
    }
}
