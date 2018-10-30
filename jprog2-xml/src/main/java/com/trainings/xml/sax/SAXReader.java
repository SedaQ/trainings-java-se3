package com.trainings.xml.sax;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Pavel Seda
 */
public class SAXReader {

    private static final String FILE_PREFIX = "./jprog2-xml/src/main/resources/";

    private boolean insideElement;

    public static void main(String[] args) {
        File file = new File(FILE_PREFIX + "persons.xml");

        readXMLFile(file);
    }

    private static void readXMLFile(File file) {
        try {
            XMLReader xmlr = XMLReaderFactory.createXMLReader();
            xmlr.setContentHandler(new PersonHandler());
            xmlr.parse(new InputSource(new FileReader(file)));

            PersonSax personSax = new PersonSax();
            ContentHandler ch = xmlr.getContentHandler();
            if (ch instanceof PersonHandler) {
                PersonHandler ph = (PersonHandler) ch;
                personSax.setPersonId(ph.getPersonId());
                personSax.setFirstName(ph.getFirstName());
                personSax.setLastName(ph.getLastName());
            }
            System.out.println(personSax);
        } catch (IOException | SAXException ioe) {
            System.err.println("IOE: " + ioe);
        }
    }

}
