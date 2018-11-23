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

    public YearMonth getMonth() {
        return month;
    }

    public long getAmount() {
        return amount;
    }

    public Period getPeroid() {
        return new Period(getStartDate(), getEndDate());
    }

    private LocalDate getEndDate() {
        return this.month.atEndOfMonth();
    }

    private LocalDate getStartDate() {
        return this.month.atDay(1);
    }

    long getAmount(Period period) {
        return getAmount() / getMonth().lengthOfMonth() * period.getDayRange(getPeroid());
    }
}
