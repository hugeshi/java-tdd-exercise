package com.odde.tdd;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    long getOverlappingDayCount(Period another) {
        LocalDate startOfOverlapping = start.isAfter(another.start) ? start : another.start;
        LocalDate endOfOverlapping = end.isBefore(another.end) ? end : another.end;
        return new Period(startOfOverlapping, endOfOverlapping).getDayCount();
    }

    public long getDayCount() {
        return start.isAfter(end) ? 0 : start.until(end, DAYS) + 1;
    }
}
