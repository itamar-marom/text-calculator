import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class MultiplicativeResolverTest extends TestCase {

    private final static MultiplicativeResolver multiplicativeResolver = new MultiplicativeResolver();

    private void testResolve(List<String> input, String expectedOutput, boolean isValid) {
        boolean answer = false;
        try {
            multiplicativeResolver.resolve(input);
            answer = true;
            assertEquals("", expectedOutput, input.toString());
        } catch (Exception ignored) {}
        assertEquals("", isValid, answer);
    }

    public void testResolveNumber() {
        List<String> input = new ArrayList<>();
        input.add("1.0");
        testResolve(input, "[1.0]", true);
    }

    public void testResolveNoMultiplicative() {
        List<String> input = new ArrayList<>();
        input.add("1.0");
        input.add("+");
        input.add("1.0");
        testResolve(input, "[1.0, +, 1.0]", true);
    }

    public void testResolveBasicMul() {
        List<String> input = new ArrayList<>();
        input.add("2");
        input.add("*");
        input.add("3");
        testResolve(input, "[6.0]", true);
    }

    public void testResolveBasicDiv() {
        List<String> input = new ArrayList<>();
        input.add("3");
        input.add("/");
        input.add("6");
        testResolve(input, "[0.5]", true);
    }

    public void testResolveBasicMod() {
        List<String> input = new ArrayList<>();
        input.add("6");
        input.add("%");
        input.add("4");
        testResolve(input, "[2.0]", true);
    }

    public void testResolveMultipleNotSeries() {
        List<String> input = new ArrayList<>();
        input.add("6");
        input.add("%");
        input.add("3");
        input.add("+");
        input.add("2");
        input.add("*");
        input.add("10");
        testResolve(input, "[0.0, +, 20.0]", true);
    }

    public void testResolveMultipleSeriesSameOperator() {
        List<String> input = new ArrayList<>();
        input.add("3");
        input.add("*");
        input.add("2");
        input.add("*");
        input.add("10");
        testResolve(input, "[60.0]", true);
    }

    public void testResolveMultipleSeriesDifferentOperator() {
        List<String> input = new ArrayList<>();
        input.add("6");
        input.add("%");
        input.add("5");
        input.add("*");
        input.add("10");
        testResolve(input, "[10.0]", true);
    }

    public void testResolveInvalidMultipleOperators() {
        List<String> input = new ArrayList<>();
        input.add("6");
        input.add("%");
        input.add("*");
        input.add("10");
        testResolve(input, "", false);
    }

    public void testResolveInvalidStartingOperator() {
        List<String> input = new ArrayList<>();
        input.add("*");
        input.add("6");
        input.add("%");
        input.add("10");
        testResolve(input, "", false);
    }

    public void testResolveInvalidEndingOperator() {
        List<String> input = new ArrayList<>();
        input.add("6");
        input.add("%");
        input.add("10");
        input.add("*");
        testResolve(input, "", false);
    }
}
