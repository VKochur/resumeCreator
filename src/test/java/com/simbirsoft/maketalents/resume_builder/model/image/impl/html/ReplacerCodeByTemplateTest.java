package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReplacerCodeByTemplateTest {


    //TODO test fails. needs see reading last line in template (ends '\n' or not)
    @Test
    public void testGetPreCode() throws IOException {
        ReplacerHtmlCodeCreator codeCreator = new ReplacerCodeByTemplate("template.html");
        String expected =
                "<html>\n" +
                        " <head>\n" +
                        "     This template for test\n" +
                        " </head>\n" +
                        " <body>\n" +
                        "    something\n" +
                        " </body>\n" +
                        "</html>";

        assertEquals(expected, codeCreator.getPreCode());
    }
}