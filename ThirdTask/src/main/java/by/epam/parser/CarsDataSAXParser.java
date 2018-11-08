package by.epam.parser;

import by.epam.entity.Car;
import by.epam.thread.CarThread;
import by.epam.entity.CarType;
import by.epam.util.IdGenerator;
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
import java.util.*;

public class CarsDataSAXParser {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DEFAULT_CARS_DATA_FILE_PATH = "data/car_data.xml";

    private static boolean isType = false;
    private static boolean isWeight = false;
    private static boolean isSquare = false;
    private static CarType type = null;
    private static int weight = 0;
    private static int square = 0;

    public static List<CarThread> parseCarsData(String carsFilePath) {

        File file = new File(carsFilePath);
        if (!file.exists() || !file.isFile()) {
            LOGGER.log(Level.ERROR, "Wrong data file path. Used default data file path.");
            carsFilePath = DEFAULT_CARS_DATA_FILE_PATH;
        }

        List<CarThread> cars = new ArrayList<>();

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
                    case "type": {
                        isType = true;
                        break;
                    }
                    case "weight": {
                        isWeight = true;
                        break;
                    }
                    case "square": {
                        isSquare = true;
                        break;
                    }
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                if (qName.equalsIgnoreCase("car")) {
                    cars.add(new CarThread(new Car(IdGenerator.getAndIncrementId(), type, weight, square)));
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                if (isType) {
                    if (new String(ch, start, length).trim().equalsIgnoreCase(CarType.PASSENGER_CAR.toString())) {
                        type = CarType.PASSENGER_CAR;
                    }

                    if (new String(ch, start, length).trim().equalsIgnoreCase(CarType.TRUCK.toString())) {
                        type = CarType.TRUCK;
                    }
                    isType = false;
                }

                if (isWeight) {
                    weight = Integer.parseInt(new String(ch, start, length).trim());
                    isWeight = false;
                }

                if (isSquare) {
                    square = Integer.parseInt(new String(ch, start, length).trim());
                    isSquare = false;
                }
            }
        };

        try {
            Objects.requireNonNull(parser).parse(carsFilePath, handler);
        } catch (SAXException | IOException e) {
            LOGGER.log(Level.FATAL, "Parsing error." + e);
        }

        return cars;
    }
}
