public class Variable {
    public static boolean isVariable(String str) {
        str = str.trim();

        if (str.isEmpty())
            return false;

        try {
            Double.parseDouble(str);
            return false;
        } catch(NumberFormatException e){}

        char[] arr = str.toCharArray();

        for (char c : arr) {
            if ((!Character.isAlphabetic(c)) && (!Character.isDigit(c)))
                return false;
        }

        return true;
    }
}
