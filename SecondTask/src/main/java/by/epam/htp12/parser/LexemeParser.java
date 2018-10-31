package by.epam.htp12.parser;

import by.epam.htp12.composite.ComponentType;
import by.epam.htp12.composite.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser {

    private static final Logger LOGGER = LogManager.getLogger();

    private static AbstractParser wordParser = new WordParser();
    private static AbstractParser expressionParser = new ExpressionParser();
    private static AbstractParser symbolParser = new SymbolParser();

    private static final String EMPTY_STRING = "";
    private static final String OTHER_SPACE_SYMBOL = " "; // c знаком \\s не работает добавление символа в конце
    private static final String SPACE_SYMBOL = "\\s";
    private static final char HYPHEN_SYMBOL = '-';
    private static final String USUAL_SYMBOL_REG_EXP = "[-.,!?()]";
    private static final String EXPRESSION_SYMBOLS_REG_EXP = "[0-9]|~|\\)|\\(|<|>|&|\\||\\^|\\+|\\*";

    @Override
    public void parse(TextComposite sentenceComposite, String sentence) {

        LOGGER.log(Level.INFO, "Lexeme parser has been launched");

        List<String> lexemes = new ArrayList<>(Arrays.asList(sentence.split(SPACE_SYMBOL)));
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        TextComposite symbolComposite = new TextComposite(ComponentType.SYMBOL);

        lexemes.removeIf(c -> c == null || c.isEmpty());

        for (String lexeme : lexemes) {

            char[] symbols = lexeme.toCharArray();
            int index = 0;
            while (index < symbols.length) {

                if (Character.isAlphabetic(symbols[index])) {
                    String word = EMPTY_STRING;

                    while (index != symbols.length &&
                            (Character.isAlphabetic(symbols[index]) || symbols[index] == HYPHEN_SYMBOL)) {
                        word = word.concat(symbols[index] + EMPTY_STRING);
                        index++;
                    }

                    wordParser.parse(wordComposite, word);
                }

                Pattern pattern = Pattern.compile(EXPRESSION_SYMBOLS_REG_EXP);
                if (index < symbols.length - 1 &&
                        pattern.matcher(symbols[index] + EMPTY_STRING).matches() &&
                        pattern.matcher(symbols[index + 1] + EMPTY_STRING).matches()) {

                    String expression = EMPTY_STRING;
                    while (index != symbols.length && pattern.matcher(symbols[index] + EMPTY_STRING).matches()) {
                        expression = expression.concat(symbols[index] + EMPTY_STRING);
                        index++;
                    }

                    expressionParser.parse(symbolComposite, expression);
                }

                if (index == symbols.length)
                    break;

                if (Pattern.compile(USUAL_SYMBOL_REG_EXP).matcher(symbols[index] + EMPTY_STRING).matches()) {
                    symbolParser.parse(symbolComposite, symbols[index] + EMPTY_STRING);
                    index++;
                    continue;
                }

                index++;
            }

            symbolParser.parse(symbolComposite, OTHER_SPACE_SYMBOL);
        }

        lexemeComposite.add(symbolComposite);
        lexemeComposite.add(wordComposite);

        sentenceComposite.add(lexemeComposite);

        LOGGER.log(Level.INFO, "Lexeme pars has been completed");
    }
}
