import operations.Operation;
import operations.OperationFactory;

import java.util.Scanner;

public class Calculator {

    private static final String EXIT = "exit";

    private final Scanner scanner;

    Calculator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printInstruction() {
        System.out.println("Простой калькулятор");
        System.out.println("Инструкция: ");
        System.out.println("Принимаются выражения из двух операндов и одной операции (+,-,*,/) через пробел");
        System.out.println("Пример операции: '2 + 2'");
        System.out.println("Введите выражение:");
    }

    public void start() {
        while (true) {
            String sequence = scanner.nextLine();
            if (EXIT.equals(sequence)) { // Условие выхода
                break;
            }
            try {
                String[] splitOperations = getOperators(sequence); // Список [операнд, оператор, операнд]
                int a = Integer.parseInt(splitOperations[0]), b = Integer.parseInt(splitOperations[2]);
                String method = splitOperations[1];
                Operation operation = OperationFactory.getOperation(a, b, method);
                System.out.println("Результат: " + operation.calculate());
            } catch (NumberFormatException e) { // Обработка ошибки преобразования String -> int
                System.out.println("Операнды должны быть числами");
            } catch (Exception e) { // Обработка выбрасываемых ошибок в ходе работы программы
                System.out.println(e.getMessage());
            }
        }
    }

    private String[] getOperators(String operation) throws Exception {
        String[] splitOperations = operation.split(" ");
        if (splitOperations.length != 3) {
            throw new Exception("Неверное выражение. Введите еще раз");
        }
        return splitOperations;
    }
}
