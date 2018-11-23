package com.odde.tdd;

import java.time.LocalDate;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    int getDayRange(Period budgetPeriod) {
        LocalDate start = startDate.isBefore(budgetPeriod.startDate)? budgetPeriod.startDate : startDate;
        LocalDate end = endDate.isAfter(budgetPeriod.endDate)? budgetPeriod.endDate : endDate;
        return new Period(start,end).getDayCount();
    }

    private int getDayCount() {
        return startDate.isBefore(endDate)?startDate.until(endDate).getDays()+1:0;
    }
}
