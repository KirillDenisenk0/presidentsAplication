package lesson13_2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Service service = new Service();


        int input = 0;
        while (input != 5) {
            System.out.println("Выберите действие: ");
            System.out.println("1. Вывести имена президентов и сроки их правления в порядке возрастания столбца \"начало срока\".");
            System.out.println("2. Вывести имена президентов и сроки их правления в обратном порядке.");
            System.out.println("3. Найти президента, который правил в определенную дату.");
            System.out.println("4. Добавить нового президента.");
            System.out.println("5. Выход");
            input = scanner.nextInt();
            if (input == 1) {
                List<President> sortedOnDate = null;
                for (President president : service.inPresidentList()) {
                    sortedOnDate = service.getPresidentList().stream().sorted(Comparator.comparing(President::getBeginningOfReign)).toList();
                }
                System.out.println(sortedOnDate);
            } else if (input == 2) {
                System.out.println("Имена президентов и сроки их правления в обратном порядке: ");
                List<President> sortedOnDateReverse = null;
                for (President i : service.inPresidentList()) {
                    sortedOnDateReverse = service.getPresidentList().stream().sorted((President o1, President o2) -> o2.getBeginningOfReign().compareTo(o1.getBeginningOfReign())).toList();
                }
                System.out.println(sortedOnDateReverse);
            } else if (input == 3) {
                System.out.println("Введите дату в формате dd.MM.yyyy");
                LocalDate temp = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                System.out.println("В это время правил президент: " + service.whoReins(temp));

            } else if (input == 4) {
                try {
                    System.out.println("Введите имя президента: ");
                    String nameOfPresident = scanner.next();
                    System.out.println("Введите дату начала правления в формате dd.mm.yyyy: ");
                    LocalDate beginningOfRein = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    System.out.println("Введите дату конца правления dd.mm.yyyy: ");
                    LocalDate endOfRein = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    System.out.println("Введите название партии: ");
                    String consignment = scanner.next();
                    service.writeNewPresident(nameOfPresident, beginningOfRein, endOfRein, consignment);
                    System.out.println("Президент успешно добавлен в список!");
                } catch (DateTimeParseException e) {
                    throw new DateInvalidFormatEx("Неверный формат даты!");
                }
            } else if (input == 5) {
                break;
            }
        }
    }
}
