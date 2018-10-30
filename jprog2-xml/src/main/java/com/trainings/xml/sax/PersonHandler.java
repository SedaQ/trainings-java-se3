package com.trainings.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Pavel Seda
 */
public class PersonHandler extends DefaultHandler {

    private static final int BUFFER_SIZE = 10000;

    private long personId;
    private String firstName;
    private String lastName;

    private StringBuilder value = new StringBuilder(BUFFER_SIZE);

    private boolean insideElement;

    public long getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void startDocument() {
    }

    /**
     * @param uri       URI of namespace, if not present then it is empty
     * @param localName name of the element in regards with namespace, if uri is not present than localName is empty not the name of the tag!
     * @param qName     fully qualified name of the element
     * @param atts      the list of attributes
     */
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        if (qName.equals("person_id")) {
            clearBuffer();
        } else if (qName.equals("first_name")) {
            clearBuffer();
        } else if (qName.equals("last_name")) {
            clearBuffer();
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("person_id")) {
            personId = Long.parseLong(value.toString().trim());
            insideElement = false;
        } else if (qName.equals("first_name")) {
            firstName = value.toString().trim();
            insideElement = false;
        } else if (qName.equals("last_name")) {
            lastName = value.toString().trim();
            insideElement = false;
        }
    }

    public void characters(char[] ch, int start, int length) {
        if (insideElement == true) {
            value.append(ch, start, length);
        }
    }

    public void clearBuffer() {
        insideElement = true;
        value.setLength(0);
    }
}
