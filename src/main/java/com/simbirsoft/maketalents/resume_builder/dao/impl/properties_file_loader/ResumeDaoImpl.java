package com.simbirsoft.maketalents.resume_builder.dao.impl.properties_file_loader;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.ResumeBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Provides info about resume from property file.
 * <p>
 * Encoding file must be UTF-8 without BOM
 * <p>
 * Set of allowed tags is set of TagTypes
 * Separator between key and value is '='
 * In the case of various options for specific key is used '|' as separator
 * In case not found specific tag, the associated context is ""
 * <p>
 * Example properties file:
 * ' FIO=Name Second_Name
 * DOB=13.08.1983
 * EMAIL=name@rambler.ru|name@gmail.com
 * PHONE=???7???
 * SKYPE=login
 * TARGET=target1|target2|target3
 * CAREER_TARGET=career target
 */
public class ResumeDaoImpl implements ResumeDao {

    private static final String CONTEXT_SEPARATOR_REGEX = "\\|";
    private static final String DEFAULT_VALUE_CONTEXT = "";

    private Properties properties;

    /**
     * @param pathFile path to file properties
     * @throws IOException in case inaccessibility file properties
     */
    public ResumeDaoImpl(String pathFile) throws IOException {
        properties = new Properties();
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(pathFile), StandardCharsets.UTF_8.name())) {
            properties.load(inputStreamReader);
        }
    }

    @Override
    public Resume getResume() {
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
                .build();
    }

    private String getPropValueByTag(TagTypes tag) {
        return properties.getProperty(tag.name(), DEFAULT_VALUE_CONTEXT);
    }

    private List<String> defineListByLine(String str) {
        return Arrays.asList(str.split(CONTEXT_SEPARATOR_REGEX));
    }
}
