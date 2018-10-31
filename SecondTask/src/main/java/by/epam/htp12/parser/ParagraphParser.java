package by.epam.htp12.parser;

import by.epam.htp12.composite.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser extends AbstractParser {

    private static final Logger LOGGER = LogManager.getLogger();

    private static AbstractParser nextParser = new SentenceParser();
    private static final String FOUR_OR_MORE_SPACE_SYMBOLS_REG_EXP = "\\s{4,}";
    private static final String TABULATION_SYMBOL_REG_EXP = "\t";

    @Override
    public void parse(TextComposite paragraphComposite, String list) {

        LOGGER.log(Level.INFO, "Paragraph parser has been launched");

        String[] paragraphs = list.replaceAll(FOUR_OR_MORE_SPACE_SYMBOLS_REG_EXP, TABULATION_SYMBOL_REG_EXP)
                .split(TABULATION_SYMBOL_REG_EXP);

        for (String paragraph : paragraphs) {
            nextParser.parse(paragraphComposite, TABULATION_SYMBOL_REG_EXP + paragraph);
        }

        LOGGER.log(Level.INFO, "Paragraph pars has been completed");
    }
}
