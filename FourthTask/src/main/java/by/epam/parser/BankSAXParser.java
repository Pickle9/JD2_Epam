package by.epam.parser;

import by.epam.entity.Bank;
import by.epam.entity.DepositType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import static by.epam.parser.ParserStringEnum.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BankSAXParser {
    private static final Logger LOGGER = LogManager.getLogger();

    private BankSAXParser() {
    }

    public static List<Bank> parse(String filePath) {

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            LOGGER.log(Level.ERROR, "Wrong data file path.");
            // кидаю runtime, т.к. xml уже скачан и проверен нан валидность,
            // поэтому ошибки могут быть связаны только с работой парсера
            throw new RuntimeException();
        }

        List<Bank> result = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.log(Level.ERROR, "BankSAXParser init error. ", e);
            throw new RuntimeException();
        }

        DefaultHandler handler = new DefaultHandler() {
            Bank bank = null;
            BankElement currentElement = null;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equalsIgnoreCase(BankElement.BANK.name())) {
                    bank = new Bank();

                    String attribute = attributes.getValue(COUNTRY_STRING.getValue());
                    bank.setCountry(Objects.requireNonNullElse(attribute, EMPTY_STRING.getValue()));
                }
                if (!qName.equalsIgnoreCase(BankElement.BANKS.name())) {
                    currentElement = BankElement.valueOf(qName.toUpperCase().replaceAll(HYPHEN_SYMBOL.getValue(),
                            UNDERSCORE_SYMBOL.getValue()));
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                if (qName.equalsIgnoreCase(BankElement.BANK.name())) {
                    result.add(bank);
                    currentElement = null;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                if (currentElement == null) {
                    return;
                }

                String str = new String(ch, start, length).trim();

                if (str.isEmpty())
                    return;

                switch (currentElement) {
                    case NAME: {
                        bank.setName(str);
                        break;
                    }
                    case TYPE: {
                        for (DepositType type : DepositType.values()) {
                            if (type.getValue().equalsIgnoreCase(str)) {
                                bank.setType(type);
                                break;
                            }
                        }
                        break;
                    }
                    case DEPOSITOR: {
                        bank.setDepositor(str);
                        break;
                    }
                    case ACCOUNT_ID: {
                        bank.setAccountId(str);
                        break;
                    }
                    case AMOUNT_ON_DEPOSIT: {
                        bank.setAmount(Double.parseDouble(str));
                        break;
                    }
                    case PROFITABILITY: {
                        bank.setProfitability(Float.parseFloat(str));
                        break;
                    }
                    case TIME_CONSTRAINTS: {
                        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getValue());
                        try {
                            bank.setTimeConstraints(format.parse(str));
                        } catch (ParseException e) {
                            LOGGER.log(Level.ERROR, "Date parse error. ", e);
                        }
                        break;
                    }
                    default: {
                        LOGGER.log(Level.ERROR, "Unsupported element: ", currentElement);
                    }
                }
            }
        };

        try {
            Objects.requireNonNull(parser).parse(file, handler);
        } catch (SAXException | IOException e) {
            LOGGER.log(Level.FATAL, "Parsing error.", e);
            throw new RuntimeException();
        }

        return result;
    }
}
