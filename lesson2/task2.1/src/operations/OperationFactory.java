package operations;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class OperationFactory {
    private static final Map<String, BiFunction<Integer, Integer, Operation>> operations = new HashMap<>();

    static {
        operations.put("+", Addition::new);
        operations.put("-", Subtraction::new);
        operations.put("*", Multiplication::new);
        operations.put("/", Division::new);
    }

    public static Operation getOperation(int a, int b, String operator) throws Exception {
        try {
            BiFunction<Integer, Integer, Operation> operation = operations.get(operator);
            return operation.apply(a, b);
        } catch (Exception e) {
            throw new Exception("Неверный тип операции");
        }
    }
}
