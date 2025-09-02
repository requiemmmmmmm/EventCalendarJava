import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Event {
    String name;
    LocalDate date;

    Event(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }
}

public class EventCalendar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Event> events = new ArrayList<>();

        System.out.println("Сколько событий вы хотите добавить?");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.printf("Введите название события #%d:%n", i + 1);
            String name = scanner.nextLine();

            System.out.printf("Введите дату события #%d (год-месяц-день, например 2025-09-02):%n", i + 1);
            LocalDate date = LocalDate.parse(scanner.nextLine());

            events.add(new Event(name, date));
        }

        System.out.println("\n=== Сводка событий ===\n");

        for (Event e : events) {
            System.out.printf(
                    "Хотите посчитать прошедшее с сегодняшнего дня или между двумя датами для события '%s'? (1 - с сегодня, 2 - между двумя датами): ",
                    e.name
            );
            int choice = Integer.parseInt(scanner.nextLine());

            LocalDate startDate;
            LocalDate endDate;

            if (choice == 1) {
                startDate = e.date;
                endDate = LocalDate.now();
            } else {
                System.out.println("Введите начальную дату (год-месяц-день):");
                startDate = LocalDate.parse(scanner.nextLine());
                System.out.println("Введите конечную дату (год-месяц-день):");
                endDate = LocalDate.parse(scanner.nextLine());
            }

            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
            Period period = Period.between(startDate, endDate);

            System.out.printf(
                    "Событие '%s' (%s → %s)\nПрошло: %d %s, %d %s и %d %s (%d дней всего).\n\n",
                    e.name,
                    startDate,
                    endDate,
                    period.getYears(), period.getYears() == 1 ? "год" : "года",
                    period.getMonths(), period.getMonths() == 1 ? "месяц" : "месяца",
                    period.getDays(), period.getDays() == 1 ? "день" : "дня",
                    totalDays
            );
        }
    }
}
