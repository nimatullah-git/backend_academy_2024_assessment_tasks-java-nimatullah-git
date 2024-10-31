package org.example.module3;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class FridayThe13thFinder {
    public static List<LocalDate> findFridays13InYear(int year) {
        List<LocalDate> fridays13 = new ArrayList<>();

        for (Month month : Month.values()) {
            LocalDate date = LocalDate.of(year, month, 13);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13.add(date);
            }
        }
        return fridays13;
    }

    public static LocalDate findNextFriday13(LocalDate startDate) {
        LocalDate next13th = startDate.withDayOfMonth(13).plusMonths(startDate.getDayOfMonth() > 13 ? 1 : 0);

        while (next13th.getDayOfWeek() != DayOfWeek.FRIDAY) {
            next13th = next13th.plusMonths(1).withDayOfMonth(13);
        }
        return next13th;
    }
}
