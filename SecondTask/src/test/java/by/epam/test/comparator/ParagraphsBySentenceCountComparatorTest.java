package by.epam.test.comparator;

import by.epam.htp12.comparator.ParagraphsBySentenceCountComparator;
import by.epam.htp12.composite.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class ParagraphsBySentenceCountComparatorTest {

    TextComposite firstParagraph;
    TextComposite secondParagraph;
    TextComposite sentence;

    @BeforeClass
    public void init() {
        firstParagraph = new TextComposite(ComponentType.PARAGRAPH);
        secondParagraph = new TextComposite(ComponentType.PARAGRAPH);
        sentence = new TextComposite(ComponentType.SENTENCE);
    }

    @Test
    public void compareTest() {
        firstParagraph.add(sentence);
        firstParagraph.add(sentence);

        secondParagraph.add(sentence);

        List<Component> expected = Arrays.asList(secondParagraph, firstParagraph);
        List<Component> actual = Arrays.asList(firstParagraph, secondParagraph);

        actual.sort(new ParagraphsBySentenceCountComparator());

        Assert.assertEquals(actual, expected);
    }
}
