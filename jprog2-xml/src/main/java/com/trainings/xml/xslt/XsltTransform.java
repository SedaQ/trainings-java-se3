package com.trainings.xml.xslt;

import java.io.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;

/**
 * @author Pavel Seda
 */
public class XsltTransform {

    private static final String FILE_PREFIX = "./jprog2-xml/src/main/resources/xslt/";


    public static void main(String[] args) throws FileNotFoundException, TransformerException {
        File xsltDefinition = new File(FILE_PREFIX + "xslt-persons-transform.xsl");
        File xmlData = new File(FILE_PREFIX + "persons.xml");
        File output = new File(FILE_PREFIX + "persons.html");

        transform(xsltDefinition, xmlData, output);
    }

    private static void transform(File f1, File f2, File f3) throws FileNotFoundException, TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(f1));
        transformer.transform(new StreamSource(f2), new StreamResult(new FileOutputStream(f3)));
    }

}
