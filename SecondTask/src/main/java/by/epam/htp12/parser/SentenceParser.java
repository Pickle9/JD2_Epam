package by.epam.htp12.parser;

import by.epam.htp12.composite.ComponentType;
import by.epam.htp12.composite.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private static final Logger LOGGER = LogManager.getLogger();

    private static AbstractParser nextParser = new LexemeParser();
    private static final String DOT_SYMBOL = ".";
    private static final String TWO_DOTS_SYMBOL = "..";
    private static final String THREE_OR_MORE_DOTS_REG_EXP = "\\.{3,}";
    private static final String END_OF_SENTENCE_SYMBOLS_REG_EXP = "([^.!?]+[.!?])";

    @Override
    public void parse(TextComposite paragraphComposite, String paragraph) {

        LOGGER.log(Level.INFO, "Sentence parser has been launched");

        List<String> sentences = new ArrayList<>();
        // Ищу предложения, которые заканчиваются на "..."
        String[] paragraphsWithoutThreeDots = paragraph.split(THREE_OR_MORE_DOTS_REG_EXP);

        // Добавляю точку на место удалённого многоточия, чтобы работала регулярка
        for (int i = 0; i < paragraphsWithoutThreeDots.length - 1; i++) {
            paragraphsWithoutThreeDots[i] += DOT_SYMBOL;
        }

        Pattern pattern = Pattern.compile(END_OF_SENTENCE_SYMBOLS_REG_EXP);
        Matcher matcher;
        for (int i = 0; i < paragraphsWithoutThreeDots.length; i++) {
            matcher = pattern.matcher(paragraphsWithoutThreeDots[i]);
            while (matcher.find()) {
                sentences.add(matcher.group());
            }

            // Добавляю недостающие точки в предложения с многоточием
            if (i < paragraphsWithoutThreeDots.length - 1) {
                int lastIndex = sentences.size() - 1;
                sentences.set(lastIndex, sentences.get(lastIndex) + TWO_DOTS_SYMBOL);
            }
        }

        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);

        for (String sentence : sentences) {
            nextParser.parse(sentenceComposite, sentence);
        }

        paragraphComposite.add(sentenceComposite);

        LOGGER.log(Level.INFO, "Sentence pars has been completed");
    }
}
