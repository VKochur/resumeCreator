package com.simbirsoft.maketalents.resume_builder.model.presenter.impl.html;

import com.simbirsoft.maketalents.resume_builder.dto.ResumeDto;
import com.simbirsoft.maketalents.resume_builder.dto.Util;
import com.simbirsoft.maketalents.resume_builder.entity.Resume;
import com.simbirsoft.maketalents.resume_builder.model.core.image.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.HtmlResumeCodeCreator;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.model.core.image.impl.html.ReplacerHtmlCodeByTemplate;
import com.simbirsoft.maketalents.resume_builder.model.presenter.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("createrHtml")
public class PresenterImpl implements Presenter {

    @Autowired
    private Util utilDto;

    @Override
    public void present(ResumeDto resumeDto, String additionalInfo) throws Exception {
        ResumePrinter resumePrinter = getResumePrinter();
        Resume resume = utilDto.getResumeByDTO(resumeDto);
        resumePrinter.print(resume, additionalInfo);
    }

    private ResumePrinter getResumePrinter() {
        HtmlResumePrinter htmlResumePrinter = new HtmlResumePrinter();
        htmlResumePrinter.setHtmlResumeCodeCreator(getCodeCreator());
        return htmlResumePrinter;
    }

    public HtmlResumeCodeCreator getCodeCreator() {
        return new ReplacerHtmlCodeByTemplate();
    }
}
