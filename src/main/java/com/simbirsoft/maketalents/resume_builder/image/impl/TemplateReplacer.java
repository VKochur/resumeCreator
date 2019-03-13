package com.simbirsoft.maketalents.resume_builder.image.impl;

import com.simbirsoft.maketalents.resume_builder.entity.ResumeData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates html cod by replace template
 * <p>
 * template is html code in resources
 * All substrings in template like ${keyValue} replaces to value : getSubstitution().getKey(keyValue) = value
 */
public class TemplateReplacer extends CodeReplacerHtmlCreator {

    private String preCod;

    public TemplateReplacer(String resourcePath) throws IOException {
        InputStream inputTemplate = getClass().getClassLoader().getResourceAsStream(resourcePath);
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputTemplate, StandardCharsets.UTF_8.name()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (NullPointerException e) {
            throw new IOException("not found template resource for html: " + resourcePath, e);
        }
        preCod = fileContent.toString();
    }

    @Override
    public String getPreCode() {
        return preCod;
    }

    @Override
    public Map<String, String> getSubstitution() {
        ResumeData resumeData = getProvider().getData();
        Map<String, String> substitution = new HashMap<>();
        substitution.put("name", resumeData.getName());
        substitution.put("careerTarget", resumeData.getCareerTarget());
        substitution.put("dateOfBorn", resumeData.getDateOfBorn());
        substitution.put("phone", getPresent(resumeData.getPhoneNumbers()));
        substitution.put("email", getPresent(resumeData.getEmails()));
        substitution.put("skype", resumeData.getSkypeLogin());
        substitution.put("avatarUrl", resumeData.getUrlAvatar());
        substitution.put("target", getPresent(resumeData.getTargets()));
        substitution.put("experience", getPresent(resumeData.getExperiences()));
        substitution.put("baseEducation", getPresent(resumeData.getBasicEducations()));
        substitution.put("addedEducation", getPresent(resumeData.getAdditionalEducations()));
        substitution.put("otherInfo", resumeData.getOtherInfo());
        return substitution;
    }

    private String getPresent(List<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line).append("<br>");
        }
        return stringBuilder.toString();
    }
}
