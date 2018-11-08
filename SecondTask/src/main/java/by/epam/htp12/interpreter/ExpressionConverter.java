package by.epam.htp12.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

public class ExpressionConverter {

    private static final String SPACE_SYMBOL = " ";
    private static final char LEFT_SHIFT = '<';
    private static final char RIGHT_SHIFT = '>';
    private final Map<Character, Integer> operationMap = new TreeMap<>();

    public ExpressionConverter() {
        for (MathOperation operation : MathOperation.values()) {
            operationMap.put(operation.getOperation(), operation.getPriority());
        }
    }

    public String convertToPolishNotation(String expression) {

        Deque<Character> deque = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        int index = 0;
        while (index < expression.length()) {

            if (Character.isDigit(expression.charAt(index))) {
                int startIndex = index;
                int current = index;

                while (current < expression.length() && Character.isDigit(expression.charAt(current))) {
                    current++;
                    index++;
                }

                index--;
                result.append(expression, startIndex, current).append(SPACE_SYMBOL);
            } else {
                int current = index;
                char tmp;

                if (expression.charAt(current) == LEFT_SHIFT) {
                    tmp = MathOperation.LEFT_SHIFT.getOperation();
                    index++;
                } else if (expression.charAt(current) == RIGHT_SHIFT) {
                    tmp = MathOperation.RIGHT_SHIFT.getOperation();
                    index++;
                } else
                    tmp = expression.charAt(current);

                if (tmp == MathOperation.CLOSING_BRACKET.getOperation()) {
                    while (deque.getFirst() != MathOperation.OPENING_BRACKET.getOperation()) {
                        result.append(deque.pop()).append(SPACE_SYMBOL);
                    }
                    deque.pop();
                } else if (!deque.isEmpty() && (tmp != MathOperation.OPENING_BRACKET.getOperation()) &&
                        operationMap.get(tmp) <= operationMap.get(deque.peek())) {

                    do {
                        result.append(deque.pop()).append(SPACE_SYMBOL);
                    } while (!deque.isEmpty() && operationMap.get(tmp) <= operationMap.get(deque.peek()));

                    deque.push(tmp);
                } else {
                    deque.push(tmp);
                }
            }

            index++;
        }

        while (!deque.isEmpty()) {
            result.append(deque.pop()).append(SPACE_SYMBOL);
        }

        return result.toString().trim();
    }
}
