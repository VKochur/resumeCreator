package com.simbirsoft.maketalents.resume_builder.service.impl;

import com.simbirsoft.maketalents.resume_builder.tests_util.Util;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class SummaryServiceImplTest {

    @Test
    public void testBuildResume() throws Exception {
        SummaryServiceImpl summaryService = new SummaryServiceImpl(Util.definePathTestClasses(), "test2.properties", Util.definePathTestClasses(), "test2");
        summaryService.buildResume();
        checkCreatedHtml();
    }

    private void checkCreatedHtml() throws URISyntaxException, IOException {
        File html = new File(Util.definePathTestClasses() + "\\" + "test2.html");
        if (!html.exists()) {
            throw new IllegalStateException("html file not created");
        } else {
            try {
                String expected = "<!doctype html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <!-- Required meta tags -->\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                        "\n" +
                        "    <!-- Bootstrap CSS -->\n" +
                        "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n" +
                        "\n" +
                        "    <title>Резюме testFIO</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div class=\"container\">\n" +
                        "    <h1 class=\"text-center\">Резюме</h1>\n" +
                        "    <h2 class=\"text-center\">на должность должность</h2>\n" +
                        "    <br>\n" +
                        "    <div class=\"row\">\n" +
                        "        <div class=\"col-8\">\n" +
                        "            <table class=\"table\">\n" +
                        "                <tr><td class=\"text-right\">ФИО:</td><td>testFIO</td></tr>\n" +
                        "                <tr><td class=\"text-right\">Дата рождения:</td><td>testDOB</td></tr>\n" +
                        "                <tr><td class=\"text-right\">Телефон:</td><td>80011<br></td></tr>\n" +
                        "                <tr><td class=\"text-right\">e-mail:</td><td>test<br>test@gmail.com<br></td></tr>\n" +
                        "                <tr><td class=\"text-right\">Skype:</td><td>login|login2</td></tr>\n" +
                        "            </table>\n" +
                        "        </div>\n" +
                        "        <div class=\"col-4\">\n" +
                        "            <img src=\"https://www04fddb2797c033b087c4247630b2db7.jpg\" alt=\"avatar\" class=\"img-fluid\">\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "    <br>\n" +
                        "    <div class=\"col-12\">\n" +
                        "        <h2>Цель:</h2>\n" +
                        "        <p>Устройство Java junior.<br>Прохождение курсов IT.Place<br></p>\n" +
                        "    </div>\n" +
                        "    <div class=\"col-12\">\n" +
                        "        <h2>Опыт работы:</h2>\n" +
                        "        <p>организация1<br>организация2<br>организация3<br></p>\n" +
                        "    </div>\n" +
                        "    <div class=\"col-12\">\n" +
                        "        <h2>Образование:</h2>\n" +
                        "        <p>образование1<br>образование2<br></p>\n" +
                        "    </div>\n" +
                        "    <div class=\"col-12\">\n" +
                        "        <h2>Дополнительное образование и курсы:</h2>\n" +
                        "        <p>доп<br>доп2<br>доп3<br></p>\n" +
                        "    </div>\n" +
                        "    <div class=\"col-12\">\n" +
                        "        <h2>Дополнительная информация:</h2>\n" +
                        "        <p>дополнительная информация</p>\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "</body>\n" +
                        "</html>\n";
                Util.checkContentFile(html, expected);
            } finally {
                Files.deleteIfExists(html.toPath());
            }
        }
    }



}