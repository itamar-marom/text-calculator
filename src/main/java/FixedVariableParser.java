import java.util.HashMap;

public class FixedVariableParser implements Parser {

    private static final String[] FIXED_OPERATORS = { "++", "--" };

    @Override
    public String parse(HashMap<String, Double> variables, String str) throws Exception {
        char[] arr = str.toCharArray();
        StringBuilder newEquation = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {

            // If it is a start of a variable
            if (Character.isAlphabetic(arr[i])) {
                StringBuilder variableName = new StringBuilder();
                int variableNameEndIndex = i;
                // Get the entire name
                while (variableNameEndIndex < arr.length) {
                    if (!Character.isAlphabetic(arr[variableNameEndIndex]) &&
                            !Character.isDigit(arr[variableNameEndIndex]))
                        break;
                    variableName.append(arr[variableNameEndIndex]);
                    arr[variableNameEndIndex] = ' ';
                    variableNameEndIndex++;
                }

                // Check if it exists
                if (!variables.containsKey(variableName.toString()))
                    throw new Exception("Variable does not exists: " + variableName);

                // Get pre-post fixes before
                String potentialPreFix = getPreFix(arr, i);
                String potentialPostFix = getPostFix(arr, variableNameEndIndex - 1);

                Double value;

//                if ((potentialPreFix != null) && (potentialPostFix != null))
//                    throw new Exception("Variable have preFix and postFix operators: " + variableName);

                if (potentialPreFix != null) {
                    value = enforcePreFix(variables, variableName.toString(), potentialPreFix);
                    newEquation = new StringBuilder(newEquation.substring(0, newEquation.length() - 2) +
                            value.toString());
                    arr[i - 1] = ' ';
                    arr[i - 2] = ' ';
                    i = variableNameEndIndex - 1;
                } else if (potentialPostFix != null) {
                    value = enforcePostFix(variables, variableName.toString(), potentialPostFix);
                    newEquation.append(value.toString());
                    arr[variableNameEndIndex] = ' ';
                    arr[variableNameEndIndex + 1] = ' ';
                    i = variableNameEndIndex + 1;
                } else {
                    value = variables.get(variableName.toString());

                    if (newEquation.length() > 0) {
                        char endChar = newEquation.charAt(newEquation.length() - 1);

                        if (!Operator.isOperator(endChar))
                            throw new Exception("Can't put two components without operator between them.");
                    }

                    newEquation.append(value.toString());
                    i = variableNameEndIndex - 1;
                }
            } else {
                newEquation.append(arr[i]);
            }
        }
        
        return newEquation.toString();
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
        StringBuilder preFix = new StringBuilder();

        for (int preIndex = (index - 2); (preIndex >= 0 && preIndex < index); preIndex++)
            preFix.append(arr[preIndex]);

        for (String fixedOperator : FIXED_OPERATORS) {
            if (preFix.toString().equals(fixedOperator)) {
                if ((index - 3) >= 0)
                    return (Operator.isOperator(arr[index - 3]) ||
                            (arr[index - 3] == '(')) ? preFix.toString() : null;

                return preFix.toString();
            }
        }

        return null;
    }

    private static String getPostFix(char[] arr, int index) {
        StringBuilder postFix = new StringBuilder();

        for (int postIndex = (index + 1); (postIndex < arr.length && postIndex <= (index + 2)); postIndex++)
            postFix.append(arr[postIndex]);

        for (String fixedOperator : FIXED_OPERATORS) {
            if (postFix.toString().equals(fixedOperator)) {
                if ((index + 3) < arr.length)
                    return ((Operator.isOperator(arr[index + 3])) ||
                            (arr[index + 3] == ')')) ? postFix.toString() : null;

                return postFix.toString();
            }
        }

        return null;
    }
}
