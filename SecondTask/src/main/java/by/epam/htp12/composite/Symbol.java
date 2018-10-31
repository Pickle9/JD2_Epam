package by.epam.htp12.composite;

import by.epam.htp12.exception.LongOverflowException;
import by.epam.htp12.util.IdGenerator;

import java.util.Collections;
import java.util.List;

public class Symbol implements Component {

    private long id;
    private char data;

    public Symbol(char data) throws LongOverflowException {
        this.data = data;
        id = IdGenerator.getAndIncrementId();
    }

    @Override
    public String getData() {
        return data + "";
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public List<Component> getChild() {
        return Collections.emptyList();
    }

    @Override
    public ComponentType getType() {
        return null;
    }
}
