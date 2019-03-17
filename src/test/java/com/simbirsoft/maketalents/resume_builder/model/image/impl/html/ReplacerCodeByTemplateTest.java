package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ReplacerCodeByTemplateTest {

    @Test
    public void testGetPreCode() throws Exception {
        ReplacerHtmlCodeCreator codeCreator = new ReplacerCodeByTemplate("template.html");
        String expected ="<html>\n" +
                " <head>\n" +
                "     This template for test\n" +
                " </head>\n" +
                " <body>\n" +
                " FIO=${FIO}\n" +
                " <br>\n" +
                " DOB=${DOB}\n" +
                " <br>\n" +
                " EMAILS=${EMAILS}\n" +
                " <br>\n" +
                " PHONES=${PHONES}\n" +
                " <br>\n" +
                " SKYPE=${SKYPE}\n" +
                " <br>\n" +
                " URL_AVATAR=${URL}\n" +
                " <br>\n" +
                " TARGETS=${TARGETS}\n" +
                " <br>\n" +
                " EXPERIENCES=${EXPERIENCES}\n" +
                " <br>\n" +
                " BS_EDUCATIONS=${BSE}\n" +
                " <br>\n" +
                " AD_EDUCATIONS=${ADE}\n" +
                " <br>\n" +
                " OTHER_INFO=${INFO}\n" +
                " <br>\n" +
                " CAREER_TARGET=${CTARG}\n" +
                " </body>\n" +
                "</html>\n";

        assertEquals(expected, codeCreator.getPreCode());
    }

    @Test(expected = IOException.class)
    public void testGetPreCode2() throws IOException {
        new ReplacerCodeByTemplate("notexiststemplate.html").getPreCode();
    }
}