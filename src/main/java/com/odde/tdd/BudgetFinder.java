package com.odde.tdd;

import java.time.YearMonth;
import java.time.LocalDate;
import java.util.List;

public class BudgetFinder {
    BudgetRepo repo;

    public BudgetFinder(BudgetRepo repo) {
        this.repo = repo;
    }

    public float findBudget(LocalDate start, LocalDate end) {
        float total_budget = 0;
        YearMonth start_month = YearMonth.of(start.getYear(), start.getMonth());
        YearMonth end_month = YearMonth.of(end.getYear(), end.getMonth());
        List<Budget> budgets = repo.findAll();
        for (Budget budget: budgets) {
            if (budget.getMonth().isAfter(start_month) && budget.getMonth().isBefore(end_month)) {
                total_budget += budget.getAmount();
            }
            else if (budget.getMonth().equals(start_month) && budget.getMonth().equals(end_month)) {
                total_budget += ((float) budget.getAmount()) * (end.getDayOfMonth() - start.getDayOfMonth() + 1) / budget.getMonth().lengthOfMonth();
            }
            else if (budget.getMonth().equals(start_month)) {
                total_budget += ((float) budget.getAmount()) * (budget.getMonth().lengthOfMonth() - start.getDayOfMonth() + 1) / budget.getMonth().lengthOfMonth();
            }
            else if (budget.getMonth().equals(end_month)) {
                total_budget += ((float) budget.getAmount()) * end.getDayOfMonth() / budget.getMonth().lengthOfMonth();
            }
        }
        return total_budget;
    }
}
