import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FixedVariableParser implements Parser {

    private static final String[] FIXED_OPERATORS = { "++", "--" };

    @Override
    public String parse(HashMap<String, Double> variables, String str) throws Exception {
        char[] arr = str.toCharArray();
        String newEquation = "";

        for (int i = 0; i < arr.length; i++) {

            // If it is a start of a variable
            if (Character.isAlphabetic(arr[i])) {
                String variableName = "";
                int variableNameEndIndex = i;
                // Get the entire name
                while (variableNameEndIndex < arr.length) {
                    if (!Character.isAlphabetic(arr[variableNameEndIndex]) &&
                            !Character.isDigit(arr[variableNameEndIndex]))
                        break;
                    variableName += arr[variableNameEndIndex];
                    arr[variableNameEndIndex] = ' ';
                    variableNameEndIndex++;
                }

                // Check if it exists
                if (!variables.containsKey(variableName))
                    throw new Exception("Variable does not exists: " + variableName);

                // Get pre-post fixes before
                String potentialPreFix = getPreFix(arr, i);
                String potentialPostFix = getPostFix(arr, variableNameEndIndex - 1);

                Double value = null;

                if ((potentialPreFix != null) && (potentialPostFix != null))
                    throw new Exception("Variable have preFix and postFix operators: " + variableName);

                if (potentialPreFix != null) {
                    value = enforcePreFix(variables, variableName, potentialPreFix);
                    newEquation = newEquation.substring(0, newEquation.length() - 2) +
                            value.toString();
                    arr[i - 1] = ' ';
                    arr[i - 2] = ' ';
                    i = variableNameEndIndex - 1;
                } else if (potentialPostFix != null) {
                    value = enforcePostFix(variables, variableName, potentialPostFix);
                    newEquation += value.toString();
                    arr[variableNameEndIndex] = ' ';
                    arr[variableNameEndIndex + 1] = ' ';
                    i = variableNameEndIndex + 1;
                } else {
                    value = variables.get(variableName);
                    newEquation += value.toString();
                    i = variableNameEndIndex - 1;
                }
            } else {
                newEquation += arr[i];
            }
        }

        return newEquation;
    }

    private Double enforcePreFix(HashMap<String, Double> variables, String variableName, String preFix) {
        Double variable = variables.get(variableName);
        switch (preFix) {
            case "++":
                variables.put(variableName, ++variable);
                break;
            case "--":
                variables.put(variableName, --variable);
                break;
        }

        return variable;
    }

    private Double enforcePostFix(HashMap<String, Double> variables, String variableName, String preFix) {
        Double variable = variables.get(variableName);
        switch (preFix) {
            case "++":
                variables.put(variableName, variable + 1);
                break;
            case "--":
                variables.put(variableName, variable - 1);
                break;
        }
        return variable;
    }

    private static String getPreFix(char[] arr, int index) {
        String preFix = "";

        for (int preIndex = (index - 2); (preIndex >= 0 && preIndex < index); preIndex++)
            preFix += arr[preIndex];

        for (int i = 0; i < FIXED_OPERATORS.length; i++) {
            if (preFix.equals(FIXED_OPERATORS[i])) {
                if ((index - 3) >= 0)
                    return (Operator.isOperator(arr[index - 3]) ||
                            (arr[index - 3] == '(')) ? preFix : null;

                return preFix;
            }
        }

        return null;
    }

    private static String getPostFix(char[] arr, int index) {
        String postFix = "";

        for (int postIndex = (index + 1); (postIndex < arr.length && postIndex <= (index + 2)); postIndex++)
            postFix += arr[postIndex];

        for (int i = 0; i < FIXED_OPERATORS.length; i++) {
            if (postFix.equals(FIXED_OPERATORS[i])) {
                if ((index + 3) < arr.length)
                    return ((Operator.isOperator(arr[index + 3])) ||
                            (arr[index + 3] == ')')) ? postFix : null;

                return postFix;
            }
        }

        return null;
    }
}
