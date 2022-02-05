import java.util.ArrayList;
import java.util.List;

public class AdditiveResolver implements Resolver {
    @Override
    public void resolve(List<String> expression) {
        Double answer = Double.valueOf(expression.get(0));
        while (expression.size() >= 3) {
            Double component1 = Double.valueOf(expression.get(0));
            String operator = expression.get(1);
            Double component2 = Double.valueOf(expression.get(2));

            switch (operator) {
                case "+":
                    answer = component1 + component2;
                    break;
                case "-":
                    answer = component1 - component2;
                    break;
            }

            expression.remove(2);
            expression.remove(1);
            expression.set(0, answer.toString());
        }

        expression = new ArrayList<String>();
        expression.add(answer.toString());
    }
}
