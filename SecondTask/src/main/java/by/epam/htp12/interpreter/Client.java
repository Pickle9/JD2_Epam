package by.epam.htp12.interpreter;

import java.util.List;

public class Client {

    private Context context = new Context();

    public int handle(List<MathExpression> expressions) {
        expressions.forEach(s -> s.interpret(context));
        return context.pop();
    }
}
