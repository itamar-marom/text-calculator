import java.util.List;

public class MultiplicativeResolver implements Resolver {

    @Override
    public void resolve(List<String> expression) {
        int index = 1;
        while (index < expression.size() - 1) {
            String component = expression.get(index);
            if (component.equals("*") || component.equals("/") || component.equals("%")) {
                resolveMultiplicativeSequence(expression, index - 1);
            }
            index += 2;
        }
    }

    private void resolveMultiplicativeSequence(List<String> expression, int index) {
        String operator = expression.get(index + 1);
        Double temp = null;
        while (operator.equals("*") || operator.equals("/") || operator.equals("%")) {
            Double component1 = Double.valueOf(expression.get(index));
            Double component2 = Double.valueOf(expression.get(index + 2));
            switch (operator) {
                case "*":
                    temp = component1 * component2;
                    break;
                case "/":
                    temp = component1 / component2;
                    break;
                case "%":
                    temp = component1 % component2;
                    break;
            }
            expression.remove(index + 2);
            expression.remove(index + 1);
            expression.remove(index);
            expression.add(index, temp.toString());
            if (index + 1 >= expression.size())
                break;
            operator = expression.get(index + 1);
        }
    }
}
