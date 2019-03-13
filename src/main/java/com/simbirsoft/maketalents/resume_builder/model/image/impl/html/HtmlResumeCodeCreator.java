package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeDao;

/**
 * Creates code html for resume
 */
public abstract class HtmlResumeCodeCreator {

    private ResumeDao resumeDao;

    //package access for HtmlResumePrinter
    void setProvider(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    //public access for opportunity using in overriding getHtmlCode() in other packages
    public ResumeDao getProvider() {
        return resumeDao;
    }

    public abstract String getHtmlCode();
}
