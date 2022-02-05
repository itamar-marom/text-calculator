import junit.framework.TestCase;

import java.util.HashMap;

public class FixedVariableParserTest extends TestCase {

    private final static FixedVariableParser fixedVariableParser = new FixedVariableParser();

    private void testParse(String expression, String output, HashMap<String, Double> variables, boolean isValid){
        boolean answer = false;
        try {
            String fixedExpression = fixedVariableParser.parse(variables, expression);
            answer = true;
            assertEquals("", output, fixedExpression);
        } catch (Exception ignored) {}
        assertEquals("", isValid, answer);
    }

    public void testParseNumber() {
        testParse("0", "0", null, true);
    }

    public void testParseVariable() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        testParse("a", "0.0", variables, true);
        assertEquals("", 0.0, variables.get("a"));
    }

    public void testParseVariableNotExists() {
        HashMap<String, Double> variables = new HashMap<>();
        testParse("a", "0.0", variables, false);
    }

    public void testParseBasicNegativePrefix() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        testParse("--a", "-1.0", variables, true);
        assertEquals("", -1.0, variables.get("a"));
    }

    public void testParseBasicPositivePrefix() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        testParse("++a", "1.0", variables, true);
        assertEquals("", 1.0, variables.get("a"));
    }

    public void testParseBasicNegativePostfix() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        testParse("a--", "0.0", variables, true);
        assertEquals("", -1.0, variables.get("a"));
    }

    public void testParseBasicPositivePostfix() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        testParse("a++", "0.0", variables, true);
        assertEquals("", 1.0, variables.get("a"));
    }

    public void testParseMinusNegativeNumber() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        variables.put("b", 1.0);
        testParse("a--b", "0.0--1.0", variables, true);
        assertEquals("", 0.0, variables.get("a"));
        assertEquals("", 1.0, variables.get("b"));
    }

    public void testParseNegativePostfixMinusNumber() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        variables.put("b", 1.0);
        testParse("a---b", "0.0-1.0", variables, true);
        assertEquals("", -1.0, variables.get("a"));
        assertEquals("", 1.0, variables.get("b"));
    }

    public void testParseNegativePostfixMinusNegativeNumber() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        variables.put("b", 1.0);
        testParse("a----b", "0.0--1.0", variables, true);
        assertEquals("", -1.0, variables.get("a"));
        assertEquals("", 1.0, variables.get("b"));
    }

    public void testParseNegativePostfixMinusNegativePrefix() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        variables.put("b", 1.0);
        testParse("a-----b", "0.0-0.0", variables, true);
        assertEquals("", -1.0, variables.get("a"));
        assertEquals("", 0.0, variables.get("b"));
    }

    public void testParseTooManyFixes() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        variables.put("b", 1.0);
        testParse("a------b", "0.0--0.0", variables, true);
        assertEquals("", -1.0, variables.get("a"));
        assertEquals("", 0.0, variables.get("b"));
    }

    public void testParseBasicPrefixWithBrackets() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        testParse("(--a)", "(-1.0)", variables, true);
        assertEquals("", -1.0, variables.get("a"));
    }

    public void testParseBasicPostfixWithBrackets() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("a", 0.0);
        testParse("(a--)", "(0.0)", variables, true);
        assertEquals("", -1.0, variables.get("a"));
    }
}
