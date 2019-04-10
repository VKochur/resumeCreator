package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import com.simbirsoft.maketalents.resume_builder.tests_util.Util;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class LauncherCreateHtmlFromPropertiesMultithreadingTest {

    @Test
    public void launchTest() throws URISyntaxException, IOException {
        Launcher launcher = new LauncherCreateHtmlFromPropertiesMultithreading();
        File workDir = new File(Util.definePathTestClasses() + File.separator + UUID.randomUUID() + launcher.getClass().getName());
        File html = null;
        String pathSource1 = Util.definePathTestClasses() + File.separator + "test4.properties";
        String pathSource2 = Util.definePathTestClasses() + File.separator + "test2.properties";
        String pathToHtml = workDir + File.separator + "summaryMultithread.properties";
        workDir.mkdirs();
        try {
            launcher.launch(new String[]{pathSource1, pathSource2, pathToHtml});
            html = new File(pathToHtml);
            //check exists
            assertEquals(true, html.exists());

            //check content
            String expected = "<!doctype html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <!-- Required meta tags -->\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                    "\n" +
                    "    <!-- Bootstrap CSS -->\n" +
                    "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\n" +
                    "          integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n" +
                    "\n" +
                    "    <title>Резюме testFIO4</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <h1 class=\"text-center\">Резюме</h1>\n" +
                    "    <h2 class=\"text-center\">на должность должность4</h2>\n" +
                    "    <br>\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col-8\">\n" +
                    "            <table class=\"table\">\n" +
                    "                <tr>\n" +
                    "                    <td class=\"text-right\">ФИО:</td>\n" +
                    "                    <td>testFIO4</td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td class=\"text-right\">Дата рождения:</td>\n" +
                    "                    <td>testDOB</td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td class=\"text-right\">Телефон:</td>\n" +
                    "                    <td>80011<br></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td class=\"text-right\">e-mail:</td>\n" +
                    "                    <td>test<br>test@gmail.com4<br></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td class=\"text-right\">Skype:</td>\n" +
                    "                    <td>login|login24</td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "        </div>\n" +
                    "        <div class=\"col-4\">\n" +
                    "            <img src=\"https://www04fddb2797c033b087c4247630b2db7.jpg4\" alt=\"avatar\" class=\"img-fluid\">\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "    <div class=\"col-12\">\n" +
                    "        <h2>Цель:</h2>\n" +
                    "        <p>Устройство Java junior.<br>Прохождение курсов IT.Place<br></p>\n" +
                    "    </div>\n" +
                    "    <div class=\"col-12\">\n" +
                    "        <h2>Опыт работы:</h2>\n" +
                    "        <p>организация1<br>организация2<br>организация34<br></p>\n" +
                    "    </div>\n" +
                    "    <div class=\"col-12\">\n" +
                    "        <h2>Используемые технологии:</h2>\n" +
                    "        <p>sql - 244мес.; java - 184мес.; c++ - 124мес.; IIdea - 64мес.; </p>\n" +
                    "    </div>\n" +
                    "    <div class=\"col-12\">\n" +
                    "        <h2>Образование:</h2>\n" +
                    "        <p>образование1<br>образование2<br></p>\n" +
                    "    </div>\n" +
                    "    <div class=\"col-12\">\n" +
                    "        <h2>Дополнительное образование и курсы:</h2>\n" +
                    "        <p>доп<br>доп2<br>доп34<br></p>\n" +
                    "    </div>\n" +
                    "    <div class=\"col-12\">\n" +
                    "        <h2>Дополнительная информация:</h2>\n" +
                    "        <p>дополнительная информация</p>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n";
            Util.checkContentFile(html, expected);

            //check launch by not exists files and paths
            launcher.launch(new String[]{pathSource1, pathSource2 + "notexists", pathToHtml});
            launcher.launch(new String[]{pathSource1, pathSource2, workDir.getAbsolutePath() + "notexists" + File.separator + "summary.html"});

            //check launch by empty args
            launcher.launch(new String[]{});
        } finally {
            try {
                //NPE possibly
                Files.deleteIfExists(html.toPath());
            } finally {
                Files.deleteIfExists(workDir.toPath());
            }
        }
    }
}

