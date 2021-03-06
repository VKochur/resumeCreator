package com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Creates html cod by replace template
 * <p>
 * template is html code in resources
 * All substrings in template like ${keyValue} replaces to value : getSubstitution().getKey(keyValue) = value
 * <p>
 * <p>
 * warning: code, that returns method getPreCode can be different from code in template
 * if code in template not ends with '\n', getPreCode returns template's code and '\n' at the end
 * if code in template ends with '\n', getPreCode returns template's code
 */
public class ReplacerHtmlCodeByTemplate extends ReplacerHtmlCodeCreator {
    @Override
    public String getPreCode() throws Exception {
        String resourcePath = "html/template.html";
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
        return fileContent.toString();
    }

    @Override
    public Map<String, String> getSubstitution() {
        Map<String, String> substitution = new HashMap<>();
        substitution.put("name", getPresent(getResume().getName()));
        substitution.put("careerTarget", getPresent(getResume().getCareerTarget()));
        substitution.put("dateOfBorn", getPresent(getResume().getDateOfBorn()));
        substitution.put("phone", getPresent(getResume().getPhoneNumbers()));
        substitution.put("email", getPresent(getResume().getEmails()));
        substitution.put("skype", getPresent(getResume().getSkypeLogin()));
        substitution.put("avatarUrl", getPresent(getResume().getUrlAvatar()));
        substitution.put("target", getPresent(getResume().getTargets()));
        substitution.put("experience", getPresent(getResume().getExperiences()));
        substitution.put("baseEducation", getPresent(getResume().getBasicEducations()));
        substitution.put("addedEducation", getPresent(getResume().getAdditionalEducations()));
        substitution.put("otherInfo", getPresent(getResume().getOtherInfo()));
        substitution.put("skills", getPresent(getResume().getSkills()));
        return substitution;
    }

    private String getPresent(String value) {
        return (value != null) ? value : "";
    }

    private String getPresent(Map<String, Integer> skills) {
        if (skills != null) {
            Set<Map.Entry<String, Integer>> sortedSkills = new TreeSet<>((o1, o2) -> o2.getValue() - o1.getValue());
            sortedSkills.addAll(skills.entrySet());
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Integer> el : sortedSkills) {
                result.append(el.getKey()).append(" - ").append(el.getValue().toString()).append("мес.; ");
            }
            return result.toString();
        } else {
            return "";
        }
    }

    private String getPresent(List<String> lines) {
        if (lines != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : lines) {
                stringBuilder.append(line).append("<br>");
            }
            return stringBuilder.toString();
        } else {
            return "";
        }
    }
}
