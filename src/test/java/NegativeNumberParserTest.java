import junit.framework.TestCase;

public class NegativeNumberParserTest extends TestCase {

    private final static NegativeNumberParser negativeNumberParser = new NegativeNumberParser();

    private void testParse(String expression, String output, boolean isValid){
        boolean answer = false;
        try {
            String resultExpression = negativeNumberParser.parse(null, expression);
            answer = true;
            assertEquals("", output, resultExpression);
        } catch (Exception ignored) {}
        assertEquals("", isValid, answer);
    }

    public void testParsePositiveNumber() {
        testParse("1", "1", true);
    }

    public void testParseNegativeNumber() {
        testParse("-1", "(0-1)", true);
    }

    public void testParsePlusNegativeNumber() {
        testParse("1+-1", "1+(0-1)", true);
    }

    public void testParseMinusNegativeNumber() {
        testParse("1--1", "1-(0-1)", true);
    }

    public void testParseNegativeNumberMinusNegativeNumber() {
        testParse("-1--1", "(0-1)-(0-1)", true);
    }

    public void testParseNegativeBracketsAtStart() {
        testParse("-(1-2)", "(0-1)*(1-2)", true);
    }

    public void testParseMulNegativeNumber() {
        testParse("1*-1", "1*(0-1)", true);
    }

    public void testParseMinusWithNoNumber() {
        testParse("--1", "", false);
    }
}
