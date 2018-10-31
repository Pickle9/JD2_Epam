package by.epam.htp12.composite;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements Component {

    private ComponentType type;
    private List<Component> components = new ArrayList<>();

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public String getData() {

        List<String> list = new ArrayList<>();
        components.forEach(c -> list.add(c.getData()));

        final StringBuilder line = new StringBuilder();

        switch (type) {
            case LEXEME: {
                List<Component> leafs = new ArrayList<>();
                components.forEach(c -> leafs.addAll(c.getChild()));
                leafs.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
                leafs.forEach(c -> line.append(c.getData()));
                break;
            }
            case PARAGRAPH: {
                list.forEach(c -> line.append("\t").append(c).append("\n"));
                break;
            }
            case SENTENCE:
                list.forEach(c -> line.append(c).append("\n"));
                break;
            default: {
                list.forEach(line::append);
            }
        }

        return line.toString();
    }

    @Override
    public List<Component> getChild() {
        return components;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    public boolean add(Component component) {
        return components.add(component);
    }

    public boolean remove(Component component) {
        return components.remove(component);
    }

    public void update(int id, Component component) {
        components.set(id, component);
    }

    public Component get(int id) {
        return components.get(id);
    }
}
