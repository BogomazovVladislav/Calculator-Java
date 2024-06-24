import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);

        try (in) {
            System.out.print("Введите выражение: ");
            String expression = in.nextLine();
            String result = calc(expression);
            System.out.println("Результат = " + result);
        } catch (Exception e) {
            System.out.println("Ошибка при вычислениях - " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {

        if (input.matches("^\\s.*")) {
            throw new Exception("Допущен пробел в начале строки");
        }

        if (input.matches(".*\\s$")) {
            throw new Exception("Допущен пробел в конце строки");
        }

        if (!input.matches("\\d+(\\.\\d+)?\\s+[+\\-*/]\\s+\\d+(\\.\\d+)?")) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - " +
                    "два операнда и один оператор (+, -, /, *)");
        }

        String[] parts = input.split("\\s");

        int operand1,operand2;
        String operator = parts[1];

        int sum = 0;

        try {
            operand1 = Integer.parseInt(parts[0]);
            operand2 = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new Exception("Калькулятор умеет работать только с целыми числами");
        }

        if ((operand1 > 10 || operand1 < 1) || (operand2 > 10 || operand2 < 1)) {
            throw new Exception("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более");
        }

        sum = switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> sum;
        };

        return "" + sum;
    }
}