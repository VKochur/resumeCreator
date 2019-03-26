package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;
import com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently.Collector;
import com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently.PropertyReader;
import com.simbirsoft.maketalents.resume_builder.dao.impl.concurrently.Provider;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.image.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.service.SummaryService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainForConcurrently {

    public static void main(String[] args) {
        method();
    }

    private static void method() {
        SummaryService summaryService = new SummaryService(){
            @Override
            public ResumePrinter getPrinterData() {
                return resume -> System.out.println(resume);
            }

            @Override
            public ResumeDao getProviderData() {
                return collector() ;
            }
        };

        try {
            summaryService.buildResume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ResumeDao collector() {
        String pathToFiles = new File("").getAbsolutePath() + "\\src\\main\\resources\\concurrently\\";
        Collector collector = new Collector();
        List<Provider> providers = new ArrayList<>();
        providers.add(new PropertyReader(pathToFiles + "person1.properties"));
        providers.add(new PropertyReader(pathToFiles + "person2.properties"));
        collector.setProviders(providers);
        return collector;
    }
}
