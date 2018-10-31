package by.epam.htp12.parser;

import by.epam.htp12.composite.TextComposite;
import by.epam.htp12.interpreter.Client;
import by.epam.htp12.interpreter.MathExpression;
import by.epam.htp12.interpreter.NotationParser;
import by.epam.htp12.interpreter.ExpressionConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ExpressionParser extends AbstractParser {

    private static final Logger LOGGER = LogManager.getLogger();
    private static AbstractParser symbolParser = new SymbolParser();
    private static final String EMPTY_STRING = "";

    @Override
    public void parse(TextComposite symbolComposite, String expression) {

        LOGGER.log(Level.INFO, "Expression parser has been launched");

        String notation = new ExpressionConverter().convertToPolishNotation(expression);
        List<MathExpression> expressions = new NotationParser().parse(notation);
        char[] digit = (new Client().handle(expressions) + EMPTY_STRING).toCharArray();

        for (char symbol : digit) {
            symbolParser.parse(symbolComposite, symbol + EMPTY_STRING);
        }

        LOGGER.log(Level.INFO, "Expression pars has been completed");
    }
}
