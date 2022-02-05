import java.util.HashMap;

public interface Parser {
    String parse(HashMap<String, Double> variables, String str) throws Exception;
}
