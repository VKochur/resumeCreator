## Программа для получения html резюме
Программа формирует html страницу резюме.
В зависимости от режима запуска программы, html может формироваться как на основе одного файла properties,
так и на основе 2х файлов properties. Также программа может запущена как веб приложение.

Программа состоит из "resume.jar" создаваемом maven при команде
mvn package в "директория проекта"/target

#### Запуск программы в Windows из консоли
Предварительно следует проверить настройку путей к установленной Java 8:
выполнение в консоли
java -version
должно выдавать информацию о версии установленной java

Если это не выполняется, то в системную переменную Path должен быть добавлен путь %JAVA_HOME%\bin,
где JAVA_HOME системная переменная с указаным путем к jdk

Для запуска программы следует разместить resume.jar в каталоге, для которого есть права на запись.
При каждом запуске программы, в каталоге размещения resume.jar, пишется resume_builder.log
с информацией об используемых данных и сгенерированном html, а также проблемах в случае их возникновения.

Запуск программы из консоли:

    java -jar ПолныйПутьКДиректории\resume.jar MODE arg_1 arg_2 .. arg_i
    
где MODE - режим работы программы, arg_i - аргументы используемые программой. 
<br>
В зависимости от выбранного режима работы, список и смысл аргументов меняется

#####Режимы запуска программы

MODE должен принимать одно из значений:

     BASIC (запуск программы для формирования html файла на основе 1 файла properties. не используется spring-core)
     SPRING (запуск программы для формирования html файла на основе 1 файла properties. используется spring-core)
     MULTI_THREADS_SPRING (запуск программы для формирования html файла на основе 2 файлов properties. используется spring-core, многопоточность)
     WEB (запуск программы в как веб приложение. доступно на http://localhost:8080/)



######BASIC, SPRING
Для указанных режимов, значения и смысл передаваемых при запуске аргументов совпадают. 
</br>


    arg_1 - полный путь к файлу properties
    arg_2 - полный пуь к директории, где будет создан html
    arg_3 - имя создаваемого html

Указанные директории должны существовать и к ним должен быть соответствующий уровень доступа.
<br>
Названия файлов и пути к каталогам содержащим символ пробела, необходимо заключать в ""
<br>
Пример:

    java -jar c:\temp\тест\resume.jar BASIC "c:\temp\тест тест\1\ivanov.properties" d:\тест "сгенерированный Иванов"

Если значение arg_1 не указано, программа создает resume.html в каталоге размещения resume.jar. Данные для формирования берутся из файла resume.properties, который должен находится в каталоге размещения resume.jar 
<br>
Пример:

    java -jar c:\temp\тест\resume.jar SPRING


######MULTI_THREADS_SPRING
<br>
Программа читает данные из двух файлов в разных потоках, собирает данные и создает html файл. Путь к файлам задается в параметрах запуска.

    arg_1 - полный путь к файлу1 properties
    arg_2 - полный путь к файлу2 properties
    arg_3 - полный путь к создаваемому html
  
 Порядок указания файлов является значимым, так как данные в первом файле являются более приоритетными по сравнению с данными во втором файле. При наличии одного и того же ключа с различным значением в 1м и 2м файле, берется значение из файла 1. При отсутствии информации по какому либо ключу в 1м файле, информация дополняется значением ключа из файла 2, при ее наличии.
<br>
Названия файлов и пути к каталогам содержащим символ пробела, необходимо заключать в "".
<br>
Пример:

    java -jar "c:\temp\Новая папка\resume.jar" MULTI_THREADS_SPRING "c:\temp\Новая папка\2\данные1.properties" "c:\temp\Новая папка\1\добавочные данные.properties" "c:\temp\Новая папка\итог.html"

Если значение arg_1 не указано, в этом случае в каталоге размещения resume.jar должны быть размещены файлы person1.properties, person2.properties на основе которых программа в этом же каталоге создаст файл createdFromPerson1Person2.html.
<br>
Пример:

    java -jar c:\resume.jar MULTI_THREADS_SPRING

######WEB
Запускает веб приложение, доступное на http://localhost:8080/

    java -jar c:\resume.jar WEB

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
com.simbirsoft.maketalents.resume_builder.MainJar - точка входа в программу
<br>
package com.simbirsoft.maketalents.resume_builder.running_from_ide - содержит классы для удобства запуска программы из ide в различных режимах