import junit.framework.TestCase;

public class OperatorTest extends TestCase {

    public void testIsOperatorCharacterPlus() {
        char operator = '+';
        assertEquals("Operator '+' should be valid", true, Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterMinus() {
        char operator = '-';
        assertEquals("Operator '-' should be valid", true, Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterMul() {
        char operator = '*';
        assertEquals("Operator '*' should be valid", true, Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterDivide() {
        char operator = '/';
        assertEquals("Operator '/' should be valid", true, Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterMod() {
        char operator = '%';
        assertEquals("Operator '%' should be valid", true, Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterInvalidOperator() {
        char operator = '$';
        assertEquals("Valid operators are: +, -, *, /, %", false, Operator.isOperator(operator));
    }

    public void testIsOperatorCharacterNumber() {
        char operator = '0';
        assertEquals("Valid operators are: +, -, *, /, %", false, Operator.isOperator(operator));
    }
}
