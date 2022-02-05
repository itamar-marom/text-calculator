import java.util.*;

public class TextCalculator {

    private final static Scanner in = new Scanner(System.in);
    private final HashMap<String, Double> variables;
    private final static List<Parser> parsers = new ArrayList<>();
    private final static List<Resolver> resolvers = new ArrayList<>();

    public TextCalculator() {
        variables = new HashMap<>();
        parsers.add(new FixedVariableParser());
        parsers.add(new NegativeNumberParser());
        resolvers.add(new MultiplicativeResolver());
        resolvers.add(new AdditiveResolver());
    }
    public TextCalculator(HashMap<String, Double> variables) {
        this.variables = variables;
    }

    public void start() {
        String equationString = "";
        while (!equationString.equals("exit")) {
            System.out.print("> ");
            equationString = in.nextLine();
            if ((!equationString.isEmpty()) && (!equationString.equals("exit"))) {
                try {
                    Equation equation = new Equation(equationString);
                    solve(equation);
                } catch (Exception ex) {
                    System.out.println("Equation is not valid: " + equationString + "\n" + ex);
                }
            }
        }

        System.out.println(variables.toString());
    }

    public Double solve(Equation equation) throws Exception {
        String expression = equation.getExpression();
        expression = expression.replaceAll(" ", "");
        for (Parser parser : parsers) expression = parser.parse(variables, expression);

        Stack<String> stack = new Stack<>();
        char[] arr = expression.toCharArray();
        StringBuilder component = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if ((Character.isDigit(arr[i])) || (arr[i] == '.')) {
                component.append(arr[i]);

                if (i == (arr.length - 1))
                    stack.push(component.toString());
            } else {

                if (component.length() > 0) {
                    stack.push(component.toString());
                    component = new StringBuilder();
                }

                if (arr[i] != ')') {
                    stack.push(String.valueOf(arr[i]));
                } else {
                    ArrayList<String> subExpression = new ArrayList<>();
                    while (!stack.isEmpty()) {
                        String top = stack.pop();
                        if (top.equals("(")) {
                            break;
                        } else {
                            subExpression.add(0, top);
                        }
                    }

                    for (Resolver resolver : resolvers) resolver.resolve(subExpression);
                    stack.push(subExpression.get(0));
                }
            }
        }

        ArrayList<String> finalExpression = new ArrayList<>();
        while (!stack.isEmpty()) {
            String top = stack.pop();
            if (top.equals("(")) {
                break;
            } else {
                finalExpression.add(0, top);
            }
        }

        for (Resolver resolver : resolvers) resolver.resolve(finalExpression);

        Double expressionAnswer = Double.valueOf(finalExpression.get(0));
        Double answer = placement(equation.getVariable(), equation.getOperator(), expressionAnswer);
        variables.put(equation.getVariable(), answer);
        return answer;
    }

    private Double placement(String variable, String placement, Double calculation) throws Exception {

        if (placement.equals("="))
            return calculation;

        if (!variables.containsKey(variable))
            throw new Exception("Variable is not defined. Cannot use it in calculation: " + variable);

        Double var = variables.get(variable);

        switch (placement) {
            case "\\+=":
                var += calculation;
                return var;
            case "\\-=":
                var -= calculation;
                return var;
            case "\\*=":
                var *= calculation;
                return var;
            case "\\/=":
                var /= calculation;
                return var;
            case "\\%=":
                var %= calculation;
                return var;
        }

        return null;
    }
}
