package by.epam.htp12.composite;

import java.util.List;

public interface Component {

    long getId();

    String getData();

    List<Component> getChild();

    ComponentType getType();
}
