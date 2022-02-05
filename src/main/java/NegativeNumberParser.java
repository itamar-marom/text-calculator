import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

import java.util.HashMap;

public class NegativeNumberParser implements Parser {
    @Override
    public String parse(HashMap<String, Double> variables, String str) throws Exception {

        // -(4 - 2) --> (0 - (4 - 2))
        char[] arr = str.toCharArray();
        String newEquation = "";

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
                if ((i > 0) && (Character.isDigit(arr[i - 1]))) {
                    newEquation += arr[i];
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
