import java.util.HashMap;

public class NegativeNumberParser implements Parser {
    @Override
    public String parse(HashMap<String, Double> variables, String str) throws Exception {

        char[] arr = str.toCharArray();
        StringBuilder newEquation = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
                if ((i > 0) && ((Character.isDigit(arr[i - 1])) || (arr[i - 1] == ')'))) {
                    newEquation.append(arr[i]);
                } else if (((i + 1) < arr.length) && (arr[i + 1] == '(')) {
                    if (i == 0)
                        newEquation.append("(0-1)*");
                    else
                        newEquation.append(arr[i]).append("1*");
                } else {
                    StringBuilder number = new StringBuilder();
                    int numberEndIndex = i + 1;

                    // Get the entire number
                    while (numberEndIndex < arr.length) {
                        if ((!Character.isDigit(arr[numberEndIndex])) && ((arr[numberEndIndex]) != '.'))
                            break;
                        number.append(arr[numberEndIndex]);
                        numberEndIndex++;
                    }

                    if (number.length() == 0)
                        throw new Exception("Expression cannot end with an operator.");

                    newEquation.append("(0-").append(number).append(")");
                    i = numberEndIndex - 1;
                }
            } else {
                newEquation.append(arr[i]);
            }
        }

        return newEquation.toString();
    }
}
