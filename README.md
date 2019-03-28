## Программа для получения html резюме  на основе properties файла

Программа состоит из "resume.jar" и папки "resume.lib", создаваемых maven при команде
mvn package в "директория проекта"/target

#### Запуск программы в Windows из консоли

Предварительно следует проверить настройку путей к установленной Java 8:
выполнение в консоли
java -version
должно выдавать информацию о версии установленной java

Если это не выполняется, то в системную переменную Path должен быть добавлен путь %JAVA_HOME%\bin,
где JAVA_HOME системная переменная с указаным путем к jdk

Для запуска программы следует разместить resume.jar и resume.lib в одном каталоге, для которого есть права на запись.
При каждом запуске программы, в каталоге размещения resume.jar, пишется resume_builder.log
с информацией об используемом файле .properties и сгенерированном html, а также проблемах в случае их возникновения.

Запуск программы из консоли:
1.  "java -jar ПолныйПутьКДиректории\resume.jar"
В этом случае в каталоге размещения resume.jar должен быть размещен файл resume.properties,
на основе которого программа в этом же каталоге создаст файл resume.html

2. Запуск программы с указанием какой именно файл .properties использовать и где именно создать html
"java -jar ПолныйПутьКДиректории\resume.jar ПолныйПутьКФайлу.properties ПолныйПутьКДиректорииHtml ИмяHtml"
Указанные директории должны существовать и к ним должен быть соответствующий уровень доступа.

Названия файлов и пути к каталогам содержащим символ пробела, необходимо заключать в ""
Пример:
java -jar c:\temp\тест\resume.jar "c:\temp\тест тест\1\ivanov.properties" d:\тест "сгенерированный Иванов"

#### Требования к файлу .properies
Файл должен иметь кодировку utf-8 без BOM.
 
Информация о содержании резюме хранится в виде строк КЛЮЧ=ЗНАЧЕНИЕ
где КЛЮЧ может быть:

    FIO (Фамилия Имя Отчество),
    DOB (Дата рождения),
    EMAILS,
    PHONES,
    SKYPE,
    URL_AVATAR,
    TARGETS (Цели обращения),
    EXPERIENCES (Имеющийся опыт),
    BS_EDUCATIONS (Базовой образование),
    AD_EDUCATIONS (Дополнительное образование),
    OTHER_INFO (Дополнительная информация),
    CAREER_TARGET (Рассматриваемые должности),
    SKILLS (информация о технологиях и длительности их использования в месяцах)

В случае если для одного ключа (EMAILS, PHONES, TARGETS, EXPERIENCES, BS_EDUCATIONS, AD_EDUCATIONS) предполагается несколько вариантов значений, следует разделять различные варианты символом '|'. Значение для ключа SKILLS записывается в виде "skill1:countMonths1,skill2:countMonths2,..". Если файл не содержит какой-либо ключ, соответствующее значение считается пустой строкой. Порядок следования ключей в файле не важен.

Пример:

    CAREER_TARGET=Java разработчик (junior), стажер
    FIO=Фамилия Имя Отчество
    DOB=01.02.1983
    EMAILS=name@rambler.ru|name@gmail.com
    EXPERIENCES=Организация1. 2008г -н.в. Должность. Решаемые задачи.|Организация2. 2005-2008г. Должность. Решаемые задачи.
    PHONES=8909????????
    SKYPE=login
    URL_AVATAR=https://www.tourdom.ru/upload/iblock/404/404fddb2797c033b087c4247630b2db7.jpg
    TARGETS=Устройство на работу.|Стажировка.
    BS_EDUCATIONS=ВУЗ, год. Специальность
    AD_EDUCATIONS=IT курсы1|IT курсы2
    OTHER_INFO=Личные качества. Пример кода.
    SKILLS=Java:24,Spring:6,sql:36,IIdea:6,c++:12
    
#### src
com.simbirsoft.maketalents.resume_builder.Main - точка входа в программу в создаваемом jar файле
com.simbirsoft.maketalents.resume_builder.MainForIde - используется для запуска программы из IDE,
формируя на основе src/main/resources/person.properties файл src/main/webapp/summary.html

указанные далее приложения используют SpringBoot
com.simbirsoft.maketalents.resume_builder.MainForIde.MainSpringBoot - может использоваться как точка входа в jar, после настроек pom
com.simbirsoft.maketalents.resume_builder.MainForIde.MainSpringBootForIde - используется для запуска программы из IDE,
формируя на основе src/main/resources/springboot/spring_boot_person.properties файл src/main/webapp/springboot/spring_boot_summary.html
com.simbirsoft.maketalents.resume_builder.MainForIde.MainSpringBootMultiThreading - используется для запуска программы из IDE,
выводя html код по резюме полученному на основе 2 файлов src/main/resources/concurrently/person1 и person2.properties