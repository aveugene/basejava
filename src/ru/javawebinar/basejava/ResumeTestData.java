package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.security.SecureRandom;
import java.util.Random;

public class ResumeTestData {

    public static void main(String[] args) {

        Resume grigoriyKislinResume = new Resume("Григорий Кислин");
        grigoriyKislinResume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        grigoriyKislinResume.setContact(ContactType.SKYPE, "grigoriy.kislin");
        grigoriyKislinResume.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        grigoriyKislinResume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        grigoriyKislinResume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        grigoriyKislinResume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        grigoriyKislinResume.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        grigoriyKislinResume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        grigoriyKislinResume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        grigoriyKislinResume.setSection(SectionType.ACHIEVEMENT, new ListSection(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        grigoriyKislinResume.setSection(SectionType.QUALIFICATIONS, new ListSection(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix, администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\""));

        Company.Period alcatelPeriod = new Company.Period(1997, 9, 2005, 1, "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        Company.Period siemensPeriod = new Company.Period(2005, 1, 2007, 2, "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Company.Period enkataPeriod = new Company.Period(2007, 3, 2008, 6, "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Company.Period yotaPeriod = new Company.Period(2008, 6, 2010, 12, "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Company.Period luxoftPeriod = new Company.Period(2010, 12, 2012, 4, "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Company.Period ritCenterPeriod = new Company.Period(2012, 4, 2014, 10, "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Company.Period wrikePeriod = new Company.Period(2014, 10, 2016, 1, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Company.Period javaOnlineProjectsPeriod = new Company.Period(2013, 10, "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");

        Company alcatel = new Company("http://www.alcatel.ru/", "Alcatel", alcatelPeriod);
        Company siemens = new Company("https://www.siemens.com/ru/ru/home.html", "Siemens AG", siemensPeriod);
        Company enkata = new Company("http://enkata.com/", "Enkata", enkataPeriod);
        Company yota = new Company("https://www.yota.ru/", "Yota", yotaPeriod);
        Company luxoft = new Company("http://www.luxoft.ru/", "Luxoft (Deutsche Bank)", luxoftPeriod);
        Company ritCenter = new Company("", "RIT Center", ritCenterPeriod);
        Company wrike = new Company("https://www.wrike.com/", "Wrike", wrikePeriod);
        Company javaOnlineProjects = new Company("http://javaops.ru/", "Java Online Projects", javaOnlineProjectsPeriod);

        grigoriyKislinResume.setSection(SectionType.EXPERIENCE, new CompaniesSection(alcatel, siemens, enkata, yota, luxoft, ritCenter, wrike, javaOnlineProjects));

        Company.Period mftiTeachingPeriod = new Company.Period(1984, 9, 1987, 6, "Закончил с отличием", null);
        Company.Period spbNIEITMiOTeachingPeriod1 = new Company.Period(1987, 9, 1993, 7, "Инженер (программист Fortran, C)", null);
        Company.Period spbNIEITMiOTeachingPeriod2 = new Company.Period(1993, 9, 1996, 7, "Аспирантура (программист С, С++)", null);
        Company.Period alcatelTeachingPeriod = new Company.Period(1997, 9, 1998, 3, "6 месяцев обучения цифровым телефонным сетям (Москва)", null);
        Company.Period siemensTeachingPeriod = new Company.Period(2005, 1, 2005, 4, "3 месяца обучения мобильным IN сетям (Берлин)", null);
        Company.Period luxoftTeachingPeriod = new Company.Period(2011, 3, 2011, 4, "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null);
        Company.Period courseraTeachingPeriod = new Company.Period(2013, 3, 2013, 5, "\"Functional Programming Principles in Scala\" by Martin Odersky", null);

        Company mftiTeaching = new Company("http://www.school.mipt.ru/", "Заочная физико-техническая школа при МФТИ", mftiTeachingPeriod);
        Company spbNIEITMiOTeaching = new Company("http://www.ifmo.ru/", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", spbNIEITMiOTeachingPeriod1, spbNIEITMiOTeachingPeriod2);
        Company alcatelTeaching = new Company("http://www.alcatel.ru/", "Alcatel", alcatelTeachingPeriod);
        Company siemensTeaching = new Company("https://www.siemens.com/ru/ru/home.html", "Siemens AG", siemensTeachingPeriod);
        Company luxoftTeaching = new Company("http://www.luxoft.ru/", "Luxoft (Deutsche Bank)", luxoftTeachingPeriod);
        Company courseraTeaching = new Company("https://www.coursera.org/course/progfun", "Coursera", courseraTeachingPeriod);

        grigoriyKislinResume.setSection(SectionType.EDUCATION, new CompaniesSection(mftiTeaching, spbNIEITMiOTeaching, alcatelTeaching, siemensTeaching, luxoftTeaching, courseraTeaching));

//        System.out.println(grigoriyKislinResume.getContact(ContactType.SKYPE));
//        grigoriyKislinResume.deleteContact(ContactType.HOMEPAGE);
//        grigoriyKislinResume.getSection(SectionType.PERSONAL);
//        grigoriyKislinResume.deleteSection(SectionType.QUALIFICATIONS);

        System.out.println(grigoriyKislinResume);

        System.out.println(createResume(null, "test testov"));
    }

    public static Resume createResume(String uuid, String fullName) {
        Random random = new Random();
        Resume resume;
        if (uuid == null) {
            resume = new Resume(fullName);
        } else {
            resume = new Resume(uuid, fullName);
        }

        String name = randomString(8, ALPHABET);
        resume.setContact(ContactType.PHONE, "+7(9" + random.nextInt(99) + ") " + random.nextInt(999) + "-" + random.nextInt(9999));
        resume.setContact(ContactType.SKYPE, name);
        resume.setContact(ContactType.EMAIL, name + "@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/" + name);
        resume.setContact(ContactType.GITHUB, "https://github.com/" + name);
        resume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/" + name);
        resume.setContact(ContactType.HOMEPAGE, "http://" + name + "/");

        resume.setSection(SectionType.OBJECTIVE, new TextSection(randomString(80, ALPHABET_RUS)));
        resume.setSection(SectionType.PERSONAL, new TextSection(""));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(
                randomString(random.nextInt(99), ALPHABET_RUS),
                randomString(random.nextInt(99), ALPHABET_RUS)
        ));
        resume.setSection(
                SectionType.QUALIFICATIONS, new ListSection(
                        randomString(random.nextInt(99), ALPHABET_RUS),
                        randomString(random.nextInt(99), ALPHABET_RUS)
                ));

        resume.setSection(SectionType.EXPERIENCE, new CompaniesSection(
                new Company(null, "Last company ltd.",
                        new Company.Period(1980 + random.nextInt(39), random.nextInt(11) + 1, randomString(random.nextInt(15), ALPHABET_RUS), randomString(random.nextInt(99), ALPHABET_RUS)),
                        new Company.Period(1980 + random.nextInt(39), random.nextInt(11) + 1, 1980 + random.nextInt(39), random.nextInt(11) + 1, randomString(random.nextInt(15), ALPHABET_RUS), randomString(random.nextInt(99), ALPHABET_RUS))
                ),
                new Company("http://www." + randomString(random.nextInt(9), ALPHABET) + ".ru/", randomString(random.nextInt(9), ALPHABET),
                        new Company.Period(1980 + random.nextInt(39), random.nextInt(11) + 1, 1980 + random.nextInt(39), random.nextInt(11) + 1, randomString(random.nextInt(15), ALPHABET_RUS), randomString(random.nextInt(99), ALPHABET_RUS))
                )
        ));
        resume.setSection(SectionType.EDUCATION, new CompaniesSection(
                new Company("http://www." + randomString(random.nextInt(9), ALPHABET) + ".ru/", randomString(random.nextInt(9), ALPHABET),
                        new Company.Period(1980 + random.nextInt(39), random.nextInt(11) + 1, 1980 + random.nextInt(39), random.nextInt(11) + 1, randomString(random.nextInt(15), ALPHABET_RUS), ""),
                        new Company.Period(1980 + random.nextInt(39), random.nextInt(11) + 1, 1980 + random.nextInt(39), random.nextInt(11) + 1, randomString(random.nextInt(15), ALPHABET_RUS), "")
                ),
                new Company("http://www." + randomString(random.nextInt(9), ALPHABET) + ".ru/", randomString(random.nextInt(9), ALPHABET),
                        new Company.Period(1980 + random.nextInt(39), random.nextInt(11) + 1, 1980 + random.nextInt(39), random.nextInt(11) + 1, randomString(random.nextInt(15), ALPHABET_RUS), ""),
                        new Company.Period(1980 + random.nextInt(39), random.nextInt(11) + 1, 1980 + random.nextInt(39), random.nextInt(11) + 1, randomString(random.nextInt(15), ALPHABET_RUS), ""),
                        new Company.Period(1980 + random.nextInt(39), random.nextInt(11) + 1, 1980 + random.nextInt(39), random.nextInt(11) + 1, randomString(random.nextInt(15), ALPHABET_RUS), "")
                )
        ));


        return resume;
    }

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
    private static final String ALPHABET_RUS = "йцукенгшщзхъэждлорпавыфячсмитьбюЦУКЕНГЗХЭЖДЛОРПАВЧСМИТБ.             ";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String randomString(int count, String alphabet) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(alphabet.charAt(RANDOM.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    public static Resume createGKResume() {
        Resume grigoriyKislinResume = new Resume("Григорий Кислин");
        grigoriyKislinResume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        grigoriyKislinResume.setContact(ContactType.SKYPE, "grigoriy.kislin");
        grigoriyKislinResume.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        grigoriyKislinResume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        grigoriyKislinResume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        grigoriyKislinResume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        grigoriyKislinResume.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        grigoriyKislinResume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        grigoriyKislinResume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        grigoriyKislinResume.setSection(SectionType.ACHIEVEMENT, new ListSection(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        grigoriyKislinResume.setSection(SectionType.QUALIFICATIONS, new ListSection(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix, администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\""));

        Company.Period alcatelPeriod = new Company.Period(1997, 9, 2005, 1, "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        Company.Period siemensPeriod = new Company.Period(2005, 1, 2007, 2, "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Company.Period enkataPeriod = new Company.Period(2007, 3, 2008, 6, "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Company.Period yotaPeriod = new Company.Period(2008, 6, 2010, 12, "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Company.Period luxoftPeriod = new Company.Period(2010, 12, 2012, 4, "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Company.Period ritCenterPeriod = new Company.Period(2012, 4, 2014, 10, "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Company.Period wrikePeriod = new Company.Period(2014, 10, 2016, 1, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Company.Period javaOnlineProjectsPeriod = new Company.Period(2013, 10, "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");

        Company alcatel = new Company("http://www.alcatel.ru/", "Alcatel", alcatelPeriod);
        Company siemens = new Company("https://www.siemens.com/ru/ru/home.html", "Siemens AG", siemensPeriod);
        Company enkata = new Company("http://enkata.com/", "Enkata", enkataPeriod);
        Company yota = new Company("https://www.yota.ru/", "Yota", yotaPeriod);
        Company luxoft = new Company("http://www.luxoft.ru/", "Luxoft (Deutsche Bank)", luxoftPeriod);
        Company ritCenter = new Company("", "RIT Center", ritCenterPeriod);
        Company wrike = new Company("https://www.wrike.com/", "Wrike", wrikePeriod);
        Company javaOnlineProjects = new Company("http://javaops.ru/", "Java Online Projects", javaOnlineProjectsPeriod);

        grigoriyKislinResume.setSection(SectionType.EXPERIENCE, new CompaniesSection(javaOnlineProjects, wrike, ritCenter, luxoft, yota, enkata, siemens, alcatel));

        Company.Period mftiTeachingPeriod = new Company.Period(1984, 9, 1987, 6, "Закончил с отличием", null);
        Company.Period spbNIEITMiOTeachingPeriod2 = new Company.Period(1993, 9, 1996, 7, "Аспирантура (программист С, С++)", null);
        Company.Period spbNIEITMiOTeachingPeriod1 = new Company.Period(1987, 9, 1993, 7, "Инженер (программист Fortran, C)", null);
        Company.Period alcatelTeachingPeriod = new Company.Period(1997, 9, 1998, 3, "6 месяцев обучения цифровым телефонным сетям (Москва)", null);
        Company.Period siemensTeachingPeriod = new Company.Period(2005, 1, 2005, 4, "3 месяца обучения мобильным IN сетям (Берлин)", null);
        Company.Period luxoftTeachingPeriod = new Company.Period(2011, 3, 2011, 4, "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null);
        Company.Period courseraTeachingPeriod = new Company.Period(2013, 3, 2013, 5, "\"Functional Programming Principles in Scala\" by Martin Odersky", null);

        Company mftiTeaching = new Company("http://www.school.mipt.ru/", "Заочная физико-техническая школа при МФТИ", mftiTeachingPeriod);
        Company spbNIEITMiOTeaching = new Company("http://www.ifmo.ru/", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", spbNIEITMiOTeachingPeriod1, spbNIEITMiOTeachingPeriod2);
        Company alcatelTeaching = new Company("http://www.alcatel.ru/", "Alcatel", alcatelTeachingPeriod);
        Company siemensTeaching = new Company("https://www.siemens.com/ru/ru/home.html", "Siemens AG", siemensTeachingPeriod);
        Company luxoftTeaching = new Company("http://www.luxoft.ru/", "Luxoft (Deutsche Bank)", luxoftTeachingPeriod);
        Company courseraTeaching = new Company("https://www.coursera.org/course/progfun", "Coursera", courseraTeachingPeriod);

        grigoriyKislinResume.setSection(SectionType.EDUCATION, new CompaniesSection(courseraTeaching, luxoftTeaching, siemensTeaching, alcatelTeaching, spbNIEITMiOTeaching, mftiTeaching));
        return grigoriyKislinResume;
    }
}
