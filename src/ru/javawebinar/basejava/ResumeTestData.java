package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.HashMap;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {

        Map<ContactType, String> contacts = new HashMap<>();
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigoriy.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");

        Map<SectionType, Section> sections = new HashMap<>();
        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        sections.put(SectionType.ACHIEVEMENT, new ListSection(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        sections.put(SectionType.QUALIFICATIONS, new ListSection(
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

        Period alcatelPeriod = new Period(9, 1997, 1, 2005, "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        Period siemensPeriod = new Period(1, 2005, 2, 2007, "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Period enkataPeriod = new Period(3, 2007, 6, 2008, "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Period yotaPeriod = new Period(6, 2008, 12, 2010, "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Period luxoftPeriod = new Period(12, 2010, 4, 2012, "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Period ritCenterPeriod = new Period(4, 2012, 10, 2014, "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Period wrikePeriod = new Period(10, 2014, 1, 2016, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Period javaOnlineProjectsPeriod = new Period(10, 2013, "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");

        Company alcatel = new Company("http://www.alcatel.ru/", "Alcatel", alcatelPeriod);
        Company siemens = new Company("https://www.siemens.com/ru/ru/home.html", "Siemens AG", siemensPeriod);
        Company enkata = new Company("http://enkata.com/", "Enkata", enkataPeriod);
        Company yota = new Company("https://www.yota.ru/", "Yota", yotaPeriod);
        Company luxoft = new Company("http://www.luxoft.ru/", "Luxoft (Deutsche Bank)", luxoftPeriod);
        Company ritCenter = new Company("", "RIT Center", ritCenterPeriod);
        Company wrike = new Company("https://www.wrike.com/", "Wrike", wrikePeriod);
        Company javaOnlineProjects = new Company("http://javaops.ru/", "Java Online Projects", javaOnlineProjectsPeriod);

        sections.put(SectionType.EXPERIENCE, new CompaniesSection(alcatel, siemens, enkata, yota, luxoft, ritCenter, wrike, javaOnlineProjects));

        Period mftiTeachingPeriod = new Period(9, 1984, 6, 1987, "Закончил с отличием", "");
        Period spbNIEITMiOTeachingPeriod1 = new Period(9, 1987, 7, 1993, "Инженер (программист Fortran, C)", "");
        Period spbNIEITMiOTeachingPeriod2 = new Period(9, 1993, 7, 1996, "Аспирантура (программист С, С++)", "");
        Period alcatelTeachingPeriod = new Period(9, 1997, 3, 1998, "6 месяцев обучения цифровым телефонным сетям (Москва)", "");
        Period siemensTeachingPeriod = new Period(1, 2005, 4, 2005, "3 месяца обучения мобильным IN сетям (Берлин)", "");
        Period luxoftTeachingPeriod = new Period(3, 2011, 4, 2011, "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", "");
        Period courseraTeachingPeriod = new Period(3, 2013, 5, 2013, "\"Functional Programming Principles in Scala\" by Martin Odersky", "");

        Company mftiTeaching = new Company("http://www.school.mipt.ru/", "Заочная физико-техническая школа при МФТИ", mftiTeachingPeriod);
        Company spbNIEITMiOTeaching = new Company("http://www.ifmo.ru/", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", spbNIEITMiOTeachingPeriod1, spbNIEITMiOTeachingPeriod2);
        Company alcatelTeaching = new Company("http://www.alcatel.ru/", "Alcatel", alcatelTeachingPeriod);
        Company siemensTeaching = new Company("https://www.siemens.com/ru/ru/home.html", "Siemens AG", siemensTeachingPeriod);
        Company luxoftTeaching = new Company("http://www.luxoft.ru/", "Luxoft (Deutsche Bank)", luxoftTeachingPeriod);
        Company courseraTeaching = new Company("https://www.coursera.org/course/progfun", "Coursera", courseraTeachingPeriod);

        sections.put(SectionType.EDUCATION, new CompaniesSection(mftiTeaching, spbNIEITMiOTeaching, alcatelTeaching, siemensTeaching, luxoftTeaching, courseraTeaching));

        Resume grigoriyKislinResume = new Resume("Григорий Кислин", contacts, sections);

        System.out.println(grigoriyKislinResume);
    }
}
