package by.epam.htp12.parser;

import by.epam.htp12.composite.TextComposite;
import by.epam.htp12.composite.Symbol;
import by.epam.htp12.exception.LongOverflowException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser extends AbstractParser {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void parse(TextComposite symbolComposite, String symbol) {

        try {
            if (symbol.length() == 1) {
                symbolComposite.add(new Symbol(symbol.charAt(0)));
            } else {
                LOGGER.log(Level.ERROR, "Incorrect symbol: " + symbol + ". The \"symbol\" object hasn't been created.");
            }
        } catch (LongOverflowException e) {
            LOGGER.log(Level.ERROR, e + "\n The \"symbol\" object hasn't been created.");
        }
    }
}
