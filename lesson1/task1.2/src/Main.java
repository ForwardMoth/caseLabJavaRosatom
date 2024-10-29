import java.util.Scanner;

public class Main {
    private static final String EXIT = "exit";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Простой калькулятор");
        System.out.println("Инструкция: ");
        System.out.println("Принимаются выражения из двух операндов и одной операции (+,-,*,/) через пробел");
        System.out.println("Пример операции: '2 + 2'");
        System.out.println("Введите выражение:");

        while(true) {
            String operation = scanner.nextLine();
            if(EXIT.equals(operation)) { // Условие выхода
                break;
            }
            try {
                String[] splitOperations = getOperators(operation); // Список [операнд, оператор, операнд]
                String operator = splitOperations[1];
                int a = Integer.parseInt(splitOperations[0]), b = Integer.parseInt(splitOperations[2]);
                calculator(a, b, operator);
            } catch (NumberFormatException e) { // Обработка ошибки преобразования String -> int
                System.out.println("Операнды должны быть числами");
            } catch (Exception e) { // Обработка выбрасываемых ошибок в ходе работы программы
                System.out.println(e.getMessage());
            }
        }
    }

    // Проверяет количество операторов, если не равно 3, то выбрасывает ошибку, иначе возвращает операторы
    private static String[] getOperators(String operation) throws Exception {
        String[] splitOperations = operation.split(" ");
        if (splitOperations.length != 3) {
            throw new Exception("Неверное выражение. Введите еще раз");
        }
        return splitOperations;
    }

    /*
     Основная функция калькулятора, через switch-case выбирается выполнение функции в зависимости от оператора
     Если оператор не найден, то выбрасывается ошибка
     */

    private static void calculator(int a, int b, String operator) throws Exception{
        switch (operator) {
            case "+" -> System.out.println("Результат: " + sum(a, b));
            case "-" -> System.out.println("Результат: " + subtract(a, b));
            case "*" -> System.out.println("Результат: " + multiply(a, b));
            case "/" -> System.out.println("Результат: " + divide(a, b));
            default -> throw new Exception("Неверный тип операции");
        }
    }

    /*
    * Основные математические функции
     */

    private static int sum(int a, int b) {
        return a + b;
    }

    private static int subtract(int a, int b) {
        return a - b;
    }

    private static int multiply(int a, int b) {
        return a * b;
    }

    private static double divide(int a, int b) throws Exception{
        if (b == 0) {
            throw new Exception("Ошибка - деление на 0");
        }
        return (double) a / b;
    }
}