package com.simbirsoft.maketalents.resume_builder.model.core.data.impl.properties_file_loader;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.core.ResumeBuilder;
import com.simbirsoft.maketalents.resume_builder.model.core.data.ManagerDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Provides info about resume from property file.
 * <p>
 * Encoding file must be UTF-8 without BOM
 * <p>
 * Set of allowed tags is set of TagTypes
 * Separator between key and value is '='
 * In the case of various options for specific key is used '|' as separator
 * In case not found specific tag, the associated context is null
 * <p>
 * SKILLS - info about skills in format like: "skill1:countMonthsInUsing1,skill2:countMonthsInUsing2,..."
 *
 * <p>
 * Example properties file:
 * FIO=Name Second_Name
 * DOB=13.08.1983
 * EMAIL=name@rambler.ru|name@gmail.com
 * PHONE=???7???
 * SKYPE=login
 * TARGET=target1|target2|target3
 * CAREER_TARGET=career target
 * SKILLS=java:12,sql:24,IIdea:6
 */
public class ManagerDataSourceImpl implements ManagerDataSource {

    private static final String CONTEXT_SEPARATOR_REGEX = "\\|";
    private static final String SEPARATOR_SKILLS = ",";
    private static final String SEPARATOR_DURATION_SKILL = ":";

    private Properties properties;

    public ManagerDataSourceImpl() {
    }

    /**
     * Method returns Resume from properties file
     * @param pathFile unique key  -  this is path to properties file
     * @return
     * @throws IOException if file not exists or not no access
     */
    @Override
    public Resume getResume(String pathFile) throws IOException {

        properties = new Properties();
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(pathFile), StandardCharsets.UTF_8.name())) {
            properties.load(inputStreamReader);
        }

        return new ResumeBuilder()
                .setCareerTarget(getPropValueByTag(TagTypes.CAREER_TARGET))
                .setName(getPropValueByTag(TagTypes.FIO))
                .setDataOfBorn(getPropValueByTag(TagTypes.DOB))
                .setPhoneNumbers(defineListByLine(getPropValueByTag(TagTypes.PHONES)))
                .setEmails(defineListByLine(getPropValueByTag(TagTypes.EMAILS)))
                .setSkypeLogin(getPropValueByTag(TagTypes.SKYPE))
                .setUrlAvatar(getPropValueByTag(TagTypes.URL_AVATAR))
                .setTargets(defineListByLine(getPropValueByTag(TagTypes.TARGETS)))
                .setExperiences(defineListByLine(getPropValueByTag(TagTypes.EXPERIENCES)))
                .setBasicEducations(defineListByLine(getPropValueByTag(TagTypes.BS_EDUCATIONS)))
                .setAdditionalEdications(defineListByLine(getPropValueByTag(TagTypes.AD_EDUCATIONS)))
                .setOtherInfo(getPropValueByTag(TagTypes.OTHER_INFO))
                .setSkills(getSkillsByLine(properties.getProperty(TagTypes.SKILLS.name())))
                .build();
    }

    /**
     * Builds skills map by string
     *
     * @param lineSkills info about skills. format: "skill1:countInMonths1,skill2:countInMonths2"
     * @return map of skills. example {skill1 -> countInMonths1 , skill2 -> countInMonths2}
     */
    private Map<String, Integer> getSkillsByLine(String lineSkills) {
        if (lineSkills == null) {
            return null;
        }

        Map<String, Integer> skills = new HashMap<>();
        String[] infoSkills = lineSkills.split(SEPARATOR_SKILLS);
        for (String skill : infoSkills) {
            Integer count;
            if (skill.contains(SEPARATOR_DURATION_SKILL)) {
                try {
                    count = Integer.parseInt(skill.substring(skill.indexOf(SEPARATOR_DURATION_SKILL) + 1, skill.length()));
                } catch (NumberFormatException e) {
                    count = -1;
                }
                skills.put(skill.substring(0, skill.indexOf(SEPARATOR_DURATION_SKILL)), count);
            }
        }
        return skills;

    }

    private String getPropValueByTag(TagTypes tag) {
        return properties.getProperty(tag.name());
    }

    private List<String> defineListByLine(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.asList(str.split(CONTEXT_SEPARATOR_REGEX));
    }
}
