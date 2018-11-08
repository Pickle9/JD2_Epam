package by.epam.htp12.composite;

import by.epam.htp12.exception.LongOverflowException;
import by.epam.htp12.util.IdGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Word implements Component {

    private long id;
    private String data;

    public Word(String data) throws LongOverflowException {
        this.data = data;
        id = IdGenerator.getAndIncrementId();
    }

    @Override
    public String getData() {
        return data;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return id == word.id &&
                Objects.equals(data, word.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
