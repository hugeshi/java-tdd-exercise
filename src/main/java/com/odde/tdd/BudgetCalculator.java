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

    /**
     * Calculate budget between given dates
     * @param start start date, inclusive
     * @param end end date, inclusive
     * @return sum of budgets between start and end
     */
    public long calcBudget(LocalDate start, LocalDate end) {
        long sum = amountTillMonthEnd(start) +
                wholeYearMonthsBetween(start, end).stream().mapToLong(ym -> visitor.getAmount(ym)).sum() +
                amountFromMonthStart(end);

        if (yearMonth(start).equals(yearMonth(end))) {
            sum -= visitor.getAmount(yearMonth(start));
        }

        return sum;
    }

    private long amountTillMonthEnd(LocalDate d) {
        int daysOfMonth = daysOfMonth(d);
        int days = daysOfMonth - d.getDayOfMonth() + 1;
        return (long) amountSum(days, d);
    }

    private long amountFromMonthStart(LocalDate d) {
        int days = d.getDayOfMonth();
        return (long) amountSum(days, d);
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

    private int daysOfMonth(LocalDate d) {
        return d.getMonth().length(d.isLeapYear());
    }

    /**
     * amountSum calculates sum of amount of given #days, according to budget setting from the month of given date
     * @param days number of days to sum amount
     * @param d date from a month
     * @return sum of amount of the days
     */
    private double amountSum(int days, LocalDate d) {
        return visitor.getMonthAmount(d) * days / daysOfMonth(d);
    }
}
