package smarttraffic.violation_service.util;

import java.time.LocalDate;
import java.time.Month;

public class DateCounter {
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        System.out.print("currentMonth " + currentMonth);
    }
}
