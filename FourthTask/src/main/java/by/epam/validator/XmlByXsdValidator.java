package by.epam.fourthtask.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class XmlByXsdValidator {
    private static final Logger LOGGER = LogManager.getLogger();

    private XmlByXsdValidator() {
    }

    public static boolean validate(String xmlFilepath, String xsdFilePath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdFilePath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFilepath));
            return true;
        } catch (SAXException | IOException e) {
            LOGGER.log(Level.ERROR, e);
            return false;
        }
    }
}
