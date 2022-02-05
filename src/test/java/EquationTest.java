import junit.framework.TestCase;

public class EquationTest extends TestCase {

    private void testEquation(String variable, String operator, String expression, boolean isValid, String expectedOperator){
        String equationString = variable+operator+expression;
        if (expectedOperator == null)
            expectedOperator = operator;
        boolean answer = false;
        try {
            Equation e = new Equation(equationString);
            assertEquals("", variable, e.getVariable());
            assertEquals("", expectedOperator, e.getOperator());
            assertEquals("", expression, e.getExpression());
            answer = true;
        } catch (Exception e) {}
        assertEquals("Equation built from: variable, operator, and mathematical expression",
                isValid, answer);
    }

    public void testEquationEqualPlacement() {
        testEquation("i","=","0", true, null);
    }

    public void testEquationPlusEqualPlacement() {
        testEquation("i", "+=", "0", true, "\\+=");
    }

    public void testEquationMinusEqualPlacement() {
        testEquation("i","-=","0", true, null);
    }

    public void testEquationMulEqualPlacement() {
        testEquation("i","*=","0", true, "\\*=");
    }

    public void testEquationModEqualPlacement() {
        testEquation("i","%=","0", true, null);
    }

    public void testEquationInvalidPlacement() {
        testEquation("i","!=","0", false, null);
    }

    public void testEquationTooManyOperators() {
        testEquation("i=i","-=","0", false, null);
    }

    public void testEquationDifficultExpression() {
        testEquation("i","=","i+4*(1-5)/7", true, null);
    }
}
