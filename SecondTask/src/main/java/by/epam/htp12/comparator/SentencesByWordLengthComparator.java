package by.epam.htp12.comparator;

import by.epam.htp12.composite.Component;
import by.epam.htp12.composite.ComponentType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class SentencesByWordLengthComparator implements Comparator<Component> {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public int compare(Component o1, Component o2) {

        if (o1.getType() == null || o2.getType() == null) {
            LOGGER.log(Level.ERROR, "Comparing error. There is null \"type\" field in one or two of objects.");
            return 0;
        }

        if (o1.getType().equals(ComponentType.SENTENCE) && o2.getType().equals(ComponentType.SENTENCE)) {
            return calculateLength(o1) - calculateLength(o2);
        }

        LOGGER.log(Level.ERROR, "Unknown error. Compare method was completed incorrectly");
        return 0;
    }

    private static int calculateLength(Component parentComponent) {
        AtomicInteger length = new AtomicInteger();

        for (Component component : parentComponent.getChild()) {
            for (Component lexemeComponent : component.getChild()) {
                if (lexemeComponent.getType().equals(ComponentType.WORD)) {
                    lexemeComponent.getChild().forEach(w -> length.addAndGet(w.getData().length()));
                }
            }
        }

        return length.get();
    }
}
