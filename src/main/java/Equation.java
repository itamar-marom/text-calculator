import java.util.List;

public class Equation {
    private final String variable;
    private final String operator;
    private final String expression;
    private static final String[] OPERATOR_REGEX = { "\\+=", "-=", "\\*=", "/=", "%=", "="};

    public String getVariable() {
        return variable;
    }

    public String getOperator() {
        return operator;
    }

    public String getExpression() {
        return expression;
    }

    public Equation(String equation) throws Exception {
        // Meta Characters
        equation = equation.replaceAll("[+]", "+");
        equation = equation.replaceAll("[*]", "*");

        String[] equationComponents = {};

        int index = -1;

        while (equationComponents.length != 2) {
            index++;
            if (index >= OPERATOR_REGEX.length)
                throw new Exception("Not a valid equation.");
            equationComponents = equation.split(OPERATOR_REGEX[index]);
            if (equationComponents.length > 2)
                throw new Exception("Not a valid equation.");
        }

        variable = equationComponents[0].trim();
        if (!Variable.isVariable(variable))
            throw new Exception("Variable is not valid: " + variable);

        operator = OPERATOR_REGEX[index];
        expression = equationComponents[1];
    }

    public String toString() {
        return "" + variable +
                " " + operator +
                " " + expression;
    }
}
