package com.simbirsoft.maketalents.resume_builder.config;

import com.simbirsoft.maketalents.resume_builder.model.core.SummaryService;
import com.simbirsoft.maketalents.resume_builder.model.core.data.ManagerDataSource;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently.Collector;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently.PropertyReader;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently.Provider;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.properties_file_loader.ManagerDataSourceImpl;
import com.simbirsoft.maketalents.resume_builder.model.core.image.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.HtmlResumeCodeCreator;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.ReplacerHtmlCodeCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Configuration
public class AppConfig {

    @Bean("serviceGetsResumeByPartsAndPrintToStOut")
    public SummaryService summaryServiceCollectorAndStOut() {
        return new SummaryService() {
            @Override
            public ResumePrinter getPrinterData() {
                return resumePrinterToStOut();
            }

            @Override
            public ManagerDataSource getProviderData() {
                return collector();
            }
        };
    }

    @Bean("serviceGetsResumeByPartsAndCreatesHtml")
    public SummaryService summaryServiceCollectorAndHtml() {
        return new SummaryService() {
            @Override
            public ResumePrinter getPrinterData() {
                return resumePrinter();
            }

            @Override
            public ManagerDataSource getProviderData() {
                return collector();
            }
        };
    }

    @Bean("managerDataSourceFromProperties")
    public ManagerDataSource managerDataSource() {
        return new ManagerDataSourceImpl();
    }

    @Bean("resumePrinterByReplaceTemplate")
    public ResumePrinter resumePrinter() {
        HtmlResumePrinter htmlResumePrinter = new HtmlResumePrinter();
        htmlResumePrinter.setHtmlResumeCodeCreator(getCodeCreator());
        return htmlResumePrinter;
    }

    public Collector collector() {
        Collector collector = new Collector();
        List<Provider> providers = new ArrayList<>();
        providers.add(new PropertyReader());
        providers.add(new PropertyReader());
        collector.setProviders(providers);
        return collector;
    }

    public ResumePrinter resumePrinterToStOut() {
        return (resume, infoForPrinter) -> {
            HtmlResumeCodeCreator htmlResumeCodeCreator = getCodeCreator();
            htmlResumeCodeCreator.setResume(resume);
            System.out.println("---begin------from bean printerHtmlToStOut--");
            System.out.println(htmlResumeCodeCreator.getHtmlCode());
            System.out.println("---end--------from bean printerHtmlToStOut--");
        };
    }

    public HtmlResumeCodeCreator getCodeCreator() {
        return new ReplacerHtmlCodeCreator() {
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
        };
    }
}
