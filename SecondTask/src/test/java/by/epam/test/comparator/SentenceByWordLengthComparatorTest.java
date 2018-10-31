package by.epam.test.comparator;

import by.epam.htp12.comparator.SentencesByWordLengthComparator;
import by.epam.htp12.composite.*;
import by.epam.htp12.exception.LongOverflowException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SentenceByWordLengthComparatorTest {

    TextComposite firstSentence;
    TextComposite secondSentence;
    TextComposite lexeme;
    TextComposite word;

    @BeforeClass
    public void init() {
        firstSentence = new TextComposite(ComponentType.SENTENCE);
        secondSentence = new TextComposite(ComponentType.SENTENCE);
        lexeme = new TextComposite(ComponentType.LEXEME);
        word = new TextComposite(ComponentType.WORD);
    }

    @Test
    public void compareTest() {
        List<Component> expected;
        List<Component> actual;

        try {
            word.add(new Word("example"));
        } catch (LongOverflowException e) {
            Assert.fail();
        }

        lexeme.add(word);
        firstSentence.add(lexeme);

        secondSentence = new TextComposite(ComponentType.SENTENCE);
        lexeme = new TextComposite(ComponentType.LEXEME);
        word = new TextComposite(ComponentType.WORD);

        try {
            word.add(new Word("q"));
        } catch (LongOverflowException e) {
            Assert.fail();
        }

        lexeme.add(word);
        secondSentence.add(lexeme);

        expected = (List.of(secondSentence, firstSentence));
        actual = new ArrayList<>(List.of(firstSentence, secondSentence));
        actual.sort(new SentencesByWordLengthComparator());

        Assert.assertEquals(actual, expected);
    }
}
