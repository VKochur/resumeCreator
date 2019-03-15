package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ReplacerCodeByTemplateTest {

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
                        "</html>\n";

        assertEquals(expected, codeCreator.getPreCode());
    }

    @Test(expected = IOException.class)
    public void testGetPreCode2() throws IOException {
        new ReplacerCodeByTemplate("notexiststemplate.html");
    }
}