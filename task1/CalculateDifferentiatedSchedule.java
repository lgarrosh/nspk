import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class CalculateDifferentiatedSchedule {

    public static boolean ifSunday(LocalDate date) {
        // Получаем день недели
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // Выводим результат
        if (dayOfWeek.name() == "SUNDAY")
            return (true);
        return (false);
    }

    public static void calculate(float creditAmount, int deadline, float interestRate, int issueDate) {
        LocalDate currentDate = LocalDate.now();
        float basePayment = creditAmount / deadline;
        float interestPayment;
        float debt = creditAmount;
        System.out.println("Дата:" + "\t\t" + "Платеж по кредиту:");
        for (int i = 0; i < deadline; i++) {
            currentDate = currentDate = currentDate.plusMonths(1);
            currentDate = currentDate.withDayOfMonth(Math.min(issueDate, currentDate.lengthOfMonth()));
            if (ifSunday(currentDate))
            currentDate = currentDate = currentDate.plusDays(1);

            interestPayment = (debt * interestRate/100 * currentDate.lengthOfMonth())/currentDate.lengthOfYear();
            System.out.println(currentDate + "\t" + (basePayment + interestPayment));
            debt = debt - basePayment;
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int creditAmount = 0;
        int deadline = 0;
        int interestRate = 0;
        int issueDate = 0;
        try {
            String[] words;
            while (true) {
                System.out.println("Введите данные без пробела в следующем порядке: сумма кредита, срок, процентная ставка, дата выдачи");
                String line = reader.readLine();
                if (line.isEmpty()) {
                    System.out.println("Вы не ввели данные!");
                    continue;
                }
                words = line.split(" ");
                if (words.length < 4) {
                    System.out.println("Получено недостаточно данных!");
                    continue;
                }
                try {
                    creditAmount = Integer.parseInt(words[0]);
                    deadline = Integer.parseInt(words[1]);
                    interestRate = Integer.parseInt(words[2]);
                    issueDate = Integer.parseInt(words[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Некорректные данные");
                    continue;
                }
                if (issueDate > 31 || issueDate < 1) {
                    System.out.println("Некорректная дата выдачи: " + issueDate);
                    continue;
                }
                if (creditAmount < 0 || deadline < 0 || interestRate < 0) {
                    System.out.println("Параметры не могут быть отрицательными");
                    continue;
                }
                break;
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода/вывода: " + e.getMessage());
        }
        calculate(creditAmount, deadline, interestRate, issueDate);
    }
}