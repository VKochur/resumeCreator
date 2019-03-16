package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates html cod by replace template
 * <p>
 * template is html code in resources
 * All substrings in template like ${keyValue} replaces to value : getSubstitution().getKey(keyValue) = value
 *
 *
 * warning: code, that returns method getPreCode can be different from code in template
 * if code in template not ends with '\n', getPreCode returns template's code and '\n' at the end
 * if code in template ends with '\n', getPreCode returns template's code
 */
public class ReplacerCodeByTemplate extends ReplacerHtmlCodeCreator {

    private String resourcePath;

    public ReplacerCodeByTemplate(String resourcePath){
        this.resourcePath = resourcePath;
    }

    @Override
    public String getPreCode() throws IOException {
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
        substitution.put("name", getResume().getName());
        substitution.put("careerTarget", getResume().getCareerTarget());
        substitution.put("dateOfBorn", getResume().getDateOfBorn());
        substitution.put("phone", getPresent(getResume().getPhoneNumbers()));
        substitution.put("email", getPresent(getResume().getEmails()));
        substitution.put("skype", getResume().getSkypeLogin());
        substitution.put("avatarUrl", getResume().getUrlAvatar());
        substitution.put("target", getPresent(getResume().getTargets()));
        substitution.put("experience", getPresent(getResume().getExperiences()));
        substitution.put("baseEducation", getPresent(getResume().getBasicEducations()));
        substitution.put("addedEducation", getPresent(getResume().getAdditionalEducations()));
        substitution.put("otherInfo", getResume().getOtherInfo());
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
