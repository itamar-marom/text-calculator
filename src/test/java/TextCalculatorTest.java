import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Objects;

public class TextCalculatorTest extends TestCase {

    TextCalculator textCalculator;

    private void testSolve(String equationString, Double expectedOutput, boolean isValid, HashMap<String, Double> variables) {
        if (variables == null)
            textCalculator = new TextCalculator();
        else
            textCalculator = new TextCalculator(variables);

        boolean answer = false;
        try {
            Equation equation = new Equation(equationString);
            Double result = textCalculator.solve(equation);
            answer = true;
            assertEquals("", expectedOutput, result);
            assertEquals("", expectedOutput, Objects.requireNonNull(variables).get(equation.getVariable()));
        } catch (Exception e) {}
        assertEquals("", isValid, answer);
    }

    public void testSolveNumber() {
        testSolve("i=1",1.0, true, null);
    }

    public void testSolveBasicAdditive() {
        testSolve("i=1+2",3.0, true, null);
    }

    public void testSolveBasicMultiplicative() {
        testSolve("i=3*2.5",7.5, true, null);
    }

    public void testSolveBasicNegative() {
        testSolve("i=-1",-1.0, true, null);
    }

    public void testSolveBasicVariable() {
        HashMap<String, Double> variables =  new HashMap<>();
        variables.put("a", 1.0);
        testSolve("i=a",1.0, true, variables);
    }

    public void testSolveInvalidVariable() {
        testSolve("i=a",0.0, false, null);
    }

    public void testSolveWithBrackets() {
        testSolve("i = 5 * (3 + 2)",25.0, true, null);
    }

    public void testSolveWithInsideBrackets() {
        testSolve("i = 5 * (3 * (2 - 4) + 2)",-20.0, true, null);
    }

    public void testSolvePlusEqual() {
        HashMap<String, Double> variables =  new HashMap<>();
        variables.put("a", 1.0);
        testSolve("a += 1",2.0, true, variables);
    }

    public void testSolveBasicPreFixedVariable() {
        HashMap<String, Double> variables =  new HashMap<>();
        variables.put("a", 1.0);
        testSolve("i=++a",2.0, true, variables);
        assertEquals("", 2.0, variables.get("a"));
    }

    public void testSolveBasicPostFixedVariable() {
        HashMap<String, Double> variables =  new HashMap<>();
        variables.put("a", 1.0);
        testSolve("i=a++",1.0, true, variables);
        assertEquals("", 2.0, variables.get("a"));
    }

    public void testSolveFixedAndAdditive() {
        HashMap<String, Double> variables =  new HashMap<>();
        variables.put("a", 1.0);
        testSolve("i=a++ - 2",-1.0, true, variables);
        assertEquals("", 2.0, variables.get("a"));
    }

    public void testSolveFixedAndMultiplicative() {
        HashMap<String, Double> variables =  new HashMap<>();
        variables.put("a", 1.0);
        testSolve("i=2 * --a",0.0, true, variables);
        assertEquals("", 0.0, variables.get("a"));
    }

    public void testSolveMultiplicativeAndAdditive() {
        testSolve("i = 4 - 2 * 7",-10.0, true, null);
    }

    public void testSolveComplicated() {
        HashMap<String, Double> variables =  new HashMap<>();
        variables.put("a", 1.0);
        variables.put("b", 4.0);
        testSolve("i = a++ * (2 / (--b + 1) - 10) * a--",-19.0, true, variables);
        assertEquals("", 1.0, variables.get("a"));
        assertEquals("", 3.0, variables.get("b"));
    }

    public void testSolveNegativeBrackets() {
        testSolve("i = -(4 - 2)",-2.0, true, null);
    }
}
