package com.simbirsoft.maketalents.resume_builder.service.image.impl.html;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ReplacerHtmlCodeCreatorTest {

    @Test
    public void testGetHtmlCode() throws Exception {
        ReplacerHtmlCodeCreator codeCreatorCreator = new ReplacerHtmlCodeCreator() {
            @Override
            public String getPreCode() {
                return "${var1} other ${var1} ${var2} ${var3}";
            }

            @Override
            public Map<String, String> getSubstitution() {
                Map<String, String> substitution = new HashMap<>();
                substitution.put("var1", "VALUE1");
                substitution.put("var3", "VALUE3");
                return substitution;
            }
        };


        assertEquals("VALUE1 other VALUE1 ${var2} VALUE3", codeCreatorCreator.getHtmlCode());

    }
}