package by.epam.fourthtask.parser;

import by.epam.fourthtask.entity.Bank;
import by.epam.fourthtask.entity.DepositType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankDOMParser {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String TYPE_STRING = "type";
    private static final String TIME_CONSTRAINTS_STRING = "time-constraints";
    private static final String NAME_STRING = "name";
    private static final String DEPOSITOR_STRING = "depositor";
    private static final String ACCOUNT_ID_STRING = "account-id";
    private static final String AMOUNT_ON_DEPOSIT_STRING = "amount-on-deposit";
    private static final String PROFITABILITY_STRING = "profitability";

    private BankDOMParser() {
    }

    public static List<Bank> parse(String filePath) {
        List<Bank> result = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            LOGGER.log(Level.ERROR, "Wrong data file path.");
            throw new RuntimeException();
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            LOGGER.log(Level.FATAL, "Parse init error.", e);
            throw new RuntimeException(e);
        }

        document.getDocumentElement().normalize();

        NodeList elements = document.getElementsByTagName(StringEnum.BANK_STRING.getValue());

        for (int temp = 0; temp < elements.getLength(); temp++) {

            Node nNode = elements.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                result.add(getBank(element));
            }
        }

        return result;
    }

    private static Bank getBank(Element element) {
        Bank bank = new Bank();

        String country = element.getAttribute(StringEnum.COUNTRY_STRING.getValue());
        bank.setCountry(Objects.requireNonNullElse(country, StringEnum.EMPTY_STRING.getValue()));

        String strType = (getTagValue(TYPE_STRING, element));
        for (DepositType type : DepositType.values()) {
            if (type.getValue().equalsIgnoreCase(strType)) {
                bank.setType(type);
                break;
            }
        }

        String strDate = getTagValue(TIME_CONSTRAINTS_STRING, element);
        SimpleDateFormat format = new SimpleDateFormat(StringEnum.DATE_FORMAT.getValue());
        try {
            bank.setTimeConstraints(format.parse(strDate));
        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, "Wrong data format.", e);
        }

        bank.setName(getTagValue(NAME_STRING, element));
        bank.setDepositor(getTagValue(DEPOSITOR_STRING, element));
        bank.setAccountId(getTagValue(ACCOUNT_ID_STRING, element));
        bank.setAmount(Double.parseDouble(getTagValue(AMOUNT_ON_DEPOSIT_STRING, element)));
        bank.setProfitability(Float.parseFloat(getTagValue(PROFITABILITY_STRING, element)));

        return bank;
    }

    private static String getTagValue(String tag, Element element) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }
}
