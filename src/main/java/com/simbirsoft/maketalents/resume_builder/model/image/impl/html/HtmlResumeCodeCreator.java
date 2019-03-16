package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import com.simbirsoft.maketalents.resume_builder.entity.Resume;

/**
 * Creates code html for resume
 */
public abstract class HtmlResumeCodeCreator {

    private Resume resume;

    //package access for HtmlResumePrinter
    void setResume(Resume resume) {
        this.resume = resume;
    }

    //public access for opportunity using in overriding getHtmlCode() in other packages
    public Resume getResume() {
        return resume;
    }

    public abstract String getHtmlCode() throws Exception;
}
