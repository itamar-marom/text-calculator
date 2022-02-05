import junit.framework.TestCase;

public class OperatorTest extends TestCase {

    public void testIsOperatorCharacterPlus() {
        char operator = '+';
        assertTrue("Operator '+' should be valid", Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterMinus() {
        char operator = '-';
        assertTrue("Operator '-' should be valid", Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterMul() {
        char operator = '*';
        assertTrue("Operator '*' should be valid", Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterDivide() {
        char operator = '/';
        assertTrue("Operator '/' should be valid", Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterMod() {
        char operator = '%';
        assertTrue("Operator '%' should be valid", Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterInvalidOperator() {
        char operator = '$';
        assertFalse("Valid operators are: +, -, *, /, %", Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterNumber() {
        char operator = '0';
        assertFalse("Valid operators are: +, -, *, /, %", Operator.isOperator(operator));
    }
}
