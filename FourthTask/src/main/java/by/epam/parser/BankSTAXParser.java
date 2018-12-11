package by.epam.fourthtask.parser;

import by.epam.fourthtask.entity.Bank;
import by.epam.fourthtask.entity.DepositType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BankSTAXParser {
    private static final Logger LOGGER = LogManager.getLogger();

    private BankSTAXParser() {
    }

    public static List<Bank> parse(String filePath) {
        List<Bank> result = new ArrayList<>();
        Bank bank = new Bank();

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));

            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    bank = getBank(bank, xmlEventReader, startElement);
                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals(StringEnum.BANK_STRING.getValue())) {
                        result.add(bank);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            LOGGER.log(Level.ERROR, e);
            throw new RuntimeException();
        }

        return result;
    }

    private static Bank getBank(Bank bank, XMLEventReader xmlEventReader, StartElement startElement) throws XMLStreamException {
        String element = startElement.getName().getLocalPart();
        XMLEvent xmlEvent;

        element = element.replaceAll(StringEnum.HYPHEN_SYMBOL.getValue(), StringEnum.UNDERSCORE_SYMBOL.getValue()).toUpperCase();
        switch (BankElement.valueOf(element)) {
            case BANK: {
                bank = new Bank();
                Attribute idAttr = startElement.getAttributeByName(new QName(StringEnum.COUNTRY_STRING.getValue()));
                if (idAttr != null) {
                    bank.setCountry(idAttr.getValue());
                } else {
                    bank.setCountry(StringEnum.EMPTY_STRING.getValue());
                }
                break;
            }
            case NAME: {
                xmlEvent = xmlEventReader.nextEvent();
                bank.setName(xmlEvent.asCharacters().getData());
                break;
            }
            case TYPE: {
                xmlEvent = xmlEventReader.nextEvent();
                String strType = xmlEvent.asCharacters().getData();
                for (DepositType type : DepositType.values()) {
                    if (type.getValue().equalsIgnoreCase(strType)) {
                        bank.setType(type);
                        break;
                    }
                }
                break;
            }
            case DEPOSITOR: {
                xmlEvent = xmlEventReader.nextEvent();
                bank.setDepositor(xmlEvent.asCharacters().getData());
                break;
            }
            case ACCOUNT_ID: {
                xmlEvent = xmlEventReader.nextEvent();
                bank.setAccountId(xmlEvent.asCharacters().getData());
                break;
            }
            case AMOUNT_ON_DEPOSIT: {
                xmlEvent = xmlEventReader.nextEvent();
                bank.setAmount(Double.parseDouble(xmlEvent.asCharacters().getData()));
                break;
            }
            case PROFITABILITY: {
                xmlEvent = xmlEventReader.nextEvent();
                bank.setProfitability(Float.parseFloat(xmlEvent.asCharacters().getData()));
                break;
            }
            case TIME_CONSTRAINTS: {
                xmlEvent = xmlEventReader.nextEvent();
                String strDate = xmlEvent.asCharacters().getData();
                SimpleDateFormat format = new SimpleDateFormat(StringEnum.DATE_FORMAT.getValue());
                try {
                    bank.setTimeConstraints(format.parse(strDate));
                } catch (ParseException e) {
                    LOGGER.log(Level.ERROR, "Date parse error. ", e);
                }
                break;
            }
            default: {
                LOGGER.log(Level.ERROR, "Unsupported element: ", element);
            }
        }

        return bank;
    }
}
