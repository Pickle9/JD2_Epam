package by.epam.htp12.interpreter;

public enum MathOperation {

    MINUS('-', 5),
    PLUS('+', 5),
    DIVIDE('/', 6),
    MULTIPLY('*', 6),
    LEFT_SHIFT('<', 4),
    RIGHT_SHIFT('>', 4),
    BITWISE_COMPLIMENT('~', 7),
    BITWISE_AND('&', 3),
    BITWISE_OR('|', 2),
    BITWISE_XOR('^', 1),
    OPENING_BRACKET('(', 0),
    CLOSING_BRACKET(')', 0);

    private char operation;
    private int priority;

    MathOperation(char operation, int priority) {
        this.operation = operation;
        this.priority = priority;
    }

    public char getOperation() {
        return operation;
    }

    public int getPriority() {
        return priority;
    }
}
