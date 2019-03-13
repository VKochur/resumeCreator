package com.simbirsoft.maketalents.resume_builder.image.impl;

import com.simbirsoft.maketalents.resume_builder.dao.ResumeProvider;

/**
 * Creates code html for resume
 */
public interface HtmlResumeCodeCreator {

    void setProvider(ResumeProvider provider);

    ResumeProvider getProvider();

    String getHtmlCode();
}
