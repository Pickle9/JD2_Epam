package by.epam.htp12.parser;

import by.epam.htp12.composite.TextComposite;
import by.epam.htp12.composite.Word;
import by.epam.htp12.exception.LongOverflowException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser extends AbstractParser {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void parse(TextComposite wordComposite, String word) {
        try {
            wordComposite.add(new Word(word));
        } catch (LongOverflowException e) {
            LOGGER.log(Level.ERROR, e + "\n The \"word\" object hasn't been created.");
        }
    }
}
