import java.util.List;
import java.util.Objects;

public class AdditiveResolver implements Resolver {
    @Override
    public void resolve(List<String> expression) throws Exception {
        Double answer = null;
        if (expression.get(0).equals("+"))
            expression.remove(0);

        while (expression.size() >= 3) {
            try {
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
                expression.set(0, Objects.requireNonNull(answer).toString());
            } catch(NumberFormatException e){
                throw new Exception("Invalid order of components and operators.");
            }
        }

        if (expression.size() > 1)
            throw new Exception("Invalid order of components and operators.");
    }
}
