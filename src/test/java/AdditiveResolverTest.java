import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class AdditiveResolverTest extends TestCase {

    private final static AdditiveResolver additiveResolver = new AdditiveResolver();

    private void testResolve(List<String> input, String expectedOutput, boolean isValid) {
        boolean answer = false;
        try {
            additiveResolver.resolve(input);
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

    public void testResolveBasicAdd() {
        List<String> input = new ArrayList<>();
        input.add("1.0");
        input.add("+");
        input.add("2.0");
        testResolve(input, "[3.0]", true);
    }

    public void testResolveBasicMinus() {
        List<String> input = new ArrayList<>();
        input.add("1.0");
        input.add("-");
        input.add("2.0");
        testResolve(input, "[-1.0]", true);
    }

    public void testResolveMultipleOperators() {
        List<String> input = new ArrayList<>();
        input.add("1");
        input.add("+");
        input.add("10.5");
        input.add("-");
        input.add("4");
        testResolve(input, "[7.5]", true);
    }

    public void testResolvePositiveNumber() {
        List<String> input = new ArrayList<>();
        input.add("+");
        input.add("1");
        testResolve(input, "[1]", true);
    }

    public void testResolveInvalidOneComponent() {
        List<String> input = new ArrayList<>();
        input.add("1");
        input.add("+");
        testResolve(input, "", false);
    }

    public void testResolveInvalidStartingOperator() {
        List<String> input = new ArrayList<>();
        input.add("-");
        input.add("1");
        input.add("+");
        input.add("2");
        testResolve(input, "", false);
    }

    public void testResolveInvalidEndingOperator() {
        List<String> input = new ArrayList<>();
        input.add("1");
        input.add("+");
        input.add("2");
        input.add("+");
        testResolve(input, "", false);
    }

    public void testResolveInvalidSeriesOperators() {
        List<String> input = new ArrayList<>();
        input.add("1");
        input.add("+");
        input.add("+");
        input.add("2");
        testResolve(input, "", false);
    }
}
