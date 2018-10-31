package by.epam.htp12.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotationParser {

    private static final String SPACE_SYMBOL = "\\s";

    public List<MathExpression> parse(String polishNotation) {

        List<MathExpression> expression = new ArrayList<>();
        List<String> symbols = Arrays.asList(polishNotation.split(SPACE_SYMBOL));

        symbols.forEach(symbol -> {
            if (Character.isDigit(symbol.charAt(0))) {
                expression.add(c -> c.push(Integer.parseInt(symbol)));
            } else {
                for (MathOperation operation : MathOperation.values()) {
                    if (operation.getOperation() == symbol.charAt(0)) {
                        switch (operation) {
                            case PLUS:
                                expression.add(c -> c.push(c.pop() + c.pop()));
                                break;
                            case MINUS:
                                expression.add(c -> c.push(-c.pop() + c.pop()));
                                break;
                            case DIVIDE:
                                expression.add(c -> {
                                    int a = c.pop();
                                    int b = c.pop();
                                    c.push(b / a);
                                });
                                break;
                            case MULTIPLY:
                                expression.add(c -> c.push(c.pop() * c.pop()));
                                break;
                            case LEFT_SHIFT:
                                expression.add(c -> {
                                    int a = c.pop();
                                    int b = c.pop();
                                    c.push(b << a);
                                });
                                break;
                            case RIGHT_SHIFT:
                                expression.add(c -> {
                                    int a = c.pop();
                                    int b = c.pop();
                                    c.push(b >> a);
                                });
                                break;
                            case BITWISE_COMPLIMENT:
                                expression.add(c -> c.push(~c.pop()));
                                break;
                            case BITWISE_AND:
                                expression.add(c -> c.push(c.pop() & c.pop()));
                                break;
                            case BITWISE_OR:
                                expression.add(c -> c.push(c.pop() | c.pop()));
                                break;
                            case BITWISE_XOR:
                                expression.add(c -> c.push(c.pop() ^ c.pop()));
                                break;
                        }
                    }
                }
            }
        });

        return expression;
    }
}
