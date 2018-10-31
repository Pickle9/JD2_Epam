package by.epam.htp12.interpreter;

import java.util.ArrayDeque;

public class Context {

    private ArrayDeque<Integer> contextValue = new ArrayDeque<>();

    Integer pop() {
        return contextValue.pop();
    }

    void push(Integer number) {
        contextValue.push(number);
    }
}
