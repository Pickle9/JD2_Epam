package by.epam.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FerryBoatDataSAXParser {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_FERRY_BOAT_DATA_FILE_PATH = "data/ferry_boat_data.xml";

    private static boolean isPlatformCarrying = false;
    private static boolean isPlatformSquare = false;
    private static int platformCarrying = 0;
    private static int platformSquare = 0;

    public static Map<String, Integer> parseFerryBoatData(String ferryBoatFilePath) {

        File file = new File(ferryBoatFilePath);
        if (!file.exists() || !file.isFile()) {
            LOGGER.log(Level.ERROR, "Wrong data file path. Used default data file path.");
            ferryBoatFilePath = DEFAULT_FERRY_BOAT_DATA_FILE_PATH;
        }

        Map<String, Integer> result = new HashMap<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.log(Level.ERROR, "SAXParser init error. " + e);
        }

        DefaultHandler handler = new DefaultHandler() {

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                switch (qName.toLowerCase()) {
                    case "platform_carrying": {
                        isPlatformCarrying = true;
                        break;
                    }
                    case "platform_square": {
                        isPlatformSquare = true;
                        break;
                    }
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                if (qName.equalsIgnoreCase("ferry_boat")) {
                    result.put("platformCarrying", platformCarrying);
                    result.put("platformSquare", platformSquare);
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                if (isPlatformCarrying) {
                    platformCarrying = Integer.parseInt(new String(ch, start, length).trim());
                    isPlatformCarrying = false;
                }

                if (isPlatformSquare) {
                    platformSquare = Integer.parseInt(new String(ch, start, length).trim());
                    isPlatformSquare = false;
                }
            }
        };

        try {
            Objects.requireNonNull(parser).parse(ferryBoatFilePath, handler);
        } catch (SAXException | IOException e) {
            LOGGER.log(Level.FATAL, "Parsing error." + e);
        }

        return result;
    }
}
