import junit.framework.TestCase;

public class VariableTest extends TestCase {

    public void testIsVariableValid() {
        String variable = "variable";
        assertEquals("Valid variable contains only alphabetic and numeric characters",
                true, Variable.isVariable(variable) );
    }

    public void testIsVariableEmptyString() {
        String variable = "";
        assertEquals("Empty string is not variable", false, Variable.isVariable(variable) );
    }

    public void testIsVariableIntegerNumber() {
        String variable = "1";
        assertEquals("Integer number is not variable", false, Variable.isVariable(variable) );
    }

    public void testIsVariableDoubleNumber() {
        String variable = "1.0";
        assertEquals("Double number is not variable", false, Variable.isVariable(variable) );
    }

    public void testIsVariableInvalidCharacter() {
        String variable = "*";
        assertEquals("Variable cannot contain invalid characters",
                false, Variable.isVariable(variable) );
    }
}
