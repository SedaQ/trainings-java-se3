package com.trainings.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pavel Seda
 */
public class DomReader {

    private static final String FILE_PREFIX = "./jprog2-xml/src/main/resources/";
    private static final String XML_VALIDATION_FILE = FILE_PREFIX + "persons.xsd";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        File file = new File(FILE_PREFIX + "persons.xml");
        readFile(file);
    }

    private static void readFile(File file) throws ParserConfigurationException, IOException, SAXException {
        //loopkup procedure to find the implementation class
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setIgnoringElementContentWhitespace(true);

        DocumentBuilder domParser = dbf.newDocumentBuilder();
        Document document = domParser.parse(file);
        //validatingXMLDocument(document);

        Document doc = domParser.parse(file);
        System.out.printf("Version = %s%n", doc.getXmlVersion());
        System.out.printf("Encoding = %s%n", doc.getXmlEncoding());
        System.out.printf("Standalone = %b%n%n", doc.getXmlStandalone());

        List<PersonDOM> persons = new ArrayList<>();

        if (doc.hasChildNodes()) {
            NodeList contentList = doc.getElementsByTagName("content");
            if (contentList.getLength() == 1) {
                if (contentList.item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Node content = contentList.item(0);
                    NodeList personsList = content.getChildNodes();
                    for (int i = 0; i < personsList.getLength(); i++) {
                        if (personsList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                            Node personNode = personsList.item(i);
                            PersonDOM person = getPerson(personNode);
                            persons.add(person);
                        }
                    }
                }
            }
        }

        persons.forEach(p -> {
            System.out.println(p);
        });
    }

    private static PersonDOM getPerson(Node personNode) {
        PersonDOM personDom = new PersonDOM();
        NodeList nodeList = personNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals("person_id")) {
                    personDom.setPersonId(Long.parseLong(node.getTextContent().trim()));
                } else if (node.getNodeName().equals("first_name")) {
                    personDom.setFirstName(node.getTextContent().trim());
                } else if (node.getNodeName().equals("last_name")) {
                    personDom.setLastName(node.getTextContent().trim());
                }
            }
        }
        return personDom;
    }

    public static void innerElement(Element e) {
        System.out.printf("Element: %s, %s, %s, %s%n", e.getNodeName(),
                e.getLocalName(), e.getPrefix(),
                e.getNamespaceURI());
        NodeList nl = e.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element)
                innerElement((Element) node);
        }
    }

    private static boolean validatingXMLDocument(Document document) throws SAXException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(new File(XML_VALIDATION_FILE));
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        //Validate the DOM tree
        try {
            validator.validate(new DOMSource(document));
        } catch (SAXException | IOException saxe) {
            saxe.printStackTrace();
            System.out.println("Schema is invalid");
            return false;
        }
        return true;
    }

}
