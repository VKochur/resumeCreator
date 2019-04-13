package com.simbirsoft.maketalents.resume_builder.entity;

import com.simbirsoft.maketalents.resume_builder.model.core.ResumeBuilder;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "summary")
public class Resume implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String careerTarget;
    private String name;
    private String dateOfBorn;

    @ElementCollection
    @CollectionTable (name = "phones", joinColumns = @JoinColumn(name = "resume_id"))
    private List<String> phoneNumbers;

    @ElementCollection
    @CollectionTable (name = "emails", joinColumns = @JoinColumn(name = "resume_id"))
    private List<String> emails;

    private String skypeLogin;
    private String urlAvatar;

    @ElementCollection
    @CollectionTable (name = "targets", joinColumns = @JoinColumn(name = "resume_id"))
    private List<String> targets;

    @ElementCollection
    @CollectionTable (name = "experiences", joinColumns = @JoinColumn(name = "resume_id"))
    private List<String> experiences;

    @ElementCollection
    @CollectionTable (name = "basic_educations", joinColumns = @JoinColumn(name = "resume_id"))
    private List<String> basicEducations;

    @ElementCollection
    @CollectionTable (name = "added_educations", joinColumns = @JoinColumn(name = "resume_id"))
    private List<String> additionalEducations;

    private String otherInfo;

    @ElementCollection
    @MapKeyColumn
    private Map<String, Integer> skills;

    public Resume() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ID:").append((getId() == null) ? "null" : getId().toString()).append("\n")
                .append("FIO:").append(getName()).append("\n")
                .append("Data of born: ").append(getDateOfBorn()).append("\n")
                .append("Career target: ").append(getCareerTarget()).append("\n")
                .append("Phones: ").append(getPhoneNumbers()).append("\n")
                .append("SKYPE: ").append(getSkypeLogin()).append("\n")
                .append("URL_AVATAR: ").append(getUrlAvatar()).append("\n")
                .append("Targets: ").append(getTargets()).append("\n")
                .append("Experiences: ").append(getExperiences()).append("\n")
                .append("Basic education: ").append(getBasicEducations()).append("\n")
                .append("Additional education: ").append(getAdditionalEducations()).append("\n")
                .append("Other info: ").append(getOtherInfo()).append("\n")
                .append("Skills: ").append(getSkills())
                .toString();
    }
}
