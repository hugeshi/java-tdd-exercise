package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BudgetCalculator {

    private BudgetVisitor visitor;

    public BudgetCalculator(BudgetVisitor visitor) {
        this.visitor = visitor;
    }

    public long calcBudget(LocalDate start, LocalDate end) {
        long sum = amountTillMonthEnd(start) +
                wholeYearMonthsBetween(start, end).stream().mapToLong(ym -> visitor.getAmount(ym)).sum() +
                amountFromMonthStart(end);
        if (start.getYear() == end.getYear() && start.getMonthValue() == end.getMonthValue()) {
            sum -= visitor.getAmount(yearMonth(start));
        }
        return sum;
    }

    private long amountTillMonthEnd(LocalDate d) {
        int daysOfMonth = d.getMonth().length(d.isLeapYear());
        int days = daysOfMonth - d.getDayOfMonth() + 1;
        return (long) ((double) visitor.getMonthAmount(d) / daysOfMonth * days);
    }

    private long amountFromMonthStart(LocalDate d) {
        int daysOfMonth = d.getMonth().length(d.isLeapYear());
        int days = d.getDayOfMonth();
        return (long) ((double) visitor.getMonthAmount(d) / daysOfMonth * days);
    }

    private List<YearMonth> wholeYearMonthsBetween(LocalDate start, LocalDate end) {
        long monthsCnt = ChronoUnit.MONTHS.between(start, end);
        List<YearMonth> yms = new ArrayList<>((int) monthsCnt);
        for (int m = 1; m < monthsCnt; m++) {
            LocalDate d = start.plusMonths(m);
            yms.add(yearMonth(d));
        }
        return yms;
    }

    private YearMonth yearMonth(LocalDate d) {
        return YearMonth.of(d.getYear(), d.getMonthValue());
    }
}
