package by.epam.test.parser;

import by.epam.htp12.composite.ComponentType;
import by.epam.htp12.composite.Symbol;
import by.epam.htp12.composite.TextComposite;
import by.epam.htp12.composite.Word;
import by.epam.htp12.exception.LongOverflowException;
import by.epam.htp12.parser.ParagraphParser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChainParserTest {

    @Test
    public void parseTest() throws Exception {

        String expected = "\tFirst second.\n\n";

        TextComposite symbol = new TextComposite(ComponentType.SYMBOL);
        TextComposite word = new TextComposite(ComponentType.WORD);
        TextComposite lexeme = new TextComposite(ComponentType.LEXEME);
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);

        lexeme.add(symbol);
        lexeme.add(word);
        sentence.add(lexeme);
        paragraph.add(sentence);

        try {
            word.add(new Word("First"));
            symbol.add(new Symbol(' '));
            word.add(new Word("Second"));
            symbol.add(new Symbol('.'));
        } catch (LongOverflowException e) {
            Assert.fail();
        }

        TextComposite actual = new TextComposite(ComponentType.PARAGRAPH);
        new ParagraphParser().parse(actual, expected);

        Assert.assertEquals(actual.getData().trim(), expected.trim());
    }
}
