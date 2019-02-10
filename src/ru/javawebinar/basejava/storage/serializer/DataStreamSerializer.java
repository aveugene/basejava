package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void writeResume(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            writeSection(dataOutputStream, contacts.entrySet(), entry -> {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeSection(dataOutputStream, sections.entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                AbstractSection section = entry.getValue();
                dataOutputStream.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dataOutputStream.writeUTF(section.toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeSection(dataOutputStream, ((ListSection) section).getTexts(), dataOutputStream::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeSection(dataOutputStream, ((CompaniesSection) section).getCompanies(), company -> {
                            Link link = company.getWebsiteLink();
                            dataOutputStream.writeUTF(link.getUrl());
                            dataOutputStream.writeUTF(link.getName());
                            writeSection(dataOutputStream, company.getPeriods(), period -> {
                                writeDate(dataOutputStream, period.getStartDate());
                                writeDate(dataOutputStream, period.getEndDate());
                                dataOutputStream.writeUTF(period.getTitle());
                                dataOutputStream.writeUTF(period.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume readResume(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream((inputStream))) {
            String uuid = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readSection(dataInputStream, () -> {
                resume.addContact(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF());
            });
            readSection(dataInputStream, () -> {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dataInputStream.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(sectionType, new ListSection(readSectionList(dataInputStream, dataInputStream::readUTF)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.addSection(sectionType, new CompaniesSection(readSectionList(dataInputStream, () ->
                                new Company(new Link(dataInputStream.readUTF(), dataInputStream.readUTF()), readSectionList(dataInputStream, () ->
                                        new Company.Period(readDate(dataInputStream), readDate(dataInputStream), dataInputStream.readUTF(), dataInputStream.readUTF())
                                ))
                        )));
                        break;
                }
            });
            return resume;
        }
    }

    @FunctionalInterface
    private interface WriteInterface<T> {
        void write(T item) throws IOException;
    }

    @FunctionalInterface
    private interface ReadInterface {
        void read() throws IOException;
    }

    @FunctionalInterface
    private interface ReadListInterface<T> {
        T read() throws IOException;
    }

    private void writeDate(DataOutputStream dataOutputStream, LocalDate date) throws IOException {
        dataOutputStream.writeInt(date.getYear());
        dataOutputStream.writeInt(date.getMonth().getValue());
    }

    private LocalDate readDate(DataInputStream dataInputStream) throws IOException {
        return LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), 1);
    }

    private <T> void writeSection(DataOutputStream dataOutputStream, Collection<T> collection, WriteInterface<T> writeInterface) throws IOException {
        dataOutputStream.writeInt(collection.size());
        for (T item : collection) {
            writeInterface.write(item);
        }
    }

    private void readSection(DataInputStream dataInputStream, ReadInterface readInterface) throws IOException {
        int size = dataInputStream.readInt();
        for (int i = 0; i < size; i++) {
            readInterface.read();
        }
    }

    private <T> List<T> readSectionList(DataInputStream dataInputStream, ReadListInterface<T> readListInterface) throws IOException {
        int size = dataInputStream.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(readListInterface.read());
        }
        return list;
    }


}
