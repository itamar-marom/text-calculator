import junit.framework.TestCase;

public class VariableTest extends TestCase {

    public void testIsVariableValid() {
        String variable = "variable";
        assertTrue("Valid variable contains only alphabetic and numeric characters", Variable.isVariable(variable));
    }

    public void testIsVariableEmptyString() {
        String variable = "";
        assertFalse("Empty string is not variable", Variable.isVariable(variable));
    }

    public void testIsVariableIntegerNumber() {
        String variable = "1";
        assertFalse("Integer number is not variable", Variable.isVariable(variable));
    }

    public void testIsVariableDoubleNumber() {
        String variable = "1.0";
        assertFalse("Double number is not variable", Variable.isVariable(variable));
    }

    public void testIsVariableInvalidCharacter() {
        String variable = "*";
        assertFalse("Variable cannot contain invalid characters", Variable.isVariable(variable));
    }
}
