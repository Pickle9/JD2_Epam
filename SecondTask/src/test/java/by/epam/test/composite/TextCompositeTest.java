package by.epam.test.composite;

import by.epam.htp12.composite.ComponentType;
import by.epam.htp12.composite.TextComposite;
import by.epam.htp12.composite.Symbol;
import by.epam.htp12.composite.Word;
import by.epam.htp12.exception.LongOverflowException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextCompositeTest {

    @Test
    public void getDataTest() {
        String expected = "\tHello test.\n\n";

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
            word.add(new Word("Hello"));
            symbol.add(new Symbol(' '));
            word.add(new Word("test"));
            symbol.add(new Symbol('.'));
        } catch (LongOverflowException e) {
            Assert.fail();
        }

        Assert.assertEquals(paragraph.getData(), expected);
    }
}
