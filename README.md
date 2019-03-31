## Программа для получения html резюме на основе 2х properties файлов

Программа читает данные из двух файлов в разных потоках, собирает данные и создает html файл.

Программа состоит из "resume.jar".
jar создается maven при команде mvn package в "директория проекта"/target

#### Запуск программы в Windows из консоли

Предварительно следует проверить настройку путей к установленной Java 8:
выполнение в консоли
java -version
должно выдавать информацию о версии установленной java

Если это не выполняется, то в системную переменную Path должен быть добавлен путь %JAVA_HOME%\bin,
где JAVA_HOME системная переменная с указаным путем к jdk

Для запуска программы следует разместить resume.jar в каталоге, для которого есть права на запись.
При каждом запуске программы, в каталоге размещения resume.jar, пишется resume_builder.log
с информацией об используемых файлах .properties и сгенерированном html, а также проблемах в случае их возникновения.

Запуск программы из консоли:
1.  "java -jar ПолныйПутьКДиректории\resume.jar"
В этом случае в каталоге размещения resume.jar должены быть размещены файлы person1.properties, person2.properties
на основе которых программа в этом же каталоге создаст файл createdFromPerson1Person2.html

2. Запуск программы с указанием какие именно файлы .properties использовать и где именно создать html
"java -jar ПолныйПутьКДиректории\resume.jar ПолныйПутьКФайлу1properties ПолныйПутьКФайлу2properties ПолныйПутьКCоздаваемомуHtml"
Указанные директории должны существовать и к ним должен быть соответствующий уровень доступа.

Информация указанная в файле 1 является более приоритетной по сравнению с информацией указанной в файле 2, т.е. при наличии
одного и того же ключа с различным значением в 1м и 2м файле, берется значение из файла 1.
При отсутствии информации по какому либо ключу в 1м файле, информация дополняется значением ключа из файла 2, при ее наличии.

Названия файлов и пути к каталогам содержащим символ пробела, необходимо заключать в ""
Пример:
C:\>java -jar "c:\temp\Новая папка\resume.jar" "c:\temp\Новая папка\2\данные1.properties" "c:\temp\Новая папка\1\добавочные данные.properties" "c:\temp\Новая папка\итог.html"

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

В случае если для одного ключа (EMAILS, PHONES, TARGETS, EXPERIENCES, BS_EDUCATIONS, AD_EDUCATIONS) предполагается несколько вариантов значений, следует разделять различные варианты символом '|'.
Значение для ключа SKILLS записывается в виде "skill1:countMonths1,skill2:countMonths2,..".
Если файл не содержит какой-либо ключ, соответствующее значение считается пустой строкой. Порядок следования ключей в файле не важен.

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
Возможные точки входа в программу:
не используют ApplicationContext spring-boot
com.simbirsoft.maketalents.resume_builder.Main - использовалась как точка входа в программу в создаваемом jar файле (требует изменения pom в части использования плагинов пример Commit d7b6bba8)
com.simbirsoft.maketalents.resume_builder.MainForIde - используется для запуска программы из IDE,
формируя на основе src/main/resources/person.properties файл src/main/webapp/summary.html

указанные далее приложения используют SpringBoot
приложения формируют html по указанному файлу properties
com.simbirsoft.maketalents.resume_builder.MainSpringBoot - может использоваться как точка входа в jar, после настроек pom (изменение mainClass)
com.simbirsoft.maketalents.resume_builder.MainSpringBootForIde - используется для запуска программы из IDE,
формируя на основе src/main/resources/springboot/spring_boot_person.properties файл src/main/webapp/springboot/spring_boot_summary.html

приложения формируют html по 2м properties файлам. читают в отдельных потоках 2 properties файла, на основе которых создают резюме
com.simbirsoft.maketalents.resume_builder.MainSpringBootMultiThreadingForIde - используется для запуска программы из IDE,
выводя в stdout html код по резюме полученному на основе 2 файлов src/main/resources/concurrently/person1 и person2.properties
com.simbirsoft.maketalents.resume_builder.MainSpringBootMultithreadingJar - используется как точка входа в jar