package com.simbirsoft.maketalents.resume_builder.launcher.impl;

import com.simbirsoft.maketalents.resume_builder.launcher.Launcher;
import com.simbirsoft.maketalents.resume_builder.tests_util.Util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class GeneralForTestingBasicAndCoreSpringBootLauncher {

    public void runTestFor(Launcher launcher) throws URISyntaxException, IOException {
        File workDir = new File(Util.definePathTestClasses() + File.separator + UUID.randomUUID() + launcher.getClass().getName());
        File html = null;
        String pathSource = Util.definePathTestClasses() + File.separator + "test2.properties";
        workDir.mkdirs();
        try{
            launcher.launch(new String[] {pathSource, workDir.getAbsolutePath(), "generated"});
            html = new File(workDir.getAbsolutePath() + File.separator + "generated.html");
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
                    "                <tr>\n" +
                    "                    <td class=\"text-right\">ФИО:</td>\n" +
                    "                    <td>testFIO</td>\n" +
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
                    "                    <td>test<br>test@gmail.com<br></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td class=\"text-right\">Skype:</td>\n" +
                    "                    <td>login|login2</td>\n" +
                    "                </tr>\n" +
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
                    "        <h2>Используемые технологии:</h2>\n" +
                    "        <p>sql - 24мес.; java - 18мес.; c++ - 12мес.; IIdea - 6мес.; </p>\n" +
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

            //check launch by not exists files and paths
            launcher.launch(new String[] {pathSource + "notexist", workDir.getAbsolutePath(), "123"});
            launcher.launch(new String[] {pathSource, workDir.getAbsolutePath() + "notexists", "123"});

            //check launch by empty args
            launcher.launch(new String[] {});
        } finally {
            Files.deleteIfExists(html.toPath());
            Files.deleteIfExists(workDir.toPath());
        }
    }
}
