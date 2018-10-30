package com.trainings.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Pavel Seda
 */
public class DomWriter {

    private static final String FILE_PREFIX = "./jprog2-xml/src/main/resources/";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        File file = new File(FILE_PREFIX + "persons-write-dom.xml");
        PersonDOM personDom = new PersonDOM();
        personDom.setPersonId(1L);
        personDom.setFirstName("Pavel");
        personDom.setLastName("Seda");

        // create XML document
        Document doc = createDocument(personDom);

        // write XML document
        writeDocument(doc, file);
    }

    private static Document createDocument(PersonDOM personDom) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.newDocument();

        Element content = doc.createElement("content");
        doc.appendChild(content);

        Element person = doc.createElement("person");
        content.appendChild(person);

        person.setAttribute("state", "CZE");

        Element personId = doc.createElement("person_id");
        personId.appendChild(doc.createTextNode(String.valueOf(personDom.getPersonId())));
        person.appendChild(personId);

        Element firstName = doc.createElement("first_name");
        firstName.appendChild(doc.createTextNode(personDom.getFirstName()));
        person.appendChild(firstName);

        Element lastName = doc.createElement("last_name");
        lastName.appendChild(doc.createTextNode(personDom.getLastName()));
        person.appendChild(lastName);

        return doc;
    }

    private static void writeDocument(Document doc, File file) throws TransformerException {
        DOMSource source = new DOMSource(doc);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

}
