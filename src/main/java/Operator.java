public class Operator {
    public static boolean isOperator(char c) {
        return ((c == '+') || (c == '-') || (c == '*') || (c == '/') || (c == '%'));
    }
    public static boolean isOperator(String s) {
        return ((s.equals("+")) || (s.equals("-")) || (s.equals("*")) || (s.equals("/")) || (s.equals("%")));
    }
}
