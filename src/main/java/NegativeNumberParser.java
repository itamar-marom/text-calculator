import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

import java.util.HashMap;

public class NegativeNumberParser implements Parser {
    @Override
    public String parse(HashMap<String, Double> variables, String str) throws Exception {

        char[] arr = str.toCharArray();
        String newEquation = "";

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
                if ((i > 0) && ((Character.isDigit(arr[i - 1])) || (arr[i - 1] == ')'))) {
                    newEquation += arr[i];
                } else if (((i + 1) < arr.length) && (arr[i + 1] == '(')) {
                    if (i == 0)
                        newEquation += "(0-1)*";
                    else
                        newEquation += arr[i] + "1*";
                } else {
                    String number = "";
                    int numberEndIndex = i + 1;

                    // Get the entire number
                    while (numberEndIndex < arr.length) {
                        if ((!Character.isDigit(arr[numberEndIndex])) && ((arr[numberEndIndex]) != '.'))
                            break;
                        number += arr[numberEndIndex];
                        numberEndIndex++;
                    }

                    if (number.isEmpty())
                        throw new Exception("Expression cannot end with an operator.");

                    newEquation += "(0-" + number + ")";
                    i = numberEndIndex - 1;
                }
            } else {
                newEquation += arr[i];
            }
        }

        return newEquation;
    }
}
