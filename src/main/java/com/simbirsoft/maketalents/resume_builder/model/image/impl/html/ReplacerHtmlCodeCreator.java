package com.simbirsoft.maketalents.resume_builder.model.image.impl.html;

import java.util.Map;
import java.util.Set;

/**
 * Creates html code by replace template
 *
 * template is a string returned from method getPreCode()
 * All substrings in template like ${keyValue} replaces to value : getSubstitution().getKey(keyValue) = value
 */
public abstract class ReplacerHtmlCodeCreator extends HtmlResumeCodeCreator {

    public abstract String getPreCode() throws Exception;

    public abstract Map<String,String> getSubstitution();

    @Override
    public String getHtmlCode() throws Exception {
        String postCode = new String(getPreCode());
        Set<Map.Entry<String, String>> entrySet = getSubstitution().entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String regEx =  new StringBuilder("\\$\\{").append(entry.getKey()).append("\\}").toString();
            postCode = postCode.replaceAll(regEx, entry.getValue());
        }
        return postCode;
    }
}
