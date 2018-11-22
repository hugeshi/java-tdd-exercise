package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;

public class Budget {
    private final YearMonth month;
    private final long amount;

    public Budget(YearMonth month, long amount) {
        this.month = month;
        this.amount = amount;
    }

    public LocalDate getStart() {
        return month.atDay(1);
    }

    public LocalDate getEnd() {
        return month.atEndOfMonth();
    }

    public Period getPeriod() {
        return new Period(getStart(), getEnd());
    }

    public long getDayCount() {
        return getPeriod().getDayCount();
    }

    long getOverlappingAmount(Period period) {
        return amount / getDayCount() * period.getOverlappingDayCount(getPeriod());
    }
}
