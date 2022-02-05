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

        for (int i = 0; i < arr.length; i++) {
            if ((!Character.isAlphabetic(arr[i])) && (!Character.isDigit(arr[i])))
                return false;
        }

        return true;
    }
}
