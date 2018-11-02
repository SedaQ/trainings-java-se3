package com.trainings.xml.jaxb.main;

import com.trainings.xml.jaxb.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Pavel Seda
 */
public class MainJaxb {

    private static final String FILE_PREFIX = "./jprog2-xml/src/main/resources/jaxb/";

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        File file = new File(FILE_PREFIX + "persons-jaxb.xml");

        marshalling(file);

        Content content = unmarshalling(file);
        content.getPerson().forEach(System.out::println);
    }

    private static void marshalling(File file) throws JAXBException {
        Roles role = new Roles();
        role.setRole("ADMIN");

        Group group = new Group();
        group.setName("group1");
        group.getRoles().add(role);

        Address address = new Address();
        address.setZipCode(1123123);
        address.setHouseNumber(44);
        address.setCountry("CZE");
        address.setCity("Brno");
        address.setStreet("Masarykova");

        Person person = new Person();
        person.setAddress(address);
        person.setGroup(group);

        Content content = new Content();
        content.getPerson().add(person);

        JAXBContext context = JAXBContext.newInstance(Content.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(content, file);
    }

    private static Content unmarshalling(File file) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Content.class);
        return (Content) context.createUnmarshaller().unmarshal(new FileReader(file));
    }
}
