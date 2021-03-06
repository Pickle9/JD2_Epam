package by.epam.test.comparator;

import by.epam.htp12.comparator.SentencesByLexemeLengthComparator;
import by.epam.htp12.composite.*;
import by.epam.htp12.exception.LongOverflowException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class SentenceByLexemeLengthComparatorTest {

    TextComposite firstSentence;
    TextComposite secondSentence;
    TextComposite lexeme;
    TextComposite word;
    TextComposite symbol;

    @BeforeClass
    public void init() {
        firstSentence = new TextComposite(ComponentType.SENTENCE);
        secondSentence = new TextComposite(ComponentType.SENTENCE);
        lexeme = new TextComposite(ComponentType.LEXEME);
        word = new TextComposite(ComponentType.WORD);
        symbol = new TextComposite(ComponentType.SYMBOL);
    }

    @Test
    public void compareTest() {
        List<Component> expected;
        List<Component> actual;

        try {
            word.add(new Word("example"));
            symbol.add(new Symbol('.'));
        } catch (LongOverflowException e) {
            Assert.fail();
        }

        lexeme.add(symbol);
        lexeme.add(word);

        firstSentence.add(lexeme);

        secondSentence = new TextComposite(ComponentType.SENTENCE);
        lexeme = new TextComposite(ComponentType.LEXEME);
        word = new TextComposite(ComponentType.WORD);
        symbol = new TextComposite(ComponentType.SYMBOL);

        try {
            word.add(new Word("q"));
            symbol.add(new Symbol('.'));
            symbol.add(new Symbol('.'));

        } catch (LongOverflowException e) {
            Assert.fail();
        }

        lexeme.add(symbol);
        lexeme.add(word);

        secondSentence.add(lexeme);

        expected = (List.of(secondSentence, firstSentence));
        actual = new ArrayList<>(List.of(firstSentence, secondSentence));
        actual.sort(new SentencesByLexemeLengthComparator());

        Assert.assertEquals(actual, expected);
    }
}
