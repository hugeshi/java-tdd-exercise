package com.odde.tdd;

import java.time.LocalDate;
import java.util.*;
import java.time.YearMonth;

public class Calculator {
    private BudgetRepo budgetRepo;
    private Map<YearMonth, Long> bMap;

    public int add(int a, int b) {
        return a + b;
    }

    public Calculator() {
    }

    public Calculator(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;

    }

    public double calcBudget(LocalDate start, LocalDate end) {
        List<Budget> allBudgets = budgetRepo.findAll();
        this.bMap = new HashMap<>();
        for (Budget b : allBudgets) {
            bMap.put(b.getMonth(), b.getAmount());
        }

        YearMonth startYm = YearMonth.of(start.getYear(), start.getMonth());
        YearMonth endYm = YearMonth.of(end.getYear(), end.getMonth());

        // Accumulate
        LocalDate curr = LocalDate.of(start.getYear(), start.getMonth(), 1);
        double sumBudget = 0;
        for (; curr.compareTo(end) <= 0; curr = curr.plusMonths(1)) {
            sumBudget += getMonthAmount(curr);
        }

        // 斩头去尾
        double headAmount = (double) getMonthAmount(start) * (start.getDayOfMonth() - 1) / startYm.lengthOfMonth(); // fixme: days
        double tailAmount = (double) getMonthAmount(end) * (endYm.lengthOfMonth() - end.getDayOfMonth()) / endYm.lengthOfMonth(); // fixme: days
        sumBudget -= headAmount;
        sumBudget -= tailAmount;

        return sumBudget;
    }

    private Long getMonthAmount(LocalDate dt) {
        YearMonth ym = YearMonth.of(dt.getYear(), dt.getMonthValue());
        return bMap.getOrDefault(ym, 0L);
    }
}
